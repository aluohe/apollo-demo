package com.example.demo.compent;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author aluohe
 * @className AsynTest
 * @projectName demo
 * @date 2020/3/20 11:00
 * @description
 * @modified_by
 * @version:
 */
@Component
public class AsynTest {

    @Async
    public void testAsyn(){

        try {
            Thread.sleep(15000);
            System.out.println("异步执行任务");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}