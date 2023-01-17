package com.app.everyvent.domain;

import com.app.everyvent.domain.airline.Airline;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue
    @Column(name = "subscription_id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id")
    private Airline airline;

    private Integer status = 1;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 메서드
    public Subscription(Member member, Airline airline) {
        this.member = member;
        this.airline = airline;
    }

    public static Subscription subscribe(Member member, Airline airline) {
        return new Subscription(member, airline);
    }

    public void register() {
        this.member.subscribe(this);
        this.airline.addSubscription(this);
    }


}
