/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.aroundLife;

/**
 * Class for all equipment in the game
 * it could be weapon, or protection
 * @author theap
 */
public class Equipment {
    int rarity = 0;
    float damage = 3;
    float defence = 1;
    int criticalHitChance = 1;
    String name = "Sword";

    String type = "Bow"; // Weapon or Protection
    public String getType() {return this.type;}

    // Constructor
    public Equipment(String name, int rarity, float damage, float defence){
        this.name = name;
        this.rarity = rarity;
        this.damage = damage;
        this.defence = defence;
    }
    public Equipment(){
        // Default constructor
    }

    public static Equipment dropEquipment(int rarity){
        Equipment item = new Equipment();
        item.rarity = rarity;
        item.damage *=rarity;
        item.defence *=rarity;
        item.criticalHitChance +=rarity;
        return item;
    }

    // getters
    public int getRarity() {return this.rarity;}
    public float getDamage() {return this.damage;}
    public float getDefence() {return this.defence;}
    public int getCriticalHitChance() {return this.criticalHitChance;}
    public String getName() {return this.name;}

    // setters
    public void setName(String name) {this.name = name;}

    public void getStat(){
        System.out.println("Name: " + this.name);
        System.out.println("Rarity: " + this.rarity);
        System.out.println("Damage: " + this.damage);
        System.out.println("Defence: " + this.defence);
        System.out.println("Critical Hit Chance: " + this.criticalHitChance);
    }

}
