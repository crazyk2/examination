package additionaltasks.task4;

import org.assertj.core.api.SoftAssertions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class Task4 {
    //Даны две коллекции ObjectA (исходные/справочные данные и проверяемые данные),
    // который содержит поля: int id, String name, String value.
    //   Ваша задача проверить эквивалентность всех полей попарно
    // и в случае не совпадения вывести в отчет,
    // а в конце проверки выкинуть ошибку проверки


    //Делаем предположение, что количество объектов в справочных данных
    // всегда будет больше либо равно количеству проверяемых объектов
    //Можно сравнивать между собой поля элементов id которых совпадает, потому что предполагается,
    // что это равные объекты и id есть уникальный идентификатор объекта
    //Либо проверять все объекты справочные со всеми объектами проверяемыми
    // и находить минимальное расхождение и отталкиваясь от этого создавать отчет о расхождении
    //Я реализую первый вариант
    private static Collection<ObjectA> generateCollection() {
        Collection<ObjectA> result = new ArrayList<>();
        result.add(new ObjectA(1, "Stepan", "one"));
        result.add(new ObjectA(2, "Jane", "two"));
        result.add(new ObjectA(3, "Nick", "three"));
        return result;
    }

    public static void main(String[] args) {
        Collection<ObjectA> expected = generateCollection();
        Collection<ObjectA> actual = generateCollection();
        expected.add(new ObjectA(4, "Mike", "four"));
        expected.add(new ObjectA(5, "Michel", "five"));

        actual.add(new ObjectA(4, "Miki", "four"));
        actual.add(new ObjectA(5, "Michelya", "fiveee"));
        actual.add(new ObjectA(6, "Doesntmatter", "yeahh"));

        SoftAssertions soft = new SoftAssertions();
        for (ObjectA act : actual) {

            Optional<ObjectA> optExp = expected.stream().filter(exp -> exp.getId() == act.getId()).findFirst();
            if (optExp.isPresent()) {
                soft.assertThat(act).isEqualToComparingFieldByField(optExp.get());
            } else {
                soft.fail("ObjectA with id " + act.getId() + " doesn't exist in dictionary!");
            }
        }
        soft.assertAll();

    }


}
