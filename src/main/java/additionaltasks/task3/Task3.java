package additionaltasks.task3;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {

    //Напишите функцию, которая будет из входной строки удалять парные,
    // идущие друг за другом буквы в одну и на выходе вернуть строку,
    // которая не будет иметь парных, идущих друг за другом букв.
    // Пример: aaabccddd => abd, baab => пусто
    // Пример: aaabccddd => abd, baab => пусто

    private static String solution1(String input) {

        char[] chars = input.toCharArray();

        boolean _continue = true;

        while (_continue && chars.length > 1) {
            _continue = false;
            char[] tmpResult = new char[chars.length];
            int k = 0;
            for (int i = 0; i < chars.length; i++) {
                if (i == chars.length - 1) {
                    tmpResult[k] = chars[i];
                    k++;
                    break;
                }
                if (chars[i] == chars[i + 1]) {
                    _continue = true;
                    i++;
                } else {
                    tmpResult[k] = chars[i];
                    k++;
                }

            }
            chars = Arrays.copyOf(tmpResult, k);

        }
        return String.valueOf(chars);
    }

    private static String solution2(String input) {
        Pattern pattern = Pattern.compile("([a-z])\\1");
        boolean _continue = true;

        while (_continue) {
            _continue = false;
            Matcher m = pattern.matcher(input);
            if (m.find()) {
                _continue = true;
                input = input.replaceAll("([a-z])\\1", "");
            }
        }
        return input;

    }


    public static void main(String[] args) {

        System.out.println("----------------------");
        System.out.println(solution1("a"));
        System.out.println(solution1("aabbbcc"));
        System.out.println(solution1("aabbbbcccde"));
        System.out.println(solution1("aababbcccde"));
        System.out.println(solution1("baab"));
        System.out.println("----------------------");
        System.out.println(solution2("a"));
        System.out.println(solution2("aabbbcc"));
        System.out.println(solution2("aabbbbcccde"));
        System.out.println(solution2("aababbcccde"));
        System.out.println(solution2("baab"));
        System.out.println("----------------------");



    }
}
