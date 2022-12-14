package rpgIsen.Class;

import rpgIsen.Class.Equipments.Equipment;
import rpgIsen.Class.wildLife.Enemy;
import rpgIsen.Class.wildLife.Hero;
import rpgIsen.Class.wildLife.Spell;
import rpgIsen.Class.wildLife.Stat;
import rpgIsen.Class.World.EventManager.Chest;
import rpgIsen.Class.World.EventManager.Merchant;
import rpgIsen.Class.World.EventManager.TypeEvents;
import rpgIsen.Class.World.EventManager.Unknown;
import rpgIsen.Class.World.Map;
import rpgIsen.Class.World.Tile;
import rpgIsen.Class.gameMechanics.Fight;

import java.util.Objects;
import java.util.Scanner;

import static rpgIsen.Class.FileManagement.GameObjects.*;
import static rpgIsen.Class.FileManagement.JsonManager.*;
import static rpgIsen.Class.wildLife.Enemy.GetBoss;
import static rpgIsen.Class.wildLife.Enemy.GetEnemy;
import static rpgIsen.Class.wildLife.Hero.SpawnHero;
import static rpgIsen.Class.World.EventManager.TypeEvents.getEventFromRarity;

public class Main {

    public static void gameManager(Chest[] chests, Enemy[] enemies, Merchant[] merchants, Equipment[] equipment, Enemy[] Bosses, Spell[] heroSpells) {

        //Get the name of the player
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose your name");
        String name = sc.nextLine();

        //Get map from json
        Map map = readMap();
        map.showMap(map);
        //display weather
        System.out.println("The weather is " + map.getWeather());

        //Spawn Hero
        Stat heroStat = new Stat(1, 1, 10, 10, 20, 20, 1, name);
        Hero character = new Hero(name, heroStat, SpawnHero(map));
        System.out.println("Your role is to kill the boss of the dungeon and get out alive");


        Tile eventManager = Tile.Position;
        //While Event_Manager != End, and character hp > 0
        while (!eventManager.getTile().equals("End") && character.getStat().getHealth() > 0) {
            //Move player with scanner parameters and get the tile where he is
            eventManager = character.MoveHero(character, map);

            //If new location is a chest
            if (Objects.equals(eventManager.getTile(), "Chest")) {
                //Get random chest with function
                Chest actualChest = (Chest) getEventFromRarity(chests);
                //Interact with chest
                actualChest.interact(character, equipment);
            }
            // else if new location is a merchant
            else if (Objects.equals(eventManager.getTile(), "Merchant")) {
                //Get random merchant with function
                Merchant actualMerchant = (Merchant) getEventFromRarity(merchants);
                actualMerchant.interact(character, equipment);
                map.showMap(map);
            }
            //Else If new location is unknown
            else if (Objects.equals(eventManager.getTile(), "Unknown")) {
                //Get random unknown with function
                TypeEvents unknownEvent = new TypeEvents("Unknown", 1);
                Unknown unknown = new Unknown(unknownEvent, "Unknown");
                //Interact with unknown
                unknown.interact(character, heroSpells);

            }
            //Else If new location is an enemy
            else if (Objects.equals(eventManager.getTile(), "Enemy")) {
                //Get random enemy with function
                Enemy actualEnemy = GetEnemy(enemies, character.getLevel());
                //Create fight
                new Fight(character, actualEnemy);
                map.showMap(map);
            }
            //Else If new location is a boss
            else if (Objects.equals(eventManager.getTile(), "Boss")) {
                System.out.println("boss");
                //Get random enemy with function
                Enemy actualBoss = GetBoss(Bosses, character.getLevel());
                System.out.println(actualBoss.getName());
                //Create fight
                new Fight(character, actualBoss);
                map.showMap(map);
            }

        }

        //If rpgIsen.Class.World.Event_Manager == End or character die, we print the end of the game
        System.out.println("The end");
        writeFile(character.getScore(), character.getName());
        displayScore();
    }


    public static void main(String[] args) {

        gameManager(InitChests(), InitEnemies(InitEnemiesSpells()), InitMerchants(), InitEquipments(), InitBosses(InitEnemiesSpells()), InitHeroSpells());

    }

}
