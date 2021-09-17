package crypto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface CryptoEncoder {

    final List<Character> baseAlphabet = new ArrayList<>(Arrays.asList(
            'а', 'б', 'в', 'г', 'д', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о',
            'п', 'р', 'с', 'т', 'm', 'у', 'ф',
            'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы',
            'ь', 'э', 'ю', 'я'
    ));


    public String encrypt(String data);

    public String decrypt(String data);

    public void printDebugInfo();
}
