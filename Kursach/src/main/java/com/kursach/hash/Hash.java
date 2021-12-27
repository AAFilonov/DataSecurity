package com.kursach.hash;

import java.io.InputStream;

public interface Hash {
    public byte[] calcHash(InputStream input) ;
}
