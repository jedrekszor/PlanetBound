package jedrekszor.planetbound.logic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    public static void log(String msg) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true));
            writer.write(msg);
            writer.write("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
