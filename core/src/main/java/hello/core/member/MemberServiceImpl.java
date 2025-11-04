package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;    // 추상화에만 의존. DIP 원칙을 지키고 있음

    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    } // 어떤 객체가 주입 될지는 모름. 넣어줄 객체는 외부에서 컨트롤 -> 그걸 AppConfig 가 함


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
    // Test용
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
