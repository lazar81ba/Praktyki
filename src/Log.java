import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

    //this class is responsible for creating logs
public class Log {

    private String name; //name of current log

    public Log(){
        this.name=null;
    }

    public void setName(String name){this.name=name;}

        //creating new log, name is current date
    private void logCreate() throws IOException {
        //create name and path for new log
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        name= dtf.format(now)+".txt";
        //check directory
        String path = new File( "." ).getCanonicalPath()+"/Logs/";
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }
        directory=new File(path+name);
        //log create
        directory.createNewFile();
    }

        //writing information to log
    public void writeLog(String message, Action action)throws Exception{
        //check if log exist or last action was Restart
        if(name==null||action.getOption().equals("Restart")) logCreate();
        //open writer
        String file = new File( "." ).getCanonicalPath()+"/Logs/"+name;
        BufferedWriter out = new BufferedWriter(new FileWriter(file,true));
        //write information
        out.write("##########################################################################\n\n");
        out.write("Used command: "+action.getOption()+"\n");
        out.write("Parameter: "+action.getParameter()+"\n");
        out.write(message);
        out.write("\n\n");
        //close writer
        out.close();
    }

}
