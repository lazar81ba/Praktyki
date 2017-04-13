    //this class is representation of actions for making execute command
public class Action {

    private String option;
    private String parameter;

    public Action() {
        option="";
        parameter="";
    }

    public void resetAction(){
        this.option="";
        this.parameter="";
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

}
