package Class.World;

import java.util.Objects;

public class Map {
    Weather weather;
    Tile[][] map;

    public Map(Weather weather, Tile [][] map) {
        this.weather = weather;
        this.map = map;
    }
    public Weather GetWeather(Map map ){
        return map.weather;
    }

    public Tile[][] GetMap(Map map ){
        return map.map;
    }


    public Tile GetTileMap(Map map,int x, int y){
        return map.map[x][y];
    }

    public void ShowMap(Map map){
        int length = map.GetMap(map).length;
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
                    default -> System.out.print("? ");
                }
            }
            System.out.println();
            }
        }

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

    public void ChangeTile(Map map, int[] pos, Tile tile){
        Tile[][] actualMap;
        actualMap = map.GetMap(map);
        actualMap[pos[0]][pos[1]] = tile;
    }
}


