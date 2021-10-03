package encryption;

import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FrequencyDecoder implements CryptoDecoder {

    HashMap<Character, Double> baseAlphabet_frequencies = new HashMap<>();
    CryptoEncoder encoder;

    List<Character> baseAlphabet;

    List<String> keywords = new ArrayList( List.of("Salad, potato, shirt"));
    List<Integer> keys = List.of(6);

    public FrequencyDecoder(List<Character> baseAlphabet, String pathToAlbphabetExample) throws IOException {

        this.baseAlphabet = baseAlphabet;
        Path path = Paths.get(pathToAlbphabetExample);

        baseAlphabet_frequencies = Files.lines(path)
                .map(s -> countLetters(s, baseAlphabet))
                .reduce((map1, map2) ->
                {
                    map2.forEach((key, value) -> map1.merge(key, value, Double::sum));
                    return map1;
                }).get();
        normalize(baseAlphabet_frequencies);

    }


    public void doHack(String data) {
        data = data.toLowerCase(Locale.ROOT);

        Pair<String, Double> bestKeyword = new Pair<>(keywords.get(0), Double.MAX_VALUE);
        for (String keyword : keywords) {
            double difference = checkKey(data, keyword, keys.get(0));
            if (bestKeyword.getValue().compareTo(difference) < 0)
                bestKeyword = new Pair<>(keyword, difference);
        }
        String.format("%sРазница составляет %s", System.out.printf("Лучшее совпадение по ключевому слову - %s%n", bestKeyword.getKey()), bestKeyword.getValue());
    }


    public double checkKey(String encrypted_data, String keyword, int key) {
        CryptoEncoder encoder = new СeasarEncoder(baseAlphabet, key, keyword);
        var probably_decoded_data = encoder.decrypt(encrypted_data);
        var text_frequencies = countFrequencies(probably_decoded_data);

        return countDeffenece(text_frequencies);
    }

    private double countDeffenece(HashMap<Character, Double> text_frequencies) {
        var difference = 0.0;
        for (Map.Entry<Character, Double> entry : text_frequencies.entrySet()) {
            difference += (entry.getValue() + baseAlphabet_frequencies.get(entry.getKey()))
                    * (entry.getValue() + baseAlphabet_frequencies.get(entry.getKey()));
        }
        return difference;
    }

    public HashMap<Character, Double> countFrequencies(String data) {

        var letter_encounters = countLetters(data, baseAlphabet);
        return normalize(letter_encounters);

    }


    public static HashMap<Character, Double> countLetters(String data, List<Character> alphabet) {
        HashMap<Character, Double> letter_encounters = new HashMap<>();

        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if (alphabet.contains(c)) {
                letter_encounters.merge(c, 1.0, Double::sum);
            }
        }
        return letter_encounters;
    }

    private static HashMap<Character, Double> normalize(HashMap<Character, Double> data_frequencies) {
        double total_count = 0.0f;
        for (double f : data_frequencies.values()) {
            total_count += f;
        }
        double finalTotal_count = total_count;
        data_frequencies.replaceAll((character, value) -> value = value / finalTotal_count);
        return data_frequencies;
    }


}



