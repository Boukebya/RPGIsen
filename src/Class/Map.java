package Class;
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
                Tile tile = map.GetTileMap(map,x,y);
                if (tile.GetTile(tile) == "Wall")
                    System.out.print("■ ");
                if (tile.GetTile(tile) == "Empty")
                    System.out.print("□ ");
                if (tile.GetTile(tile) == "Enemy")
                    System.out.print("* ");
                if (tile.GetTile(tile) == "Chest")
                    System.out.print("▴ ");
                if (tile.GetTile(tile) == "Spawn")
                    System.out.print("▦ ");
                if (tile.GetTile(tile) == "End")
                    System.out.print("▣ ");
                if (tile.GetTile(tile) == "Position")
                    System.out.print("Ω ");
            }
            System.out.println("");
            }

        }

    public int[] PosTile(Map map,Tile tile) {
        int [] pos = new int[2];
        int length = map.GetMap(map).length;
        String category = tile.GetTile(tile);
        for (int y = 0; y < length;y++) {
            for (int x = 0; x < length; x++) {
                Tile tileseek = map.GetTileMap(map,x,y);
                if (tileseek.GetTile(tileseek) == category) {
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


