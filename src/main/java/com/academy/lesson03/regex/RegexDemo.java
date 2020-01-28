package com.academy.lesson03.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {
    public static void main(String[] args) {
        String str = "123name;lastname:status";
        System.out.println(str.matches("[\\d]{4}name"));
        System.out.println(str.matches("[\\d]{3}name"));

        String[] array = str.split("[:;]");
        System.out.println(Arrays.toString(array));

        greedySample();
        lazySample();
        possessiveSample();
    }

    // Пример жадного квантификатора
    // Жадный квантификатор пытается захватить максимальный диапазон
    public static void greedySample() {
        System.out.println("---greedySample---");
        String str = "1Ashot2Peter3Helen4Nikolas5";

        // Ищем вхождение, которое
        //      - начинается с цифры
        //      - далее идет любое кол-во (в т.ч. и ноль) произвольных символов
        //      - заканчивается цифрой
        String regex = "\\d.*\\d";

        // Результатом будет вся подстрока - одно вхождение
        // т.е. регулярное выражение захватило максимально возможный диапазон - жадно
        // По-умолчанию регулярки работают в жадном режиме
        //      => 1Ashot2Peter3Helen4Nikolas5
        printAllOccurrences(regex, str);
    }

    // Пример ленивого квантификатора
    // Ленивый квантификатор пытается захватить минимальный диапазон
    public static void lazySample() {
        System.out.println("---lazySample---");
        String regex = "\\d.*?\\d"; // Знак вопроса (?) делает квантификатор * ленивым
        String str = "1Ashot2Peter3Helen4Nikolas5";

        // Результатом будет два вхождения:
        //      => 1Ashot2
        //      => 3Helen4
        // В этом случае найден первый наиболее короткий диапазон  1Ashot2 (лениво)
        // далее поиск продолжается с этого место для поиска следующего совпадения  3Helen4
        printAllOccurrences(regex, str);
    }

    // Пример ревнивого квантификатора
    // Ревнивый захватывает максимальный диапазон и не откатывает размер захваченного
    // диапазона назад, если вхождение не найдено
    public static void possessiveSample() {
        System.out.println("---possessiveSample---");

        // Найти подстроку, кот.
        //      - начинается с цифры
        //      - далее любой символ в кол-ве от одного до трех
        //      - заканчивается цифрой
        String regex1 = "\\d.{1,3}+\\d"; // Знак плюсик (+) делает квантификатор {1,3} ревнивым
        String regex2 = "\\d.{1,4}+\\d";
        String str = "1abc2";

        System.out.println("by regex1 result: ");
        // При поиске считаем символы ревниво - первая цифра (1)
        // далее от отдно до трех. Но так как ревнивый - то забирает все три (abc)
        // далее цифра (2)
        printAllOccurrences(regex1, str); // => 1abc2

        System.out.println("by regex2 result: ");
        // При поиске считаем символы ревниво - первая цифра (1)
        // далее от отдно до ЧЕТЫРЕХ. Но так как ревнивый - то забирает все ЧЕТЫРЕ (abc2)
        // далее должна быть цифра.
        // Квантификатор от одного до..., что значит можно было бы найти вхождение для трех символов.
        // Но квантификатор ревнивый - означет, что захватил по максимуму - 4 и не отпускает назад
        printAllOccurrences(regex2, str); // => ничего не найдено
    }

    // Метод выводит все вхождения в строке str по регулярному выражению regex
    private static void printAllOccurrences(String regex, String str) {
        // Компилируем регулярное выражение regex
        Pattern pattern = Pattern.compile(regex);

        // Применяем регулярное выражение к строке str
        Matcher matcher = pattern.matcher(str);

        // Пока найдено очередное вхождение
        while (matcher.find()) {
            // Выводим найденное вхождение (группа - под номером 0 по умолчанию)
            System.out.println(matcher.group());
        }
    }

}
