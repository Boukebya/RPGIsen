package Class.Event_Manager;

import Class.World.Hero;

import java.util.Objects;
import java.util.Scanner;

public class Chest extends Type_Events {
    String name_Object;
    String description;
    int rarity;
    String[] choices;
    //Consequences array
    String[] consequences;

    public Chest(Type_Events type_Event, String name_Object,int rarity,  String description, String[] choices, String[] consequences) {
        super(type_Event.name);
        this.name_Object= name_Object;
        this.description = description;
        this.rarity = rarity;
        this.choices = choices;
        this.consequences = consequences;

    }

    //Compare the choice with the choices array
    @Override
    public void Interact(Hero hero) {
        //Print that we find a chest named name_object
        System.out.println(description);
        System.out.println("You find a " + name_Object + " !");
        //print different choices
        System.out.println("What do you want to do ?");
        System.out.println("Type your choice to proceed:");
        for (int i = 0; i < choices.length; i++) {
            System.out.println(": "+choices[i]);
        }

        //Create scanner to get the choice
        while (true) {
            //scanner to know what the player want to do
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();
            for (int i = 0; i < choices.length; i++) {
                if (Objects.equals(choice, choices[i])) {
                    System.out.println(consequences[i]);
                    return;
                }
            }
            System.out.println("Wrong input, be serious you're on a duty !");
        }
    }

}
