import java.io.*;
import java.util.HashMap;

/**
 * Created by zachary on 7/16/17.
 */
public class Assembler {

    static String[] cmds = {"nop","add","sub","mul","div","mod","and","or","not","xor","inp","echo","print", "eq", "neq", "lt", "gt", "jump", "if", "ditto", "ditto2", "flop", "swap"};

    public static void main(String[] args){
        String inp = args[0];
        String outp = args[1];

        int char_count = 0;

        HashMap<String, Byte> cmd = new HashMap<>();
        for (byte i = 0; i < cmds.length; i++) {
            cmd.put(cmds[i], i);
        }

        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(inp)));
            FileOutputStream out = new FileOutputStream(outp);
            while (in.ready()){
                String j = in.readLine();
                j = j.trim();
                if (!j.startsWith("#") && !j.equals("")) {
                    if (j.startsWith("'")){
                        j = j.replace("'","");
                        out.write(0);
                        for (char k : j.toCharArray()){
                            byte b = (byte)k;
                            b |= 0x80;
                            System.out.print(b + " ");
                            out.write(k);
                            char_count++;
                        }
                    }else {
                        try {
                            int i = Integer.parseInt(j);
                            byte c = (byte) i;
                            c |= 0x80;
                            System.out.print((int)c + " ");
                            out.write(c);
                            char_count++;
                        } catch (Exception e) {
                            System.out.print((int)cmd.get(j) + " ");
                            out.write(cmd.get(j));
                            char_count++;
                        }
                    }
                }
            }
        }catch (IOException e){
            System.out.println("An Error Occurred: "+e);
        }

        System.out.println("\nByte Count: "+char_count);
    }
}
