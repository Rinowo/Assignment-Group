
import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class StudentList {
    ArrayList<Student> list = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public StudentList() {
        list.add(new Student(1, "Tùng Anh", 9.3));
        list.add(new Student(2, "Ngọc Anh", 4.3));
        list.add(new Student(3, "Mai Anh", 5.3));
    }

    public void addStudent(Student student) {
        this.list.add(student);
    }

    public void inputStudent() {
        System.out.print("Enter student name: "); String name = sc.nextLine();
        System.out.print("Enter student ID: "); Integer id = sc.nextInt();
        System.out.print("Enter student mark: "); Double mark = sc.nextDouble();
        Student student = new Student(id, name, mark);
        addStudent(student);
    }

    public void printStudent() {
        for (Student student :
                list) {
            System.out.println(student);
        }
    }

    public void writeFile() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Writer writer = Files.newBufferedWriter(Paths.get("StudentList.json"));
        gson.toJson(list, writer);
        writer.close();
    }

    public void readFile() throws FileNotFoundException {
        Gson gson = new Gson();
        FileReader reader = new FileReader("StudentList.json");
        list = new Gson().fromJson(reader, new TypeToken<List<Student>>(){}.getType());
    }

    public void findByName() {
        System.out.print("Enter name to find: "); String name = sc.nextLine();
        File file = new File("StudentList.json");
        Student student = new Student(name);
        Scanner scanner;
        try {
            scanner = new Scanner(file).useDelimiter(",");

            while (scanner.hasNext()) {
                final String lineFromFile = scanner.nextLine();
                if (lineFromFile.contains(name)) {
                    System.err.println("I Found " + name);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Cannot write to file: " + file.toString());
        }
    }

    public void findById() {
        System.out.print("Enter id to find: "); String id = sc.nextLine();
        File file = new File("StudentList.json");
        Student student = new Student(id);
        Scanner scanner;
        try {
            scanner = new Scanner(file).useDelimiter(",");

            while (scanner.hasNext()) {
                final String lineFromFile = scanner.nextLine();
                if (lineFromFile.contains(id)) {
                    System.err.println("I Found " + id);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Cannot write to file: " + file.toString());
        }
    }

    public void sortMark() throws FileNotFoundException {
        readFile();
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if(o1.getMark() > o2.getMark()) {
                    return -1;
                } else if (o1.getMark() < o2.getMark()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }

    public boolean removeById(Student student) throws IOException {
        readFile();
        return this.list.remove(student);
    }

    public void remove(int id) throws IOException {
        readFile();
        boolean found = false;
        for (Student student: list) {
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
