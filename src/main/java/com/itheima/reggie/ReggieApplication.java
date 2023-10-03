package com.itheima.reggie;

/**
 * ClassName: ReggieApplication
 * Package: com.itheima.reggie
 * Description:
 *
 * @Author:
 * @Create: 3/10/2023 - 10:04 pm
 * @Version:
 */




import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication
public class ReggieApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReggieApplication.class,args);
        log.info("Reggie Application Started ...");
    }
}
