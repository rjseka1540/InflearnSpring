package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService ms;
    MemoryMemberRepository mmr;

    @BeforeEach
    void beforeEach()
    {
        mmr = new MemoryMemberRepository();
        ms = new MemberService(mmr);
    }

    @AfterEach
    void afterEach()
    {
        mmr.clearStore();
    }

    @Test
    void join() {
        //given
        Member m = new Member();
        m.setName("string");

        //when
        Long saveId = ms.join(m);

        //then
        Member findMember = ms.findOne(saveId).get();
        Assertions.assertThat(m.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        //given
        Member m1 = new Member();
        m1.setName("spring");

        Member m2 = new Member();
        m2.setName("spring");

        //when
        ms.join(m1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> ms.join(m2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
       /* try
        {
            ms.join(m2);
            fail();

        }catch (IllegalStateException e)
        {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}