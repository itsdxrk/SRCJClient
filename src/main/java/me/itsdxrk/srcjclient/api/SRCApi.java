package me.itsdxrk.srcjclient.api;

import com.google.gson.*;
import me.itsdxrk.srcjclient.models.*;
import me.itsdxrk.srcjclient.util.TimeFormat;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

public class SRCApi {
    private String baseurl = ("https://www.speedrun.com/api/v2/");
    TimeFormat timeFormat = new TimeFormat();

    public Color getTheming(String gameId) {
        String params = "{\"gameId\":\"" + gameId + "\"}";
        params = Base64.getUrlEncoder().withoutPadding().encodeToString(params.getBytes()).toString();
        try {
            Color primaryColor = Color.WHITE;
            URL url = new URL(baseurl + "GetGameData?_r=" + params);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "SRCJ-Client");
            if (conn.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                conn.disconnect();

                JsonObject jsonObj = JsonParser.parseString(String.valueOf(response)).getAsJsonObject();
                if (jsonObj.has("theme")) {
                    String hexColor = jsonObj.get("theme").getAsJsonObject().get("primaryColor").getAsString();
                    primaryColor = Color.decode("#" + hexColor);
                    return primaryColor;
                }
                return primaryColor;
            } else {
                JOptionPane.showMessageDialog(null, "Failed with code " + conn.getResponseCode());
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, " + e);
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<GameID> getGameSearch(String gameName) {
        ArrayList<GameID> gameList = new ArrayList<>();

        String params = "{\"query\":\"" + gameName + "\",\"limit\":500,\"favorExactMatches\":true,\"includeGames\":true," +
                "\"includeNews\":false,\"includePages\":false,\"includeSeries\":false,\"includeUsers\":false}";
        params = Base64.getUrlEncoder().withoutPadding().encodeToString(params.getBytes()).toString();
        try {
            URL url = new URL(baseurl + "GetSearch?_r=" + params);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "SRCJ-Client");
            if (conn.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                conn.disconnect();

                JsonObject jsonObj = JsonParser.parseString(String.valueOf(response)).getAsJsonObject();
                JsonArray jsonGameList = jsonObj.get("gameList").getAsJsonArray();
                int numOfGames = jsonGameList.size();
                for (int i = 0; i < numOfGames; i++) {
                    String currentName = jsonGameList.get(i).getAsJsonObject().get("name").getAsString();
                    String currentId = jsonGameList.get(i).getAsJsonObject().get("id").getAsString();

                    gameList.add(new GameID(currentName, currentId));
                }
                return gameList;
            } else {
                JOptionPane.showMessageDialog(null, "Failed with code " + conn.getResponseCode());
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, " + e);
        }
        return null;
    }

    public ArrayList<CategoryID> getCats(String gameId) {
        ArrayList<CategoryID> categoryList = new ArrayList<>();

        String params = "{\"gameId\":\"" + gameId + "\"}";
        params = Base64.getUrlEncoder().withoutPadding().encodeToString(params.getBytes()).toString();
        try {
            URL url = new URL(baseurl + "GetGameData?_r=" + params);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "SRCJ-Client");
            if (conn.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                conn.disconnect();

                JsonObject jsonObj = JsonParser.parseString(String.valueOf(response)).getAsJsonObject();
                JsonArray jsonCatList = jsonObj.get("categories").getAsJsonArray();
                int numOfCats = jsonCatList.size();
                for (int i = 0; i < numOfCats; i++) {
                    String currentCat = jsonCatList.get(i).getAsJsonObject().get("name").getAsString();
                    String currentId = jsonCatList.get(i).getAsJsonObject().get("id").getAsString();
                    Boolean isPerLevel = jsonCatList.get(i).getAsJsonObject().get("isPerLevel").getAsBoolean();

                    if (!isPerLevel) {
                        categoryList.add(new CategoryID(currentCat, currentId));
                    }
                }
                return categoryList;
            } else {
                JOptionPane.showMessageDialog(null, "Failed with code " + conn.getResponseCode());
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, " + e);
        }
        return null;
    }

    public ArrayList<ArrayList> getSubcats(String gameId, String categoryId) {
        ArrayList<String> varIdList = new ArrayList<>();
        ArrayList<SubcategoryID> subcatsList = new ArrayList<>();

        String params = "{\"gameId\":\"" + gameId + "\"}";
        params = Base64.getUrlEncoder().withoutPadding().encodeToString(params.getBytes()).toString();
        try {
            URL url = new URL(baseurl + "GetGameData?_r=" + params);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "SRCJ-Client");
            if (conn.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                conn.disconnect();

                JsonObject jsonObj = JsonParser.parseString(String.valueOf(response)).getAsJsonObject();
                JsonArray jsonVarList = jsonObj.get("variables").getAsJsonArray();
                int numOfVars = jsonVarList.size();
                for (int i = 0; i < numOfVars; i++) {
                    JsonObject currentVar = jsonVarList.get(i).getAsJsonObject();
                    Boolean isSubcat = currentVar.get("isSubcategory").getAsBoolean();
//                    Integer varCatScope = currentVar.get("categoryScope").getAsInt();
//                    System.out.println(currentVar.get("name").getAsString() + " " + varCatScope);
                    if (isSubcat) {
                        Integer varCatScope = currentVar.get("categoryScope").getAsInt();
                        if (varCatScope == 1) {
                            String varCat = currentVar.get("categoryId").getAsString();
                            if (varCat.equals(categoryId)) {
                                String varName = currentVar.get("name").getAsString();
                                String varId = currentVar.get("id").getAsString();

                                varIdList.add(varId);
                            }
                        }
                    }
                    else {
                        System.out.println(currentVar.get("name").getAsString());
                    }
                }
                JsonArray jsonValueList = jsonObj.get("values").getAsJsonArray();
                int numOfValues = jsonValueList.size();
                int numOfIds = varIdList.size();
                for (int id = 0; id < numOfIds; id++) {
                    for (int val = 0; val < numOfValues; val++) {
                        String currentId = varIdList.get(id);
                        JsonObject values = jsonValueList.get(val).getAsJsonObject();
                        String valueVarId = values.get("variableId").getAsString();
                        String valueName = values.get("name").getAsString();
                        String valueId = values.get("id").getAsString();
                        if (valueVarId.equals(currentId)) {
                            subcatsList.add(new SubcategoryID(valueName, valueId, valueVarId));
                        }
                    }
                }
                ArrayList<ArrayList> subcatsAndVarIds = new ArrayList<>();
                subcatsAndVarIds.add(subcatsList);
                subcatsAndVarIds.add(varIdList);

                return subcatsAndVarIds;
            } else {
                JOptionPane.showMessageDialog(null, "Failed with code " + conn.getResponseCode());
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, " + e);
        }
        return null;
    }

    public ArrayList<String> getWRTime(String gameId, String categoryId, ArrayList<VariablesValuesIDs> varValIds) {
        ArrayList<String> paralist = new ArrayList<>();
        String params = "{\"params\":{\"categoryId\":\"" + categoryId + "\",\"gameId\":\"" + gameId + "\",\"obsolete\":0,\"platformIds\":[],\"regionIds\":[],\"verified\":1,\"values\":[";
        for (VariablesValuesIDs varValId : varValIds) {
            System.out.println(varValId.getVarId());
            System.out.println(varValId.getValueId());
            String currentParams = "{\"variableId\":\"" + varValId.getVarId() + "\",\"valueIds\":[\"" + varValId.getValueId() + "\"]}";
            paralist.add(currentParams);
        }
        String conjoinedParams = String.join(",", paralist);
        params = params + conjoinedParams + "],\"video\":0},\"page\":1}";
        params = Base64.getUrlEncoder().withoutPadding().encodeToString(params.getBytes()).toString();
        try {
            URL url = new URL(baseurl + "GetGameLeaderboard2?_r=" + params);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "SRCJ-Client");
            if (conn.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                conn.disconnect();

                JsonObject jsonObj = JsonParser.parseString(String.valueOf(response)).getAsJsonObject();
                JsonArray jsonRunList = jsonObj.get("runList").getAsJsonArray();
                JsonObject firstPlace = jsonRunList.get(0).getAsJsonObject();
                double time = firstPlace.get("time").getAsDouble();
                String realTime = timeFormat.formatTime(time);
                ArrayList<String> wrInfo = new ArrayList<>();
                if (firstPlace.has("igt")) {
                    double igtDouble = firstPlace.get("igt").getAsDouble();
                    String igt = timeFormat.formatTime(igtDouble);
                    wrInfo.add(igt);
                }

                wrInfo.add(realTime);

                return wrInfo;
            } else {
                JOptionPane.showMessageDialog(null, "Failed with code " + conn.getResponseCode());
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, " + e);
        }
        return null;
    }

    public URL getWRLink(String runId) {
        String params = "{\"params\":{\"runId\":\"" + runId + "\"}}";
        params = Base64.getUrlEncoder().withoutPadding().encodeToString(params.getBytes());
        try {
            URL url = new URL(baseurl + "GetRun?_r=" + params);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "SRCJ-Client");
            if (conn.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                conn.disconnect();

                JsonObject jsonObj = JsonParser.parseString(String.valueOf(response)).getAsJsonObject();
                String gameAbbreviation = jsonObj.get("game").getAsJsonObject().get("url").getAsString();

                return new URL("https://speedrun.com/" + gameAbbreviation + "/runs/" + runId);
            } else {
                JOptionPane.showMessageDialog(null, "Failed with code " + conn.getResponseCode());
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, " + e);
        }
        return null;
    }

    public ArrayList<UserID> getWRNames(String gameId, String categoryId, ArrayList<VariablesValuesIDs> varValIds) {
        ArrayList<String> paralist = new ArrayList<>();
        String params = "{\"params\":{\"categoryId\":\"" + categoryId + "\",\"gameId\":\"" + gameId + "\",\"obsolete\":0,\"platformIds\":[],\"regionIds\":[],\"verified\":1,\"values\":[";
        for (VariablesValuesIDs varValId : varValIds) {
            String currentparams = "{\"variableId\":\"" + varValId.getVarId() + "\",\"valueIds\":[\"" + varValId.getValueId() + "\"]}";
            paralist.add(currentparams);
        }
        String conjoinedParams = String.join(",", paralist);
        params = params + conjoinedParams + "],\"video\":0},\"page\":1}";
        params = Base64.getUrlEncoder().withoutPadding().encodeToString(params.getBytes()).toString();
        try {
            URL url = new URL(baseurl + "GetGameLeaderboard2?_r=" + params);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "SRCJ-Client");
            if (conn.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                conn.disconnect();

                JsonObject jsonObj = JsonParser.parseString(String.valueOf(response)).getAsJsonObject();
                JsonArray jsonRunList = jsonObj.get("runList").getAsJsonArray();
                JsonObject firstPlace = jsonRunList.get(0).getAsJsonObject();

                //Uses v1 API due to seemingly no API endpoint for users?
                JsonArray wrPlayersId = firstPlace.get("playerIds").getAsJsonArray();
                String currentPlayer;
                String currentUsername;
                UserID playerInfo;
                ArrayList<UserID> wrHolderList = new ArrayList<>();
                for (int i = 0; i < wrPlayersId.size(); i++) {
                    currentPlayer = wrPlayersId.get(i).getAsString();
                    currentUsername = getUserName(currentPlayer);
                    playerInfo = new UserID(currentUsername, currentPlayer);
                    wrHolderList.add(playerInfo);
                }
                return wrHolderList;
            } else {
                JOptionPane.showMessageDialog(null, "Failed with code " + conn.getResponseCode());
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, " + e);
        }
        return null;
    }

    public String getUserName(String userId) {
        try {
            URL url = new URL("https://www.speedrun.com/api/v1/users/" + userId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "SRCJ-Client");
            if (conn.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                conn.disconnect();

                JsonObject jsonObj = JsonParser.parseString(String.valueOf(response)).getAsJsonObject();
                String userName = jsonObj.get("data").getAsJsonObject().get("names").getAsJsonObject()
                        .get("international").getAsString();
                return userName;
            } else {
                JOptionPane.showMessageDialog(null, "Failed with code " + conn.getResponseCode());
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, " + e);
        }
        return null;
    }

    public BufferedImage getUserProfilePic(String userId) {
        try {
            URL url = new URL("https://www.speedrun.com/api/v1/users/" + userId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "SRCJ-Client");
            if (conn.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                conn.disconnect();

                JsonObject jsonObj = JsonParser.parseString(String.valueOf(response)).getAsJsonObject();
                JsonElement userPfp = jsonObj.get("data").getAsJsonObject().get("assets").getAsJsonObject()
                        .get("image").getAsJsonObject().get("uri");
                if (!(userPfp.isJsonNull())) {
                    return ImageIO.read(new URL("https://www.speedrun.com/static/user/" + userId + "/image"));
                }
                else {
                    return null;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Failed with code " + conn.getResponseCode());
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error getting profile picture: " + e);
        }
        return null;
    }

    public BufferedImage getCoverImage(String id) {
        try {
            URL url = new URL("https://www.speedrun.com/static/game/" + id + "/cover.png");
            return ImageIO.read(url);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error Showing Image - " + ex);
        }
        return null;
    }
}
