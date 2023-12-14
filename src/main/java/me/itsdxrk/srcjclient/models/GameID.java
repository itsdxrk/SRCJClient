package me.itsdxrk.srcjclient.models;

public class GameID {

    public String gameName;
    public String gameId;

    public GameID(String gameName, String gameId) {
        this.gameName = gameName;
        this.gameId = gameId;
    }
    public String getName() {
        return gameName;
    }
    public void setName(String gameName) {
        this.gameName = gameName;
    }
    public String getId() {
        return gameId;
    }
    public void setId(String gameId) {
        this.gameId = gameId;
    }

    @Override
    public String toString() {
        return gameName;
    }
}
