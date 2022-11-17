package rpgIsen.Class.World.EventManager;

import rpgIsen.Class.Equipments.Armor;
import rpgIsen.Class.Equipments.Consumable;
import rpgIsen.Class.Equipments.Equipment;
import rpgIsen.Class.Equipments.Weapon;
import rpgIsen.Class.wildLife.Hero;

import java.util.Scanner;

public class Merchant extends TypeEvents {
    String name_Merchant;
    int rarity;
    String type;

    //Constructor
    public Merchant(TypeEvents type_event, String name_Object, int rarity, String type) {
        super(type_event.name, type_event.rarity);
        this.name_Merchant = name_Object;
        this.rarity = rarity;
        this.type = type;
    }

    //Methods
    //get random items for shop
    public static Equipment[] getShop(Equipment[] Equipments, String type) {
        Equipment[] soldObjects = new Equipment[5];
        int i = 0;

        while (i != 5) {
            //redo until soldObject is not null
            while (soldObjects[i] == null) {
                int random = (int) (Math.random() * Equipments.length);
                //If type is "Potions", keep only potions
                if (type.equals("Potions")) {
                    if (Equipments[random] instanceof Consumable) {
                        soldObjects[i] = Equipments[random];
                    }
                }
                //if type is "Goods" keep only equipments
                else if (type.equals("Goods")) {
                    if (Equipments[random] instanceof Weapon || Equipments[random] instanceof Armor) {
                        soldObjects[i] = Equipments[random];
                    }
                }
            }

            //get the rarity of the object
            int rarity = soldObjects[i].getRarity();
            //if rarity is 1, 35% chance to get it
            if (rarity == 1) {
                if (Math.random() < 0.35) {
                    i++;
                }
            }
            //if rarity is 2, 20% chance to get it
            else if (rarity == 2) {
                if (Math.random() < 0.2) {
                    i++;
                }
            }
            //if rarity is 3, 10% chance to get it
            else if (rarity == 3) {
                if (Math.random() < 0.1) {
                    i++;
                }
            }
            //if rarity is 4, 5% chance to get it
            else if (rarity == 4) {
                if (Math.random() < 0.05) {
                    i++;
                }
            }
            //if rarity is 5, 1% chance to get it
            else if (rarity == 5) {
                if (Math.random() < 0.01) {
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

    //Purchase system of an item
    public void getChoice(int choice, Equipment[] soldObjects, int[] cost, Hero hero) {
        if (choice == 0) return;
        int item = choice - 1;
        //if player has enough money, buy the object
        if (hero.getGold() >= cost[item]) {
            //remove the money
            hero.modifyGold(-cost[item]);
            //add the object to the inventory
            //if the object is an instance of consumable
            if (soldObjects[item] instanceof Consumable) {
                Consumable.addConsumable((Consumable) soldObjects[item], hero);
            } else {
                hero.equipmentManagement(soldObjects[item]);
            }
            //print that the object has been bought
            System.out.println("You bought the " + soldObjects[item].getName() + "!");
            //Remove item from shop
            soldObjects[item] = null;
        }
        //if player doesn't have enough money, print that he doesn't have enough money
        else {
            System.out.println("You don't have enough money to buy this object!");
        }

    }

    @Override
    public void interact(Hero hero, Equipment[] Equipments) {
        // get 5 objects to sell to the player
        System.out.println("After travelling through the dungeon, you find a man who offers you to buy some of his objects.");
        System.out.println("His name is " + name_Merchant + "." + "Say hi.");

        //get 5 random equipments and their costs
        Equipment[] soldObjects = getShop(Equipments, this.type);
        int[] cost = getCost(soldObjects);

        String choice = "";
        //if player type 1, buy the first object
        while (!choice.equals("0")) {
            //print the objects
            System.out.println("You got " + hero.getGold() + " gold");
            System.out.println("You can buy these objects:");
            //display items and cost
            for (int j = 0; j < soldObjects.length; j++) {
                if (soldObjects[j] != null) {
                    System.out.println((j + 1) + ". " + soldObjects[j].getName() + " (" + soldObjects[j].getRarity() + ") for " + cost[j] + " gold");
                } else {
                    System.out.println("Empty");
                }
            }

            //Create a scanner
            Scanner scanner = new Scanner(System.in);

            //Ask the player which object he wants to buy
            System.out.println("Which object do you want to buy? (1,2,3,4,5) (0 to exit)");
            choice = scanner.nextLine();
            getChoice(Integer.parseInt(choice), soldObjects, cost, hero);
        }
        System.out.println("You exit the shop.");
    }
}
