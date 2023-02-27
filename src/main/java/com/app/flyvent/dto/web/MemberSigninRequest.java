package com.app.flyvent.dto.web;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSigninRequest {
    private String email;
    private String password;
}
