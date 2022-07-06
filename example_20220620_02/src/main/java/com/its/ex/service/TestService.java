package com.its.ex.service;

import com.its.ex.dto.TestDTO;
import com.its.ex.entity.TestEntity;
import com.its.ex.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Test;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public Long save(TestDTO testDTO){  // save 라는 메서드는 testEntity 메서드를 만듬
        System.out.println("testDTO = " + testDTO); // TestDTO 객체에 담긴 값을 TestEntity 객체에 옮겨담기
//        TestEntity testEntity = new TestEntity(); // jpl 을 쓸 때는 javaClass 와 DB와 주고받고 싶을 때는 Entity 또는 Pk 값으로 주고받아야 함.

        TestEntity testEntity = TestEntity.toEntity(testDTO);
        Long id = testRepository.save(testEntity).getId();
        return id;

        //Hibernate: insert into test_table (test_column1, test_column2) values (?, ?) save 메서드가 이 일 을 처리해줌
    }
    // test 하기 위해 만듬
    public TestDTO findById(Long id) { //jpa 는 optional 이라는 객체로 감싸서 리턴을 줌
        Optional<TestEntity> optionalTestEntity = testRepository.findById(id);
        if (optionalTestEntity.isPresent()){
            // 조회된 결과가 있다.
//           TestEntity testEntity = optionalTestEntity.get();
//           TestDTO testDTO = TestDTO.toDTO(testEntity);
//           return testDTO;   // 위 세문장을 밑 한문장으로 압축
           return TestDTO.toDTO(optionalTestEntity.get());
        }else{
            // 조회된 결과가 없다.
            return null;
        }
    }

    public List<TestDTO> findAll() {
        List<TestEntity> entityList = testRepository.findAll();
        List<TestDTO> findList = new ArrayList<>();  //TestDTO 의 List 호출
        for(TestEntity testEntity: entityList) {
            TestDTO testDTO = TestDTO.toDTO(testEntity); // TestDTO 에 TestEntity 값 넣기
            findList.add(testDTO);
        }
        return findList;
    }

    public void delete(Long id) {
        testRepository.deleteById(id);
    }

    public Long update(TestDTO testDTO) {
        // save 메서드 호출로 update 쿼리 가능 ( 단,id가 같이 가야함)
        TestEntity testEntity = TestEntity.toUpdateEntity(testDTO);
        Long id = testRepository.save(testEntity).getId();
        return id;
    }
}
