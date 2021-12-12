package com.kursach.crypto.RS6;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RoundRunner {
    @Value("${crypto.wordSize}")
    private static int wordSize = 32;

    public static Registers runRound(Registers reg, int roundKeyLeft, int roundKeyRight) {

        int temp1 = function(reg.B);
        int temp2 = function(reg.D);
        reg.A = (shiftLeft(reg.A ^ temp1, temp2)) + roundKeyLeft;
        reg.C = (shiftLeft(reg.C ^ temp2, temp1)) + roundKeyRight;

        int tmp = reg.A;
        reg.A = reg.B;
        reg.B = reg.C;
        reg.C = reg.D;
        reg.D = tmp;
        return reg;
    }

    //f(x)=x(2x+1)<<<(mod2^w), для w=32  (mod2^w)==5
    private static int function(int x) {
        return shiftLeft((x * (2 * x + 1)), (int) (Math.log(wordSize) / Math.log(2)));
    }

    //сдвиг влево, тк в Java нет  <<<
    private static int shiftLeft(int n, int x) {
        return ((n << x) | (n >>> (wordSize - x)));
    }


}
