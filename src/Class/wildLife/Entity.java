/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.wildLife;

import aroundLife.Equipement;

/**
 *
 * @author theap
 */
public class Entity {
    
    Stat stat = new Stat(); 
    Equipement weapon; // on peut faire un tableau si on ajoute des protection
    int[][] position;
    
    public Entity(String name,int strenght){
        stat.health = 10;
        this.stat.name = name;
        this.stat.strength = strenght;
    }
    
    public void getAttack(float damage){
        /*
        The entity has been attack
        */
        if(this.stat.health-damage<=0){
            this.getKilled();
        }
        else{
            this.stat.health-=damage;
            System.out.println(this.stat.name + "'s HP = " + this.stat.health);
        }
    }
    
    public void Attack(Entity target){
        /*
        The entity atteck another one
       */
        target.getAttack(this.stat.strength);
    }
    
    public void getKilled(){
        /*
        The entity is dead
        */
        this.stat.health =0;
        System.out.println(this.stat.name +" died!");
    }
    
    public void increaseStrength(int increase){
        this.stat.strength+=increase;
    }
    
}
