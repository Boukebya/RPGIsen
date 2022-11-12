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
import java.io.*;
import java.util.Objects;
import static Class.World.Event_Manager.Type_Events.GetEventFromRarity;
import static Class.wildLife.Enemy.GetBoss;
import static Class.wildLife.Enemy.GetEnemy;
import static Class.wildLife.Hero.SpawnHero;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.Scanner;
import org.json.JSONException;
import org.json.simple.parser.ParseException;

public class Main {

    //Function to initialize chests
    public static Chest[] InitChests(){
        //Create array of chests
        Chest[] chests = new Chest[4];
        //We create a new type of event
        Type_Events Chest_Event = new Type_Events("Chest",1);

        //We create a wooden chest
        Chest wooden_Chest = new Chest(Chest_Event,"Wooden chest", new int[]{75, 10, 10, 5, 0},"You enter a small cave, it seems that somebody left quickly..\nAfter some research, you find something interesting..",new String[]{"Open it","Leave it"},new String[]{"You found an item !","You left it"});
        chests[0] = wooden_Chest;
        //modify rarity
        chests[0].setRarity(50);

        //We create a bloody chest
        Chest bloody_Chest = new Chest(Chest_Event,"bloody chest",new int[]{20, 30, 30, 15, 5},"Lost in ruins, you find a corpse of an adventurer just like you.\nThe trail of blood left behind seems to lead somewhere..\nYou decide to follow it and after a moment you you come across a red chest.\nyou don't know why, but you instinctively understand what the chest wants..\nshivers run through your body.",new String[]{"Open","Leave"},new String[]{"The chest opens by itself, after a few seconds, it vomits an object...","as you walk away, grunts echo through the ruins"});
        chests[1] = bloody_Chest;
        //modify rarity
        chests[1].setRarity(20);

        //We create a golden chest
        Chest golden_Chest = new Chest(Chest_Event,"golden chest",new int[]{40, 30, 25, 5, 0},".You found a golden chest...",new String[]{"Open","Leave"},new String[]{"You found an item!","You left it"});
        chests[2] = golden_Chest;
        //modify rarity
        chests[2].setRarity(20);

        //We create a legendary chest
        Chest legendary_Chest = new Chest(Chest_Event,"legendary chest",new int[]{0, 10, 30, 30, 30},".You found a legendary chest!",new String[]{"Open","Leave"},new String[]{"You found an item!","You left it"});
        chests[3] = golden_Chest;
        //modify rarity
        chests[3].setRarity(10);

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
        Enemy[] enemies = new Enemy[10];

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
        Stat skeletonStat = new Stat(5,1,0,0,15,15,2,"Skeleton");
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

        //Create human stat
        Stat humanStat = new Stat(5,5,10,10,20,20,3,"Human");
        //Create human enemies
        Spell[] BanditSpells = new Spell[1];
        BanditSpells[0] = enemiesSpells[0];
        enemies[4] = new Enemy(humanStat,"Bandit",BanditSpells);
        Spell[] hoodlumSpells = new Spell[1];
        enemies[5] = new Enemy(humanStat,"Hoodlum",hoodlumSpells);

        //Create elf stat
        Stat elfStat = new Stat(5,5,10,10,25,25,4,"Elf");
        //Create elf enemies
        Spell[] mageElfSpells = new Spell[1];
        mageElfSpells[0] = enemiesSpells[0];
        enemies[6] = new Enemy(elfStat,"Mage elf",mageElfSpells);
        Spell[] archerElfSpells = new Spell[1];
        enemies[7] = new Enemy(elfStat,"Archer elf",archerElfSpells);

        //Create orc stat
        Stat orcStat = new Stat(1,8,10,10,30,30,5,"orc");
        //Create orc enemies
        Spell[] OrcSpells = new Spell[1];
        OrcSpells[0] = enemiesSpells[0];
        enemies[8] = new Enemy(orcStat,"Orc",OrcSpells);
        Spell[] assassinOrcSpells = new Spell[1];
        enemies[9] = new Enemy(orcStat,"Assassin orc",assassinOrcSpells);

        return enemies;
    }
    //Function to initialize bosses
    public static Enemy[] InitBosses(){
        //Create array of enemies_fight
        Enemy[] Bosses = new Enemy[8];

        //Create Ogre stat
        Stat ogreStat = new Stat(1,8,0,0,100,100,5,"Ogre");
        //First boss
        Spell[] ogreSpells = new Spell[1];
        Bosses[0] = new Enemy(ogreStat,"Bratirek",ogreSpells);
        //Create Breana stat
        Stat BreanaStat = new Stat(5,1,10,10,75,75,5,"Human");
        //Create Breana boss
        Spell[] BreanaSpells = new Spell[1];
        Bosses[1] = new Enemy(BreanaStat,"Breana, the silent",BreanaSpells);

        //Create Ogre stat
        Stat centaurStat = new Stat(1,14,10,10,150,150,10,"Centaur");
        //First boss
        Spell[] EupenioSpells = new Spell[1];
        Bosses[2] = new Enemy(centaurStat,"Eupenio",EupenioSpells);
        //Create Breana stat
        Stat OrgothStat = new Stat(8,1,20,20,75,75,10,"Orc");
        //Create Breana boss
        Spell[] OrgothSpells = new Spell[1];
        Bosses[3] = new Enemy(OrgothStat,"Orgoth",OrgothSpells);

        //Create Ogre stat
        Stat golemStat = new Stat(1,18,10,10,200,200,15,"Golem");
        //First boss
        Spell[] golemSpells = new Spell[1];
        Bosses[4] = new Enemy(golemStat,"Unon",golemSpells);
        //Create Breana stat
        Stat CiradylStat = new Stat(8,4,10,10,150,150,15,"Elf");
        //Create Breana boss
        Spell[] CiradylSpells = new Spell[1];
        Bosses[5] = new Enemy(CiradylStat,"Ciradyl",CiradylSpells);

        //Create Ogre stat
        Stat dragonStat = new Stat(1,20,10,10,300,300,20,"Dragon");
        //First boss
        Spell[] dragonSpells = new Spell[1];
        Bosses[6] = new Enemy(dragonStat,"bralzranth, Eternal Fire",dragonSpells);
        //Create Breana stat
        Stat Alvaxoth = new Stat(10,10,20,20,250,250,20,"Demon");
        //Create Breana boss
        Spell[] AlvaxothSpells = new Spell[1];
        Bosses[7] = new Enemy(Alvaxoth,"Alvaxoth",AlvaxothSpells);



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
        Equipment[] equipment = new Equipment[26];

        equipment[0] = new Weapon(2,0.05f,1,"Club","Broken club");
        equipment[1] = new Weapon(3,0.1f,2,"Club","Common club");
        equipment[2] = new Weapon(5,0.15f,3,"Club","Rare club");
        equipment[3] = new Weapon(8,0.2f,4,"Club","Mythic club");
        equipment[4] = new Weapon(12,0.3f,5,"Club","Legendary stick");

        equipment[5] = new Weapon(1,0.1f,1,"Sword","Broken sword");
        equipment[6] = new Weapon(2,0.15f,2,"Sword","Common sword");
        equipment[7] = new Weapon(5,0.3f,3,"Sword","Rare sword");
        equipment[8] = new Weapon(7,0.35f,4,"Sword","Mythic sword");
        equipment[9] = new Weapon(10,0.4f,5,"Sword","Legendary sword");

        equipment[10] = new Weapon(2,0.1f,1,"Bow","Broken bow");
        equipment[11] = new Weapon(3,0.2f,2,"Bow","Common bow");
        equipment[12] = new Weapon(4,0.4f,3,"Bow","Rare bow");
        equipment[13] = new Weapon(6,0.5f,4,"Bow","Mythic bow");
        equipment[14] = new Weapon(8,0.7f,5,"Bow","Legendary bow");

        equipment[15] = new Armor(2,"Leather armor");
        equipment[15].setRarity(1);
        equipment[16] = new Armor(4,"Iron armor");
        equipment[16].setRarity(2);
        equipment[17] = new Armor(7,"plate armor");
        equipment[17].setRarity(3);
        equipment[18] = new Armor(10,"Dragon slayer armor");
        equipment[18].setRarity(4);
        equipment[19] = new Armor(12,"Araqiel's armor");
        equipment[19].setRarity(5);
        
        equipment[20] = new Consumable("Potion of health",2,"Health",10);
        equipment[21] = new Consumable("Potion of mana",2,"Mana",10);
        equipment[22] = new Consumable("Great potion of health",3,"Health",25);
        equipment[23] = new Consumable("Great potion of mana",3,"Mana",50);
        equipment[24] = new Consumable("Legendary potion of health",4,"Health",50);
        equipment[25] = new Consumable("Legendary potion of mana",4,"Mana",50);

        return equipment;
    }

    //Test it
    public static void Test(Chest[] chests, Enemy[] enemies, Unknown[] unknowns, Merchant[] merchants, Equipment[] equipment,Enemy[] Bosses){

        //Get the choice of the player
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose your name");
        String name = sc.nextLine();

        //Spawn Hero
        Stat heroStat = new Stat(1,1,10,10,20,20,1,name);

        Map map = readMap();
        Hero character = new Hero(name,heroStat,SpawnHero(map));


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
                actualChest.Interact(character,equipment);


            }
            // else if new location is a merchant
            else if (Objects.equals(Event_Manager.GetTile(), "Merchant")){
                //Get random merchant with function
                Merchant actualMerchant = (Merchant) GetEventFromRarity(merchants);
                actualMerchant.Interact(character,equipment);
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
                Enemy actualBoss = (Enemy) GetBoss(Bosses, character.getLevel());
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

        writeFile(character.getScore(),character.getName());
        displayScore();
    }

    //Write scores in a file
    public static void writeFile(int score, String name){
        String path = "src/Data.json";

        JSONObject array = new JSONObject();
        try {
            FileReader reader = new FileReader(path);
            JSONObject jsonObj = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonArray = (JSONArray) new JSONParser().parse(reader);

            //get leaderboard element
            JSONObject leaderboard = (JSONObject) jsonArray.get(0);
            //get leaderboard array
            JSONArray leaderboardArray = (JSONArray) leaderboard.get("Scores");
            //System.out.println(leaderboardArray);

            String scoreString = name + " : " + score;
            //add scoreString to leaderboard array
            JSONObject newScore = new JSONObject();
            newScore.put("Scores", scoreString);

            //System.out.println(scoreString);
            //add to leaderboard array
            leaderboardArray.add(scoreString);

            //write to file
            FileWriter file = new FileWriter(path);
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();



        }
        catch (JSONException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }
    //Display scores of players
    public static void displayScore(){
        String path = "src/Data.json";

        JSONObject array = new JSONObject();
        try {
            FileReader reader = new FileReader(path);
            JSONObject jsonObj = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonArray = (JSONArray) new JSONParser().parse(reader);

            //get leaderboard element
            JSONObject leaderboard = (JSONObject) jsonArray.get(0);
            //get leaderboard array
            JSONArray leaderboardArray = (JSONArray) leaderboard.get("Scores");
            //print leaderboard elements
            System.out.println("Leaderboard :");
            String[][] leaderboardMatrix = new String[0][];
            //Create a matrix of leaderboarsarray size
            leaderboardMatrix = new String[leaderboardArray.size()][2];
            for (int i = 0; i < leaderboardArray.size(); i++) {

                //Get the string of the leaderboard array
                leaderboardMatrix[i][0] = (String) leaderboardArray.get(i);
                //Split the string to get the name and the score
                leaderboardMatrix[i] = leaderboardMatrix[i][0].split(" : ");
                //Print the leaderboard

            }
            //Sort the leaderboard by higher score
            for (int i = 0; i < leaderboardArray.size(); i++) {
                for (int j = 0; j < leaderboardArray.size(); j++) {
                    if (Integer.parseInt(leaderboardMatrix[i][1]) > Integer.parseInt(leaderboardMatrix[j][1])){
                        String[] temp = leaderboardMatrix[i];
                        leaderboardMatrix[i] = leaderboardMatrix[j];
                        leaderboardMatrix[j] = temp;
                    }
                }
            }
            //Print the leaderboard
            for (int i = 0; i < leaderboardArray.size(); i++) {
                System.out.println(leaderboardMatrix[i][0] + " : " + leaderboardMatrix[i][1]);
            }



        }
        catch (JSONException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    //Read map from JSON
    public static Map readMap(){
        Map map;
        //read map from files
        String path = "src/Data.json";
        JSONObject array = new JSONObject();
        try { FileReader reader = new FileReader(path);
            JSONObject jsonObj = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonArray = (JSONArray) new JSONParser().parse(reader);

            //get leaderboard element
            JSONObject tilesMap = (JSONObject) jsonArray.get(1);
            //get leaderboard array
            JSONArray tilesArray = (JSONArray) tilesMap.get("Map_tiles");

            //print leaderboard elements
            //System.out.println(tilesArray);

            //tilesArray to string
            String tilesString = tilesArray.toString();

            //array size of the map
            //while , count the number of , in the string
            //get mapTiles
            String[] mapTile = tilesString.split(",");


            // for every ■, size ++
            int size = 0;
            for (int i = 0; i < mapTile[0].length(); i++) {
                char c = mapTile[0].charAt(i);
                if (c == '■'){
                    size++;
                }
            }

            // keep only the ■,□,▲,?,*,&,$
            String[][] mapTile2 = new String[size][size];
            int j = 0;
            //get real map character
            System.out.println(mapTile.length);
            int nb;
            for(j = 0; j < mapTile.length; j++){
                nb=0;
                for (int i = 0; i < mapTile[j].length(); i++) {
                    char c = mapTile[j].charAt(i);
                    if (c == '■' || c == '□' || c == '▴' || c == '?' || c == '*' || c == '&' || c == '$'|| c == '!'|| c == 'o'){
                        mapTile2[j][nb] = String.valueOf(c);
                        nb++;
                    }
                }
            }


            Tile[][] mapTiles = new Tile[size][size];

             for(j = 0 ; j < mapTiles.length; j++) {
                for (int i = 0; i < mapTiles.length; i++) {
                    //Get char at [j][i]
                    char c = mapTile2[j][i].charAt(0);

                    // if char = ■ create a wall
                    if (c == '■') {
                        mapTiles[j][i] = Tile.Wall;
                    }
                    // if char = □ create a floor
                    else if (c == '□') {
                        mapTiles[j][i] = Tile.Empty;
                    }
                    // if char = ▲ create a chest
                    else if (c == '▴') {
                        mapTiles[j][i] = Tile.Chest;
                    }
                    // if char = ? create an unknown
                    else if (c == '?') {
                        mapTiles[j][i] = Tile.Unknown;
                    }
                    // if char = E create an enemy
                    else if (c == '*') {
                        mapTiles[j][i] = Tile.Enemy;
                    }
                    // if char = P create a player
                    else if (c == '&') {
                        mapTiles[j][i] = Tile.Spawn;
                    }
                    // if char = C create a chest
                    else if (c == '$') {
                        mapTiles[j][i] = Tile.Merchant;
                    }
                    // if char = C create a boss
                    else if (c == '!') {
                        mapTiles[j][i] = Tile.Boss;
                    }
                    // if char = C create a chest
                    else if (c == 'o') {
                        mapTiles[j][i] = Tile.End;
                    }
                }
            }
            System.out.println("Get map...");

            map = new Map(Weather.SUNNY,mapTiles);
            map.ShowMap(map);
            return map;

        }
        catch (JSONException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

/*
Need to:
-Implement JSON file to read map                                                     DONE
-Do consumable                                                                       DONE
-Create enemies, weapons, armors...                                                  DONE
-Improve merchant                                                                    DONE
-Create chest and manage blood chest                                                 DONE, aborted blood chest
-Create a way for hero to add spell and create them

Do this when the project is finished:
-Input management and errors
-Enhance code and manage class
 */
    public static void main(String[] args) {
        System.out.println("Begin");
        Test(InitChests(),InitEnemies(InitEnemiesSpells()),InitUnknowns(),InitMerchants(),InitEquipments(),InitBosses());

    }
}
