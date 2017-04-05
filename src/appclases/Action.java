package appclases;

public class Action {
    private String option;
    private Integer parameter;

    public Action() {
        option=null;
        parameter=null;
    }
    public Action(String option){
        this.option = option;parameter=null;
    }
    public Action(String option,Integer parameter){
        this.option = option;
        this.parameter = parameter;
    }


    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Integer getParameter() {
        return parameter;
    }

    public void setParameter(Integer parameter) {
        this.parameter = parameter;
    }
}
