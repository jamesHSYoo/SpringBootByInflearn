package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberService;

public class SingletonTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("스프링 없는 순수한 DI컨테이너")
    void pureContainer(){

        AppConfig appConfig = new AppConfig();

        // 1. 조회 : 호출 할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회 : 호출 할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();


        System.out.println("memberService1 : " + memberService1);
        System.out.println("memberService2 : " + memberService2);

        // memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

        /*
         * 이 방식의 문제점은 요청 할때 마다 객체가 새로 생성된다. 
         * 예시로 만약 고객 트래픽이 초당 100이 나오면 초당 100개의 객체가 생성되고 소멸됨 => 메모리 낭비가 심하게 된다. 
         * 해결 방법은 해당 객체가 딱 1개만 생성되고, 공유 하도록 설계 하면 된다. => 싱슬톤 패턴
         */
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService service1 = SingletonService.getInstance();
        SingletonService service2 = SingletonService.getInstance();

        System.out.println("service1 : " + service1);
        System.out.println("service2 : " + service2);

        Assertions.assertThat(service1).isSameAs(service2);

    }



}
