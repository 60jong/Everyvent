package com.app.flyvent.controller;

import com.app.flyvent.dto.web.DestinationIds;
import com.app.flyvent.dto.web.*;
import com.app.flyvent.domain.Member;
import com.app.flyvent.service.MailService;
import com.app.flyvent.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;
import java.util.List;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MailService mailService;

    // 멤버 회원가입 페이지
    @GetMapping("/signup")
    public String getSignupPage(Model model) {
        model.addAttribute("newMemberParam", new NewMemberParam());
        return "members/signup";
    }

    // 멤버 회원가입
    @PostMapping("/signup")
    public String postMember(@ModelAttribute("newMemberParam") NewMemberParam newMemberParam) {

        Long newMemberId = memberService.join(
                newMemberParam.getName(),
                newMemberParam.getEmail(),
                newMemberParam.getPassword(),
                newMemberParam.getPhoneNumber(),
                newMemberParam.getMailNotificationEnable()
        );

        Member newMember = memberService.findOne(newMemberId);
        return "redirect:/";
    }

    // 멤버 로그인 페이지
    @GetMapping("/signin")
    public String getSigninPage(Model model) {
        model.addAttribute("memberSigninParam", new MemberSigninParam());
        return "members/signin";
    }

    // 멤버 회원가입
    @PostMapping("/signup")
    public String signin(@ModelAttribute("memberSigninParam") MemberSigninParam memberSigninParam) {

        memberService.login(memberSigninParam.getEmail(), memberSigninParam.getPassword());

        return "redirect:/";
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
    @PostMapping(value = "/{memberId}/destinations", produces = MediaType.IMAGE_JPEG_VALUE)
    public void postMemberDestinations(@PathVariable("memberId") Long memberId, @RequestBody DestinationIds destinationIds) {
        memberService.addDestinations(memberId, destinationIds.getDestinationIds());
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
