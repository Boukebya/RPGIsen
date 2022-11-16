package Class.World.EventManager;
import Class.Equipments.Equipment;
import Class.WildLife.Hero;

import java.util.Objects;
import java.util.Scanner;

public class Chest extends TypeEvents {
    String name_Object;
    String description;
    int [] modifier;
    String[] choices;
    //Consequences array
    String[] consequences;

    //Constructor
    public Chest(TypeEvents type_Event, String name_Object, int[] modifier, String description, String[] choices, String[] consequences) {
        super(type_Event.name,type_Event.rarity);
        this.name_Object= name_Object;
        this.modifier = modifier;
        this.description = description;
        this.choices = choices;
        this.consequences = consequences;
    }

    //Methods
    //Return the item to drop
    public Equipment dropItem(Equipment[] equipments){
        //get modifier
        int[] modifier = this.modifier;
        //random number
        int random_number = (int) (Math.random() * 100);
        //if random number is smaller than modifier, drop item
        int count = 0;
        for (int i = 0; i < modifier.length; i++) {
            count += modifier[i];
            if (random_number < count) {
                //System.out.println("You found a tier " + (i+1) + " item!");
                int rank = i+1;
                //for each weapon, check if it's a tier i weapon
                while(true) {
                    //System.out.println("seeking weapon");
                    for (Equipment equipment : equipments) {
                        if (equipment.getRarity() == rank) {
                            //if it's a tier i weapon 20% chance to drop it
                            if (Math.random() < 0.2) {
                                System.out.println("You found a " + equipment.getName() + "!");
                                return equipment;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void interact(Hero hero, Equipment [] weapons) {
        //Print that we find a chest named name_object
        System.out.println(description);
        System.out.println("You find a " + name_Object + " !");
        //print different choices
        System.out.println("What do you want to do ?");
        System.out.println("Type your choice to proceed:");
        for (String s : choices) {
            System.out.println(": " + s);
        }

        //Create scanner to get the choice
        while (true) {
            //scanner to know what the player want to do
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();
            for (int i = 0; i < choices.length; i++) {
                if (Objects.equals(choice, choices[i])) {
                    System.out.println(consequences[i]);
                    if (i == 0) {
                        //If we take the object, we add it to the inventory
                        Equipment item = dropItem(weapons);
                        hero.equipmentManagement(item);
                    }
                    return;
                }
            }
            System.out.println("Wrong input, be serious you're on a duty !");
        }
    }
}
