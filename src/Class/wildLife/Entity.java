package Class.wildLife;

/**
 * @author theap
 */
public class Entity {
    public Stat stat = new Stat();
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

    //Methods
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
    public void addHP(float newAmount) {
        this.stat.health += newAmount;
        if (this.stat.health > this.stat.maxHealth) {
            this.stat.health = this.stat.maxHealth;
        }
    }
}
