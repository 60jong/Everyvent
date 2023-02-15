package com.app.flyvent.dto.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // 스프링이 직렬화/역직렬화 시에 객체의 getter와 기본 생성자를 필요로한다.
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewMemberParam {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private Boolean mailNotificationEnable;
}
