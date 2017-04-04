package logcreator;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    private static boolean isActive = false; //is any log currently active
    private static String name = null; //which log is now beeing changed

    public static void logCreate() throws IOException {
        if(!isActive){
            isActive=true;

            //create name and path for new log
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            name= dtf.format(now)+".txt";
            // TO DO : check directory
            String path = new java.io.File( "." ).getCanonicalPath()+"/Logs/"+name;

            //tworze plik
            File file = new File(path);
            file.createNewFile();

        }
    }
}
