package com.junitdemo;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

// Testleri hangi sırayla çalışacağını belirleme.
// @Order(1) Sınıfların hangi sırayla çalışacağını da belirleyebiliriz, ancak resources kısmında yapılandırma gerekir.
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MethodsOrderedByOrderIndexTest {

    @Order(2)
    @Test
    void test2() {
        System.out.println("Test 2");
    }

    @Order(1)
    @Test
    void test1(){
        System.out.println("Test 1");
    }

    @Order(3)
    @Test
    void test3(){
        System.out.println("Test 3");
    }

    @Order(4)
    @Test
    void test4(){
        System.out.println("Test 4");
    }
}
