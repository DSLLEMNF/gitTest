package com.its.ex.dto;

import com.its.ex.entity.TestEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDTO {
    private Long id;
    private String column1;
    private String testColumn2;

    public TestDTO(String column1, String testColumn2) {
        this.column1 = column1;
        this.testColumn2 = testColumn2;
    }

    public static TestDTO toDTO(TestEntity testEntity){ //testEntity 를 testDTO 안에 넣는 작업
        TestDTO testDTO = new TestDTO();    //객체화
        testDTO.setId(testEntity.getId());  //testEntity 의 pk 값 id 값을 끌어와 DTO 에 값을 넣음
        testDTO.setColumn1(testEntity.getColumn1()); // testEntity 의 Column1 을 DTO column1에 넣음
        testEntity.setTestColumn2(testEntity.getTestColumn2());// testEntity 의 TestColumn2 를 DTO TestColumn2 에 넣음
        return testDTO;
    }
}
