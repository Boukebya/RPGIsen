package Class.Equipments;

/**
 * Class for all equipment in the game
 * it could be weapon, or protection
 * @author theap
 */
public class Equipment {
    int rarity = 0;
    String name = "Armor";

    String type = "Bow"; // Weapon or Protection
    public String getType() {return this.type;}

    // getters
    public int getRarity() {return this.rarity;}
    public String getName() {return this.name;}


    // setters
    public void setName(String name) {this.name = name;}
    public void setRarity(int i) {
        this.rarity = i;
    }
    public void setType(String type) {this.type = type;}

    //Methods
    public void getStat(){
        System.out.println("Name: " + this.name);
    }


}
