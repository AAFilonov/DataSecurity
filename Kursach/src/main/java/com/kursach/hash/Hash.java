package com.kursach.hash;

import java.io.InputStream;

public interface Hash {
    public  void init();
    public byte[] calcHash(InputStream input) ;
}
