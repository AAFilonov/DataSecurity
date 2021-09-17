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

    public String encryptBigram(String bigram) {
        Pair<Integer, Integer> leftPosition = findChar(bigram.charAt(0), table1);

        Pair<Integer, Integer> rightPosition = findChar(bigram.length() == 2 ? bigram.charAt(1) : 'а', table2);
        Character leftEncryptedChar;
        Character rightEncryptedChar;


        if (leftPosition.getKey() <= rightPosition.getKey()) {
            leftEncryptedChar = table2[leftPosition.getKey()][rightPosition.getValue()];
            rightEncryptedChar = table1[rightPosition.getKey()][leftPosition.getValue()];
        } else {
            leftEncryptedChar = table1[rightPosition.getKey()][leftPosition.getValue()];
            rightEncryptedChar = table2[leftPosition.getKey()][rightPosition.getValue()];
        }

        String encryptedBigram = leftEncryptedChar + String.valueOf(rightEncryptedChar);
        System.out.println("Encrypted '" + bigram + "' to '" + encryptedBigram + "'");

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

    @Override
    public String decrypt(String data) {
        return null;
    }
}
