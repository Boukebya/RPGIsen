package Class.Equipments;

//Class to manage all equipments -> consumable, weapon, armor
public abstract class Equipment {
    int rarity = 1;
    String name = "default";
    String type = "default"; //Club, sword,bow armor or consumable
    //constructor
    public Equipment(int rarity,String type) {
        this.rarity = rarity;
        this.type = type;
    }
    public Equipment() {

    }

    // getters
    public String getType() {return this.type;}
    public int getRarity() {return this.rarity;}
    public String getName() {return this.name;}

    // setters
    public void setName(String name) {this.name = name;}
    public void setRarity(int i) {
        this.rarity = i;
    }
    public void setType(String type) {this.type = type;}

    //Methods
    //Print stat of equipment
    public void getStat(){
        System.out.println("Name: " + this.name);
    }
}
