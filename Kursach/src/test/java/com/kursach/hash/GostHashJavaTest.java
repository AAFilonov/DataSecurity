package com.kursach.hash;

import com.kursach.kursach.service.HashService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GostHashJavaTest {
    @Test
    public  void testHash(){
        HashService service= new HashService();
        System.out.println(service.calcHash("abc"));
        Assertions.assertEquals("b285056dbf18d7392d7677369524dd14747459ed8143997e163b2986f92fd42c",service.calcHash("abc"));
    }

}