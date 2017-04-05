import appclases.Account;
import appclases.Action;
import appclases.Client;
import logcreator.Log;

import java.io.IOException;

/**
 * Created by root on 4/4/17.
 */
public class TestPlatform {
    public static void main(String argv[]){
        Account account= new Account("lazar81ba@gmail.com","61ADBBB5AEBBEAF640AEBEBFD0CB751F");
        Client.setAccount(account);
        try {
            Log log  = new Log();
            log.setName("2017.04.05 16:41:57.txt");
            log.writeLog(Client.describe(),new Action());
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

}
