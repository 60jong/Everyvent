package com.app.everyvent.controller;

import com.app.everyvent.domain.airline.Airline;
import com.app.everyvent.dto.*;
import com.app.everyvent.service.AirlineService;
import com.app.everyvent.domain.destination.City;
import com.app.everyvent.service.DestinationService;
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
    private final DestinationService destinationService;

    /**
     * 멤버 회원가입
     * @param postMemberReq
     * @return
     */
    @PostMapping("/new")
    public PostMemberRes postMember(@RequestBody PostMemberReq postMemberReq) {
        Member newMember = postMemberReq.toMember();
        memberService.join(newMember);

        return new PostMemberRes(newMember);
    }

    /**
     * 멤버의 구독 항공사 이름 조회
     * @param memberId
     * @return
     */
    @GetMapping("/{memberId}/subscriptions")
    public GetMemberSubscriptionsRes getMemberSubscriptions(@PathVariable("memberId") Long memberId) {
        Member member = memberService.findOne(memberId);
        List<String> airlineEnglishNames = memberService.getAirlineEnglishNames(member);

        return new GetMemberSubscriptionsRes(memberId, airlineEnglishNames);
    }

    /**
     * 멤버 구독 항공사 추가
     * @param memberId
     * @param postMemberSubscriptionsReq
     */
    @PostMapping("/{memberId}/subscriptions")
    public void postMemberSubscriptions(@PathVariable("memberId") Long memberId,
                                        @RequestBody PostMemberSubscriptionsReq postMemberSubscriptionsReq) {
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
    @PostMapping("/{memberId}/destinations")
    public void postMemberDestinations(@PathVariable("memberId") Long memberId,
                                       @RequestBody PostMemberDestinationsReq postMemberDestinationsReq) {
        List<City> cities = destinationService.findCities(postMemberDestinationsReq.getCityIds());
        Member member = memberService.findOne(memberId);
        memberService.setDestinations(member, cities);
    }
}
