package Class.aroundLife;

import Class.aroundLife.Equipment;

public class Armor extends Equipment {
    int defence=5;
    // Constructor
    public Armor(int defence,String name){
        super();
        this.defence = defence;
        this.setName(name);
    }
    public Armor() {
    }

    // Getters
    public int getDefence(){return this.defence;}

    public static Armor dropArmor(int rarity){
        Armor item = new Armor();
        item.rarity = rarity;
        item.defence += Math.pow(1.7, rarity); // Damage increase with the rarity
        return item;
    }

}
