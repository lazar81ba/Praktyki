import logcreator.Log;

import java.io.IOException;

/**
 * Created by root on 4/4/17.
 */
public class TestPlatform {
    public static void main(String argv[]){

        try {
            System.out.print(Client.describe());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
