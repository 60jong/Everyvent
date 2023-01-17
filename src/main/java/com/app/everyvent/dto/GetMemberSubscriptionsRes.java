package com.app.everyvent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetMemberSubscriptionsRes {
    private Long memberId;
    private List<String> airlineEnglishNames;
}
