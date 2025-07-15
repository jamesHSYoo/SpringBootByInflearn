package hello.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.member.Grade;
import hello.core.member.MemberService;
import hello.core.member.Member;

public class MemberApp {

    public static void main(String[] args) {


        // AppConfig 에 있는 내용을 기반으로 설정을 가져온다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);  
        
        // return 타입이 MemeberService 인 함수 memberService를 등록된 빈에서 가져온다. 
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "MemberA", Grade.VIP);

        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("New Member : " + member.getName());
        System.out.println("find Member : " + findMember.getName());
    }

}
