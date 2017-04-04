import okhttp3.*;

import java.io.IOException;


public class Client {
    static String email = null;
    static String token = null;

    static void setAccount(Account account){
        email = account.email;
        token = account.token;
    }

    static String request(Action action) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body;
        if(action.parameter==null) {
            body = RequestBody.create(mediaType, "{\"Command\":\"" + action.option + "\", \"Login\":\""+email+"\", \"Token\":\""+token+"\"}");
        }else{
            body = RequestBody.create(mediaType, "{\"Command\":\"" + action.option + "\", \"Login\":\""+email+"\", \"Token\":\""+token+"\"}, \"Parameter\":\""+action.parameter.toString()+"\"}");

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

    static String describe() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://arcology.prime.future-processing.com/describe?login=lazar81ba%40gmail.com&token=61ADBBB5AEBBEAF640AEBEBFD0CB751F")
                .get()
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "db449928-3f75-c02f-a49c-36b57cb6f869")
                .build();

        Response response = client.newCall(request).execute();

        String output = response.body().string();
        //TO DO: jakis regex zeby to sformatowac
        return output;
    }
}
