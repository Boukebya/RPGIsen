package Class.World;
import java.util.Objects;

// Class to manage map, show,create, modify, get tile
public class Map {
    //Map parameters
    Weather weather;
    Tile[][] map;

    //Constructor
    public Map(Weather weather, Tile [][] map) {
        this.weather = weather;
        this.map = map;
    }
    public Map(){}

    //Getters
    public Weather GetWeather(Map map ){
        return map.weather;
    }
    public Tile[][] GetMap(Map map ){
        return map.map;
    }

    //Setters
    public void SetWeather(Map map, Weather weather){
        map.weather = weather;
    }

    //Methods
    //Display map
    public void ShowMap(Map map){
        int length = map.GetMap(map).length;
        //Double for to display map
        for (int y = 0; y < length;y++) {
            for (int x = 0; x < length; x++) {
                Tile tile = (map.GetTileMap(map,x,y));
                //Create a switch for every tiles
                switch (tile.GetTile()) {
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
    public void ChangeTile(Map map, int[] pos, Tile tile){
        Tile[][] actualMap;
        actualMap = map.GetMap(map);
        actualMap[pos[0]][pos[1]] = tile;
    }
    //Return tile's type from coordinates
    public Tile GetTileMap(Map map,int x, int y){
        return map.map[x][y];
    }
    //Return the first Tile of this type
    public int[] PosTile(Map map,Tile tile) {
        int [] pos = new int[2];
        int length = map.GetMap(map).length;
        String category = tile.GetTile();
        for (int y = 0; y < length;y++) {
            for (int x = 0; x < length; x++) {
                Tile TileSeek = map.GetTileMap(map,x,y);
                String categorySeek = TileSeek.GetTile();
                if (Objects.equals(categorySeek, category)) {
                    pos[0] = x;
                    pos[1] = y;
                    return pos;
                }
            }
        }
    return null;
    }
    public static void Info() {
        System.out.println("□ Empty, ■ Wall, & Player, o End, ▴ Chest, * Enemy, ! Boss, $ Merchant, ? Unknown");
    }

}


