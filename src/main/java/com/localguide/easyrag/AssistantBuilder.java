package com.localguide.easyrag;

import com.localguide.service.impl.LocalGuideServiceImpl;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.bgesmallenv15q.BgeSmallEnV15QuantizedEmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class AssistantBuilder {

    // private static final String API_KEY = System.getenv("OPENAI_API_KEY");
    private static final String API_KEY = "demo";

    public Assistant buildAssistant() {
        String url = Objects.requireNonNull(LocalGuideServiceImpl.class.getClassLoader().getResource("easyrag_documents")).getPath();
        List<Document> documents = FileSystemDocumentLoader.loadDocuments(url);

        // Creating in-memory embedding store.
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        EmbeddingStoreIngestor.ingest(documents, embeddingStore);

        EmbeddingModel embeddingModel = new BgeSmallEnV15QuantizedEmbeddingModel();

        ContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .maxResults(2) // on each interaction we will retrieve the 2 most relevant segments
                .minScore(0.5) // Minimum similarity allowed for the context sent to the LLM.
                .build();

        return AiServices.builder(Assistant.class)
                .chatLanguageModel(OpenAiChatModel.withApiKey(API_KEY))
                .chatMemory(MessageWindowChatMemory.withMaxMessages(1))
                .contentRetriever(contentRetriever)
                .build();
    }

}
