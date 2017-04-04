package logcreator;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private String name; //name of current log

    public Log(){
        this.name=null;
    }

    public void setName(String name){this.name=name;}

    public void logCreate() throws IOException {

        //create name and path for new log
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        name= dtf.format(now)+".txt";
        //check directory
        String path = new java.io.File( "." ).getCanonicalPath()+"/Logs/";
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }
        directory=new File(path+name);
        //log create
        directory.createNewFile();

    }

    public void writeLog(){



    }


}
