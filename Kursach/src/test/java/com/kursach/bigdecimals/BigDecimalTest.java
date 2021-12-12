package com.kursach.bigdecimals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BigIntegerTest {

    @Nested
    class creationTests {

        @Test
        public void testCreateFromLong() {
            BigInteger MyBigDecimal = new BigInteger(-100L);

            assertEquals("-100", MyBigDecimal.toString());
        }

        @Test
        public void testCreateDefault() {
            BigInteger MyBigDecimal = new BigInteger();

            assertEquals("0", MyBigDecimal.toString());
        }

        @Test
        public void testCreateFromString() {
            BigInteger MyBigDecimal = new BigInteger("123453637");

            assertEquals("123453637", MyBigDecimal.toString());
        }

        @Test
        public void testCreateFromStringLeadingZeros() {
            BigInteger MyBigDecimal = new BigInteger("0000123453637");

            assertEquals("123453637", MyBigDecimal.toString());
        }
        @Test
        public void testCreateFromStringLeadingZero() {
            BigInteger MyBigDecimal = new BigInteger("0");

            assertEquals("0", MyBigDecimal.toString());
        }
        @Test
        public void testCreateFromStringLeadingMultipleZero() {
            BigInteger MyBigDecimal = new BigInteger("00000");

            assertEquals("0", MyBigDecimal.toString());
        }

        @Test
        public void testCreateFromStringNeg() {
            BigInteger MyBigDecimal = new BigInteger("-123453637");

            assertEquals("-123453637", MyBigDecimal.toString());
        }

        @Test
        public void testCreateFromStringPos() {
            BigInteger MyBigDecimal = new BigInteger("+123453637");

            assertEquals("123453637", MyBigDecimal.toString());
        }

        @Test
        public void testCreateFromStringPos_WhenWrongFormat1() {
            assertThrows(NumberFormatException.class, () -> new BigInteger("++5"));
        }

        @Test
        public void testCreateFromStringPos_WhenWrongFormat2() {
            assertThrows(NumberFormatException.class, () -> new BigInteger("--5"));
        }

        @Test
        public void testCreateFromStringPos_WhenWrongFormat3() {
            assertThrows(NumberFormatException.class, () -> new BigInteger(" 5"));
        }

        @Test
        public void testCreateFromStringPos_WhenWrongFormat5() {
            assertThrows(NumberFormatException.class, () -> new BigInteger("agasg"));

        }
    }

    @Nested
    class ariphmeticsTest {
        @Nested
        class Mul {
            @Test
            public void testMul_WhenBothPositive_ThenReturnPositive() {
                BigInteger left = new BigInteger("111111111111111111111111111111111111111111111111111111111111111111111111");
                BigInteger right = new BigInteger("2");

                assertEquals("222222222222222222222222222222222222222222222222222222222222222222222222", left.multiply(right).toString());
            }

            @Test
            public void testMul_WhenBothNegative_ThenReturnPositive() {
                BigInteger left = new BigInteger("-111111111111111111111111111111111111111111111111111111111111111111111111");
                BigInteger right = new BigInteger("-2");

                assertEquals("222222222222222222222222222222222222222222222222222222222222222222222222", left.multiply(right).toString());
            }

            @Test
            public void testMul_WhenLeftPositive_ThenReturnNegative() {
                BigInteger left = new BigInteger("111111111111111111111111111111111111111111111111111111111111111111111111");
                BigInteger right = new BigInteger("-2");

                assertEquals("-222222222222222222222222222222222222222222222222222222222222222222222222", left.multiply(right).toString());
            }

            @Test
            public void testMul_WhenRightPositive_ThenReturnNegative() {
                BigInteger left = new BigInteger("111111111111111111111111111111111111111111111111111111111111111111111111");
                BigInteger right = new BigInteger("-2");

                assertEquals("-222222222222222222222222222222222222222222222222222222222222222222222222", left.multiply(right).toString());
            }

            @Test
            public void testMul_WhenBothSameLength() {

                BigInteger left = new BigInteger("200");
                BigInteger right = new BigInteger("200");
                assertEquals("40000", left.multiply(right).toString());
            }
            @Test
            public void testMul_WhenBothSameLength_AndRightIsNegative() {

                BigInteger left = new BigInteger("-200");
                BigInteger right = new BigInteger("200");
                assertEquals("-40000", left.multiply(right).toString());
            }
            @Test
            public void testMul_WhenBothSameLength_AndLeftIsNegative() {

                BigInteger left = new BigInteger("200");
                BigInteger right = new BigInteger("-200");
                assertEquals("-40000", left.multiply(right).toString());
            }
            @Test
            public void testMul_WhenBothSameLength_AndLeftIsZero() {

                BigInteger left = new BigInteger("200");
                BigInteger right = new BigInteger("0");
                assertEquals("0", left.multiply(right).toString());
            }
            @Test
            public void testMul_WhenBothSameLength_AndRightIsZero() {

                BigInteger left = new BigInteger("0");
                BigInteger right = new BigInteger("200");
                assertEquals("0", left.multiply(right).toString());
            }

        }

        @Nested
        class Add {
            @Test
            public void testadd_WhenPositive_1() {
                BigInteger left = new BigInteger("1111");
                BigInteger right = new BigInteger("2222");

                assertEquals("3333", left.add(right).toString());
            }

            @Test
            public void testadd_WhenPositive_2() {
                BigInteger left = new BigInteger("1111");
                BigInteger right = new BigInteger("9");

                assertEquals("1120", left.add(right).toString());
            }

            @Test
            public void testadd_WhenPositive_3() {
                BigInteger left = new BigInteger("9");
                BigInteger right = new BigInteger("1111");

                assertEquals("1120", left.add(right).toString());
            }

            @Test
            public void testadd_WhenPositive_4() {
                BigInteger left = new BigInteger("5000");
                BigInteger right = new BigInteger("5000");

                assertEquals("10000", left.add(right).toString());
            }
            @Test
            public void testadd_WhenPositive_5() {
                BigInteger left = new BigInteger("1111");
                BigInteger right = new BigInteger("0");

                assertEquals("1111", left.add(right).toString());
            }

        }

        @Nested
        class Neg {
            @Test
            public void testNeg_WhenPositive_ThenReturnNegative() {
                BigInteger val = new BigInteger("111111111111111111111111111111111111111111111111111111111111111111111111");
                assertEquals(new BigInteger("-111111111111111111111111111111111111111111111111111111111111111111111111"), val.neg());
            }

            @Test
            public void testNeg_WhenNegative_ThenReturnPositive() {
                BigInteger val = new BigInteger("-111111111111111111111111111111111111111111111111111111111111111111111111");
                assertEquals(new BigInteger("111111111111111111111111111111111111111111111111111111111111111111111111"), val.neg());

            }


        }

    }
}