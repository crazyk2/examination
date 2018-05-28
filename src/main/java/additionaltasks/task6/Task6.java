package additionaltasks.task6;


import java.util.HashMap;
import java.util.Map;


public class Task6 {
    //Есть строка вида “aaabbcccd”.
    // Задача посчитать количество каждой буквы и вывести.
    // Например: “3a2b3c1d”.


    private static void solution2(String input) {
        long startTime = System.nanoTime();
        //MAX possible char
        //int[] dictionary = new int[65536];
        //'A' is 65 and 'z' is 122
        int[] dictionary = new int[123];
        char[] chars = input.toCharArray();
        for (char c : chars) {
            dictionary[c] = dictionary[c] + 1;
        }

        for (int i = 0; i < dictionary.length; i++) {
            int charCount = dictionary[i];
            if (charCount != 0) {
                System.out.print(charCount);
                System.out.print((char) i);
            }
        }
        long endTime = System.nanoTime();
        System.out.println();

        System.out.println((endTime-startTime));


    }

    private static void solution1(String input) {
        long startTime = System.nanoTime();

        Map<Character, Integer> dictonary = new HashMap();
        char[] chars = input.toCharArray();
        for (char c : chars) {
            dictonary.put(c, dictonary.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : dictonary.entrySet()) {
            System.out.print(entry.getValue());
            System.out.print(entry.getKey());
        }

        long endTime = System.nanoTime();

        System.out.println();
        System.out.println((endTime-startTime));
    }

    public static void main(String[] args) {
        solution1("AazZaabbccasdadasdasdqweqwesdkmsdklfjsiofhiuhfuiwehfiuhwiufhwifbqwifbweiufbcd");
        solution2("AazZaabbccasdadasdasdqweqwesdkmsdklfjsiofhiuhfuiwehfiuhwiufhwifbqwifbweiufbcd");
        solution1("AazZaabbccasdadasdasdqweqwesdkmsdklfjsiofhiuhfuiwehfiuhwiufhwifbqwifbweiufbcd");
        solution2("AazZaabbccasdadasdasdqweqwesdkmsdklfjsiofhiuhfuiwehfiuhwiufhwifbqwifbweiufbcd");
    }

}
