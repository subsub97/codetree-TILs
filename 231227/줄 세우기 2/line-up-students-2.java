import java.util.*;
import java.io.*;

class Student {
    int id;
    int height;
    int weight;

    Student(int id, int height, int weight) {
        this.id = id;
        this.height = height;
        this.weight = weight;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        Student[] students = new Student[n];

        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            students[i-1] = new Student(i,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(students,new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                if(s1.height != s2.height) return s1.height - s2.height;
                return s2.weight - s1.weight; 
            }
        });

        for(Student s: students) {
            System.out.println(s.height + " "+ s.weight + " " + s.id);
        }
        
    }
}