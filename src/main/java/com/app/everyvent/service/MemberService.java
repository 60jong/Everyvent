package com.app.everyvent.service;

import com.app.everyvent.domain.airline.Airline;
import com.app.everyvent.domain.destination.City;
import com.app.everyvent.domain.destination.Destination;
import com.app.everyvent.repository.DestinationRepository;
import com.app.everyvent.domain.Member;
import com.app.everyvent.domain.Subscription;
import com.app.everyvent.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final DestinationRepository destinationRepository;

    public void join(Member newMember) {
        memberRepository.save(newMember);
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    public void subscribe(Member member, List<Airline> airlines) {
        for (Airline airline : airlines) {
            Subscription subscription = new Subscription(member, airline);
            subscription.register();
            memberRepository.saveSubscription(subscription);
        }

    }

    public List<String> getAirlineEnglishNames(Member member) {
        return member.getAirlineEnglishNames();
    }

    public void setDestinations(Member member, List<City> cities) {
        for (City city : cities) {
            Destination destination = new Destination(member, city);
            destination.register();
            destinationRepository.save(destination);
        }
    }
}
