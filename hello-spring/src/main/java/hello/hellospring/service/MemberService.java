package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    //Dependency Injection(DI : 의존관계 주입)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

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
