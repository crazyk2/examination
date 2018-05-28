package additionaltasks.task3;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {

    //Напишите функцию, которая будет из входной строки удалять парные,
    // идущие друг за другом буквы в одну и на выходе вернуть строку,
    // которая не будет иметь парных, идущих друг за другом букв.
    // Пример: aaabccddd => abd, baab => пусто

    private static void solution1(String input) {

        char[] chars = input.toCharArray();

        boolean _continue = true;

        while (_continue) {
            _continue = false;
            char[] tmpResult = new char[chars.length];
            int k = 0;
            for (int i = 0; i < chars.length; i++) {
                tmpResult[k] = chars[i];
                k++;
                if (i == chars.length - 1) break;
                if (chars[i] == chars[i + 1]) {
                    _continue = true;
                    i++;
                }

            }
            chars = Arrays.copyOf(tmpResult, k);

        }
        System.out.println(String.valueOf(chars));
    }

    private static void solution2(String input) {
        Pattern pattern = Pattern.compile("([a-z])\\1");
        boolean _continue = true;

        while (_continue) {
            _continue = false;
            Matcher m = pattern.matcher(input);
            while (m.find()) {
                _continue = true;
                String replaceChar = m.group().substring(0, 1);
                input = input.replaceFirst("("+replaceChar+")\\1", replaceChar);
            }
        }
        System.out.println(input);


    }

    public static void main(String[] args) {
        solution1("aabbbcc");
        solution1("aabbbbcccde");
        solution1("aababbcccde");

        solution2("aabbbcc");
        solution2("aabbbbcccde");
        solution2("aababbcccde");
    }
}
