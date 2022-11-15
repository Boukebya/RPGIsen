import Class.Equipments.Equipment;
import Class.World.Event_Manager.Chest;
import Class.World.Event_Manager.Merchant;
import Class.World.Event_Manager.Type_Events;
import Class.World.Event_Manager.Unknown;
import Class.World.Map;
import Class.World.Tile;
import Class.gameMechanics.Fight;
import Class.wildLife.Enemy;
import Class.wildLife.Hero;
import Class.wildLife.Spell;
import Class.wildLife.Stat;
import java.util.Objects;
import static Class.World.Event_Manager.Type_Events.GetEventFromRarity;
import static Class.fileManagement.gameObjects.*;
import static Class.fileManagement.jsonManager.*;
import static Class.wildLife.Enemy.GetBoss;
import static Class.wildLife.Enemy.GetEnemy;
import static Class.wildLife.Hero.SpawnHero;
import java.util.Scanner;

public class Main {

    public static void gameManager(Chest[] chests, Enemy[] enemies, Merchant[] merchants, Equipment[] equipment, Enemy[] Bosses, Spell [] heroSpells){

        //Get the name of the player
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose your name");
        String name = sc.nextLine();

        //Get map from json
        Map map = readMap();
        map.ShowMap(map);

        //Spawn Hero
        Stat heroStat = new Stat(1,1,10,10,20,20,1,name);
        Hero character = new Hero(name,heroStat,SpawnHero(map));
        System.out.println("Your role is to kill the boss of the dungeon and get out alive");


        Tile eventManager = Tile.Position;
        //While Event_Manager != End, and character hp > 0
        while(!eventManager.GetTile().equals("End") && character.getStat().getHealth() > 0){
            //Move player with scanner parameters and get the tile where he is
            eventManager = character.MoveHero(character, map);

            //If new location is a chest
            if (Objects.equals(eventManager.GetTile(), "Chest")){
              //Get random chest with function
                Chest actualChest = (Chest) GetEventFromRarity(chests);
                //Interact with chest
                actualChest.Interact(character,equipment);
            }
            // else if new location is a merchant
            else if (Objects.equals(eventManager.GetTile(), "Merchant")){
                //Get random merchant with function
                Merchant actualMerchant = (Merchant) GetEventFromRarity(merchants);
                actualMerchant.Interact(character,equipment);
                map.ShowMap(map);
            }
            //Else If new location is unknown
            else if (Objects.equals(eventManager.GetTile(), "Unknown")){
                //Get random unknown with function
                Type_Events Unknown_Event = new Type_Events("Unknown",1);
                Unknown unknown = new Unknown(Unknown_Event,"Unknown");
                //Interact with unknown
                unknown.Interact(character,heroSpells);

            }
            //Else If new location is an enemy
            else if (Objects.equals(eventManager.GetTile(), "Enemy")){
                //Get random enemy with function
                Enemy actualEnemy = GetEnemy(enemies, character.getLevel());
                //Create fight
                new Fight(character,actualEnemy);
                map.ShowMap(map);
            }
            //Else If new location is a boss
            else if (Objects.equals(eventManager.GetTile(), "Boss")){
                System.out.println("boss");
                //Get random enemy with function
                Enemy actualBoss = GetBoss(Bosses, character.getLevel());
                System.out.println(actualBoss.getName());
                //Create fight
                 new Fight(character,actualBoss);
                map.ShowMap(map);
            }

        }

        //If Class.World.Event_Manager == End or character die, we print the end of the game
        System.out.println("The end");
        writeFile(character.getScore(),character.getName());
        displayScore();
    }


    public static void main(String[] args) {

        gameManager(InitChests(),InitEnemies(InitEnemiesSpells()),InitMerchants(),InitEquipments(),InitBosses(InitEnemiesSpells()),InitHeroSpells());

    }

}
