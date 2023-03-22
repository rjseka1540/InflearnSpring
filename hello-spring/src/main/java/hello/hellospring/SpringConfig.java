package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//스프링 빈 직접 만들기
@Configuration
public class SpringConfig {
    //Spring에서 자동으로 Spring Bean 생성 해줌
    private DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return  new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        //return  new MemoryMemberRepository();
        //return  new JdbcMemberRepository(dataSource);
        return  new JdbcTemplateMemberRepository(dataSource);
    }
}
