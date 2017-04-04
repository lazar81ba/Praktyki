
public class Action {
    protected String option;
    protected Integer parameter = null;
    public Action(String string){
        option = string;
    }
    public Action(String string,Integer param){
        option = string;
        parameter = param;
    }
}
