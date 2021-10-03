package encryption;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.StringUtils;

class СeasarEncoderTest {

    private static СeasarEncoder encoder;

    @BeforeAll
    public static void setUp() {
        encoder = new СeasarEncoder(CryptoEncoder.baseAlphabetEN, 6, "Salad");
    }

    @Test
    public void testInit() {
        var encoder = new СeasarEncoder(CryptoEncoder.baseAlphabetEN, 6, "Salad");

    }
    @Test
    public void testEncrypt() {
        var data = "abc";
        var encodedData =  encoder.encrypt(data);
        Assertions.assertEquals("xyz",encodedData);
    }

    @Test
    public void testDecrypt() {
        var data = "abc";
        var encodedData =  encoder.encrypt(data);
        var decodedData =  encoder.decrypt(encodedData);

        Assertions.assertEquals("abc",decodedData);
    }

    @Test
    public void testCastToAlphabet() {
        var data = "a`bc&336";
        var decodedData = StringUtils.castToAlphabet(data,CryptoEncoder.baseAlphabetEN);

        Assertions.assertEquals("abc",decodedData);
    }


}