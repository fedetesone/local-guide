package com.localguide.mappers;
import com.localguide.controller.request.ChatRequestApi;
import com.localguide.controller.response.ChatResponseApi;
import com.localguide.service.dto.ChatRequestDto;
import com.localguide.service.dto.ChatResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocalGuideMapper {

    // Requests
    ChatRequestDto toChatRequestDto(ChatRequestApi request);

    // Responses
    ChatResponseApi toChatResponseApi(ChatResponseDto dto);
}

