
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {

    public static void menu() {
        System.out.println("1. Add a list of Students and save to File");
        System.out.println("2. Loading list of Students from a File");
        System.out.println("3. Search by name");
        System.out.println("4. Search by id");
        System.out.println("5. Print student into in descending order of mark");
        System.out.println("6. Remove by id");
        System.out.println("0. Exit");
        System.out.print("Your choice:  ");
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        StudentList studentList = new StudentList();

        while (true) {
            menu();
            int choose = sc.nextInt();
            switch (choose) {
                case 1 -> {
                    studentList.inputStudent();
                    studentList.writeFile();
                }
                case 2 -> {
                    studentList.readFile();
                    studentList.printStudent();
                }
                case 3 -> studentList.findByName();
                case 4 -> studentList.findById();
                case 5 -> {
                    studentList.sortMark();
                    studentList.printStudent();
                }
                case 6 -> {
                    System.out.println("Enter id to remove: "); int id = sc.nextInt();
                    Student student = new Student(id);
                    studentList.remove(id);
                    //studentList.removeById(student);
                    System.out.println();
                }
                case 0 -> System.exit(0);
            }
        }
    }
}
