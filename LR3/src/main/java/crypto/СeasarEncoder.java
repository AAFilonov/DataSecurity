package crypto;

import utils.TablesUtils;

import java.util.List;
import java.util.Locale;

import static utils.StringUtils.removeDuplicates;
import static utils.TablesUtils.fillByAlphabet;

public class СeasarEncoder implements CryptoEncoder {

    Character[][] table;
    int key;
    String keyword;

    public СeasarEncoder(List<Character> baseAlphabet, int key, String keyword) {
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

    private  Character[] alterAlphabet( List<Character> baseAlphabet ){
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
            if(keyword.indexOf(c)==-1) {
                int index = counter % baseAlphabet.size();
                alteredAlphabet[index] = c;
                counter++;
            }
        }
        return alteredAlphabet;
    }

    @Override
    public String encrypt(String data) {
        return null;
    }

    @Override
    public String decrypt(String data) {
        return null;
    }

    @Override
    public void printDebugInfo() {
        TablesUtils.printTable(table);
    }
}
