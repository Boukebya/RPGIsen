package Class.wildLife;
import Class.Equipments.Equipment;
import Class.Equipments.Weapon;

public class Enemy extends Entity{
    int dropChance;// on a range 0 to 10    
    String name;
    //constructor
    public Enemy(Stat stat,String name) {
        super(stat);
        this.name = name;
    }

    //Getters
    public int getDropChance() {
        return dropChance;
    }
    public String getName() {
        return name;
    }
    //get max health
    public float getMaxHealth() {
        return this.stat.maxHealth;
    }

    //Setters
    public void setDropChance(int dropChance) {
        this.dropChance = dropChance;
    }
    public void setName(String name) {
        this.name = name;
    }

    //Methods
    public Equipment dropItem(){
        int rarity = this.getLevel();
        rarity/=5;
        if(rarity==0){rarity=1;}
        if(rarity>5){rarity=5;}
        return Weapon.dropWeapon(rarity);
    }
    //Drop gold
    public int dropGold(){
        int gold = (int) ((Math.random() * this.stat.level*10) + this.stat.level*4);
        return gold;
    }
    //Drop experience
    public int dropExp(){
        int exp = (int) ((Math.random() * this.stat.level*5) + this.stat.level);
        return exp;
    }
    //Function to get a random enemy of the same lvl as player
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
    public static Enemy GetBoss(Enemy[] bosses) {
        while (true) {
            //if enemy is same level as player
            for (Enemy boss : bosses) {
                    if (Math.random() < 0.1) {
                        return boss;
                    }
            }
        }
    }

    @Override
    public void attack(Entity target) {

        target.getHit(this.stat.strength);
    }

}
