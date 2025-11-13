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

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("Bean : " + bean.getClass());
        /*
         * 결과물
         * Bean : class hello.core.AppConfig$$EnhancerBySpringCGLIB$$ee4ff54b
         * 
         * 예상되는 결과 값은 class hello.core.AppConfig 
         * 
         *  다른 이유는 내가 만든 클래스가 아니라 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 이용하여 AppConfig 클래스를 상속받은
         * 임의의 다른 클래스로 만들고, 그 다른 클래스를 스프링 빈으로 등록 한 것이다. 
         * 
         * 만약 AppConfig 에 @Configuration 애노테이션을 제거 하면 예상되는 결과값이 나옴
         * -> configurationTest() 테스트에서의 예상되는 결과값이 나오게됨.
         * -> 위의 테스트가 오류가 날것 이유는 각 객체가 서로 다른 객체가 생성되기 때문에.
         */
    }

}
