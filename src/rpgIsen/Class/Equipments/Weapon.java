package rpgIsen.Class.Equipments;

//rpgIsen.Class to manage Weapons
public class Weapon extends Equipment {
    float criticChance;
    int damage;
    int rarity;// on a range 1 to 5 1 is the lowest and 5 is the highest

    // Constructor
    public Weapon(int damage, float criticChance, int rarity, String type, String name) {
        super();
        this.damage = damage;
        this.criticChance = criticChance;
        this.rarity = rarity;
        this.setType(type);
        this.setName(name);
    }

    //Getters
    public int getRarity() {
        return rarity;
    }

    public float getCriticChance() {
        return this.criticChance;
    }

    public int getDamage() {
        return this.damage;
    } // DOIT ENTRE CHANGER EN GET DAMAGE

    //Methods
    @Override
    public void getStat() {
        System.out.print(this.getName() + " have ");
        System.out.print(" Damage: " + this.damage);
        System.out.println(" Critic chance: " + this.criticChance);
    }
}
