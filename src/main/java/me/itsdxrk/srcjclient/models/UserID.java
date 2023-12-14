package me.itsdxrk.srcjclient.models;

public class UserID {

    public String userName;
    public String userId;

    public UserID(String userName, String userId) {
        this.userName = userName;
        this.userId = userId;
    }

    public String getName() {
        return userName;
    }

    public void setName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return userId;
    }

    public void getId(String userId) {
        this.userId = userId;
    }

    public String toString() {
        return userName;
    }
}
