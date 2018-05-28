package maintasks.task2;


import java.util.stream.IntStream;

public class Task2 {

    //2. Напишите программу, которая выводит на экран числа от 1 до 100.
    // При этом вместо чисел, кратных 3, программа должна выводить слово Fizz,
    // а вместо чисел, кратных 5 — слово Buzz. Если число кратно 15,
    // то программа должна выводить слово FizzBuzz.

    private static void solution1() {
        for (int i = 1; i <= 100; i++) {
            if (i % 15 == 0) {
                System.out.println("FizzBuzz");
                continue;
            }
            if (i % 5 == 0) {
                System.out.println("Buzz");
                continue;
            }
            if (i % 3 == 0) {
                System.out.println("Fizz");
                continue;
            }
            System.out.println(i);
        }
    }

    private static void solution2() {
        IntStream.rangeClosed(1, 100)
                .mapToObj(i -> {
                    if (i % (15) == 0) {
                        return "FizzBuzz";
                    } else if (i % 3 == 0) {
                        return "Fizz";
                    } else if (i % 5 == 0) {
                        return "Buzz";
                    } else {
                        return Integer.toString(i);
                    }
                }).forEach(System.out::println);
    }


    public static void main(String[] args) {
        solution1();
        solution2();
    }
}
