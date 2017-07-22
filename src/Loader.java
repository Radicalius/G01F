import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by zachary on 7/21/17.
 */
public class Loader {
    public static void main(String[] args){
        byte index = 0;
        HashMap<Byte, String> cmds = new HashMap<>();
        for (String cmd: Assembler.cmds){
            cmds.put(index, cmd);
            index++;
        }
        String filename = args[0];
        try {
            FileInputStream in = new FileInputStream(filename);
            byte c = (byte)in.read();
            while (c != -1){
                if (cmds.containsKey(c)) {
                    System.out.println(cmds.get(c));
                }else{
                    byte mask1 = 0x7F;
                    c = (byte)(c & mask1);
                    if (c > 63){
                        c = (byte)(c & 0xF);
                    }
                    System.out.println(c);
                }
                c = (byte) in.read();
            }
            in.close();

        } catch (Exception h) {}
    }
}
