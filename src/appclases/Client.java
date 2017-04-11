package appclases;

import okhttp3.*;

import java.io.IOException;
import java.io.SyncFailedException;
import java.util.*;


public class Client {
    static private String email = null;
    static private String token = null;
    static private String eventList;


    public static void setAccount(Account account){
        email = account.getEmail();
        token = account.getToken();
        eventList = "none";
        }

    private static void resetEvents(boolean check){
        if(check) eventList="";
    }
        //set events nie powinien dzialac na liscie, lepiej zapisac na stringu i obcinac nowe info
    private static String setEvents(String events){

        events = events.replace("events:","");
        String returnString = events.replace(eventList+",","");
        returnString = returnString.replaceAll(",",","+System.lineSeparator());
        eventList=events;
        return "Events:"+returnString+System.lineSeparator();
    }


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
        if(!output.equals("")) return true;

        return false;
    }

    public static void request(Action action) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body;
        if(action.getParameter()==null) {
            body = RequestBody.create(mediaType, "{\"Command\":\"" + action.getOption() + "\", \"Login\":\""+email+"\", \"Token\":\""+token+"\"}");
        }else{
            body = RequestBody.create(mediaType, "{\"Command\":\"" + action.getOption() + "\", \"Login\":\""+email+"\", \"Token\":\""+token+"\", \"Parameter\":\""+ action.getParameter() +"\"}");

        }
        Request request = new Request.Builder()
                .url("http://arcology.prime.future-processing.com/execute")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "75b37ba6-19ad-0577-de2c-0aa6d0577ce2")
                .build();

        client.newCall(request).execute();
        resetEvents(action.getOption().equals("Restart"));
    }

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
        }else neho="nehoRunes:null";

        //extract events
        int startEventsIndex = message.indexOf("events");
        int endEventsIndex = message.lastIndexOf("]",message.length()+1);
        String events = message.substring(startEventsIndex,endEventsIndex);
        events = events.replace("[","");
        events = events.replace("]","");
        events = events.replace(":",":"+System.lineSeparator());
        events = setEvents(events);

        //erase neho and events
        message = message.replaceAll("nehoRunes:\\[.*],","");
        message = message.replaceAll("events:\\[.*]", "");

        //formating other part of message
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
