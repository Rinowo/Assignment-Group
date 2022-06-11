public class Student {
    int id;
    String name;
    double mark;

    public Student(int id, String name, double mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
    }

    public Student(int id) {
        this.id = id;
    }

    public Student(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public double getMark() {
        return this.mark;
    }

    public String toString() {
        return "Student{id=" + this.id + ", name='" + this.name + "', mark=" + this.mark + "}";
    }
}
