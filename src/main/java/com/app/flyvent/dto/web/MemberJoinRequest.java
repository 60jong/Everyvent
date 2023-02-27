package com.app.flyvent.dto.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter // 스프링이 직렬화/역직렬화 시에 객체의 getter와 기본 생성자를 필요로한다.
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberJoinRequest {
    @NotBlank(message = "[request] 이름은 반드시 입력되어야 합니다.")
    private String name;
    @NotBlank(message = "[request] 이메일은 반드시 입력되어야 합니다.")
    private String email;
    @NotBlank(message = "[request] 비밀번호은 반드시 입력되어야 합니다.")
    private String password;
    private String phoneNumber;
    private Boolean mailNotificationEnable;
}
