package additionaltasks.task4;

public class ObjectA {

    private int id;
    private String name;
    private String value;

    public ObjectA(int id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectA objectA = (ObjectA) o;

        if (id != objectA.id) return false;
        if (!name.equals(objectA.name)) return false;
        return value.equals(objectA.value);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}
