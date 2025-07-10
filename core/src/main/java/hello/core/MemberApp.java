package hello.core;

import hello.core.member.Grade;
import hello.core.member.MemberService;
import hello.core.member.Member;

public class MemberApp {

    public static void main(String[] args) {

        AppConfig config = new AppConfig();
        MemberService memberService = config.memberService();
        Member member = new Member(1L, "MemberA", Grade.VIP);

        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("New Member : " + member.getName());
        System.out.println("find Member : " + findMember.getName());
    }

}
