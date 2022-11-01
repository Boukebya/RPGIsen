package Class.Equipments;

public class Armor extends Equipment {
    int defense =5;

    // Constructor
    public Armor(int defense,String name){
        super();
        this.defense = defense;
        this.setName(name);
    }
    public Armor() {
    }

    // Getters
    public int getDefense(){return this.defense;}

    // Setters
    public void setDefense(int defense){this.defense = defense;}

    // Methods
    public static Armor dropArmor(int rarity){
        Armor item = new Armor();
        item.rarity = rarity;
        item.defense += Math.pow(1.7, rarity); // Damage increase with the rarity
        return item;
    }

    @Override
    public String getType(){
        return this.type;
    }

    public void getStat(){
        System.out.print(this.name+" have ");
        System.out.println(" Defence: " + this.defense);
    }
}
