package maintasks.task1;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;


public class Task1 {

    //Есть огромный файл в несколько гигабайт, в котором записаны целые числа.
    // Известно, что каждое число встречается два раза, но есть единственное число,
    // которое встречается один раз.
    //Предложите эффективный алгоритм для поиска этого числа.
    // Как изменится алгоритм, если каждое число будет встречаться в файле чётное число раз,
    // а единственное из них нечётное число раз?


    //Ответ: никак не изменится, все четные числа при XOR сложении дают 0,
    // а нечетное, естесвенно, выделится в итоге
    private static int COUNT_OF_UNIQUE_NUMBERS = 128;
    private static final int[] ARRAY = IntStream.range(0, COUNT_OF_UNIQUE_NUMBERS).toArray();
    private static final Random random = new Random();
    public final static int MASK = 0xff;


    /**
     * При каждом вызове функция возвращает УНИКАЛЬНОЕ псевдослучайное целое число
     * при этом количество уникальных чисел (соотвественно, и количество вызовов функции)
     * регулируется переменной COUNT_OF_UNIQUE_NUMBERS
     */
    public static int getUniqueRandomInt() {
        int randomNumber = random.nextInt(COUNT_OF_UNIQUE_NUMBERS);
        int result = ARRAY[randomNumber];
        ARRAY[randomNumber] = ARRAY[COUNT_OF_UNIQUE_NUMBERS - 1];
        COUNT_OF_UNIQUE_NUMBERS = COUNT_OF_UNIQUE_NUMBERS - 1;
        return result;
    }


    public static File generateFile() {

        File file = new File(System.getProperty("user.dir") + File.separator + "test");
        FileWriter fr = null;
        BufferedWriter br = null;
        try {
            fr = new FileWriter(file);
            br = new BufferedWriter(fr);

            for (int i = 0; i < 100; i++) {
                int evenDigit = getUniqueRandomInt();
                br.write(evenDigit + " " + evenDigit + " ");

            }
            br.write(String.valueOf(getUniqueRandomInt()));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }


    public static int readFileAndFindOddNumber(File file) {
        int result = 0;

        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                result ^= input.nextInt();
            }
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("File " + file.getPath() + " wasn't found!");
        }

        return result;
    }


    public static void main(String[] args) {
        System.out.println(readFileAndFindOddNumber(generateFile()));
    }

}
