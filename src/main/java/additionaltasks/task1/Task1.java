package additionaltasks.task1;


import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;


public class Task1 {

    //Написать функцию, которая будет возвращать цифру от 0 до 9,
    // которая будет уникальна для любого треда, обратившегося к этой функции в единицу времени.
    // После выполнения работы тред должен вернуть в массив цифр свою цифру.
    //Пример: число тредов <= 10 => каждый тред получит любую свободную цифру из массива [0-9].
    // При количестве тредов > 10 => первые 10 получат любую свободную цифру из массива [0-9],
    // остальные N - 10 тредов должны получить первую свободную цифру

    private static final int MAX_THREAD = 15;
    //private static final int MAX_THREAD = 10;

    private static final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10, false,
            Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));

    private static final Random random = new Random();

    public static void main(String[] args) {
        ExecutorService executors = Executors.newFixedThreadPool(MAX_THREAD);

        for (int i = 0; i < MAX_THREAD; i++) {
            executors.execute(() -> {
                int number = take();
                System.out.println("Thread - " + Thread.currentThread().getName() + ". Number - " + number);
                sleep(random.nextInt(5000));
                put(number);
                System.out.println("Thread - " + Thread.currentThread().getName() + ".DONE");

            });

        }
        try {
            executors.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {

        }
        executors.shutdown();

        System.out.println("DONE!");
    }

    private static void put(int number) {
        try {
            System.out.println("Thread - " + Thread.currentThread().getName() + " try to return number - " + number);
            queue.put(number);

        } catch (InterruptedException e) {
            throw new RuntimeException("Не могу вернуть свой порядковый номер в очередь! Thread -" + Thread.currentThread().getName());
        }
    }

    private static Integer take() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException("Не могу получить свой порядковый номер из очереди! Thread -" + Thread.currentThread().getName());
        }

    }

    private static void sleep(long milliseconds) {
        try {
            Thread.sleep(random.nextInt(5000));
        } catch (InterruptedException e) {
            throw new RuntimeException("Что-то мне не спится! Выйду в окно. Thread -" + Thread.currentThread().getName());
        }
    }
}
