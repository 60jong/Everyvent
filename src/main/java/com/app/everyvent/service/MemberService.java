package com.app.everyvent.service;

import com.app.everyvent.domain.MemberDestination;
import com.app.everyvent.domain.airline.Airline;
import com.app.everyvent.domain.Member;
import com.app.everyvent.domain.Subscription;
import com.app.everyvent.domain.destination.Destination;
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
    private final AirlineService airlineService;
    private final DestinationService destinationService;

    public void join(Member newMember) {
        memberRepository.save(newMember);
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    public void subscribe(Long memberId, List<Long> airlineIds) {
        Member member = findOne(memberId);
        List<Airline> airlines = airlineService.findAllById(airlineIds);

        airlines.stream()
                .forEach(airline -> new Subscription(member, airline));
    }

    public List<String> getAirlineEnglishNames(Member member) {
        return member.getAirlineEnglishNames();
    }

    public void addDestinations(Long memberId, List<Long> destinationIds) {
        Member member = findOne(memberId);
        List<Destination> destinations = destinationService.findAllById(destinationIds);

        destinations.stream()
                .forEach(destination -> new MemberDestination(member, destination));
    }
}
