package crypto;

import javafx.util.Pair;

import java.util.Arrays;

import static utils.TablesUtils.fillRandom;
import static utils.TablesUtils.printTwoTable;

public class WinstonCryptoEncoder implements CryptoEncoder {
    Character[][] table1;
    Character[][] table2;

    public WinstonCryptoEncoder(int rows, int columns) {
        table1 = new Character[rows][columns];
        table2 = new Character[rows][columns];

        fillRandom(table1, baseAlphabet);
        fillRandom(table2, baseAlphabet);
    }

    @Override
    public void printDebugInfo() {
        printTwoTable(table1, table2);
    }


    @Override
    public String encrypt(String data) {
        return Arrays.stream(data.replaceAll("..(?!$)", "$0 ")
                .split(" "))
                .map(this::encryptBigram)
                .reduce((s, s2) -> s + s2).get();

    }


    @Override
    public String decrypt(String data) {
        String decrypted_data = Arrays.stream(data.replaceAll("..(?!$)", "$0 ")
                .split(" "))
                .map(this::decryptBigram)
                .reduce((s, s2) -> s + s2).get();


        if (decrypted_data.charAt(decrypted_data.length() - 1) == 'ъ')
            decrypted_data = decrypted_data.substring(0, decrypted_data.length() - 1);
        return decrypted_data;
    }


    public String encryptBigram(String bigram) {
        Pair<Integer, Integer> leftPosition = findChar(bigram.charAt(0), table1);
        Pair<Integer, Integer> rightPosition = findChar(bigram.length() == 2 ? bigram.charAt(1) : 'ъ', table2);


        Character leftEncryptedChar = table2[leftPosition.getKey()][rightPosition.getValue()];
        Character rightEncryptedChar = table1[rightPosition.getKey()][leftPosition.getValue()];


        String encryptedBigram = leftEncryptedChar + String.valueOf(rightEncryptedChar);
        System.out.println("Encrypted '" + bigram + "' to '" + encryptedBigram + "'");

        return encryptedBigram;
    }

    public String decryptBigram(String bigram) {
        Pair<Integer, Integer> leftPosition = findChar(bigram.charAt(0), table2);
        Pair<Integer, Integer> rightPosition = findChar(bigram.charAt(1), table1);


        Character leftDecryptedChar = table1[leftPosition.getKey()][rightPosition.getValue()];
        Character rightDecryptedChar = table2[rightPosition.getKey()][leftPosition.getValue()];

        String encryptedBigram = leftDecryptedChar + String.valueOf(rightDecryptedChar);
        System.out.println("Decrypted '" + bigram + "' to '" + encryptedBigram + "'");

        return encryptedBigram;
    }


    private Pair<Integer, Integer> findChar(Character c, Character[][] table) {
        for (int i = 0; i < table.length; i++)
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j].equals(c))
                    return new Pair<>(i, j);
            }
        throw new RuntimeException("Char " + c + " not found");
    }

}
