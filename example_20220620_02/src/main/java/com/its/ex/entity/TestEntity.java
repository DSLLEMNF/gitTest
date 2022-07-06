package com.its.ex.entity;

import com.its.ex.dto.TestDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "test_table")
public class TestEntity { //table , 자동적으로 카멜케이스 이름으로 변경됨
    @Id //pk만 주는 것
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(name = "test_id")
    private Long id; //pk id 지정


    @Column(name = "test_column1", length = 50)
    private String column1; // column 지정

    @Column(unique = true)
    private String testColumn2;


    //목적: TestDTO 객체를 밭아서 Entity 객체에 옮겨 담은 후 Entity 객체를 리턴
    //Service 클레서에서 toEntity 메서드를 호출해서 리턴받은 객체를
    //testRepository 의 save 메서드에 전달하세요.

    // 정적 팩토리 메서드 (다른 클래스에서 객체를 만들지 않고 직접 접근 가능)
    // Entity 객체에 대한 기본 생성자를 외부에서 막을 수 있음.(Entity 는 소중)
    // 장점: 내부구조는 노출이 안된 채 매개변수와 리턴만 공개하여 캡슐화 가능
    //      원하는 리턴을 줄 수 있음.

    public static TestEntity toEntity(TestDTO testDTO) {//toEntity 메서드를 만들어서 testDTO 의 값을 Entity 로 옮기는 작업
        TestEntity testEntity = new TestEntity(); // testEntity 로 객체 선언
        testEntity.setColumn1(testDTO.getColumn1()); // entity 의 column1 을 testDTO 의 Column1 값으로 set
        testEntity.setTestColumn2(testDTO.getTestColumn2()); // 마찬가지로 나머지 값을 다 따로따로 담아주어야 함
        return testEntity;
    }

    public static TestEntity toUpdateEntity(TestDTO testDTO) {
        TestEntity testEntity = new TestEntity();
        testEntity.setId(testDTO.getId());
        testEntity.setColumn1(testDTO.getColumn1());
        testEntity.setTestColumn2(testDTO.getTestColumn2());
        return testEntity;
    }
}
