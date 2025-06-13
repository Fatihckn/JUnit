package com.junitdemo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

// Normalde her test sınıfı ayrı bir instance olur ancak bu anotasyon ile lifecycle'ı değiştirebiliriz
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ChangingTestLifeCycle {

    StringBuilder stringBuilder = new StringBuilder();

    @AfterEach
    void afterEach() {
        System.out.println("Örnek değişkenin durumu " + stringBuilder);
    }

    @Test
    void test2() {
        System.out.println("Test 2");
        stringBuilder.append("2");
    }

    @Test
    void test1(){
        System.out.println("Test 1");
        stringBuilder.append("1");
    }

    @Test
    void test3(){
        System.out.println("Test 3");
        stringBuilder.append("3");
    }


    @Test
    void test4(){
        System.out.println("Test 4");
        stringBuilder.append("4");
    }
}
