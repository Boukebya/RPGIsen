package Class.Equipments;

//Class to manage Armors
public class Armor extends Equipment {
    int defense;

    // Constructor
    public Armor(int defense,String name){
        super();
        this.defense = defense;
        this.setName(name);
    }

    // Getters
    public int getDefense(){return this.defense;}

    // Setters
    public void setDefense(int defense){this.defense = defense;}

    // Methods
    @Override
    public void getStat(){
        System.out.print(this.name+" have ");
        System.out.println(" Defense: " + this.defense);
    }
}
