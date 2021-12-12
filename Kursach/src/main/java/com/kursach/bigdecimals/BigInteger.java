package com.kursach.bigdecimals;

import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class BigInteger implements Comparable<BigInteger> {
    static private int BASE;
    //reversed
    String digits = "0";
    Boolean isPositive = true;

    public BigInteger() {
    }

    public BigInteger(BigInteger other) {
        this.digits = other.digits;
        this.isPositive = other.isPositive;
    }

    public BigInteger(Long value) {
        this.isPositive = value.compareTo(0L) >= 0;
        Long absVal = Math.abs(value);
        this.digits = new StringBuilder(absVal.toString()).reverse().toString();
    }


    public BigInteger(String s) {
        if (!s.matches("^[-+]?\\d+$"))
            throw new NumberFormatException("Incorrect format!");
        StringBuilder input = new StringBuilder(s);

        if (input.charAt(0) == '+') {
            this.isPositive = true;
            input.deleteCharAt(0);
        } else if (input.charAt(0) == '-') {
            this.isPositive = false;
            input.deleteCharAt(0);
        }

        while (input.charAt(0) == '0'&&input.length()>1)
            input.deleteCharAt(0);
        digits = input.reverse().toString();

    }

    public BigInteger neg() {
        var output = new BigInteger(this);
        output.isPositive = !output.isPositive;
        return output;
    }

    public BigInteger multiply(BigInteger other) {
        StringBuilder output = new StringBuilder();

        if (this.digits.length()>=other.digits.length()) {
            return this.multiplayVal(other);
        }
        return other.multiplayVal(this);
    }

    private BigInteger multiplayVal(BigInteger other) {
        Boolean bothAreSameSing = this.isPositive.equals(other.isPositive);

        Stack<BigInteger> subValues = new Stack<>();
        for (int i = 0; i < other.digits.length() ; i++) {
            char digitChar = other.digits.charAt(i);
            int digitFactor = digitChar-'0';
            StringBuilder value = new StringBuilder();
            int buffer = 0;
            for (int j = 0; j < this.digits.length(); j++) {

                int digitMul = this.digits.charAt(j) - '0';
                int mul = digitFactor*digitMul;
                value.append(mul);
                if(mul>=10){
                    mul-=10;
                    buffer++;
                }
            }
            value.reverse().append(Strings.repeat("0",i));
            subValues.add(new BigInteger(value.toString()));
        }
        BigInteger sum = new BigInteger(0L);
        while (!subValues.empty()){
            BigInteger val = subValues.pop();
            sum= sum.add(val);
        }
        if(!bothAreSameSing)
            sum = sum.neg();
        return sum;
    }

    public BigInteger add(BigInteger other) {
        StringBuilder output = new StringBuilder();
        Boolean sign = this.isPositive.equals(other.isPositive);

        boolean areEqualLength = false;
        //TODO  если одно из числе отрицательное вызвать вычитание
        int minLength = other.digits.length();
        boolean isFirstValBigger = true;

        if (this.digits.length() == other.digits.length())
            areEqualLength = true;
        else if (this.digits.length() < other.digits.length()) {
            isFirstValBigger = false;
            minLength = this.digits.length();
        }
        int buffer = 0;
        for (int i = 0; i < minLength; i++) {
            int val1 = this.digits.charAt(i) - '0';
            int val2 = other.digits.charAt(i) - '0';
            int sum = val1 + val2 + buffer;
            buffer = 0;
            if (sum >= 10) {
                sum -= 10;
                buffer += 1;
            }
            output.append(sum);
        }
        if (areEqualLength) {
            output.append(buffer);
        } else if (isFirstValBigger) {
            int val = this.digits.charAt(minLength) - '0';
            val += buffer;
            buffer = 0;
            output.append(val);
            var remainingDigits = this.digits.substring(minLength + 1);
            output.append(remainingDigits);
        } else {
            int val = other.digits.charAt(minLength) - '0';
            val += buffer;
            buffer = 0;
            output.append(val);
            var remainingDigits = other.digits.substring(minLength + 1);
            output.append(remainingDigits);
        }


        output.reverse();

        return new BigInteger(output.toString());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BigInteger that = (BigInteger) o;
        return digits.equals(that.digits) && isPositive.equals(that.isPositive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(digits, isPositive);
    }

    @Override
    public String toString() {
        String sign = this.isPositive ? "" : "-";
        return sign + new StringBuilder(digits).reverse();
    }

    @Override
    public int compareTo(BigInteger o) {

        switch (this.isPositive.compareTo(o.isPositive)) {
            case 1:
                return this.compareDigits(o);
            case -1:
                return o.compareDigits(this);
            default:
                return 0;
        }
    }

    private int compareDigits(BigInteger o) {
        //TODO сравнение
        return 0;
    }

}
