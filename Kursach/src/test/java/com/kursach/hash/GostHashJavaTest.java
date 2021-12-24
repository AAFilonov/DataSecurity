package com.kursach.hash;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GostHashJavaTest {
    @Test
    public  void testHash(){
        GostHashJavaWrapper hash= new GostHashJavaWrapper();
        System.out.println(hash.hash("abc"));
        Assertions.assertEquals("b285056dbf18d7392d7677369524dd14747459ed8143997e163b2986f92fd42c",hash.hash("abc"));
    }

}