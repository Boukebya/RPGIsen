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

    //Setters
    public void setDropChance(int dropChance) {
        this.dropChance = dropChance;
    }
    public void setName(String name) {
        this.name = name;
    }

    //Methods
    public Equipment dropItem(){
        /*
        Drop an Item based on Enemy level
        */
        int rarity = this.getLevel();
        rarity/=5;
        if(rarity==0){rarity=1;}
        if(rarity>5){rarity=5;}
        return Weapon.dropWeapon(rarity);
    }
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

}
