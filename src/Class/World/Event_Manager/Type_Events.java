package Class.World.Event_Manager;
import Class.aroundLife.Equipment;
import Class.aroundLife.Weapon;
import Class.wildLife.Hero;

//Mother's class for every event
public class Type_Events {
    //name of the event
    String name;
    int rarity;

    //Constructor
    public Type_Events(String name, int rarity){
        this.name = name;
        this.rarity = rarity;
    }

    //Getter for rarity
    public float getRarity() {
        return rarity;
    }

    //Function to modify rarity
    public void modifyRarity(int rarity) {
        this.rarity = rarity;
    }

    //By default, just show description
    public void Interact(Hero hero, Equipment[] weapons) {
        System.out.println("description");
    }

}
