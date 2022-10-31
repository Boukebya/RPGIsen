/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.wildLife;

/**
 *
 * @author theap
 */
public class Stat {
    /*
    All statistic of an Entity
    */
    int dexterity =1;   // stat for bow
    int strength=1;     // stat for sword

    int maxMana = 10;   // stat for magic
    float mana=10;

    float maxHealth = 10F;
    float health=10F;
    int level=1;
    String name = "Creature";

    // Getters
    public int getDexterity() {return this.dexterity;}
    public int getStrength() {return this.strength;}
    public int getMaxMana() {return this.maxMana;}
    public float getMana() {return this.mana;}
    public float getMaxHealth() {return this.maxHealth;}
    public float getHealth() {return this.health;}
    public int getLevel() {return this.level;}
    public String getName() {return this.name;}

    public void getStatus(){
        System.out.println("Name: " + this.name);
        System.out.println("Level: " + this.level);
        System.out.println("Health: " + this.health + "/" + this.maxHealth);
        System.out.println("Mana: " + this.mana + "/" + this.maxMana);
        // peut être enlever ces deux lignes d'infos juste en bas
        System.out.println("Strength: " + this.strength);
        System.out.println("Dexterity: " + this.dexterity);
    }

    // Setters
    public void setDexterity(int dexterity) {this.dexterity = dexterity;}
    public void setStrength(int strength) {this.strength = strength;}
    public void setMaxMana(int maxMana) {this.maxMana = maxMana;}
    public void setMana(int mana) {this.mana = mana;}
    public void setMaxHealth(float maxHealth) {this.maxHealth = maxHealth;}
    public void setHealth(float health) {this.health = health;}
    public void setLevel(int level) {this.level = level;}
    public void setName(String name) {this.name = name;}

    // Methods
    public void increaseDexterity(int increase) {this.dexterity+=increase;}
    public void increaseStrength(int increase) {this.strength+=increase;}
    public void increaseMaxMana(int increase) {this.maxMana+=increase;}
    public void increaseMana(int increase) {this.mana+=increase;}
    public void increaseMaxHealth(float increase) {this.maxHealth+=increase;}
    public void increaseHealth(float increase) {this.health+=increase;}
    public void increaseLevel(int increase) {this.level+=increase;}

}