package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/*
 * 애플리케이션의 실제 동작에 필요한 구현 객체를 생성을 담당
 * 
 * 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결) 해줌
 * MemberServiceImpl -> MemoryMemberRepository
 * OrderSeriviceImpl -> MemoryMemeberRepository, FixDiscountPolicy
 * 
 * DIP의 완성 : MemberServiceImpl 은 MemberRepository(인터페이스)에만 의존하고 주입되는 객체(인스턴스)는 모름
 * 관심사의 분리 : 객체를 생성하고 연결하는 역할과 실행 하는 역할이 명확하게 분리됨.
 * 
 * AppConfig 처럼 객체를 생성하고 관리하면서 의존관계를 연결해주는 것을 IoC 컨테이너 또는 DI 컨테이너라고 한다.
 * 의존관계 주입에 초점을 맞추어 최근에는 주로 DI 컨테이너라고한다.
 * 또는 어샘블러, 오브젝트 팩토리 등으로 불리기도 한다. 
 */

@Configuration
public class AppConfig {
    
    @Bean     // 스프링 컨테이너에 함수들이 저장된다.
    public MemberRepository getMemberRepository(){
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy getDiscountPolicy(){
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(getMemberRepository());
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(getMemberRepository(), getDiscountPolicy());

    }
}
