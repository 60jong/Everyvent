package com.app.everyvent.dto;

import com.app.everyvent.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostMemberRes {
    private String name;
    private String email;
    private String phoneNumber;

    public PostMemberRes(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.phoneNumber = member.getPhoneNumber();
    }
}
