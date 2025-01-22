package com.korea.shop.service;

import com.korea.shop.domain.Member;
import com.korea.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // 클래스에 붙임
@Transactional(readOnly = true)
public class MemberService {

    // 싱글톤 객체를 컨테이너가 생성해서 주입해줌 = 의존성 주입
    // 스프링 컨테이너가 관리하는 Bean(클래스) 중에 Type이 맞는 Bean을 찾아서 주입
//    @Autowired 생성자,필드,메서드

    // final 키워드 = 불변, 이키워드에 자동으로 @RequiredArgsConstructor에 의해 주입
    public final MemberRepository memberRepository;

    // 회원등록
    // 트랜잭션(실행중 중간에 실패시 DB에 적용시키지 않음 무조건 끝까지 성공해야 DB에 저장, 커밋)
    @Transactional // 변경 작업이 있을시 readOnly가 아님을 표시
    public Long join(Member member){
        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    // 유효성 검사 - 중복 멤버 검사
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // 특정 회원 조회
    public Member findOne(Long memberid){
        return memberRepository.findOne(memberid);
    }
}
