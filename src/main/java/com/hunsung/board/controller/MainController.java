package com.hunsung.board.controller;


import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController // 해당 클래스를 Controller 레이어로 인식함
                // Rest한 형태로(@Controller+@ResponseBody )
@RequestMapping("/") //Request 의 URL 패턴을 보고 해당하는 패턴이 왔을 때 해당 클래스를 실행
public class MainController {
    @GetMapping("")
    public String hello(){
        return "연습용";
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOriginPatterns();
            }
        };
    }
}
