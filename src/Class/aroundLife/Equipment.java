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
    String name = "ALED";

    String type = "Bow"; // Weapon or Protection
    public String getType() {return this.type;}

    public Equipment(){
        // Default constructor
    }

    public Equipment dropEquipment(int rarity){
        Equipment item = new Equipment();
        return item;
    }

    // getters
    public int getRarity() {return this.rarity;}
    public String getName() {return this.name;}

    // setters
    public void setName(String name) {this.name = name;}

    public void getStat(){
        System.out.println("Name: " + this.name);
    }

}
