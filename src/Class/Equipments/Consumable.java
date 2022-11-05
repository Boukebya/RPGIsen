package Class.Equipments;
import Class.wildLife.Hero;
import java.util.Scanner;

//Class to manage consumable
public class Consumable extends Equipment{
    String name;
    int regenPower;
    //constructor
    public Consumable(String name,int rarity,String type,int regenPower) {
        super(rarity,type);
        this.name = name;
        this.regenPower = regenPower;
    }

    // getters
    public int getRarity() {return this.rarity;}
    public String getName() {return this.name;}

    // setters
    public void setName(String name) {this.name = name;}
    public void setRarity(int i) {
        this.rarity = i;
    }
    public void setType(String type) {this.type = type;}

    //Methods
    //Use a consumable
    public void use(Hero hero){
        System.out.println("You used a "+this.name);
        if(this.type.equals("Health")){
            System.out.println("You regen "+this.regenPower+" HP");
            hero.setHP((int) (hero.getHP()+this.regenPower));

        }
        else if(this.type.equals("Mana")){
            System.out.println("You regen "+this.regenPower+" MP");
            hero.setMana(hero.getMana()+this.regenPower);
        }


    }
    //add a consumable to the inventory
    public static void addPot(Consumable consumable, Hero hero){
        Consumable[] consumables = hero.getConsumables();
        for(int i = 0; i < consumables.length; i++){
        //if it's empty
            if(consumables[i] == null){
                consumables[i] = consumable;
                return;
            }
        }
            System.out.println("Your inventory is full, replace a consumable");
            //display consumable inventory
            for(int j = 0; j < consumables.length; j++){
                System.out.println(consumables[j].getName());
            }
            //replace consumable
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            consumables[choice-1] = null;
            consumables[choice-1] = consumable;
        System.out.println("You replaced a consumable");

    }
    //Remove a consumable from the inventory
    public void remove(int slot,Hero hero){
        Consumable[] consumables = hero.getConsumables();
        consumables[slot] = null;
    }
}
