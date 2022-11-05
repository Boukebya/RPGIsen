package Class.World.Event_Manager;

import Class.Equipments.Equipment;
import Class.wildLife.Hero;

public class Unknown extends Type_Events {
    String name_Object;

    //Constructor
    public Unknown(Type_Events type_Event,String name_Object) {
        super(type_Event.name, type_Event.rarity);
        this.name_Object= name_Object;
    }

    @Override
    public void Interact(Hero hero, Equipment[] weapons) {
        System.out.println("You find a " + name_Object + " !");
        System.out.println("Need to implement unknown manager...");
    }


}
