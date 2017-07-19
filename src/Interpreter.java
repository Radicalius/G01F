import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by zachary on 7/16/17.
 */
public class Interpreter {

    static int MAX_STACK_SIZE = 1000;

    static Stack<Integer> stack = new Stack<>();
    static BufferedReader in;
    static BufferedReader stdin;
    static Stack<Character> char_buffer = new Stack<>();
    static ArrayList<String> code = new ArrayList<>();
    static int PC = 0;

    public static int exec(String command){
        int rv = 1;
        try{
            int val = Integer.parseInt(command);
            stack.push(val);
        }catch(Exception e) {
            try {
                if (command.equals("inps")){
                    command = "'"+stdin.readLine().trim()+"'";
                }
                if (command.startsWith("'")) {
                    String g = command.replace("'", "");
                    stack.push(0);
                    for (char i : g.toCharArray()) {
                        stack.push((int) i);
                    }
                }
                if (command.equals("add")) {
                    int arg1 = stack.pop();
                    int arg2 = stack.pop();
                    stack.push(arg1 + arg2);
                }
                if (command.equals("sub")) {
                    int arg1 = stack.pop();
                    int arg2 = stack.pop();
                    stack.push(arg2 - arg1);
                }
                if (command.equals("mul")) {
                    int arg1 = stack.pop();
                    int arg2 = stack.pop();
                    stack.push(arg1 * arg2);
                }
                if (command.equals("div")) {
                    int arg1 = stack.pop();
                    int arg2 = stack.pop();
                    stack.push(arg2 / arg1);
                }
                if (command.equals("mod")) {
                    int arg1 = stack.pop();
                    int arg2 = stack.pop();
                    stack.push(arg2 % arg1);
                }if (command.equals("and")){
                    int arg1 = stack.pop();
                    int arg2 = stack.pop();
                    stack.push(arg2 & arg1);
                }if (command.equals("not")){
                    int arg1 = stack.pop();
                    stack.push(~arg1);
                }if (command.equals("or")){
                    int arg1 = stack.pop();
                    int arg2 = stack.pop();
                    stack.push(arg2 | arg1);
                }if (command.equals("xor")){
                    int arg1 = stack.pop();
                    int arg2 = stack.pop();
                    stack.push(arg2 ^ arg1);
                }
                if (command.equals("echo")) {
                    int arg1 = stack.pop();
                    System.out.println(arg1);
                }
                if (command.equals("inp")) {
                    try {
                        String inp = stdin.readLine();
                        stack.push(Integer.parseInt(inp));
                    } catch (Exception e2) {
                        System.out.println("ERROR: invalid input");
                        System.exit(2);
                    }
                }
                if (command.equals("print")) {
                    char a = (char) (int) stack.pop();
                    while (a != '\0') {
                        char_buffer.push(a);
                        a = (char) (int) stack.pop();
                    }
                    while (!char_buffer.empty()) {
                        System.out.print(char_buffer.pop());
                    }
                    System.out.println();
                }
                if (command.equals("jump")) {
                    rv = stack.pop();
                }
                if (command.equals("if")) {
                    int prv = stack.pop();
                    int cond = stack.pop();
                    if (cond != 0) {
                        rv = prv;
                    } else {
                        rv = 1;
                    }
                }
                if (command.equals("ditto")) {
                    stack.push(stack.peek());
                }
                if (command.equals("ditto2")) {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b);
                    stack.push(a);
                    stack.push(b);
                    stack.push(a);
                }
                if (command.equals("gt")) {
                    int a = stack.pop();
                    int b = stack.pop();
                    if (b < a) {
                        stack.push(0);
                    } else {
                        stack.push(1);
                    }
                }
                if (command.equals("lt")) {
                    int a = stack.pop();
                    int b = stack.pop();
                    if (b < a) {
                        stack.push(1);
                    } else {
                        stack.push(0);
                    }
                }
                if (command.equals("eq")) {
                    int a = stack.pop();
                    int b = stack.pop();
                    if (b != a) {
                        stack.push(0);
                    } else {
                        stack.push(1);
                    }
                }
                if (command.equals("neq")) {
                    int a = stack.pop();
                    int b = stack.pop();
                    if (b == a) {
                        stack.push(0);
                    } else {
                        stack.push(1);
                    }
                }if (command.equals("flop")){
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(a);
                    stack.push(b);
                }if (command.equals("swap")){
                    int num = stack.pop();
                    int[] ints = new int[num];
                    for (int i = 0; i < num; i++){
                        ints[i] = stack.pop();
                    }
                    int a = ints[0];
                    ints[0] = ints[num-1];
                    ints[num-1] = a;
                    for (int i = num-1; i >= 0; i--){
                        stack.push(ints[i]);
                    }
                }
            }catch (EmptyStackException j){
                System.out.println("ERROR: Stack Underflow!");
                System.exit(3);
            }catch (IOException f){}
            catch (ArithmeticException g){
                System.out.println("ERROR: Arithmetic Exception!");
                System.exit(4);
            }
            if (stack.size() > MAX_STACK_SIZE){
                System.out.println("ERROR: Stack Overflow!");
                System.exit(4);
            }
        }
        return rv;
    }

    public static void main(String[] args){
        String filename = args[0];
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            stdin = new BufferedReader(new InputStreamReader(System.in));
            while (in.ready()){
                String line = in.readLine();
                line = line.trim();
                if (!line.startsWith("#") && !line.equals("")) {
                    String[] g = line.split("#");
                    line = g[0].trim();
                    code.add(line);
                }
            }
            while (PC < code.size()){
                //System.out.println("----------------");
                //System.out.println(code.get(PC));
                PC += exec(code.get(PC));
                //System.out.println(stack);
                //stdin.readLine();
            }
        }catch(IOException e){
            System.out.println("ERROR: Failed to open file "+filename);
            System.exit(1);
        }
    }
}
