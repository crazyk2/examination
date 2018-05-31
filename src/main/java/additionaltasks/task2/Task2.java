package additionaltasks.task2;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task2 {
    //Есть файл формата txt в котором две строки => первая строка колонки,
    // вторая значения. Задача – на вход функции подается название колонки и новое значение,
    // напишите функцию, которая будет менять у указанной колонки значение на новое.


    //Предположим, что формат файла будет таким
    // Название_колонки1<пробел>Название_колонки2....
    // Значение_колонки1<пробел>Значение_колонки2....
    // примем за условие, что колонки без значения не может быть,
    //то есть каждой колонке соответствует не пустое значение

    public static void main(String[] args) {
        solution1("Column1", "aaa");
        solution2("Column3", "eeeeee");
    }


    private static void solution1(String columnName, String newValue) {
        String path = System.getProperty("user.dir") + File.separator + "Task2.txt";
        try {
            RandomAccessFile file = new RandomAccessFile(path, "rw");
            String columnLine = file.readLine();
            if (!columnLine.contains(columnName)) {
                throw new IllegalStateException("Column '" + columnName + "' doesn't exist!");
            }
            String[] columns = columnLine.split(" ");
            int orderPosition = 0;
            for (int i = 0; i < columns.length; i++) {
                if (columns[i].equals(columnName)) {
                    orderPosition = i;
                    break;
                }
            }

            String valueLine = file.readLine();
            String[] values = valueLine.split(" ");
            int startPosition = 0;
            for (int i = 0; i < orderPosition; i++) {
                startPosition = startPosition + values[i].length() + 1;
            }
            String toWrite = newValue + " " + Stream.of(values).skip(orderPosition + 1).collect(Collectors.joining(" "));
            file.setLength(columnLine.length() + startPosition + 1 + toWrite.length());
            file.seek(columnLine.length() + startPosition + 1);
            file.write(toWrite.getBytes());

            file.close();
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Can't find file " + path, e);

        } catch (IOException e) {
            throw new IllegalStateException("Can't work with file " + path, e);

        }
    }

    public static void solution2(String columnName, String newValue) {
        Path path = Paths.get(System.getProperty("user.dir") + File.separator + "Task2.txt");

        try {

            List<String> lines = Files.readAllLines(path);
            int indexOfColumn = Arrays.asList(lines.get(0).split(" ")).indexOf(columnName);
            if (indexOfColumn == -1) throw new IllegalArgumentException("Column " + columnName + " doesn't exist!");

            String[] values = lines.get(1).split(" ");
            values[indexOfColumn] = newValue;
            lines.remove(1);
            lines.add(String.join(" ", values));

            Files.write(path, lines);

            System.out.println("Find and Replace done!!!");
        } catch (IOException e) {
            throw new IllegalStateException("Can't work with file " + path, e);
        }


    }


}
