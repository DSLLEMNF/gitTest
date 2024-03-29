package com.its.member.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration // 해당 클래스의 설정 정보를 스프링 컨테이너에 등록
public class WebConfig implements WebMvcConfigurer {
    @Override //재정의
    public  void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginCheckInterceptor()) //어떤 인터셉터 쓸래?
                .order(1) // 해당 인터셉터의 우선순위
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/member/save-form", "/member/login-form",
                        "/member/login", "/member/save", "/js/**",
                        "/css/**", "/*.ico", "/error", "/images/**", "/favicon/**");
                // 로그인을 하지 않아도 접속 가능한 주소
    }

}
