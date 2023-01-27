package com.app.everyvent.dto.web;

import com.app.everyvent.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter // 스프링이 직렬화/역직렬화 시에 객체의 getter와 기본 생성자를 필요로한다.
@NoArgsConstructor
@AllArgsConstructor
public class NewMemberParam {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private Boolean mailNotificationEnable;

    public Member toMember() {
        return new Member(name, email, password, phoneNumber);
    }
}
