package com.example.ex.service;

import org.springframework.stereotype.Service;

@Service //MainService mainService = new MainService(); 대신 annotation을 이용해 자동으로 객체를 만들어줌 (single tom) (Spring bin 에 등록한다)
public class MainService {
    public String req1() {
        System.out.println("MainService.req1");
        return "방문 인증";
    }
}
