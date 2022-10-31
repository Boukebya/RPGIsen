/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.wildLife;

import Class.aroundLife.Equipment;

/**
 * @author theap
 */
public class Entity {

    public Stat stat = new Stat();
    Equipment weapon; // on peut faire un tableau si on ajoute des protection
    int[][] position;


    public Entity(String name, int strength) {
        stat.health = 10;
        this.stat.name = name;
        this.stat.strength = strength;
    }

    public void getAttack(float damage) {
        /*
        The entity has been attack
        */
        if (this.stat.health - damage <= 0) {
            this.killed();
        } else {
            this.stat.health -= damage;
            System.out.println(this.stat.name + "'s HP = " + this.stat.health);
        }
    }

    public void attack(Entity target) {
        /*
        The entity attack another one
       */
        target.getAttack(this.stat.strength);
    }

    public void killed() {
        /*
        The entity is dead
        */
        this.stat.health = 0;
        System.out.println(this.stat.name + " died!");
    }

    public void increaseStrength(int increase) {
        this.stat.strength += increase;
    }

    public int getLevel() {
        return this.stat.level;
    }

    public float getHP() {
        return this.stat.health;
    }

    public int getStrength() {
        return this.stat.strength;
    }

    public void addHP(float newAmount) {
        this.stat.health += newAmount;
        if (this.stat.health > this.stat.maxHealth) {
            this.stat.health = this.stat.maxHealth;
        }
    }

    public String getName() {
        return this.stat.name;
    }

}
