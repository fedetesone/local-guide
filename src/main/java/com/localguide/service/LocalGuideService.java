package com.localguide.service;

import com.localguide.service.dto.ChatRequestDto;
import com.localguide.service.dto.ChatResponseDto;

public interface LocalGuideService {
    String sayHello();

    ChatResponseDto easyRagChat(ChatRequestDto request);

    ChatResponseDto redisRagChat(ChatRequestDto request);
}
