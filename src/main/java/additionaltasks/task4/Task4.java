package additionaltasks.task4;

import java.util.ArrayList;
import java.util.Collection;

public class Task4 {
    //Даны две коллекции ObjectA (исходные/справочные данные и проверяемые данные),
    // который содержит поля: int id, String name, String value.
    //   Ваша задача проверить эквивалентность всех полей попарно
    // и в случае не совпадения вывести в отчет,
    // а в конце проверки выкинуть ошибку проверки



    private static Collection<ObjectA> generateCollection(){
        Collection<ObjectA> result  = new ArrayList<>();
        result.add(new ObjectA(1, "Stepan", "one"));
        result.add(new ObjectA(2, "Jane", "two"));
        result.add(new ObjectA(3, "Nick", "three"));
        return result;
    }

    public static void main(String[] args) {
        Collection<ObjectA> expected = generateCollection();
        Collection<ObjectA> actual = generateCollection();
        expected.add(new ObjectA(4, "Mike", "four"));
        actual.add(new ObjectA(4, "Miki", "five"));

    }

    public static void compare(Collection<ObjectA> actual, Collection<ObjectA> expected){

    }


}
