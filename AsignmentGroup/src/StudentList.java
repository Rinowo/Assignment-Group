import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class StudentList {
    ArrayList<Student> list = new ArrayList();
    Scanner sc;

    public StudentList() {
        this.sc = new Scanner(System.in);
        this.list.add(new Student(1, "Tùng Anh", 9.3));
        this.list.add(new Student(2, "Ngọc Anh", 4.3));
        this.list.add(new Student(3, "Mai Anh", 5.3));
    }

    public void addStudent(Student student) {
        this.list.add(student);
    }

    public void inputStudent() {
        System.out.print("Enter student name: ");
        String name = this.sc.nextLine();
        System.out.print("Enter student ID: ");
        Integer id = this.sc.nextInt();
        System.out.print("Enter student mark: ");
        Double mark = this.sc.nextDouble();
        Student student = new Student(id, name, mark);
        this.addStudent(student);
    }

    public void printStudent() {
        Iterator var1 = this.list.iterator();

        while(var1.hasNext()) {
            Student student = (Student)var1.next();
            System.out.println(student);
        }

    }

    public void writeFile() throws IOException {
        Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
        Writer writer = Files.newBufferedWriter(Paths.get("StudentList.json"));
        gson.toJson(this.list, writer);
        writer.close();
    }

    public void readFile() throws IOException {
        new Gson();
        FileReader reader = new FileReader("StudentList.json");
        this.list = (ArrayList)(new Gson()).fromJson(reader, (new TypeToken<List<Student>>() {
        }).getType());
        reader.close();
    }

    public void findByName() {
        System.out.print("Enter name to find: ");
        String name = this.sc.nextLine();
        File file = new File("StudentList.json");
        new Student(name);

        try {
            Scanner scanner = (new Scanner(file)).useDelimiter(",");

            while(scanner.hasNext()) {
                String lineFromFile = scanner.nextLine();
                if (lineFromFile.contains(name)) {
                    System.err.println("I Found " + name);
                    break;
                }
            }
        } catch (IOException var6) {
            System.out.println("Cannot write to file: " + file.toString());
        }

    }

    public void findById() {
        System.out.print("Enter id to find: ");
        String id = this.sc.nextLine();
        File file = new File("StudentList.json");
        new Student(id);

        try {
            Scanner scanner = (new Scanner(file)).useDelimiter(",");

            while(scanner.hasNext()) {
                String lineFromFile = scanner.nextLine();
                if (lineFromFile.contains(id)) {
                    System.err.println("I Found " + id);
                    break;
                }
            }
        } catch (IOException var6) {
            System.out.println("Cannot write to file: " + file.toString());
        }

    }

    public void sortMark() throws IOException {
        this.readFile();
        Collections.sort(this.list, new Comparator<Student>() {
            public int compare(Student o1, Student o2) {
                if (o1.getMark() > o2.getMark()) {
                    return -1;
                } else {
                    return o1.getMark() < o2.getMark() ? 1 : 0;
                }
            }
        });
    }


    public void removeById(int id) throws IOException {
        readFile();
        boolean found = false;
        for (Student student
                : list) {
            if (student.getId() == id) {
                int choice;
                System.out.println("Are you sure about deleting this student? (1.Yes 2.No)");
                choice = new Scanner(System.in).nextInt();
                if (choice == 1) {
                    list.remove(student);
                    writeFile();
                }
                found = true;
            }
        }
        if (found == false) {
            System.out.println("Cannot find student with id " + id);
        }
    }
}
