package by.shumilov.bean;

public class Position extends BaseEntity {
    private String name;

    public Position() {
    }

    public Position(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Position{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
