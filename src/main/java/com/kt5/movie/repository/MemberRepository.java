package com.kt5.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt5.movie.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

}
