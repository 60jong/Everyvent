package com.app.flyvent.dto.web;

import com.app.flyvent.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewMemberRes {
    private String name;
    private String email;
    private String phoneNumber;

    public NewMemberRes(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.phoneNumber = member.getPhoneNumber();
    }
}
