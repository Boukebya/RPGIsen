package Class.wildLife;

/**
 * @author theap
 */
public class Entity {
    public Stat stat;
    //Constructor
    public Entity(Stat stat) {
        this.stat = stat;
    }

    //Getters
    public int getLevel() {
        return this.stat.level;
    }
    public float getHP() {
        return this.stat.health;
    }
    public int getStrength() {
        return this.stat.strength;
    }
    public String getName() {
        return this.stat.name;
    }
    public int getDexterity() {
        return this.stat.dexterity;
    }
    public int getMana() {
        return this.stat.mana;
    }

    //Setters
    public void setLevel(int level) {
        this.stat.level = level;
    }
    public void setHP(float health) {
        this.stat.health = health;
    }
    public void setStrength(int strength) {
        this.stat.strength = strength;
    }
    public void setName(String name) {
        this.stat.name = name;
    }
    public void setDexterity(int dexterity) {
        this.stat.dexterity = dexterity;
    }
    //set mana
    public void setMana(int mana) {
        this.stat.mana = mana;
    }

    //Methods
    //Target get hit
    public void getHit(float damage) {
        if (this.stat.health - damage <= 0) {
            this.killed();
        } else {
            this.stat.health -= damage;
            System.out.println(this.stat.name + "'s HP = " + this.stat.health);
        }
    }
    //Entity hit a target
    public void attack(Entity target) {
        target.getHit(this.stat.strength);
    }
    //Entity died
    public void killed() {
        this.stat.health = 0;
        System.out.println(" ,and killed It!");
    }
    public void increaseStrength(int increase) {
        this.stat.strength += increase;
    }
    public void increaseDexterity(int increase) {
        this.stat.dexterity += increase;
    }
}
