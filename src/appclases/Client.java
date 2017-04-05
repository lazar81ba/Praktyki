package appclases;

import okhttp3.*;

import java.io.IOException;


public class Client {
    static String email = null;
    static String token = null;

    public static void setAccount(Account account){
        email = account.getEmail();
        token = account.getToken();
    }

    public static String request(Action action) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body;
        if(action.getParameter()==null) {
            body = RequestBody.create(mediaType, "{\"Command\":\"" + action.getOption() + "\", \"Login\":\""+email+"\", \"Token\":\""+token+"\"}");
        }else{
            body = RequestBody.create(mediaType, "{\"Command\":\"" + action.getOption() + "\", \"Login\":\""+email+"\", \"Token\":\""+token+"\"}, \"Parameter\":\""+action.getParameter().toString()+"\"}");

        }
        Request request = new Request.Builder()
                .url("http://arcology.prime.future-processing.com/execute")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "75b37ba6-19ad-0577-de2c-0aa6d0577ce2")
                .build();

        Response response = client.newCall(request).execute();
        String output = response.body().string();
        return output;    }

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

        return output.substring(1,output.length()-1);
    }
}
