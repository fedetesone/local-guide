package com.localguide.controller;

import com.localguide.controller.request.ChatRequestApi;
import com.localguide.controller.response.ChatResponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface LocalGuideController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/hello")
    String sayHello();

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/easyrag/chat")
    ChatResponseApi easyRagChat(@RequestBody ChatRequestApi request);

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/redis/chat")
    ChatResponseApi redisRagChat(@RequestBody ChatRequestApi request);
}
