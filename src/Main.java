import Class.World.*;
import Class.World.Event_Manager.*;
import Class.aroundLife.Fight;
import Class.aroundLife.Weapon;
import Class.wildLife.Enemie;
import Class.wildLife.Hero;
import java.util.Objects;

import static Class.wildLife.Hero.SpawnHero;


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
        intro[8][6] = Chest;
        intro[5][8] = Chest;
        //Create enemy
        intro[1][7] = Enemy;
        intro[6][2] = Enemy;
        intro[2][6] = Enemy;
        intro[5][1] = Enemy;
        intro[4][5] = Enemy;
        intro[8][4] = Enemy;
        intro[7][7] = Enemy;
        intro[7][5] = Enemy;
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
        intro[7][2] = Wall;
        intro[8][2] = Wall;
        intro[8][5] = Wall;
        intro[7][4] = Wall;
        intro[6][4] = Wall;
        intro[5][4] = Wall;
        intro[4][4] = Wall;
        intro[4][6] = Wall;
        intro[4][7] = Wall;
        intro[4][8] = Wall;
        intro[6][6] = Wall;
        intro[7][6] = Wall;
        intro[6][8] = Wall;
        intro[7][8] = Wall;
        //Create unknown
        intro[3][3] = Unknown;
        intro[5][5] = Unknown;
        intro[3][1] = Unknown;
        intro[8][1] = Unknown;


        //Create Map object, with its weather and its tiles
        return new Map(mapWeather,intro);

    }
    //Function to initialize chests
    public static Chest[] InitChests(){
        //Create array of chests
        Chest[] chests = new Chest[3];
        //We create a new type of event
        Type_Events Chest_Event = new Type_Events("Chest",1);

        //We create a wooden chest
        Chest wooden_Chest = new Chest(Chest_Event,"Wooden chest","You enter a small cave, it seems that somebody left quickly..\nAfter some research, you find something interesting..",new String[]{"Open it","Leave it"},new String[]{"You found a sword !","You left it"});
        chests[0] = wooden_Chest;
        //modify rarity
        chests[0].modifyRarity(50);

        //We create a bloody chest
        Chest bloody_Chest = new Chest(Chest_Event,"bloody chest","Lost in ruins, you find a corpse of an adventurer just like you.\nThe trail of blood left behind seems to lead somewhere..\nYou decide to follow it and after a moment you you come across a red chest.\nyou don't know why, but you instinctively understand what the chest wants..\nshivers run through your body.",new String[]{"Bring your hand closer","Leave fast!"},new String[]{"The chest opens by itself and bites you, after a few seconds, it vomits an object...","as you walk away, grunts echo through the ruins"});
        chests[1] = bloody_Chest;
        //modify rarity
        chests[1].modifyRarity(20);

        //We create a golden chest
        Chest golden_Chest = new Chest(Chest_Event,"golden chest",".You found a golden chest...",new String[]{"Open","Leave"},new String[]{"You found a sword !","You left it"});
        chests[2] = golden_Chest;
        //modify rarity
        chests[2].modifyRarity(30);

        return chests;
    }
    //Function to initialize enemies
    public static Enemy_Fight[] InitEnemies(){
        //Create array of enemies_fight
        Enemy_Fight[] enemies = new Enemy_Fight[2];
        //We create a new type of event
        Type_Events Enemy_Event = new Type_Events("Enemy",1);

        return enemies;
    }
    //Function to initialize Unknown
    public static Unknown[] InitUnknowns(){
        //Create array og Unknown
        Unknown[] unknowns = new Unknown[2];
        //We create a new type of event
        Type_Events Unknown_Event = new Type_Events("Unknown",1);

        return unknowns;
    }
    //Function to initialize merchants
    public static Merchant[] InitMerchants(){
        //Create array of merchants
        Merchant[] merchants = new Merchant[2];
        //We create a new type of event
        Type_Events Merchant_Event = new Type_Events("Merchant",1);

        return merchants;
    }
    //Function to initialize weapons
    public static Weapon[] InitWeapons(){
        //Create array of weapons
        Weapon[] weapons = new Weapon[2];
        //We create a new type of event
        weapons[0] = new Weapon(2,10,2,"Sword","Wooden sword");
        weapons[1] = new Weapon(2,10,5,"Club","Wooden club");
        return weapons;
    }
    //Function to get an event from rarity
    public static Type_Events GetEventFromRarity(Type_Events[] events) {
        //Create new random array with same size as events
        int[] randoms = new int[events.length];
        //limit is the sum of every rarity
        int limit = 0;
        //for every elements, get rarity of events and add it to randoms
        for (int i = 0; i < events.length; i++) {
            randoms[i] = (int) events[i].getRarity();
            System.out.println(randoms[i]);
            limit+= randoms[i];
        }
        System.out.println("limit = " + limit);

        //Create random number between 0 and limit
        int random_number = (int) (Math.random() * limit);
        System.out.println("random = "+random_number);

        int count = 0;
        //for every elements, if random is smaller than the rarity, return the event
        for (int i = 0; i < events.length; i++) {
            count +=  (int) events[i].getRarity();
            if (random_number < count) {
                System.out.println("event " + i + " selected");
                return events[i];
            }
        }

        System.out.println("default event");
        return events[0];
    }


    //Test it
    public static void Test(Map map, Chest[] chests, Enemy_Fight[] enemies, Unknown[] unknowns, Merchant[] merchants, Weapon[] weapons){
        //Spawn Hero
        Hero character = new Hero("Blanc-Louis",3,SpawnHero(map));
        //Show Map
        map.ShowMap(map);

        //We create a new Tile to know when we have to manage an event
        Tile Event_Manager = Tile.Position;
        //While Class.World.Event_Manager != End, we continue to travel through the map
        while(!Event_Manager.GetTile().equals("End")){
            //Move player with scanner parameters and get the tile where he is
            Event_Manager = character.MoveHero(character, map);
            //If new location is a chest
            if (Objects.equals(Event_Manager.GetTile(), "Chest")){
              //Get random chest with function
                Chest actualChest = (Chest) GetEventFromRarity(chests);
                //Interact with chest
                actualChest.Interact(character);

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
        //If Class.World.Event_Manager == End, we print the end of the game
        System.out.println("End of map !");
    }

    public static void testClovisFight(){
        Hero player = new Hero("Hero",3,new int[]{0,0});
        Enemie dragon = new Enemie("Dragon",1);
        Fight fight = new Fight(player, dragon);
    }
    public static void main(String[] args) {
        System.out.println("Begin");
        //Test(InitMap(),InitChests(),InitEnemies(),InitUnknowns(),InitMerchants(),InitWeapons());
        testClovisFight();
    }
}
