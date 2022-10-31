package Class.aroundLife;

public class Weapon extends Equipment {
    float criticChance=0.1f;
    int damage=2;
    int rarity=0;// on a range 1 to 5 1 is the lowest and 5 is the highest 0 is for default
    String type; // bow, sword,
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
    public float getCriticChance(){return this.criticChance;}
    public int getDamage(){return this.damage;} // DOIT ENTRE CHANGER EN GET DAMAGE

    public static Weapon dropWeapon(int rarity){
        Weapon item = new Weapon();
        item.rarity = rarity;
        item.damage += Math.pow(1.5, rarity); // Damage increase with the rarity
        item.criticChance += rarity*0.05; // Critic chance increase with the rarity
        return item;

    }
}
