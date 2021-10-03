package encryption;

import utils.StringUtils;
import utils.TablesUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static utils.StringUtils.removeDuplicates;
import static utils.TablesUtils.fillByAlphabet;

public class СeasarEncoder implements CryptoEncoder {

    Character[][] table;
    int key;
    String keyword;
    List<Character> baseAlphabet;

    public СeasarEncoder(List<Character> baseAlphabet, int key, String keyword) {
        this.baseAlphabet = baseAlphabet;
        table = new Character[2][baseAlphabet.size()];
        this.key = key;
        this.keyword = removeDuplicates(keyword.toLowerCase(Locale.ROOT));
        init(baseAlphabet);
    }


    public void init(List<Character> baseAlphabet) {
        fillByAlphabet(table[0], baseAlphabet);
        fillByAlphabet(table[1], alterAlphabet(baseAlphabet));
        printDebugInfo();
    }

    private Character[] alterAlphabet(List<Character> baseAlphabet) {
        Character[] alteredAlphabet = new Character[baseAlphabet.size()];
        //insert keyword
        int counter = key;
        for (char c : keyword.toCharArray()) {
            int index = counter % baseAlphabet.size();
            alteredAlphabet[index] = c;
            counter++;
        }
        //insert other chars of alphabet
        for (char c : baseAlphabet) {
            if (keyword.indexOf(c) == -1) {
                int index = counter % baseAlphabet.size();
                alteredAlphabet[index] = c;
                counter++;
            }
        }
        return alteredAlphabet;
    }

    @Override
    public String encrypt(String data) {
        data = data.replaceAll("\n", " ");
       // data = StringUtils.castToAlphabet(data.toLowerCase(), baseAlphabet);

        return Arrays.stream(data.replaceAll(".(?!$)", "$0 ")
                .split(" "))
                .map(this::encryptChar)
                .reduce((s, s2) -> s + s2).get();
    }


    @Override
    public String decrypt(String data) {
        return Arrays.stream(data.replaceAll(".(?!$)", "$0 ")
                .split(" "))
                .map(this::decryptChar)
                .reduce((s, s2) -> s + s2).get();
    }

    private String encryptChar(String s) {
        for (int i = 0; i < table[0].length; i++) {

            if (!s.equals("") &&table[0][i] == s.charAt(0)) {
                System.out.printf("Ceasar encrypted char [%c] to [%c] \n", s.charAt(0), table[1][i]);
                return String.valueOf(table[1][i]);
            }
        }

      return "";
    }

    private String decryptChar(String s) {
        for (int i = 0; i < table[0].length; i++) {
            if (!s.equals("") &&table[1][i] == s.charAt(0)) {
                System.out.printf("Ceasar decrypted char [%c] to [%c] \n", s.charAt(0), table[0][i]);
                return String.valueOf(table[0][i]);
            }
        }
      //  throw new RuntimeException("Char " + s.charAt(0) + " not found");
        return "";
    }


    @Override
    public void printDebugInfo() {
        TablesUtils.printTable(table);
    }
}
