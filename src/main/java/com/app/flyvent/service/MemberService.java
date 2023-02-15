package com.app.flyvent.service;

import com.app.flyvent.domain.MemberDestination;
import com.app.flyvent.domain.airline.Airline;
import com.app.flyvent.domain.Member;
import com.app.flyvent.domain.Subscription;
import com.app.flyvent.domain.destination.Destination;
import com.app.flyvent.repository.MemberRepository;
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

    public Long join(String name, String email, String password, String phoneNumber, Boolean mailNotificationEnable) {
        Member newMember = new Member(name, email, password, phoneNumber, mailNotificationEnable);
        memberRepository.save(newMember);

        return newMember.getId();
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

    public List<Member> findAllByDestination(Destination destination) {
        return memberRepository.findAllByDestination(destination);
    }

    public void login(String email, String password) {

    }
}
