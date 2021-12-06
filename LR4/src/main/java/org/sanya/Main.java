package org.sanya;

import org.sanya.lab4.*;
import org.sanya.utils.ByteConverter;

import java.nio.charset.Charset;

/*Реализовать генератор Фибоначчи с лагами a=17, b=5  и конгруэнтный генератор с модулем 2^24.
Стартовые значения сгенерировать с помощью конгруэнтного генератора.
Для преобразования целых последовательностей чисел в вещественные необходимо использовать деление на 10^7.
Для получения гаммы шифра числа последовательности фибоначчиева датчика необходимо умножать на 10^7 и переводить в двоичную систему счисления.
Реализовать гаммирование текста, состоящего из символов 64-символьной кодовой таблицы.
*/
public class Main {
    public static void main(String[] args) {
        CongruentGenerator congruentGenerator = new CongruentGenerator(1664525, 1, (int) Math.pow(2, 24), 0);
        FibonachiGenerator fibonachiGenerator = new FibonachiGenerator(17, 5, congruentGenerator,Math.pow(10,7) );
        ByteEncoder byteEncoder = new ByteEncoder(new GammaGenerator(fibonachiGenerator));
        var flowEncoder = new FlowEncoder(byteEncoder, new ByteConverter(Charset.defaultCharset()));
        flowEncoder.runTranslation();
    }
}
/*
*
Sonnet 12: When I Do Count The Clock That Tells Time

When I do count the clock that tells the time,
And see the brave day sunk in hideous night;
When I behold the violet past prime,
And sable curls, all silvered o’er with white;
When lofty trees I see barren of leaves,
Which erst from heat did canopy the herd,
And summer’s green all girded up in sheaves,
Borne on the bier with white and bristly beard,
Then of thy beauty do I question make,
That thou among the wastes of time must go,
Since sweets and beauties do themselves forsake
And die as fast as they see others grow;
And nothing ‘gainst Time’s scythe can make defence
Save breed, to brave him when he takes thee hence.
*
* */