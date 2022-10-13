import Class.*;
public class Main {


    //Ici on teste
    public static void Test(){
        //Ici ondéclare la météo
        Weather actual = Weather.FOGGY;
        //Ici on déclare les tiles
        Tile Chest = new Tile("Chest");
        Tile Empty = new Tile("Empty");
        Tile Enemy = new Tile("Enemy");
        Tile Wall = new Tile("Wall");
        Tile Spawn = new Tile("Spawn");
        Tile End = new Tile("End");
        Tile Position = new Tile("Position");

        //Ici on crée la map
        Tile intro [][] = new Tile[7][7];
        for (int y = 0; y < 7; y++) {
            for (int x = 0; x < 7; x++) {
                intro[x][y] = Wall;
            }
        }
        intro[0][0] = Spawn ;
        intro[1][0] = Empty ;
        intro[2][0] = Empty ;
        intro[3][0] = Enemy ;
        intro[4][0] = Empty ;
        intro[5][0] = Chest ;
        intro[3][1] = Empty ;
        intro[3][1] = Empty ;
        intro[3][2] = End ;
        //Map object
        Map map1 = new Map(actual,intro);

        //On print pour vérifier
        System.out.println(actual.GetAcc());
        System.out.println(Chest.GetTile(Chest));

        System.out.println(map1.GetWeather(map1));
        System.out.println(map1.GetMap(map1));
        Tile tile1 = map1.GetTileMap(map1,0,0);
        System.out.println(tile1.GetTile(tile1));
        //On montre la carte
        map1.ShowMap(map1);

    }


    public static void main(String[] args) {
        System.out.println("Begin");
        Test();
    }
}