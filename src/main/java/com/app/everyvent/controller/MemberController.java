package com.app.everyvent.controller;

import com.app.everyvent.domain.airline.Airline;
import com.app.everyvent.dto.web.*;
import com.app.everyvent.service.AirlineService;
import com.app.everyvent.domain.Member;
import com.app.everyvent.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AirlineService airlineService;

    /**
     * 멤버 회원가입
     * @param newMemberParam
     * @return
     */
    @PostMapping("")
    public NewMemberRes postMember(@RequestBody NewMemberParam newMemberParam) {
        Member newMember = newMemberParam.toMember();
        memberService.join(newMember);

        return new NewMemberRes(newMember);

    }

    /**
     * 멤버의 구독 항공사 이름 조회
     * @param memberId
     * @return
     */
    @GetMapping("/{memberId}/subscriptions")
    public SubscribedAirlines getMemberSubscriptions(@PathVariable("memberId") Long memberId) {
        Member member = memberService.findOne(memberId);
        List<String> airlineEnglishNames = memberService.getAirlineEnglishNames(member);

        return new SubscribedAirlines(memberId, airlineEnglishNames);
    }

    /**
     * 멤버 구독 항공사 추가
     * @param memberId
     * @param postMemberSubscriptionsReq
     */
    @PostMapping("/{memberId}/subscriptions")
    public void postMemberSubscriptions(@PathVariable("memberId") Long memberId,
                                        @RequestBody AirlineIds postMemberSubscriptionsReq) {
        List<Airline> airlines = airlineService.findAllById(
                postMemberSubscriptionsReq.getAirlineIds());
        Member member = memberService.findOne(memberId);
        memberService.subscribe(member, airlines);
    }

    /**
     * 멤버 여행지 추가
     * @param memberId
     * @param postMemberDestinationsReq
     */

}
