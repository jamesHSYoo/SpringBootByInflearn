package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
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
 */

public class AppConfig {
    
    private MemberRepository getMemberRepository(){
        return new MemoryMemberRepository();
    }

    private DiscountPolicy getDiscountPolicy(){
        return new FixDiscountPolicy();
    }

    public MemberService memberService(){
        //return new MemberServiceImpl(new MemoryMemberRepository());   memberRepository 도 함수로 빼서 관리, 인스턴스 생성하는 일이 중복되어 있어 함수로 따로 관리 한 것
        return new MemberServiceImpl(getMemberRepository());
    }

    public OrderService OrderService(){
        //return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy()); 위 함수와 같은 이유로 함수로 빼서 관리
        return new OrderServiceImpl(getMemberRepository(), getDiscountPolicy());

    }
}
