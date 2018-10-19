package com.rokid.skill.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

/**
 * @author wuyukai on 2018/4/3.
 */
@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class,
  DataSourceAutoConfiguration.class})
@MapperScan(value = {"com.rokid.skill.demo.dao"})
public class SkillDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkillDemoApplication.class, args);
    }

}
