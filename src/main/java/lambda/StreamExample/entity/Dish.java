package lambda.StreamExample.entity;

public class Dish {

    public final String name;

    public final boolean vegetation;

    public final int colories;

    public final Type type;

    public Dish(String name, boolean vegetation, int colories, Type type) {
        this.name = name;
        this.vegetation = vegetation;
        this.colories = colories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetation() {
        return vegetation;
    }

    public int getColories() {
        return colories;
    }

    public Type getType() {
        return type;
    }

    public enum Type{
        MEAT,
        FISH,
        OTHER
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", vegetation=" + vegetation +
                ", colories=" + colories +
                ", type=" + type +
                '}';
    }
}
