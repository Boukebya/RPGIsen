import Class.World.Hero;
import Class.World.Map;
import Class.World.Tile;
import Class.World.Weather;
import java.util.Objects;
import static Class.World.Hero.SpawnHero;


public class Main {

    //Function to initialize the map
    public static Map InitMap(){
        //We create weather
        Weather mapWeather = Weather.FOGGY;
        //We declare all tiles to create map after
        Tile Chest = Tile.Chest;
        Tile Enemy = Tile.Enemy;
        Tile Wall = Tile.Wall;
        Tile Empty = Tile.Empty;
        Tile Spawn = Tile.Spawn;
        Tile End = Tile.End;
        Tile Unknown = Tile.Unknown;

        //Here we create map
        Tile[][] intro = new Tile[10][10];
        //Create walls
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                if (y == 0 || y == 9 || x == 0 || x == 9) {
                    intro[y][x] = Wall;
                } else {
                    intro[y][x] = Empty;
                }
            }
        }
        //Create spawn
        intro[1][1] = Spawn;
        //Create end
        intro[8][8] = End;
        //Create chest
        intro[1][8] = Chest;
        //Create enemy
        intro[1][7] = Enemy;
        intro[8][1] = Enemy;
        //Create path
        intro[2][2] = Wall;
        intro[2][3] = Wall;
        intro[2][4] = Wall;
        intro[2][5] = Wall;
        intro[2][7] = Wall;
        intro[2][8] = Wall;
        intro[3][2] = Wall;
        intro[4][2] = Wall;
        intro[5][2] = Wall;
        //Create unknown
        intro[3][3] = Unknown;
        intro[5][5] = Unknown;


        //Create Map object, with its weather and its tiles
        return new Map(mapWeather,intro);

    }

    //Test it
    public static void Test(Map map){
        //Spawn Hero
        Hero character = new Hero("Blanc-Louis",SpawnHero(map));
        //Show Map
        map.ShowMap(map);

        //We create a new Tile to know when we have to manage an event
        Tile Event_Manager = Tile.Position;
        //While Event_Manager != End, we continue to travel through the map
        while(!Event_Manager.GetTile().equals("End")){
            //Move player with scanner parameters and get the tile where he is
            Event_Manager = character.MoveHero(character, map);
            //If new location is a chest
            if (Objects.equals(Event_Manager.GetTile(), "Chest")){
                System.out.println("You found a chest !");
                //Open chest

            }
            //Else If new location is an enemy
            else if (Objects.equals(Event_Manager.GetTile(), "Enemy")){
                System.out.println("You found an enemy !");
                //Fight enemy

            }
            //Else If new location is unknown
            else if (Objects.equals(Event_Manager.GetTile(), "Unknown")){
                System.out.println("You found an unknown tile !");
                //Manage unknown tile

            }

            //Clear console
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
        //If Event_Manager == End, we print the end of the game
        System.out.println("End of map !");
    }


    public static void main(String[] args) {
        System.out.println("Begin");
        Test(InitMap());
    }
}
