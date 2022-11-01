package Class.World.Event_Manager;

import Class.Equipments.Equipment;
import Class.wildLife.Hero;

import java.util.Objects;
import java.util.Scanner;

public class Enemy_Fight extends Type_Events {
    String name_Object;
    String description;
    int rarity;
    String[] choices;

    //Constructor
    public Enemy_Fight(Type_Events type_Event, String name_Object,String description,int rarity,String[] choices) {
        super(type_Event.name, type_Event.rarity);
        this.name_Object= name_Object;
        this.description = description;
        this.rarity = rarity;
        this.choices = choices;
    }

    @Override
    public void Interact(Hero hero, Equipment[] weapons) {
        //Print that we find a chest named name_object
        System.out.println(description);
        System.out.println("name_Object" + "wants to fight !");
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
            for (String s : choices) {
                if (Objects.equals(choice, s)) {
                    System.out.println("Need to implement fight manager...");
                    return;
                }
            }
            System.out.println("Wrong input, be serious you're on a duty !");
        }
    }

}
