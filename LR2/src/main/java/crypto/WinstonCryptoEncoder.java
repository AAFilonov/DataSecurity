package crypto;

import static utils.TablesUtils.*;

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
        //printTable(table1);
        //printTable(table2);
        printTwoTable(table1,table2);
    }


    @Override
    public String encrypt(String data) {
        return null;
    }

    @Override
    public String decrypt(String data) {
        return null;
    }
}
