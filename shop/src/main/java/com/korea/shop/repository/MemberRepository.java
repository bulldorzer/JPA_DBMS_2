package com.korea.shop.repository;

import com.korea.shop.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    
    /*
        엔티티를 영속성 컨텍스트에 저장하거나 데이터 베이스와 상호작용하는 역할
    */
    @PersistenceContext // 객체생성을 컨테이너가 등록 EntityManager를 주입받기위한 어노테이션
    private EntityManager em; // 비서

    /*
        저장기능
        member 객체를 영속성 컨텍스트에 저장, 데이터베이스에 반영함( 커밋했을때 데이터베이스에 반영)
        주로 새 엔티티를 데이터베이스에 저장할 때 사용됨
    */
    public void save(Member member){
        em.persist(member);
    }

    // 1개 데이터 조회
    public Member findOne(Long id){
        // EntityManage.find( 조회하려는 엔티티클래스, PK);
        return em.find(Member.class, id);
    }
    // 여러 데이터조회
    public List<Member> findAll(){
//        List<Member> list = em.createQuery("JPQL",엔티티클래스);
        List<Member> list =
                em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return list;
    }
    // 이름으로 조회 (조건)
    public List<Member> findByName(String name){

        // 해당 쿼리문은 DB를 직접 조회하는 것이 아니라 엔티티를 대상으로 조회하는 것임
        List<Member> list = em.createQuery("select m from member m where m.name = :name",Member.class)
                .setParameter("name",name) // 매개변수 name값을 :name에 매핑
                .getResultList();

        return list;
    }
}
