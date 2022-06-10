package part2File.Exercise.Exercise2;

public class Student {
    String id;
    String name;
    double mark;

    public Student(String id, String name, double mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
    }

    public Student(String name) {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mark=" + mark +
                '}';
    }
}
