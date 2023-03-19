package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository rep = new MemoryMemberRepository();

    //Test 끝난 후 클래스 클리어
    @AfterEach
    public void afterEach()
    {
        rep.clearStore();
    }

    @Test
    public  void save()
    {
        Member member = new Member();
        member.setName("string");

        rep.save(member);

        Member result = rep.findById(member.getId()).get();
        //Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName()
    {
        Member mem1 = new Member();
        mem1.setName("spring1");
        rep.save(mem1);

        Member mem2 = new Member();
        mem2.setName("spring2");
        rep.save(mem2);

        Member result = rep.findByName("spring1").get();

        assertThat(result).isEqualTo(mem1);
    }
    @Test
    public void findAll()
    {
        Member mem1 = new Member();
        mem1.setName("spring1");
        rep.save(mem1);

        Member mem2 = new Member();
        mem2.setName("spring2");
        rep.save(mem2);

        List<Member> result= rep.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
