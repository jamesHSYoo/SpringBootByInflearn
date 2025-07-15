package hello.core;

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

public class AppConfig {
    
    private MemberRepository getMemberRepository(){
        return new MemoryMemberRepository();
    }

    private DiscountPolicy getDiscountPolicy(){
        //return new FixDiscountPolicy(); // 할인 정책이 바뀌면 이 함수만 변경 하면 된다. 
        return new RateDiscountPolicy();
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
