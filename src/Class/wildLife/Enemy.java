/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.wildLife;

import Class.Equipments.Equipment;
import Class.Equipments.Weapon;

/**
 *
 * @author theap
 */
public class Enemy extends Entity{
    int dropChance;// on a range 0 to 10    
    String name;
    public Enemy(Stat stat,String name) {
        super(stat);
        this.name = name;
    }

    //get name
    public String getName(){
        return this.name;
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


    public static Enemy GetEnemy(Enemy[] enemies,int level) {

        while (true) {
            //if enemy is same level as player
            for (Enemy enemy : enemies) {
                if (enemy.getLevel() == level) {
                    //random chance to spawn enemy
                    if (Math.random() < 0.2) {
                        return enemy;
                    }
                }
            }
        }
    }

}
