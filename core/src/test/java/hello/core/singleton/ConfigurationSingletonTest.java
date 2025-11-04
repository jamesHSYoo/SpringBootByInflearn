package hello.core.singleton;

import java.lang.reflect.Member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;

import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class ConfigurationSingletonTest {


    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository repository = ac.getBean("getMemberRepository", MemberRepository.class);
        System.out.println("memberService -> memberRepository : " + memberService.getMemberRepository());
        System.out.println("orderService -> memberRepository : " + orderService.getMemberRepository());
        System.out.println("repository : " + repository);


        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(orderService.getMemberRepository());
        Assertions.assertThat(repository).isSameAs(orderService.getMemberRepository());

        /*
         * 기대되는 출력 순서
         * call AppConfig.memberService
         * call AppConfig.memberRepository
         * call AppConfig.memberRepository
         * call AppConfig.orderService
         * call AppConfig.memberRepository
         */

         /*
          * 실제 출력 결과
          * Call AppConfig.memberRepository
          * Call AppConfig.orderService
          * Call AppConfig.orderRepository
          */
    }



}
