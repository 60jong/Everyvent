package com.app.flyvent.controller;

import com.app.flyvent.dto.web.DestinationIds;
import com.app.flyvent.dto.web.*;
import com.app.flyvent.domain.Member;
import com.app.flyvent.service.MailService;
import com.app.flyvent.service.MemberService;
import com.app.flyvent.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MailService mailService;

    // 멤버 회원가입 페이지
    @GetMapping("/signup")
    public String getSignupPage(Model model) {
        model.addAttribute("memberJoinRequest", new MemberJoinRequest());
        return "members/signup";
    }

    // 멤버 회원가입
    @PostMapping("/signup")
    public String postMember(
            @Valid @ModelAttribute("memberJoinRequest") MemberJoinRequest memberJoinRequest,
            Model model
    ) {
        memberService.join(
                memberJoinRequest.getName(),
                memberJoinRequest.getEmail(),
                memberJoinRequest.getPassword(),
                memberJoinRequest.getPhoneNumber(),
                memberJoinRequest.getMailNotificationEnable()
        );

        Message message = new Message("회원가입에 성공했습니다.", "/");
        return showMessageAndRedirect(message, model);
    }

    // 멤버 로그인 페이지
    @GetMapping("/signin")
    public String getSigninPage(Model model) {
        model.addAttribute("memberSigninRequest", new MemberSigninRequest());
        return "members/signin";
    }

    // 멤버 로그인
    @PostMapping("/signin")
    public String signin(
            @ModelAttribute("memberSigninRequest") MemberSigninRequest memberSigninRequest,
            Model model
    ) {
        memberService.login(memberSigninRequest.getEmail(), memberSigninRequest.getPassword());

        Message message = new Message("로그인에 성공했습니다.", "/");
        return showMessageAndRedirect(message, model);
    }

    /**
     * 멤버의 구독 항공사 이름 조회
     *
     * @param memberId
     * @return
     */
    @ResponseBody
    @GetMapping("/{memberId}/subscriptions")
    public SubscribedAirlines getMemberSubscriptions(@PathVariable("memberId") Long memberId) {
        Member member = memberService.findById(memberId);
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
    @PostMapping(value = "/{memberId}/destinations", produces = MediaType.IMAGE_JPEG_VALUE)
    public void postMemberDestinations(@PathVariable("memberId") Long memberId, @RequestBody DestinationIds destinationIds) {
        memberService.addDestinations(memberId, destinationIds.getDestinationIds());
    }

    private String showMessageAndRedirect(Message params, Model model) {
        model.addAttribute("message", params.getMessage());
        model.addAttribute("redirectUri", params.getRedirectUri());
        return "util/alertMessage";
    }

//    @GetMapping(
//            value = "/velog/post-stats",
//            produces = MediaType.IMAGE_PNG_VALUE
//    )
//    @ResponseBody
//    public byte[] getVelogPostStats(@RequestParam("username") String username) throws IOException {
//        BufferedImage image = null;
//        FontMetrics metrics = null;
//        image = ImageIO.read(new File("src/main/resources/velog.png")); // 배경 파일 불러오기
//        Graphics g = image.getGraphics();
//        g.setColor(Color.BLACK);
//        g.setFont(new Font("NanumGothic", Font.PLAIN, 5));
//        g.drawString("username", 10, 10); // 문자열 삽입
//        ImageIO.write(image, "png", new File("src/main/resources/velog-1.png")); // 문자열이 삽입된 PNG 파일 저장
//        g.dispose();
//
//        return IOUtils.toByteArray(new FileInputStream("src/main/resources/velog-1.png"));
//    }
}
