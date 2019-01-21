package app;

import java.io.*;

public class Test {

    public static void main(String args[]) {
        try {
            FileInputStream fis = new FileInputStream("t.csv");
            BufferedReader r = new BufferedReader(new InputStreamReader(fis,
                    "UTF8"));
            for (String s = ""; (s = r.readLine()) != null;) {
                System.out.println(s);
            }
            r.close();
            System.exit(0);
        }

        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
