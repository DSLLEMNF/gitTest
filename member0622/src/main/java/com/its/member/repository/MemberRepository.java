package com.its.member.repository;

import com.its.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository  extends JpaRepository<MemberEntity, Long>{
    // select * from member_table where memberEmail = ?
    // 리턴타입: MemberEntity
    // 매개변수: memberEmail(String)
    // jpa 서 권고하는 규칙대로 메서드를 만들면 거기에 해당하는  Query문을 만들어줌
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
