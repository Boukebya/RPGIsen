import Class.World.*;
import Class.Event_Manager.*;
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
    //Function to initialize chests
    public static Chest[] InitChests(){
        //Create array of chests
        Chest[] chests = new Chest[2];
        //We create a new type of event
        Type_Events Chest_Event = new Type_Events("Chest");

        //We create a wooden chest
        Chest wooden_Chest = new Chest(Chest_Event,"Wooden chest",1,"You enter a small cave, it seems that somebody left quickly..\nAfter some research, you find something interesting..",new String[]{"Open it","Leave it"},new String[]{"You found a sword !","You left it"});
        chests[0] = wooden_Chest;
        //We create a bloody chest
        Chest bloody_Chest = new Chest(Chest_Event,"bloody chest",5,"Lost in ruins, you find a corpse of an adventurer just like you.\nThe trail of blood left behind seems to lead somewhere..\nYou decide to follow it and after a moment you you come across a red chest.\nyou don't know why, but you instinctively understand what the chest wants..\nshivers run through your body.",new String[]{"Bring your hand closer","Leave fast!"},new String[]{"The chest opens by itself and bites you, after a few seconds, it vomits an object...","as you walk away, grunts echo through the ruins"});
        chests[1] = bloody_Chest;

        return chests;
    }
    //Function to initialize enemies
    public static Enemy_Fight[] InitEnemies(){
        //Create array of enemies_fight
        Enemy_Fight[] enemies = new Enemy_Fight[2];
        //We create a new type of event
        Type_Events Enemy_Event = new Type_Events("Enemy");

        return enemies;
    }
    //Function to initialize Unknown
    public static Unknown[] InitUnknowns(){
        //Create array og Unknown
        Unknown[] unknowns = new Unknown[2];
        //We create a new type of event
        Type_Events Unknown_Event = new Type_Events("Unknown");

        return unknowns;
    }
    //Function to initialize merchants
    public static Merchant[] InitMerchants(){
        //Create array of merchants
        Merchant[] merchants = new Merchant[2];
        //We create a new type of event
        Type_Events Merchant_Event = new Type_Events("Merchant");

        return merchants;
    }

    //Test it
    public static void Test(Map map, Chest[] chests, Enemy_Fight[] enemies, Unknown[] unknowns, Merchant[] merchants){
        //Spawn Hero
        Hero character = new Hero("Blanc-Louis",SpawnHero(map));
        //Show Map
        map.ShowMap(map);

        //We create a new Tile to know when we have to manage an event
        Tile Event_Manager = Tile.Position;
        //While Class.Event_Manager != End, we continue to travel through the map
        while(!Event_Manager.GetTile().equals("End")){
            //Move player with scanner parameters and get the tile where he is
            Event_Manager = character.MoveHero(character, map);
            //If new location is a chest
            if (Objects.equals(Event_Manager.GetTile(), "Chest")){
              chests[1].Interact(character);


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
        //If Class.Event_Manager == End, we print the end of the game
        System.out.println("End of map !");
    }


    public static void main(String[] args) {
        System.out.println("Begin");
        Test(InitMap(),InitChests(),InitEnemies(),InitUnknowns(),InitMerchants());
    }
}
