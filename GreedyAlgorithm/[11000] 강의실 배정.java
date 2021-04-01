import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Course[] courses = new Course[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            courses[i] = new Course(s, t);
        }
        // 일찍 시작 & 일찍 끝나는 순서대로 정렬
        Arrays.sort(courses);
        // 끝나는 시간을 담아둠
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(courses[0].t);
        for (int i = 1; i < N; i++) {
            Course course = courses[i];
            // peek()을 하면 가장 일찍 끝나는 강의실의 종료 시간이 나옴
            if (course.s >= queue.peek()) queue.poll();
            queue.add(course.t);
        }

        System.out.println(queue.size());
    }

    static class Course implements Comparable<Course> {
        int s, t;

        public Course(int s, int t) {
            this.s = s;
            this.t = t;
        }

        @Override
        public int compareTo(Course course) {
            if (s == course.s) return t - course.t;
            return s - course.s;
        }
    }
}