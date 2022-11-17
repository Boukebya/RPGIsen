package rpgIsen.Class.FileManagement;

import rpgIsen.Class.World.Map;
import rpgIsen.Class.World.Tile;
import rpgIsen.Class.World.Weather;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public abstract class JsonManager {
    //Write scores in a file
    public static void writeFile(int score, String name) {
        String path = "src/Data.json";
        try {
            FileReader reader = new FileReader(path);
            JSONArray jsonArray;
            jsonArray = (JSONArray) new JSONParser().parse(reader);

            //get leaderboard element
            JSONObject leaderboard = (JSONObject) jsonArray.get(0);
            //get leaderboard array
            JSONArray leaderboardArray = (JSONArray) leaderboard.get("Scores");
            String scoreString = name + " : " + score;
            //add scoreString to leaderboard array
            JSONObject newScore = new JSONObject();
            newScore.put("Scores", scoreString);

            //add to leaderboard array
            leaderboardArray.add(scoreString);

            //write to file
            FileWriter file = new FileWriter(path);
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();
        } catch (JSONException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }


    }

    //Display scores of players
    public static void displayScore() {
        String path = "src/Data.json";

        try {
            FileReader reader = new FileReader(path);
            JSONArray jsonArray;
            jsonArray = (JSONArray) new JSONParser().parse(reader);

            //get leaderboard element
            JSONObject leaderboard = (JSONObject) jsonArray.get(0);
            //get leaderboard array
            JSONArray leaderboardArray = (JSONArray) leaderboard.get("Scores");
            //print leaderboard elements
            System.out.println("Leaderboard :");
            String[][] leaderboardMatrix;
            //Create a matrix of leaderBoarsArray size
            leaderboardMatrix = new String[leaderboardArray.size()][2];
            for (int i = 0; i < leaderboardArray.size(); i++) {

                //Get the string of the leaderboard array
                leaderboardMatrix[i][0] = (String) leaderboardArray.get(i);
                //Split the string to get the name and the score
                leaderboardMatrix[i] = leaderboardMatrix[i][0].split(" : ");
                //Print the leaderboard

            }
            //Sort the leaderboard by higher score
            for (int i = 0; i < leaderboardArray.size(); i++) {
                for (int j = 0; j < leaderboardArray.size(); j++) {
                    if (Integer.parseInt(leaderboardMatrix[i][1]) > Integer.parseInt(leaderboardMatrix[j][1])) {
                        String[] temp = leaderboardMatrix[i];
                        leaderboardMatrix[i] = leaderboardMatrix[j];
                        leaderboardMatrix[j] = temp;
                    }
                }
            }
            //Print the top 5 leaderboard
            for (int i = 0; i < 5; i++) {
                if (i < leaderboardArray.size()) {
                    System.out.println(leaderboardMatrix[i][0] + ":" + leaderboardMatrix[i][1]);
                } else {
                    break;
                }
            }

        } catch (JSONException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    //Read map from JSON
    public static Map readMap() {
        Map map;
        //read map from files
        String path = "src/Data.json";
        try {
            FileReader reader = new FileReader(path);
            JSONArray jsonArray;
            jsonArray = (JSONArray) new JSONParser().parse(reader);

            //get leaderboard element
            JSONObject tilesMap = (JSONObject) jsonArray.get(1);
            //get leaderboard array
            JSONArray tilesArray = (JSONArray) tilesMap.get("Map_tiles");
            System.out.println(tilesArray);
            //tilesArray to string
            String tilesString = tilesArray.toString();

            //array size of the map
            //while , count the number of , in the string
            //get mapTiles
            String[] mapTile = tilesString.split(",");

            // for every ■, size ++
            int size = 0;
            for (int i = 0; i < mapTile[0].length(); i++) {
                char c = mapTile[0].charAt(i);
                if (c == '■') {
                    size++;
                }
            }

            // keep only the ■,□,▲,?,*,&,$
            String[][] mapToConvert = new String[size][size];
            int j;
            //get real map character
            System.out.println(mapTile.length);
            int nb;
            for (j = 0; j < mapTile.length; j++) {
                nb = 0;
                for (int i = 0; i < mapTile[j].length(); i++) {
                    char c = mapTile[j].charAt(i);
                    if (c == '■' || c == '□' || c == '▴' || c == '?' || c == '*' || c == '&' || c == '$' || c == '!' || c == 'o') {
                        mapToConvert[nb][j] = String.valueOf(c);
                        nb++;
                    }
                }
            }

            Tile[][] mapTiles = new Tile[size][size];
            for (j = 0; j < mapTiles.length; j++) {
                for (int i = 0; i < mapTiles.length; i++) {
                    //Get char at [j][i]
                    char c = mapToConvert[j][i].charAt(0);

                    // if char = ■ create a wall
                    if (c == '■') {
                        mapTiles[j][i] = Tile.Wall;
                    }
                    // if char = □ create a floor
                    else if (c == '□') {
                        mapTiles[j][i] = Tile.Empty;
                    }
                    // if char = ▲ create a chest
                    else if (c == '▴') {
                        mapTiles[j][i] = Tile.Chest;
                    }
                    // if char = ? create an unknown
                    else if (c == '?') {
                        mapTiles[j][i] = Tile.Unknown;
                    }
                    // if char = E create an enemy
                    else if (c == '*') {
                        mapTiles[j][i] = Tile.Enemy;
                    }
                    // if char = P create a player
                    else if (c == '&') {
                        mapTiles[j][i] = Tile.Spawn;
                    }
                    // if char = C create a chest
                    else if (c == '$') {
                        mapTiles[j][i] = Tile.Merchant;
                    }
                    // if char = C create a boss
                    else if (c == '!') {
                        mapTiles[j][i] = Tile.Boss;
                    }
                    // if char = C create a chest
                    else if (c == 'o') {
                        mapTiles[j][i] = Tile.End;
                    }
                }
            }
            System.out.println("Get map...");

            map = new Map(Weather.SUNNY, mapTiles);
            return map;

        } catch (JSONException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
