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
        System.out.println(this.stat.name + " died!");
        //random number of gold
        int gold = (int) (Math.random() * this.stat.level*10);
        System.out.println(this.stat.name +" dropped " + gold + " gold");
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
