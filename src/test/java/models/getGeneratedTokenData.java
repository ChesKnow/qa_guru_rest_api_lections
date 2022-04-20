package models;

public class getGeneratedTokenData {

    /*
    {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImFsZXgiLCJwYXNzd29yZCI6ImFzZHNhZCNmcmV3X0RGUzIiLCJpYXQiOjE2NTA0ODc0Mzd9.icNd-g573s_dIlTew1WkOldLiiqgeXEzolADn_JZihY",
    "expires": "2022-04-27T20:43:57.736Z",
    "status": "Success",
    "result": "User authorized successfully."
}
     */
    private String token;
    private String expires;
    private String status;
    private String result;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
