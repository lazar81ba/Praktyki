
public class Action {
    protected String option;
    protected Integer parameter = null;
    public Action(String option){
        this.option = option;
    }
    public Action(String option,Integer parameter){
        this.option = option;
        this.parameter = parameter;
    }
}
