package encryption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface CryptoEncoder {

    final List<Character> baseAlphabetRU = new ArrayList<>(Arrays.asList(
            'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о',
            'п', 'р', 'с', 'т', 'у', 'ф',
            'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы',
            'ь', 'э', 'ю', 'я'
    ));

    final List<Character> baseAlphabetEN = new ArrayList<>(Arrays.asList(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            ' ', ',', '.'
    ));


    public String encrypt(String data);

    public String decrypt(String data);

    public void printDebugInfo();
}
