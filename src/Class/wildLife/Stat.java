package Class.wildLife;

public class Stat {
    //All statistic of an Entity
    int dexterity =1;
    int strength=1;
    int maxMana = 10;
    int mana=10;
    int maxHealth = 10;
    int health=10;
    int level=1;
    String name = "Creature";

    //constructor
    public Stat(int dexterity, int strength, int maxMana,int mana, int maxHealth,int health, int level, String name) {
        this.dexterity = dexterity;
        this.strength = strength;
        this.maxMana = maxMana;
        this.mana = mana;
        this.maxHealth = maxHealth;
        this.health = health;
        this.level = level;
        this.name = name;
    }

    // Getters
    public int getDexterity() {return this.dexterity;}
    public int getStrength() {return this.strength;}
    public int getMaxMana() {return this.maxMana;}
    public int getMana() {return this.mana;}
    public int getMaxHealth() {return this.maxHealth;}
    public int getHealth() {return this.health;}
    public int getLevel() {return this.level;}
    public String getName() {return this.name;}

    // Setters
    public void setDexterity(int dexterity) {this.dexterity = dexterity;}
    public void setStrength(int strength) {this.strength = strength;}
    public void setMaxMana(int maxMana) {this.maxMana = maxMana;}
    public void setMana(int mana) {this.mana = mana;}
    public void setMaxHealth(int maxHealth) {this.maxHealth = maxHealth;}
    public void setHealth(int health) {this.health = health;}
    public void setLevel(int level) {this.level = level;}
    public void setName(String name) {this.name = name;}

    // Methods
    //Display all stats name, level, health, mana, strength, dexterity
    public void getStatus(){
        System.out.println("Name: " + this.name);
        System.out.println("Level: " + this.level);
        System.out.println("Health: " + this.health + "/" + this.maxHealth);
        System.out.println("Mana: " + this.mana + "/" + this.maxMana);
        System.out.println("Strength: " + this.strength);
        System.out.println("Dexterity: " + this.dexterity);
    }
    //display in one line name,lvl,health,mana
    public void introduce(){
        System.out.println(this.name + " lvl : " + this.level + "\nHP : " + this.health + "/" + (int)this.maxHealth + " Mana : " + this.mana + "/" + this.maxMana);
    }
}