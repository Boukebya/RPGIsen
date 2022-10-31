package Class.aroundLife;

import Class.aroundLife.Equipment;

public class Weapon extends Equipment {
    int criticChance;
    int damage;
    int rarity;
    String type; // bow, sword
    public Weapon(int damage, int criticChance, int rarity, String type, String name){
        super();
        this.damage = damage;
        this.criticChance = criticChance;
        this.rarity = rarity;
        this.type = type;
        this.setName(name);

    }

    public Weapon() {

    }

    public int useWeapon(){
        /*
        Return the weapon damage and the critical hit chance
         */
        return this.damage;
    }

    // Getters
    public int getCriticChance(){return this.criticChance;}
    public int getWeaponDamage(){return this.damage;} // DOIT ENTRE CHANGER EN GET DAMAGE


}
