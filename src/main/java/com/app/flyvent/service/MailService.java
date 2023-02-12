package com.app.flyvent.service;

import com.app.flyvent.domain.Event;
import com.app.flyvent.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class MailService {
    private final JavaMailSender javaMailSender;
    private final MemberService memberService;

    public void sendMail(Event newEvent) {
        Set<Member> membersToSendMail = new HashSet<>();

        newEvent.getDestinations().stream()
                .forEach(destination -> membersToSendMail.addAll(memberService.findAllByDestination(destination)));

        for (Member member : membersToSendMail) {
            if (member.getMailNotificationEnable()) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(member.getEmail());
                message.setSubject("Flyvent, 새로운 이벤트 알림 메일");
                message.setText(newEvent.getText() + newEvent.getUrl());

                javaMailSender.send(message);
            }
        }
    }
}
