package Class.Event_Manager;

import Class.World.Hero;

public class Type_Events {
    //name of the event
    String name;
    //description of the event
    String description;
    //rarity of the event
    float rarity;
    //Choices array


    //Constructor
    public Type_Events(String name){
        this.name = name;
    }

    //By default, just show description
    public void Interact(Hero hero) {
        System.out.println(description);
    }

}
