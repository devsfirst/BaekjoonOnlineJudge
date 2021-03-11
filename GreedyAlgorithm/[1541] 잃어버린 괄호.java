import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int result;

    public static void main(String[] args) throws IOException {
        String s = br.readLine();
        // nums : 수 저장
        // ops : 연산자 저장
        String[] nums = s.split("[+-]");
        String[] ops = s.replaceAll("[0-9]", "").split("");
        int len = ops.length;
        result = Integer.parseInt(nums[0]);

        // - 를 찾은 후 - 이후 등장하는 + 연산자에 대해 연산하는 수를 모두 더한 후 그 수만큼 뺌
        // ex) -1+2+3+4-5 이면 -(1+2+3+4)-5가 되도록
        // + 연산을 수행한 + 연산자는 ""로 연산이 끝났음을 알림
        for (int i = 0; i < len; i++) {
            if (ops[i].equals("-")) {
                int tmp = Integer.parseInt(nums[i + 1]);
                while ((++i < len) && ops[i].equals("+")) {
                    ops[i] = "";
                    tmp += Integer.parseInt(nums[i + 1]);
                }
                result -= tmp;
                i--;
            }
        }

        // 위 과정에서 사용하지 않은 + 연산자 사용
        for (int i = 0; i < len; i++) {
            if (ops[i].equals("+")) result += Integer.parseInt(nums[i + 1]);
        }
        System.out.println(result);
    }
}