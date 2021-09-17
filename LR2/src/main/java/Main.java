
//Зашифровать и расшифровать русскоязычное сообщение без пробелов
// и без знаков препинания (длина алфавита 32) с помощью шифра «двойной квадрат» Уитстона.
// Размер каждой таблицы 8х4. Ключом является: порядок букв, задаваемый ключевыми словами.

import crypto.CryptoEncoder;
import crypto.WinstonCryptoEncoder;

public class Main {
    public static void main(String[] args) {
      System.out.println("Hello world");
      String message = "какоетосообщениенарусскомбезпробеловизнаковпрепинания";
      int rows = 4;
      int columns = 8;

        CryptoEncoder  encoder = new WinstonCryptoEncoder(rows,columns);
        encoder.printDebugInfo();
    }

}
