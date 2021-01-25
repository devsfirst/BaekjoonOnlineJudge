import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int[] stack;
    private static int top;
    private static final int ERROR_NUMBER = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] instructions = new String[100000];
        int instructionSize = 0;
        String input;
        while (true) {
            input = br.readLine();
            // QUIT
            if (input.equals("QUIT")) break;
            // END -> 프로그램 실행
            else if (input.equals("END")) {
                int N = Integer.parseInt(br.readLine());
                // 프로그램 N번 실행
                for (int i = 0; i < N; i++) {
                    // 스택 초기화
                    stack = new int[1000];
                    int init = Integer.parseInt(br.readLine());
                    stack[0] = init;
                    top = 0;
                    boolean error = false;
                    // 프로그램의 모든 명령어 실행
                    for (int k = 0; k < instructionSize; k++) {
                        String instruction = instructions[k];
                        if (instruction.equals("NUM")) {
                            int n = Integer.parseInt(instructions[++k]);
                            if (!num(n)) {
                                error = true;
                                break;
                            }
                        } else if (instruction.equals("POP")) {
                            if(!pop()) {
                                error = true;
                                break;
                            }
                        }
                        else if (instruction.equals("INV")) {
                            if (!inv()) {
                                error = true;
                                break;
                            }
                        }
                        else if (instruction.equals("DUP")) {
                            if (!dup()) {
                                error = true;
                                break;
                            }
                        }
                        else if (instruction.equals("SWP")) {
                            if (!swp()) {
                                error = true;
                                break;
                            }
                        }
                        else if (instruction.equals("ADD")) {
                            if (!add()) {
                                error = true;
                                break;
                            }
                        }
                        else if (instruction.equals("SUB")) {
                            if (!sub()) {
                                error = true;
                                break;
                            }
                        }
                        else if (instruction.equals("MUL")) {
                            if (!mul()) {
                                error = true;
                                break;
                            }
                        }
                        else if (instruction.equals("DIV")) {
                            if (!div()) {
                                error = true;
                                break;
                            }
                        }
                        else if (instruction.equals("MOD")) {
                            if (!mod()) {
                                error = true;
                                break;
                            }
                        }
                    }
                    if (error) {
                        System.out.println("ERROR");
                    } else {
                        if (top != 0) System.out.println("ERROR");
                        else System.out.println(stack[top]);
                    }
                }
                // 프로그램 끝나면 공백 출력 후 instruction 초기화
                System.out.println();
                instructions = new String[100000];
                instructionSize = 0;
            } else {
                String[] split = input.split(" ");
                // 명령어가 NUM 이면 뒤에 숫자도 받아야 함
                if (split[0].equals("NUM")) {
                    instructions[instructionSize++] = split[0];
                    instructions[instructionSize++] = split[1];
                } else {
                    instructions[instructionSize++] = input;
                }
            }
        }
    }

    // MOD: 첫 번째 숫자로 두 번째 숫자를 나눈 나머지를 저장한다. 두 번째 숫자가 피제수, 첫 번째 숫자가 제수이다.
    // 나눗셈의 피연산자에 음수가 있을 때는, 그 수를 절댓값을 씌운 뒤 계산한다.
    // 나머지의 부호는 피제수의 부호와 같다.
    private static boolean mod() {
        if (top < 1) return false;

        if (stack[top] == 0) return false;

        int mod = Math.abs(stack[top - 1]) % Math.abs(stack[top]);

        if (stack[top - 1] < 0) {
            mod = -mod;
        }
        stack[top - 1] = mod;

        top--;
        return true;
    }

    // DIV: 첫 번째 숫자로 두 번째 숫자를 나눈 몫을 저장한다. 두 번째 숫자가 피제수, 첫 번째 숫자가 제수이다.
    // 나눗셈의 피연산자에 음수가 있을 때는, 그 수를 절댓값을 씌운 뒤 계산한다.
    // 피연산자중 음수가 한 개일때는 몫의 부호가 음수이다. 이 경우를 제외하면 몫의 부호는 항상 양수이다.
    private static boolean div() {
        if (top < 1) return false;

        if (stack[top] == 0) return false;

        int div = Math.abs(stack[top - 1]) / Math.abs(stack[top]);

        if ((stack[top - 1] < 0 && stack[top] > 0) || (stack[top - 1] > 0 && stack[top] < 0)) {
            stack[top - 1] = -div;
        } else {
            stack[top - 1] = div;
        }
        top--;
        return true;
    }

    // MUL: 첫 번째 숫자와 두 번째 숫자를 곱한다.
    private static boolean mul() {
        if (top < 1) return false;

        long mul = (long) stack[top - 1] * stack[top];
        if (mul > ERROR_NUMBER || mul < -ERROR_NUMBER) return false;

        stack[top - 1] = (int) mul;
        top--;
        return true;
    }

    // SUB: 첫 번째 숫자와 두 번째 숫자를 뺀다. (두 번째 - 첫 번째)
    private static boolean sub() {
        if (top < 1) return false;

        long sub = stack[top - 1] - stack[top];
        if (sub > ERROR_NUMBER || sub < -ERROR_NUMBER) return false;

        stack[top - 1] = (int) sub;
        top--;
        return true;
    }

    // ADD: 첫 번째 숫자와 두 번째 숫자를 더한다.
    private static boolean add() {
        if (top < 1) return false;

        long add = stack[top] + stack[top - 1];
        if (add > ERROR_NUMBER || add < -ERROR_NUMBER) return false;

        stack[top - 1] = (int) add;
        top--;
        return true;
    }

    // SWP: 첫 번째 숫자와 두 번째 숫자의 위치를 서로 바꾼다.
    private static boolean swp() {
        if (top < 1) return false;

        int tmp = stack[top];
        stack[top] = stack[top - 1];
        stack[top - 1] = tmp;
        return true;
    }

    // DUP: 첫 번째 숫자를 하나 더 스택의 가장 위에 저장한다.
    private static boolean dup() {
        if (top < 0) return false;

        top++;
        stack[top] = stack[top - 1];
        return true;
    }

    // INV: 첫 번째 수의 부호를 바꾼다. (42 -> -42)
    private static boolean inv() {
        if (top < 0) return false;

        stack[top] = -stack[top];
        return true;
    }

    // POP: 스택 가장 위의 숫자를 제거한다.
    private static boolean pop() {
        if (top < 0) return false;

        top--;
        return true;
    }

    // NUM X: X를 스택의 가장 위에 저장한다. (0 ≤ X ≤ 10^9)
    private static boolean num(int n) {
        if (Math.abs(n) > ERROR_NUMBER) return false;

        stack[++top] = n;
        return true;
    }
}