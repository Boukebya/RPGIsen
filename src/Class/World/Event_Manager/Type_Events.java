package Class.World.Event_Manager;
import Class.Equipments.Equipment;
import Class.wildLife.Hero;

//Mother's class for every event, define the basic function of an event and what kind it is
public class Type_Events {
    //name of the event
    String name;
    //rarity of the event
    int rarity;

    //Constructor
    public Type_Events(String name, int rarity){
        this.name = name;
        this.rarity = rarity;
    }

    //Getters
    public float getRarity() {
        return rarity;
    }

    //Setters
    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    //Methods
    //By default, just show description, else, it's override by the child class
    public void Interact(Hero hero, Equipment[] weapons) {
        System.out.println("description");
    }

}
