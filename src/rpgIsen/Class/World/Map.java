package rpgIsen.Class.World;

import java.util.Objects;

// rpgIsen.Class to manage map, show,create, modify, get tile
public class Map {
    //Map parameters
    Weather weather;
    Tile[][] map;

    //Constructor
    public Map(Weather weather, Tile[][] map) {
        this.weather = weather;
        this.map = map;
    }

    public Map() {
    }

    public static void info() {
        System.out.println("□ Empty, ■ Wall, & Player, o End, ▴ Chest, * Enemy, ! Boss, $ Merchant, ? Unknown");
    }

    //Getters
    public Weather getWeather() {
        return this.weather;
    }

    public Tile[][] getMap() {
        return this.map;
    }

    //Setters
    public void setWeather(Map map, Weather weather) {
        map.weather = weather;
    }

    //Methods
    //Display map
    public void showMap(Map map) {
        int length = map.getMap().length;
        //Double for to display map
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < length; x++) {
                Tile tile = (map.getTileMap(map, x, y));
                //Create a switch for every tiles
                switch (tile.getTile()) {
                    case "Empty" -> System.out.print("□ ");
                    case "Wall" -> System.out.print("■ ");
                    case "Spawn", "Position" -> System.out.print("& ");
                    case "End" -> System.out.print("o ");
                    case "Chest" -> System.out.print("▴ ");
                    case "Enemy" -> System.out.print("* ");
                    case "Boss" -> System.out.print("! ");
                    case "Merchant" -> System.out.print("$ ");
                    default -> System.out.print("? ");
                }
            }
            System.out.println();
        }
    }

    //Change the tile's type
    public void changeTile(Map map, int[] pos, Tile tile) {
        Tile[][] actualMap;
        actualMap = map.getMap();
        actualMap[pos[0]][pos[1]] = tile;
    }

    //Return tile's type from coordinates
    public Tile getTileMap(Map map, int x, int y) {
        return map.map[x][y];
    }

    //Return the first Tile of this type
    public int[] positionTile(Map map, Tile tile) {
        int[] pos = new int[2];
        int length = map.getMap().length;
        String category = tile.getTile();
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < length; x++) {
                Tile TileSeek = map.getTileMap(map, x, y);
                String categorySeek = TileSeek.getTile();
                if (Objects.equals(categorySeek, category)) {
                    pos[0] = x;
                    pos[1] = y;
                    return pos;
                }
            }
        }
        return null;
    }

}


