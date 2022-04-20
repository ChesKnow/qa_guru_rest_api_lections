package models;

public class SetCredentialsData {

    /*
    {   "userName": "alex",   "password": "asdsad#frew_DFS2" }
     */

    private String userName,
            password;


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
