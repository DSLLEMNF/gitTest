package com.its.ex.test;

import com.its.ex.dto.TestDTO;
import com.its.ex.service.TestService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestClass {
    @Autowired
    private TestService testService; //test이기 때문에 Autowired 사용 가능

    //testService 의 save() 호출
    //호출 후 리턴값을 print
    @Test
    @Transactional
    @Rollback(value = true)
    public void saveTest() {
//       Long testResult = testService.save();     // testService 의 save 메서드를 호출함
//       System.out.println("testResult = " + testResult);
        /**
         * 2.DTO 객체를 TestService 의 save 메서드로 전달
         * 3. 전달 후 리턴 값을 받아서(Long)
         * 4. 그 리턴값으로 DB 에서 findById를 하고
         * 5. DB 에서 조회된 데이터와 1.번에서 저장한 데이터가 일치하는지를 판단하여
         * 6. 일치하면 테스트 통과, 일치하지 않으면 테스트 실패
         */
        //1. 저장할 TestDTO 객체를 만들고 필드값을 저장.
        TestDTO testDTO = new TestDTO("테스트데이터1", "테스트데이터2");
        //2.3. DTO 객체를 TestService 의 save 메서드로 전달, 이 후 리턴값 (Long) 받음
        Long saveId = testService.save(testDTO);
        //4. 리턴받은 saveId 값으로 DB 에서 findById 클레스 실행
        TestDTO findDTO = testService.findById(saveId);
        //5.6. DB 에서 조회된 데이터와 1번에서 저장한 데이터가 일치하는지 판단 테스트 실행
        assertThat(testDTO.getColumn1()).isEqualTo(findDTO.getColumn1());
    }
    // test는 값이 잘 넘어오는지 확인하는 Class

    @Test
    @Transactional
    @Rollback
    @DisplayName("findALl 테스트")
    public void findAllTest() {
        /** 테스트 데이터를 3가지 넣은 후 findAll 그리고 findAll 한 사이즈를 측정 findAll 사이즈를 측정
         *  1. 3개의 테스트 데이터 저장
         *  2. findAll 호출
         *  3. 호출한 리스트의 크기가 3인지를 판단
         *  4. 3이면 테스트 통과, 아니면 테스트 실패
         */
        //3개의 테스트 데이터를 저장해봅시다.
//        TestDTO testDTO = new TestDTO("테스트데이터1", "테스트데이터1");
//        testService.save(testDTO);
//        testDTO = new TestDTO("테스트데이터2", "테스트데이터2");
//        testService.save(testDTO);
//        testDTO = new TestDTO("테스트데이터3", "테스트데이터3");
//        testService.save(testDTO);


        // for 문을 이용한 테스트데이터 저장

//        for(int i = 1 ; i <= 3 ; i ++){
//            testDTO = new TestDTO("테스트데이터"+i,"테스트데이터"+i);
//            testService.save(testDTO);
//            testService.save(new TestDTO("테스트데이터"+i,"테스트데이터"+i)); 한줄로
//        }

        //자바 람다식(화살표함수), IntStream 익명함수
        IntStream.rangeClosed(1, 3).forEach(i -> {
            testService.save(new TestDTO("테스트데이터" + i, "테스트데이터" + i));
        });
        //findAll 호출해서 리스트 크기가 3 과 일치하는지 확인해봅시다.
        List<TestDTO> testDTOList = testService.findAll(); //findAll 호출
        assertThat(testDTOList.size()).isEqualTo(3); //리스트 size 값이 3인지 확인
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("삭제테스트")
    public void deleteTest(){
        //1.
        TestDTO testDTO = new TestDTO("테스트데이터999","테스트데이터999");
        //2.3
        Long saveId = testService.save(testDTO);
        //4.삭제수행
        testService.delete(saveId);
        assertThat(testService.findById(saveId)).isNull();
    }
    @Test
    @Transactional
    @Rollback
    @DisplayName("수정테스트")
    public void updateTest(){
        /**
         * 수정테스트 시나리오
         * 1. 새로운 데이터 저장
         * 2. 저장한 객체의 test_column1의 값을 가지고 있음
         * 3. test_column1의 값을 변경함.
         * 4. 수정처리
         * 5. 수정 후 해당 객체를 조회하여 test_column1의 값을 가져옴.
         * 6. 2번에서 조회한 값과 5번에서 조회한 값이 같은지를 비교하여 다르면 테스트 성공 , 같다면 테스트 실패
         */
//        //1
//        TestDTO testDTO = new TestDTO("테스트데이터","테스트데이터");
//        //2
//        Long saveId = testService.save(testDTO);
//        //3
//        TestDTO findDTO = testService.findById(saveId);
//        //4
//        TestDTO testDTO1 = new TestDTO("테스트데이터2","테스트데이터2");
//        //5
//        testDTO1.setId(findDTO.getId());
//        testService.update(testDTO1);
//        //6
//        assertThat(findDTO).isNotEqualTo(testDTO1);




        //1
        TestDTO testDTO = new TestDTO("수정데이터","수정데이터1");
        Long saveId = testService.save(testDTO);
        //2
        TestDTO findDTO = testService.findById(saveId);
        //3
        TestDTO updateDTO = new TestDTO(saveId,"변경데이터","번경데이터1");
        //4
        testService.update(updateDTO);
        //5
        TestDTO afterUpdateDTO = testService.findById(saveId);
        //6. 7.
        assertThat(findDTO.getColumn1()).isNotEqualTo(afterUpdateDTO.getColumn1());
    }
    /**
     * 수정 테스트를 어떻게할지 시나리오 써보시고,
     * assertThat.isNotEqualTo() 사용
     */
}
