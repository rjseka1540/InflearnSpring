package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service

//JPA로 데이터를 저장및 검색할려면 트렌젝션안에서 실행되어야함
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    //Dependency Injection(DI : 의존관계 주입)
    //DI 필드 주입식 : 비권장
    //@Autowired private MemberRepository memberRepository;

    //DI Setter 주입식 : 아무나 코드 수정 가능 -> 비권장
    /**
     *  private MemberRepository memberRepository;
     *     @Autowired
     *     public void setMemberRepository(MemberRepository memberRepository) {
     *         this.memberRepository = memberRepository;
     *     }
     */
   
    //DI 생성자 주입식 : 요즘 씀
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //Dependency Injection(DI : 의존관계 주입)

    /**
     * 회원가입
     */
    public Long join(Member member)
    {
        //같은 이름이 있는 중복회원 X
        /**
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        */
        vaildateDuplicateMember(member);

        memberRepository.save(member);
        return  member.getId();
    }

    private void vaildateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m-> {
                   throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers()
    {
        return memberRepository.findAll();
    }

    /**
     * ID 조회
     * @return
     */
    public Optional<Member> findOne(Long memberId)
    {
        return memberRepository.findById(memberId);
    }
}
