import logcreator.Log;

import java.io.IOException;

/**
 * Created by root on 4/4/17.
 */
public class TestPlatform {
    public static void main(String argv[]){

        try {
            Log.logCreate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
