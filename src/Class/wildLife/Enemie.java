/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wildLife;

import aroundLife.Equipement;

/**
 *
 * @author theap
 */
public class Enemie extends Entity{
    int dropChance;// on a range 0 to 10    
    
    public Enemie(String name,int strenght){
        super(name, strenght);
    }
    
    public Equipement dropItem(){
        /*
        Drop an Item based on Enemie's level 
        */
        int rarity = this.stat.level;
        rarity/=5;
        if(rarity==0){rarity=1;}
        return Equipement.dropEquipement(rarity);
    }
}
