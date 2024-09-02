package com.localguide.controller.impl;

import com.localguide.controller.LocalGuideController;
import com.localguide.controller.request.ChatRequestApi;
import com.localguide.controller.response.ChatResponseApi;
import com.localguide.mappers.LocalGuideMapper;
import com.localguide.service.LocalGuideService;
import com.localguide.service.dto.ChatRequestDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guide")
public class LocalGuideControllerImpl implements LocalGuideController {

    private final LocalGuideService service;
    private final LocalGuideMapper mapper;

    public LocalGuideControllerImpl(LocalGuideService service,
                                    LocalGuideMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    @ResponseBody
    public String sayHello() {
        return service.sayHello();
    }

    @Override
    public ChatResponseApi easyRagChat(ChatRequestApi request) {
        final ChatRequestDto chatRequestDto = mapper.toChatRequestDto(request);

        return mapper.toChatResponseApi(service.easyRagChat(chatRequestDto));
    }

    @Override
    public ChatResponseApi redisRagChat(ChatRequestApi request) {
        final ChatRequestDto chatRequestDto = mapper.toChatRequestDto(request);

        return mapper.toChatResponseApi(service.redisRagChat(chatRequestDto));
    }
}