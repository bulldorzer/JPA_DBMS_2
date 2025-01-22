package com.korea.shop;



import com.korea.shop.domain.Member;
import com.korea.shop.service.MemberService;
import com.korea.shop.repository.MemberRepository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest { //~~Test로 끝나여야함

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test // 테스트코드 실행 됨
    public void 회원가입() throws Exception{
        // given - 값 설정
        Member member = new Member();
        member.setName("kim"); // Member엔티티 Setter 만들어주기


        // when - 동작
        Long saveId = memberService.join(member);

        // 영속성 컨텍스트 동기화
        em.flush();
        em.clear();

        //then - 결과
        assertEquals(member.getName(), memberRepository.findOne(saveId).getName());
    }

}
