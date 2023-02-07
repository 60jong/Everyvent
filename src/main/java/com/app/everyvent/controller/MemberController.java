package com.app.everyvent.controller;

import com.app.everyvent.dto.web.DestinationIds;
import com.app.everyvent.dto.web.*;
import com.app.everyvent.domain.Member;
import com.app.everyvent.service.MailService;
import com.app.everyvent.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MailService mailService;

    /**
     * 멤버 회원가입
     *
     * @param newMemberParam
     * @return
     */
    @PostMapping("")
    public NewMemberRes postMember(@RequestBody NewMemberParam newMemberParam) {

        Long newMemberId = memberService.join(
                newMemberParam.getName(),
                newMemberParam.getEmail(),
                newMemberParam.getPassword(),
                newMemberParam.getPhoneNumber(),
                newMemberParam.getMailNotificationEnable()
        );

        Member newMember = memberService.findOne(newMemberId);
        return new NewMemberRes(newMember);

    }

    /**
     * 멤버의 구독 항공사 이름 조회
     *
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
     *
     * @param memberId
     * @param airlineIds
     */
    @PostMapping("/{memberId}/subscriptions")
    public void postMemberSubscriptions(@PathVariable("memberId") Long memberId, @RequestBody AirlineIds airlineIds) {
        memberService.subscribe(memberId, airlineIds.getAirlineIds());
    }

    /**
     * 멤버 여행지 추가
     *
     * @param memberId
     * @param destinationIds
     */
    @PostMapping("/{memberId}/destinations")
    public void postMemberDestinations(@PathVariable("memberId") Long memberId, @RequestBody DestinationIds destinationIds) {
        memberService.addDestinations(memberId, destinationIds.getDestinationIds());
    }
}
