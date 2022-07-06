package com.example.ex.controller;

import com.example.ex.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller //의존관계 주입
@RequiredArgsConstructor //매개변수 생성자를 요청한다, final 키워드가 붙은 필드만 매개변수로 하는 생성자를 만들어준다.
public class MainController {
    // MainService mainService = new MainService();  원래는 이런 형식 but 용량을 많이 차지함
    //필드주입(권고하지 않음)
    //@Autowired
    //private MainService mainService;

//    MainService mainService;
//    //생성자 주입
//    MainController(MainService mainService){
//        this.mainService = mainService;  // this.~ 로 선언 ()안의 생성자를 객체로 받아 MainService 에 적용
//                                         // Spring bin 에 전부 등록하기 때문에 더 유리함
//    }

    //lombok 활용
    private final MainService mainService;

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/req1")//req1이 요청이 되면
    public String req1(Model model){
        String data = mainService.req1();//호출
        model.addAttribute("data",data);//데이터 추가
        return "req1";//thymeleaf로 부르기기
    }
}
