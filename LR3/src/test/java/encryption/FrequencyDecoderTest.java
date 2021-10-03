package encryption;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class FrequencyDecoderTest {
    private static СeasarEncoder encoder;
    private static FrequencyDecoder decoder;

    @BeforeAll
    public static void setUp() throws IOException {
        encoder = new СeasarEncoder(CryptoEncoder.baseAlphabetEN, 6, "Salad");
        decoder = new FrequencyDecoder(CryptoEncoder.baseAlphabetEN, "src/main/resources/Hamlet.txt");
    }

    @Test
    public void testInit() throws IOException {
        var encoder = new СeasarEncoder(CryptoEncoder.baseAlphabetEN, 6, "Salad");
        var decoder = new FrequencyDecoder(CryptoEncoder.baseAlphabetEN, "src/main/resources/Hamlet.txt");
        decoder.baseAlphabet_frequencies.forEach(((character, aDouble) -> {
            System.out.printf("[%c,%f]\n", character, aDouble);
        }));
    }


    @Test
    public void testcountFrequenciess() {
        var data = "ab";
        var frequencyes = decoder.countFrequencies(data);
        frequencyes.forEach(((character, aDouble) -> {
            System.out.printf("[%c,%f]\n", character, aDouble);
        }
        ));
    }


    @Test
    public void testDoHack() {
        var data = "\n" +
                "Mar.\n" +
                "Horatio says 'tis but our fantasy,\n" +
                "And will not let belief take hold of him\n" +
                "Touching this dreaded sight, twice seen of us:\n" +
                "Therefore I have entreated him along\n" +
                "With us to watch the minutes of this night;\n" +
                "That, if again this apparition come\n" +
                "He may approve our eyes and speak to it.\n" +
                "\n" +
                "Hor.";

        var encrypted_data = encoder.encrypt(data);

        decoder.doHack(encrypted_data);
    }

}