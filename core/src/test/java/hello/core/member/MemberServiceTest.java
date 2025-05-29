package hello.core.member;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    private MemberService memberService = new MemberServiceImpl();

    @Test
    void join(){
        //Given
        Member member = new Member(1L, "MemberA", Grade.VIP);
        // When
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //Then
        /*
            강의에서는 Assertions.assetThat(member).isEqualTo(findMember); 로 되어 있음
            이 코드에서는 assertThat 은 오류가 나오고 있음
            https://velog.io/@yeonbikim/Assertions.assertThat-%EB%A9%94%EC%84%9C%EB%93%9C%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%A0-%EC%88%98-%EC%97%86%EB%8B%A4%EB%8A%94-%EC%97%90%EB%9F%AC-JUnit-%EC%9D%B4%EB%9E%80
            이 페이지 가면 관련 내용이 정리 되어 있음.
         */

        Assertions.assertEquals(member, findMember);



    }

}
