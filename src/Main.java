import Class.Equipments.Armor;
import Class.Equipments.Consumable;
import Class.Equipments.Equipment;
import Class.Equipments.Weapon;
import Class.World.Event_Manager.Chest;
import Class.World.Event_Manager.Merchant;
import Class.World.Event_Manager.Type_Events;
import Class.World.Event_Manager.Unknown;
import Class.World.Map;
import Class.World.Tile;
import Class.World.Weather;
import Class.gameMechanics.Fight;
import Class.wildLife.Enemy;
import Class.wildLife.Hero;
import Class.wildLife.Spell;
import Class.wildLife.Stat;
import java.util.Objects;
import static Class.World.Event_Manager.Type_Events.GetEventFromRarity;
import static Class.wildLife.Enemy.GetBoss;
import static Class.wildLife.Enemy.GetEnemy;
import static Class.wildLife.Hero.SpawnHero;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.Iterator;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;

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
        Tile Boss = Tile.Boss;
        Tile Merchant = Tile.Merchant;

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
        intro[8][4] = Merchant;
        intro[7][7] = Boss;
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
        intro[8][1] = Merchant;

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
        Chest wooden_Chest = new Chest(Chest_Event,"Wooden chest", new int[]{75, 10, 10, 5, 0},"You enter a small cave, it seems that somebody left quickly..\nAfter some research, you find something interesting..",new String[]{"Open it","Leave it"},new String[]{"You found an item !","You left it"});
        chests[0] = wooden_Chest;
        //modify rarity
        chests[0].setRarity(50);

        //We create a bloody chest
        Chest bloody_Chest = new Chest(Chest_Event,"bloody chest",new int[]{20, 30, 30, 15, 5},"Lost in ruins, you find a corpse of an adventurer just like you.\nThe trail of blood left behind seems to lead somewhere..\nYou decide to follow it and after a moment you you come across a red chest.\nyou don't know why, but you instinctively understand what the chest wants..\nshivers run through your body.",new String[]{"Bring your hand closer","Leave fast!"},new String[]{"The chest opens by itself and bites you, after a few seconds, it vomits an object...","as you walk away, grunts echo through the ruins"});
        chests[1] = bloody_Chest;
        //modify rarity
        chests[1].setRarity(20);

        //We create a golden chest
        Chest golden_Chest = new Chest(Chest_Event,"golden chest",new int[]{40, 30, 25, 5, 0},".You found a golden chest...",new String[]{"Open","Leave"},new String[]{"You found an item!","You left it"});
        chests[2] = golden_Chest;
        //modify rarity
        chests[2].setRarity(30);

        return chests;
    }
    //Function to initialize spells
    public static Spell[] InitEnemiesSpells(){
        // initialize array of spell
        Spell[] enemiesSpells = new Spell[6];
        enemiesSpells[0] = new Spell("Attack", 0, 0, 1,"Basic", "none",80,"Enemy");
        enemiesSpells[1] = new Spell("Attack", 0, 0, 1,"Basic", "none",80,"Enemy");
        enemiesSpells[2] = new Spell("Heal", 0, 5, 10,"Heal", "none",100,"Self");
        enemiesSpells[3] = new Spell("Buff", 0, 5, 2,"Buff", "none",100,"Self");
        enemiesSpells[4] = new Spell("Charge", 1, 2, 2,"Huge", "none",50,"Enemy");
        enemiesSpells[5] = new Spell("Stab stab stab !", 1, 2, 4,"Multiple", "none",75,"Enemy");

        return enemiesSpells;
    }
    //Function to initialize enemies
    public static Enemy[] InitEnemies(Spell[] enemiesSpells){
        //Create array of enemies_fight
        Enemy[] enemies = new Enemy[4];

        //Create goblin stat
        Stat goblinStat = new Stat(1,1,5,5,10,10,1,"Goblin");
        //Create goblin enemies
        Spell[] spearGoblinSpells = new Spell[2];
        spearGoblinSpells[0] = enemiesSpells[0];
        spearGoblinSpells[1] = enemiesSpells[4];
        enemies[0] = new Enemy(goblinStat,"Spear goblin",spearGoblinSpells);
        enemies[0].setStrength(3);


        Spell[] daggerGoblinSpells = new Spell[2];
        daggerGoblinSpells[0] = enemiesSpells[1];
        daggerGoblinSpells[1] = enemiesSpells[5];
        enemies[1] = new Enemy(goblinStat,"Knife goblin",daggerGoblinSpells);
        enemies[1].setDexterity(2);

        //Create Skeleton stat
        Stat skeletonStat = new Stat(5,1,0,0,10,20,2,"Skeleton");
        //Create Skeleton enemies
        skeletonStat.setStrength(3);
        Spell[] swordSkeletonSpells = new Spell[1];
        swordSkeletonSpells[0] = enemiesSpells[0];
        enemies[2] = new Enemy(skeletonStat,"Bag of bones",swordSkeletonSpells);
        skeletonStat.setStrength(1);

        skeletonStat.setDexterity(3);
        Spell[] daggerSkeletonSpells = new Spell[1];
        daggerSkeletonSpells[0] = enemiesSpells[1];
        enemies[3] = new Enemy(skeletonStat,"Baguette Skeleton",daggerSkeletonSpells);
        skeletonStat.setDexterity(1);

        return enemies;
    }
    //Function to initialize bosses
    public static Enemy[] InitBosses(){
        //Create array of enemies_fight
        Enemy[] Bosses = new Enemy[2];

        //Create Ogre stat
        Stat ogreStat = new Stat(1,8,0,0,100,100,5,"Ogre");
        //First boss
        Spell[] ogreSpells = new Spell[1];
        Bosses[0] = new Enemy(ogreStat,"Bratirek",ogreSpells);

        //Create Breana stat
        Stat BreanaStat = new Stat(8,1,10,10,75,75,5,"Human");
        //Create Breana boss
        Spell[] BreanaSpells = new Spell[1];
        Bosses[1] = new Enemy(BreanaStat,"Breana, the silent",BreanaSpells);



        return Bosses;
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
        //Create type events merchant
        Type_Events Merchant_Event = new Type_Events("Merchant",1);
        //We create a new type of event
        merchants[0] = new Merchant(Merchant_Event,"Merchant");
        merchants[1] = new Merchant(Merchant_Event,"Potion Merchant");


        return merchants;
    }
    //Function to initialize weapons
    public static Equipment[] InitEquipments(){
        //Create array of weapons
        Equipment[] equipment = new Equipment[12];
        //We create a new type of event
        equipment[0] = new Weapon(1,0.05f,1,"Club","Broken stick");
        equipment[1] = new Weapon(2,0.1f,2,"Club","Common stick");
        equipment[2] = new Weapon(3,0.15f,3,"Club","Rare stick");
        equipment[3] = new Weapon(4,0.2f,4,"Club","Mythic stick");
        equipment[4] = new Weapon(5,0.3f,5,"Club","Legendary stick");

        equipment[5] = new Armor(2,"Leather armor");
        equipment[5].setRarity(1);
        equipment[6] = new Armor(4,"Iron armor");
        equipment[6].setRarity(2);
        equipment[7] = new Armor(7,"plate armor");
        equipment[7].setRarity(3);
        equipment[8] = new Armor(10,"Dragon slayer armor");
        equipment[8].setRarity(4);
        equipment[9] = new Armor(12,"Araqiel's armor");
        equipment[9].setRarity(5);


        equipment[10] = new Consumable("Potion of health",1,"Health",10);
        equipment[11] = new Consumable("Potion of mana",1,"Mana",10);

        return equipment;
    }

    //Test it
    public static void Test(Map map, Chest[] chests, Enemy[] enemies, Unknown[] unknowns, Merchant[] merchants, Equipment[] weapons,Enemy[] Bosses){
        //Spawn Hero
        Stat heroStat = new Stat(1,1,10,10,20,20,1,"Hero");
        Hero character = new Hero("Blanc-Louis",heroStat,SpawnHero(map));
        //Show Map
        map.ShowMap(map);

        //We create a new Tile to know when we have to manage an event
        Tile Event_Manager = Tile.Position;
        //While Class.World.Event_Manager != End, and character hp > 0
        while(!Event_Manager.GetTile().equals("End") && character.getStat().getHealth() > 0){
            //Move player with scanner parameters and get the tile where he is
            Event_Manager = character.MoveHero(character, map);
            //If new location is a chest
            if (Objects.equals(Event_Manager.GetTile(), "Chest")){
              //Get random chest with function
                Chest actualChest = (Chest) GetEventFromRarity(chests);
                //Interact with chest
                actualChest.Interact(character,weapons);


            }
            // else if new location is a merchant
            else if (Objects.equals(Event_Manager.GetTile(), "Merchant")){
                //Get random merchant with function
                Merchant actualMerchant = (Merchant) GetEventFromRarity(merchants);
                actualMerchant.Interact(character,weapons);
            }

            //Else If new location is an enemy
            else if (Objects.equals(Event_Manager.GetTile(), "Enemy")){
                //Get random enemy with function
                Enemy actualEnemy = (Enemy) GetEnemy(enemies, character.getLevel());
                //Create fight
                Fight fight = new Fight(character,actualEnemy);
            }
            //Else If new location is a boss
            else if (Objects.equals(Event_Manager.GetTile(), "Boss")){
                //Get random enemy with function
                Enemy actualBoss = (Enemy) GetBoss(Bosses);
                //Create fight
                Fight fight = new Fight(character,actualBoss);
            }

            //Else If new location is unknown
            else if (Objects.equals(Event_Manager.GetTile(), "Unknown")){
                System.out.println("You found an unknown tile !");
                //Manage unknown tile

            }

        }
        //If Class.World.Event_Manager == End, we print the end of the game
        System.out.println("The end");
    }

    //This function need to convert the Json file to a map
    public static void JSONParser(){
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/Data.json"));
            //Get Map



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/*
Need to:
-Implement JSON file to read map
-Do consumable                                                                       DONE
-Enhance code and manage class
-Create enemies, weapons, armors...
-Input management and errors
-Improve merchant
-Create chest and manage blood chest
-Create a way for hero to add spell and create them
 */
    public static void main(String[] args) {
        System.out.println("Begin");
        Test(InitMap(),InitChests(),InitEnemies(InitEnemiesSpells()),InitUnknowns(),InitMerchants(),InitEquipments(),InitBosses());
        //JSONParser();
    }
}
