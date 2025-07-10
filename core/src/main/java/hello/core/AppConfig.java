package hello.core;

import hello.core.discount.FixDiscountPolicy;
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
 */

public class AppConfig {
    
    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService OrderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
