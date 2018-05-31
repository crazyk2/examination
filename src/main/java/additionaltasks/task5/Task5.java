package additionaltasks.task5;


import java.util.*;

import static additionaltasks.task5.utils.Lists.newArrayList;
import static additionaltasks.task5.utils.Validations.assertNotNull;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

public class Task5 {

    //Есть два List<String>.
    // Первый это эталонные ключи,
    // второй это ключи, которые содержатся в БД.
    // Задача:
    // а) проверить, что в БД нет лишних ключей и вывести все лишние ключи
    // б) проверить, что эталонные ключи содержатся в БД и вывести ключи, которых нет в БД


    private static final List<String> standardKeys = asList("key1", "key3", "key4", "key5", "key6", "key6", "key6");
    private static final List<String> dbKeys = asList("key88",  "key6", "key6", "key6");
    //private static final List<String> dbKeys = asList("key1", "key3", "key4", "key5", "key6", "key6", "key6");
    //private static final List<String> dbKeys = asList("key3", "key1", "key4", "key5", "key6", "key6", "key6");
    //private static final List<String> dbKeys = asList("key1", "key3", "key4", "key5", "key6", "key16");



    public static void main(String[] args) {
        assertContainsExactly(dbKeys,standardKeys);
    }


    public static void assertContainsExactly(List<String> actual, List<String> expected) {
        assertNotNull(actual);
        assertNotNull(expected);

        List<String> actualList = newArrayList(actual);

        List<String> missingActual = subtract(actualList, expected);
        List<String> missingExpected = subtract(expected, actualList);
        if (missingActual.isEmpty() && missingExpected.isEmpty()) {
            System.out.println("Эталонные ключи и ключи в БД совпадают");
            return;
        }
        if (!missingExpected.isEmpty()){
            System.out.println("В БД не хватает ключей: " + missingExpected);
        }
        if (!missingActual.isEmpty()){
            System.out.println("В БД лишние ключи: "+ missingActual);
        }
    }


    private static List<String> subtract(Iterable<String> first, Iterable<String> second) {
        List<String> missingInFirst = new ArrayList<>();
        // Создаем копию для работы с дубликатами
        List<String> copyOfSecond = newArrayList(second);
        for (String elementInFirst : first) {
            if (iterableContains(copyOfSecond, elementInFirst)) {
                //удаляем элемент после нахождения его, чтобы отследить отсутствие повторяющихся элементов
                iterablesRemoveFirst(copyOfSecond, elementInFirst);
            } else {
                missingInFirst.add(elementInFirst);
            }
        }
        return unmodifiableList(missingInFirst);
    }

    private static boolean iterableContains(Iterable<?> actual, Object value) {
        if (actual == null) {
            return false;
        }
        for (Object next : actual) {
            if (Objects.equals(next, value)) {
                return true;
            }
        }
        return false;
    }


    private static void iterablesRemoveFirst(Iterable<?> actual, Object value) {
        if (actual == null) {
            return;
        }
        Iterator<?> iterator = actual.iterator();
        while (iterator.hasNext()) {
            if (Objects.equals(iterator.next(), value)) {
                iterator.remove();
                return;
            }
        }
    }
}
