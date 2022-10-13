package Class;
public class Map {
    Weather weather;
    Tile [][] map = new Tile[7][7];

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
                    System.out.print("▪ ");
            }
            System.out.println("");
            }

        }
    }


