package com.binary.CarShow.repositatories;

import com.binary.CarShow.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    public Member findByEmail(String email);
}
