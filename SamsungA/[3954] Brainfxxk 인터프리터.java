import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

    static int sm, sc, si;
    static int[] memory, parentheses;
    static String program, input;
    static int loopStart;
    static Stack<Integer> stack = new Stack<>();
    static boolean infinite;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sm = Integer.parseInt(st.nextToken());
            sc = Integer.parseInt(st.nextToken());
            si = Integer.parseInt(st.nextToken());
            program = br.readLine();
            input = br.readLine();

            memory = new int[sm];
            parentheses = new int[sc];
            loopStart = Integer.MAX_VALUE;
            infinite = false;

            // parentheses[a] = b 라는 것은
            // a 인덱스에 [ 또는 ]가 있고 이에 짝 괄호가 b 인덱스에 있다는 뜻
            for (int j = 0; j < sc; j++) {
                char c = program.charAt(j);
                if (c == '[') stack.add(j);
                else if (c == ']') {
                    parentheses[j] = stack.peek();
                    parentheses[stack.pop()] = j;
                }
            }

            simulate();

            if (infinite) sb.append("Loops ").append(loopStart).append(" ").append(parentheses[loopStart]);
            else sb.append("Terminates");
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void simulate() {
        int memoryPtr = 0;
        int programPtr = 0;
        int inputPtr = 0;
        int command = 0;

        char c;
        while (programPtr < sc) {
            command++;
            c = program.charAt(programPtr);

            // 5천만 번 돌면 무한루프가 진행중인데 [ [ ] ] 처럼 안쪽이 무한루프인지 바깥쪽이 무한루프인지 모름
            // 한 번의 무한 루프에서 실행되는 명령어의 개수는 50,000,000개 이하라는 조건을 이용해서
            // 5천만 번을 다시 시도하면서 가장 왼쪽 programPtr을 찾음 -> 그 부분이 무한루프 시작하는 부분
            if (command > 50000000) loopStart = Math.min(loopStart, programPtr);
            // 추가로 실시한 5천만 번이 끝나면 무한루프 시작 부분을 괄호가 있는 부분으로 조절
            if (command > 100000000) {
                loopStart--;
                infinite = true;
                break;
            }

            switch (c) {
                case '-': memory[memoryPtr] = (memory[memoryPtr] + 256 - 1) % 256; break;
                case '+': memory[memoryPtr] = (memory[memoryPtr] + 1) % 256; break;
                case '<': memoryPtr = (memoryPtr + sm - 1) % sm; break;
                case '>': memoryPtr = (memoryPtr + 1) % sm; break;
                case '[': if (memory[memoryPtr] == 0) programPtr = parentheses[programPtr]; break;
                case ']': if (memory[memoryPtr] != 0) programPtr = parentheses[programPtr]; break;
                case ',': memory[memoryPtr] = inputPtr >= si ? 255 : input.charAt(inputPtr++); break;
            }

            programPtr++;
        }
    }
}