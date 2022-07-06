package com.its.ex.repository;


import com.its.ex.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
// jpa repository 사용중 따라서 spring bin에서 상속받아서 사용중
public interface TestRepository extends JpaRepository<TestEntity, Long> {
    //private 클레스 내에서만
    //public  페키지 내에서만
    //protected 보안
}