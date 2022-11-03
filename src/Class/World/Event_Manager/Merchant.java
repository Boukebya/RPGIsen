package Class.World.Event_Manager;
import Class.Equipments.Equipment;
import Class.wildLife.Hero;
import Class.wildLife.Stat;

import java.util.Scanner;

public class Merchant extends Type_Events {
    String name_Object;
    int rarity;

    //Constructor
    public Merchant(Type_Events type_event,String name_Object) {
        super(type_event.name,type_event.rarity);
        this.name_Object= name_Object;
    }

    @Override
    public void Interact(Hero hero, Equipment[] Equipments) {
        // get 4 objects to sell to the player
        System.out.println("After travelling through the dungeon, you find a man who offers you to buy some of his objects.");
        //get 4 random equipments
        Equipment[] soldObjects = new Equipment[4];
        int i=0;
        while(i != 4){
            soldObjects[i] = Equipments[(int) (Math.random() * Equipments.length)];
            //get the rarity of the object
            int rarity = soldObjects[i].getRarity();
            //if rarity is 1, 35% chance to get it
            if(rarity == 1){
                if(Math.random() < 0.35){
                    i++;
                }
            }
            //if rarity is 2, 20% chance to get it
            else if(rarity == 2){
                if(Math.random() < 0.2){
                    i++;
                }
            }
            //if rarity is 3, 10% chance to get it
            else if(rarity == 3){
                if(Math.random() < 0.1){
                    i++;
                }
            }
            //if rarity is 4, 5% chance to get it
            else if(rarity == 4){
                if(Math.random() < 0.05){
                    i++;
                }
            }
            //if rarity is 5, 1% chance to get it
            else if(rarity == 5){
                if(Math.random() < 0.01){
                    i++;
                }
            }
        }
        //print the objects
        System.out.println("You can buy these objects:");
        //create cost list of sold objects
        int[] cost = new int[4];
        for (int j = 0; j < 4; j++) {
            //get the rarity of the object
            int rarity = soldObjects[j].getRarity();
            //if rarity is 1, cost is 100
            if(rarity == 1){
                cost[j] = 10;
            }
            //if rarity is 2, cost is 200
            else if(rarity == 2){
                cost[j] = 25;
            }
            //if rarity is 3, cost is 300
            else if(rarity == 3){
                cost[j] = 50;
            }
            //if rarity is 4, cost is 400
            else if(rarity == 4){
                cost[j] = 80;
            }
            //if rarity is 5, cost is 500
            else if(rarity == 5){
                cost[j] = 120;
            }
        }

        for (int j = 0; j < soldObjects.length; j++) {
            System.out.println((j+1) + ". " + soldObjects[j].getName() + " (" + soldObjects[j].getRarity() + ") for " + cost[j] + " gold");
        }
        //Create a scanner
        Scanner scanner = new Scanner(System.in);
        //Ask the player which object he wants to buy
        System.out.println("Which object do you want to buy? (1,2,3,4) (0 to exit)");
        String choice = scanner.nextLine();
        //if player type 1, buy the first object
        if(choice.equals("1")){
            //if player has enough money, buy the object
            if(hero.getGold() >= cost[0]){
                //remove the money
                hero.modifyGold(- cost[0]);
                //add the object to the inventory
                //hero.getInventory().add(soldObjects[0]);
                //print that the object has been bought
                System.out.println("You bought the " + soldObjects[0].getName() + "!");
            }
            //if player doesn't have enough money, print that he doesn't have enough money
            else{
                System.out.println("You don't have enough money to buy this object!");
            }
        }
        if(choice.equals("2")){
            //if player has enough money, buy the object
            if(hero.getGold() >= cost[1]){
                //remove the money
                hero.modifyGold(-cost[1]);
                //add the object to the inventory
                //hero.getInventory().add(soldObjects[0]);
                //print that the object has been bought
                System.out.println("You bought the " + soldObjects[1].getName() + "!");
            }
            //if player doesn't have enough money, print that he doesn't have enough money
            else{
                System.out.println("You don't have enough money to buy this object!");
            }
        }
        if(choice.equals("3")){
            //if player has enough money, buy the object
            if(hero.getGold() >= cost[2]){
                //remove the money
                hero.modifyGold(- cost[2]);
                //add the object to the inventory
                //hero.getInventory().add(soldObjects[0]);
                //print that the object has been bought
                System.out.println("You bought the " + soldObjects[2].getName() + "!");
            }
            //if player doesn't have enough money, print that he doesn't have enough money
            else{
                System.out.println("You don't have enough money to buy this object!");
            }
        }
        if(choice.equals("4")){
            //if player has enough money, buy the object
            if(hero.getGold() >= cost[3]){
                //remove the money
                hero.modifyGold(- cost[3]);
                //add the object to the inventory
                //hero.getInventory().add(soldObjects[0]);
                //print that the object has been bought
                System.out.println("You bought the " + soldObjects[3].getName() + "!");
            }
            //if player doesn't have enough money, print that he doesn't have enough money
            else{
                System.out.println("You don't have enough money to buy this object!");
            }
        }
        if(choice.equals("0")){
            System.out.println("You exit the shop without buying anything.");
        }
    }

}
