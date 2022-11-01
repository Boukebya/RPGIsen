/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.wildLife;

import Class.aroundLife.Equipment;
import Class.aroundLife.Weapon;

/**
 *
 * @author theap
 */
public class Enemie extends Entity{
    int dropChance;// on a range 0 to 10    
    
    public Enemie(String name,int strength){
        super(name, strength);
    }
    
    public Equipment dropItem(){
        /*
        Drop an Item based on Enemy level
        */
        int rarity = this.getLevel();
        rarity/=5;
        if(rarity==0){rarity=1;}
        if(rarity>5){rarity=5;}
        return Weapon.dropWeapon(rarity);
    }
}
