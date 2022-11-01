package Class.Equipments;

public class Weapon extends Equipment {
    float criticChance=0.1f;
    int damage=2;
    int rarity=0;// on a range 1 to 5 1 is the lowest and 5 is the highest 0 is for default
    // Constructor
    public Weapon(int damage, int criticChance, int rarity, String type, String name){
        super();
        this.damage = damage;
        this.criticChance = criticChance;
        this.rarity = rarity;
        this.setType(type);
        this.setName(name);
    }
    public Weapon() {
    }

    //Getters
    public int getRarity() {
        return rarity;
    }
    public float getCriticChance(){return this.criticChance;}
    public int getDamage(){return this.damage;} // DOIT ENTRE CHANGER EN GET DAMAGE


    //Methods
    public static Weapon dropWeapon(int rarity){
        Weapon item = new Weapon();
        item.rarity = rarity;
        item.damage += Math.pow(1.5, rarity); // Damage increase with the rarity
        item.criticChance += rarity*0.05; // Critic chance increase with the rarity
        return item;
    }

    public int useWeapon(){
        /*
        Return the weapon damage and the critical hit chance
         */
        return this.damage;
    }

    @Override
    public void getStat() {
        System.out.print(this.getName()+" have ");
        System.out.print(" Damage: " + this.damage);
        System.out.println(" Critic chance: " + this.criticChance);
    }
}
