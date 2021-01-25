import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int recommend = Integer.parseInt(st.nextToken());
        List<Student> students = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < recommend; i++) {
            int studentNum = Integer.parseInt(st.nextToken());
            boolean exist = false;
            for (Student student : students) {
                if (student.getStudentNum() == studentNum) {
                    student.addRecommend();
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                if (students.size() >= N) {
                    int minRecommend = 999999;
                    Student minStudent = null;
                    for (Student student : students) {
                        if (student.getRecommend() < minRecommend) {
                            minRecommend = student.getRecommend();
                            minStudent = student;
                        }
                    }
                    students.remove(minStudent);
                }
                students.add(new Student(studentNum, 1));
            }
        }

        Collections.sort(students);

        for (Student student : students) {
            System.out.print(student.getStudentNum() + " ");
        }
    }

    public static class Student implements Comparable<Student> {
        private final int studentNum;
        private int recommend;

        public Student(int studentNum, int recommend) {
            this.studentNum = studentNum;
            this.recommend = recommend;
        }

        public int getStudentNum() {
            return studentNum;
        }

        public int getRecommend() {
            return recommend;
        }

        public void addRecommend() {
            this.recommend++;
        }

        @Override
        public int compareTo(Student student) {
            return this.getStudentNum() - student.getStudentNum();
        }
    }
}