/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wildLife;

/**
 *
 * @author theap
 */
public class Character extends Entity{
    int experience;
    
    public Character(String name,int strenght){
        super(name, strenght);
    }
    
    public void getExperience(int xp){
        /*
        Manage the XP of the character an his level
        */
        this.experience+=xp;
        if(this.experience>=10){
            this.experience-=10;
            this.stat.level+=1;
        }
    }
}
