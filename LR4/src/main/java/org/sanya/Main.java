package org.sanya;

import org.sanya.lab4.CongruentGenerator;
import org.sanya.lab4.FibonachiGenerator;

/*Реализовать генератор Фибоначчи с лагами a=17, b=5  и конгруэнтный генератор с модулем 224.
Стартовые значения сгенерировать с помощью конгруэнтного генератора.
Для преобразования целых последовательностей чисел в вещественные необходимо использовать деление на 107.
Для получения гаммы шифра числа последовательности фибоначчиева датчика необходимо умножать на 107 и переводить в двоичную систему счисления.
Реализовать гаммирование текста, состоящего из символов 64-символьной кодовой таблицы.
*/
public class Main {
    public static void main(String[] args) {

        CongruentGenerator congruentGenerator = new CongruentGenerator(1664525, 1, (int) Math.pow(2, 24), 0);
        FibonachiGenerator fibonachiGenerator = new FibonachiGenerator(17, 5, congruentGenerator);
    }
}
