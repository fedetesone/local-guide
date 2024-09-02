package com.localguide.service.impl;

import com.localguide.easyrag.Assistant;
import com.localguide.easyrag.AssistantBuilder;
import com.localguide.service.LocalGuideService;
import com.localguide.service.dto.ChatRequestDto;
import com.localguide.service.dto.ChatResponseDto;
import org.springframework.stereotype.Service;

@Service
public class LocalGuideServiceImpl implements LocalGuideService {

    private final Assistant assistant;

    public LocalGuideServiceImpl(AssistantBuilder assistantBuilder) {
        assistant = assistantBuilder.buildAssistant();
    }

    @Override
    public String sayHello() {
        return "I'm alive!";
    }

    @Override
    public ChatResponseDto easyRagChat(ChatRequestDto request) {
        String answer = assistant.chat(request.question());

        return new ChatResponseDto(answer);
    }

    @Override
    public ChatResponseDto redisRagChat(ChatRequestDto request) {
        return null;
    }
}
