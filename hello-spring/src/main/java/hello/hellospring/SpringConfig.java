package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//스프링 빈 직접 만들기
@Configuration
public class SpringConfig {
    //Spring에서 자동으로 Spring Bean 생성 해줌
    //JDBC로 DB 연결할 때 필요
    //private DataSource dataSource;
    //@Autowired
    //public SpringConfig(DataSource dataSource) {
    //    this.dataSource = dataSource;
    //}

    //JPA로 연결할 떄 EntityManager가 필요함
    //private EntityManager em;
    //public SpringConfig(EntityManager em) {
    //    this.em = em;
    //}

    //Spring JPA 구현체 등록됨(Spring Data JPA 쓸 때)
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService(){
        return  new MemberService(memberRepository);
    }

    //AOP Bean 등록 OR Component
    //@Bean
    //public TimeTraceAop timeTraceAop(){
    //    return new TimeTraceAop();
    //}

    //@Bean
    //public MemberRepository memberRepository(){
        //return  new MemoryMemberRepository();
        //return  new JdbcMemberRepository(dataSource);
        //return  new JdbcTemplateMemberRepository(dataSource);
        //return  new JpaMemberRepository(em);

    //}
}
