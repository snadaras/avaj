package fr_42_avaj_launcher.launch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by snadaras on 20/02/2018.
 */
public class Logger {
    private static Logger logger = null;
    private static BufferedWriter bufferedWriter = null;

    private Logger() {
//        logger = this;
//        try {
//            bufferedWriter = new BufferedWriter(new FileWriter(new File("simulation.txt")));
//            System.out.println("created");
//            System.out.println(bufferedWriter.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(bufferedWriter);
    }

    public static Logger getLogger() {
        if (logger == null) {
            logger = new Logger();
        }
        if (bufferedWriter == null) {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(new File("simulation.txt")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return logger;
    }

    public void log(String str) {
        try {
            bufferedWriter.write(str);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
