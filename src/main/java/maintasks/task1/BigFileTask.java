package maintasks.task1;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;
import java.util.stream.IntStream;


public class BigFileTask {

    //Есть огромный файл в несколько гигабайт, в котором записаны целые числа.
    // Известно, что каждое число встречается два раза, но есть единственное число,
    // которое встречается один раз.
    //Предложите эффективный алгоритм для поиска этого числа.
    // Как изменится алгоритм, если каждое число будет встречаться в файле чётное число раз,
    // а единственное из них нечётное число раз?

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

    //Так как это синтетический тест, то я не хочу использовать свой жесткий диск
    //будем использовать память с помощью  ByteArrayOutputStream и для чтения ByteArrayInputStream
    public static File generateFile() {
        int oneGBInBytes = 128;
        //1073741824


        File file = new File(System.getProperty("user.dir") + File.separator + "test");
        FileWriter fr = null;
        BufferedWriter br = null;
        try {
            fr = new FileWriter(file);
            br = new BufferedWriter(fr);

            for (int i = 0; i < 100; i++) {
                int evenDigit = getUniqueRandomInt();
                br.write(String.valueOf(evenDigit));
                br.write(" ");
                br.write(String.valueOf(evenDigit));
                br.write(" ");
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


    //использовал бы для чтения с жесткого диска данную функцию
    public static int readFileAndFindOddNumber(String filePath) {
        int result = 0;

        try (RandomAccessFile aFile = new RandomAccessFile
                (filePath, "r");
             FileChannel inChannel = aFile.getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (inChannel.read(buffer) > 0) {
                buffer.flip();
                byte[] integer = new byte[2] ;
                for (int i = 0; i < buffer.limit(); i++) {
                    byte currentByte = buffer.get();
                    System.out.print((char) currentByte);
                    if ((char) currentByte == ' ') {
                        ByteBuffer buf2 = ByteBuffer.wrap(integer);
                        System.out.println(buf2.getInt());
                        result ^= buf2.getInt();
                        continue;
                    }
                    integer[i] = currentByte;
                }
                buffer.clear(); // do something with the data and clear/compact it.
            }

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }


        return result;

    }

    public static int convertirOctetEnEntier(byte[] b) {
        int result = 0;
        result = b[0] & MASK;
//        result = result + ((b[1] & MASK) << 8);
//        result = result + ((b[2] & MASK) << 16);
//        result = result + ((b[3] & MASK) << 24);
        return result;
    }

    public static void main(String[] args) {
        //generateFile();
        System.out.println(readFileAndFindOddNumber(generateFile().getPath()));
    }

}
