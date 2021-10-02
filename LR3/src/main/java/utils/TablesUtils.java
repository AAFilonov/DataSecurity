package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TablesUtils {
    public static Character[][] initTable(Character[][] table, int rows, int columns) {
        table = new Character[rows][columns];


        return table;
    }


    public static void printTable(Character[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.printf("[%c], ", table[i][j]);
            }
            System.out.println('\n');
        }
        System.out.println('\n');
    }


    public static void printTwoTable(Character[][] table1, Character[][] table2) {
        for (int i = 0; i < table1.length; i++) {
            for (int j = 0; j < table1[i].length; j++) {
                System.out.printf("[%c], ", table1[i][j]);
            }
            System.out.printf("   ");
            for (int t = 0; t < table2[i].length; t++) {
                System.out.printf("[%c], ", table2[i][t]);
            }

            System.out.println('\n');
        }
        System.out.println('\n');
    }


    public static void fillRandom(Character[][] table, List<Character> baseAlphabet) {
        ArrayList<Character> shuffledAlphabet = new ArrayList<>(baseAlphabet);
        Collections.shuffle(shuffledAlphabet);

        for (int i = 0; i < table.length; i++)
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = shuffledAlphabet.get(table[i].length * i + j);
            }

    }

    public static void fillByAlphabet(Character[] row, Character[] alphabet) {
        for (int i = 0; i < alphabet.length; i++)
            row[i] = alphabet[i];
    }

    public static void fillByAlphabet(Character[] row, List<Character> alphabet) {
        for (int i = 0; i < alphabet.size(); i++)
            row[i] = alphabet.get(i);
    }


}
