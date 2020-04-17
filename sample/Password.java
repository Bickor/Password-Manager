package sample;

public class Password {

    private String password;
    private String userName;
    private String email;

    public Password(String password, String userName, String email) {
        this.password = password;
        this.userName = userName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
