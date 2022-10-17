/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aroundLife;

/**
 * Class for all equipement in the game
 * it could be weapon, or protection
 * @author theap
 */
public class Equipement {
    int ratity = 0;
    float damage = 3;
    float defence = 1;
    int criticalHitChance = 1;
    String name = "Sword";
    
    public static Equipement dropEquipement(int rarity){
        Equipement item = new Equipement();
        item.ratity = rarity;
        item.damage *=rarity;
        item.defence *=rarity;
        item.criticalHitChance +=rarity;
        return item;
    }
}
