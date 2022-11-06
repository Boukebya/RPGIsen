package Class.World.Event_Manager;
import Class.Equipments.Equipment;
import Class.wildLife.Hero;
import java.util.Scanner;

public class Merchant extends Type_Events {
    String name_Merchant;

    //Constructor
    public Merchant(Type_Events type_event,String name_Object) {
        super(type_event.name,type_event.rarity);
        this.name_Merchant = name_Object;
    }

    //Methods
    //get random items for shop
    public static Equipment[] getShop(Equipment[] Equipments){
        Equipment[] soldObjects = new Equipment[5];
        int i =0;
        while(i != 5){
            int random = (int) (Math.random() * Equipments.length);
            soldObjects[i] = Equipments[random];
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
        return soldObjects;
    }
    //get cost of items
    public static int[] getCost(Equipment[] soldObjects) {
        int[] cost = new int[5];
        //create cost
        for (int j = 0; j < 5; j++) {
            //get the rarity of the object
            int rarity = soldObjects[j].getRarity();
            //if rarity is 1, cost is 100
            if (rarity == 1) {
                cost[j] = 10;
            }
            //if rarity is 2, cost is 200
            else if (rarity == 2) {
                cost[j] = 25;
            }
            //if rarity is 3, cost is 300
            else if (rarity == 3) {
                cost[j] = 50;
            }
            //if rarity is 4, cost is 400
            else if (rarity == 4) {
                cost[j] = 80;
            }
            //if rarity is 5, cost is 500
            else if (rarity == 5) {
                cost[j] = 120;
            }
        }
        return cost;
    }

    @Override
    public void Interact(Hero hero, Equipment[] Equipments) {
        // get 5 objects to sell to the player
        System.out.println("After travelling through the dungeon, you find a man who offers you to buy some of his objects.");

        //get 5 random equipments and their costs
        Equipment[] soldObjects = getShop(Equipments);
        int[] cost = getCost(soldObjects);

        String choice = "";
        //if player type 1, buy the first object
        while(choice.equals("0") != true) {
            //print the objects
            System.out.println("You can buy these objects:");
            //display items and cost
            for (int j = 0; j < soldObjects.length; j++) {
                if (soldObjects[j] != null) {
                    System.out.println((j+1) + ". " + soldObjects[j].getName() + " (" + soldObjects[j].getRarity() + ") for " + cost[j] + " gold");;
                }
                else{
                    System.out.println("Empty");
                }

            }
            //Create a scanner
            Scanner scanner = new Scanner(System.in);
            //Ask the player which object he wants to buy
            System.out.println("Which object do you want to buy? (1,2,3,4,5) (0 to exit)");
            choice = scanner.nextLine();
            if (choice.equals("1")) {
                //if player has enough money, buy the object
                if (hero.getGold() >= cost[0]) {
                    //remove the money
                    hero.modifyGold(-cost[0]);
                    //add the object to the inventory
                    hero.equipmentManagement(soldObjects[0]);
                    //print that the object has been bought
                    System.out.println("You bought the " + soldObjects[0].getName() + "!");
                    //Remove item from shop
                    soldObjects[0] = null;
                }
                //if player doesn't have enough money, print that he doesn't have enough money
                else {
                    System.out.println("You don't have enough money to buy this object!");
                }
            }
            if (choice.equals("2")) {
                //if player has enough money, buy the object
                if (hero.getGold() >= cost[1]) {
                    //remove the money
                    hero.modifyGold(-cost[1]);
                    //add the object to the inventory
                    hero.equipmentManagement(soldObjects[1]);
                    //print that the object has been bought
                    System.out.println("You bought the " + soldObjects[1].getName() + "!");
                    //remove item from shop
                    soldObjects[1] = null;
                }
                //if player doesn't have enough money, print that he doesn't have enough money
                else {
                    System.out.println("You don't have enough money to buy this object!");
                }
            }
            if (choice.equals("3")) {
                //if player has enough money, buy the object
                if (hero.getGold() >= cost[2]) {
                    //remove the money
                    hero.modifyGold(-cost[2]);
                    //add the object to the inventory
                    hero.equipmentManagement(soldObjects[2]);
                    //print that the object has been bought
                    System.out.println("You bought the " + soldObjects[2].getName() + "!");
                    //remove item from shop
                    soldObjects[2] = null;
                }
                //if player doesn't have enough money, print that he doesn't have enough money
                else {
                    System.out.println("You don't have enough money to buy this object!");
                }
            }
            if (choice.equals("4")) {
                //if player has enough money, buy the object
                if (hero.getGold() >= cost[3]) {
                    //remove the money
                    hero.modifyGold(-cost[3]);
                    //add the object to the inventory
                    hero.equipmentManagement(soldObjects[3]);
                    //print that the object has been bought
                    System.out.println("You bought the " + soldObjects[3].getName() + "!");
                    //remove item from shop
                    soldObjects[3] = null;
                }
                //if player doesn't have enough money, print that he doesn't have enough money
                else {
                    System.out.println("You don't have enough money to buy this object!");
                }
            }
            if (choice.equals("5")) {
                //if player has enough money, buy the object
                if (hero.getGold() >= cost[4]) {
                    //remove the money
                    hero.modifyGold(-cost[4]);
                    //add the object to the inventory
                    hero.equipmentManagement(soldObjects[4]);
                    //print that the object has been bought
                    System.out.println("You bought the " + soldObjects[4].getName() + "!");
                    //remove item from shop
                    soldObjects[4] = null;
                }
                //if player doesn't have enough money, print that he doesn't have enough money
                else {
                    System.out.println("You don't have enough money to buy this object!");
                }
            }
            if (choice.equals("0")) {
                System.out.println("You exit the shop.");
            }
        }
    }
}
