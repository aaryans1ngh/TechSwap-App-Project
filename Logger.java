/*******************************************************************
* Name: Aaryan Singh
* Date: December 11, 2025
* Assignment: SDC330 Course Project - Final Submission
*
* This class serves as the logger file for the application.
*/

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Logger {
    private static final String LOG_FILE = "activity_log.txt";

    public static void log(String message) {
        try {
            FileWriter fw = new FileWriter(LOG_FILE, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(new Date() + ": " + message);
            pw.close();
        } catch (IOException e) {
            System.out.println("Error writing to log file.");
        }
    }
}