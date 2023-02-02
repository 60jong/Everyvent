package com.app.everyvent.domain;

import com.app.everyvent.domain.destination.Destination;

import javax.persistence.*;

@Entity
public class MemberDestination {
    @Id @GeneratedValue
    @Column(name = "member_destination_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id")
    private Destination destination;

    /*---Constructor---*/
    public MemberDestination(Member member, Destination destination) {
        this.member = member;
        member.addMemberDestination(this);
        this.destination = destination;
        destination.addMemberDestination(this);
    }
}
