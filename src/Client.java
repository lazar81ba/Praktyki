import okhttp3.*;
import java.io.IOException;


    //this class is responsible for sending requests and delivering formatted description
public class Client {

    static private String email = null;
    static private String token = null;
    static private String eventList;


    public static void setAccount(Account account){
        email = account.getEmail();
        token = account.getToken();
        eventList = "none";
        }

        //Reset events when restart
    private static void resetEvents(boolean check){
        if(check) eventList="";
    }

        //Cut old events for display and set new events
    private static String setEvents(String events){
        String returnString;
        events = events.replace("events:"+System.lineSeparator(),"");
        if(events.contains("Catastrophic")) returnString = "Catastrophic";
        else {
            returnString = events.replace(eventList,"");
            if(!returnString.equals("") && returnString.charAt(0)==',') returnString = returnString.substring(1);
        }
        returnString = returnString.replaceAll(",",","+System.lineSeparator());
        eventList=events;
        return "Events:"+System.lineSeparator()+returnString+System.lineSeparator();
    }

        //Checking connection for logging in
    public static boolean checkConnection() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String requestEmail = email.replace("@","%40");
        Request request = new Request.Builder()
                .url("http://arcology.prime.future-processing.com/describe?login="+requestEmail+"&token="+token)
                .get()
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "db449928-3f75-c02f-a49c-36b57cb6f869")
                .build();
        Response response = client.newCall(request).execute();

        String output = response.body().string();
        if(!output.equals("")) return true; // if output contains anything means login and token are correct
        return false;
    }

        //Executing commands based on action
    public static void executeCommand(Action action) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body;
        //checking if action has parameter
        if(action.getParameter().equals("")) {
            body = RequestBody.create(mediaType, "{\"Command\":\"" + action.getOption() + "\", \"Login\":\""+email+"\", \"Token\":\""+token+"\"}");
        }else{
            String command ="{\"Command\":\"" + action.getOption() + "\", \"Login\":\""+email+"\", \"Token\":\""+token+"\", \"Parameter\":\""+ action.getParameter() +"\"}";
            body = RequestBody.create(mediaType, command);
        }
        Request request = new Request.Builder()
                .url("http://arcology.prime.future-processing.com/execute")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "5bee58f2-0414-d49a-7aa3-e6b038b34813")
                .build();
        client.newCall(request).execute();
        resetEvents(action.getOption().equals("Restart"));
    }

        //getting month description
    public static String describe() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String requestEmail = email.replace("@","%40");
        Request request = new Request.Builder()
                .url("http://arcology.prime.future-processing.com/describe?login="+requestEmail+"&token="+token)
                .get()
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "db449928-3f75-c02f-a49c-36b57cb6f869")
                .build();
        Response response = client.newCall(request).execute();

        String output = response.body().string();
        if(!output.equals("")) output=formatMessage(output);

        return output;
    }

        //formatting JSON into readable message
    private static String formatMessage(String message){
        message = message.substring(1,message.length()-1);
        message = message.replace("\"","");
        String neho ;

        //extract and format nehoRunes
        if(!message.contains("nehoRunes:null")) {
            int startNehoIndex = message.indexOf("neho");
            int endNehoIndex = message.lastIndexOf("]", message.length() / 2);
            neho = message.substring(startNehoIndex, endNehoIndex + 1);
            neho = neho.replace("[", "");
            neho = neho.replace("]", "");
            neho = neho.replace(",",System.lineSeparator());
            neho = neho.replace(":",":"+System.lineSeparator());
        }else neho="nehoRunes:none";

        //extract events
        int startEventsIndex = message.indexOf("events");
        int endEventsIndex = message.lastIndexOf("]",message.length()+1);
        String events = message.substring(startEventsIndex,endEventsIndex);
        events = events.replace("[","");
        events = events.replace("]","");
        events = events.replace(":",":"+System.lineSeparator());
        events = setEvents(events);

        //erase old neho and events
        message = message.replaceAll("nehoRunes:\\[.*],","");
        message = message.replaceAll("events:\\[.*]", "");

        //formatting other part of message
        String information[] = message.split(",");
        information[0] = information[0].replace("turn", "Month");
        information[0] = information[0].replace(".0", "");
        String returnString = "";
        returnString = returnString.concat(information[0]+System.lineSeparator()+events+neho+System.lineSeparator());
        for (int i = 1;i<information.length;i++)
            returnString = returnString.concat(information[i]+System.lineSeparator());

        return returnString;
    }

}
