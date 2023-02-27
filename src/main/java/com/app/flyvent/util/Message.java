package com.app.flyvent.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Message {
    private String message;                // 사용자에게 전달할 메시지
    private String redirectUri;            // 리다이렉트 URI
}