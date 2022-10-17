package Class;

import java.util.Objects;

public class Map {
    Weather weather;
    Tile [][] map;

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
        Tile tile = map.map[x][y];
        return tile;
    }

    public void ShowMap(Map map){
        int length = map.GetMap(map).length;
        for (int y = 0; y < length;y++) {
            for (int x = 0; x < length; x++) {
                Tile tile = (map.GetTileMap(map,x,y));

                if (tile.GetTile() == "Wall")
                    System.out.print("■ ");
                if (tile.GetTile() == "Empty")
                    System.out.print("□ ");
                if (tile.GetTile() == "Enemy")
                    System.out.print("* ");
                if (tile.GetTile() == "Chest")
                    System.out.print("▴ ");
                if (tile.GetTile() == "Spawn")
                    System.out.print("▦ ");
                if (tile.GetTile() == "End")
                    System.out.print("▣ ");
                if (tile.GetTile() == "Position")
                    System.out.print("Ω ");
            }
            System.out.println("");
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

    public void ChangeTile(Map map,int pos[],Tile tile){
        Tile[][] actualmap;
        actualmap = map.GetMap(map);
        actualmap[pos[0]][pos[1]] = tile;
    }
}


