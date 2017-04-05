package appclases;

public class Account {
    private String email;
    private String token;
    public Account(String email, String token){
        this.email = email;
        this.token = token;
    }

    public Account() {
        email=null;
        token=null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
