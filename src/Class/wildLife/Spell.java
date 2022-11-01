package Class.wildLife;

public class Spell {
    String name = "IsenDiploma";
    float coolDown = 3F;
    int manaCost = 5;
    int power = 25;
    String type = "Damage"; // Damage or Heal
    // Constructors
    public Spell(String name, float coolDown, int manaCost, int power, String type) {
        this.name = name;
        this.coolDown = coolDown;
        this.manaCost = manaCost;
        this.power = power;
        this.type = type;

    }

    public Spell(){}

    // Method

    public Spell generateSpell(int level, String type) {
        /*
        Generate a spell with a level
        */
        Spell newSpell = new Spell();
        newSpell.power +=level;
        newSpell.coolDown+=0.75*level;
        newSpell.manaCost +=0.5*level;
        newSpell.type=type;
        return newSpell;
    }



    // Getters
    public String getName() {return name;}
    public float getCoolDown() {return coolDown;}
    public int getManaCost() {return manaCost;}
    public int getPower() {return power;}
    public  void getStatus(){
        System.out.println("Name: "+name);
        System.out.println("CoolDown: "+coolDown);
        System.out.println("ManaUse: "+ manaCost);
        System.out.println("Damage: "+ power);
    }
    public String getType() {return type;}

    // Setters
    public void setName(String name) {this.name = name;}
    public void setCoolDown(float coolDown) {this.coolDown = coolDown;}
    public void setManaCost(int manaCost) {this.manaCost = manaCost;}
    public void setPower(int power) {this.power = power;}

}
