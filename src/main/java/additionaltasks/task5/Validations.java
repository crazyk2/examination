package additionaltasks.task5;



public class Validations {

    public static void assertNotNull(Object actual){
        if (actual != null){
            return;
        }
        throw new IllegalArgumentException("Object not to be null!");

    }
}
