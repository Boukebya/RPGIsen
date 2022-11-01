package Class.wildLife;

public class Spell {
    String name = "IsenDiploma";
    float coolDown = 3F;
    int manaUse = 5;
    int power = 25;
    String type = "Damage"; // Damage or Heal or Effect

    // Constructors
    public Spell(String name, float coolDown, int manaUse, int power, String type) {
        this.name = name;
        this.coolDown = coolDown;
        this.manaUse = manaUse;
        this.power = power;
        this.type = type;

    }
    public Spell(){}

    // Getters
    public String getName() {return name;}
    public float getCoolDown() {return coolDown;}
    public int getManaUse() {return manaUse;}
    public int getPower() {return power;}
    public String getType() {return type;}

    // Setters
    public void setName(String name) {this.name = name;}
    public void setCoolDown(float coolDown) {this.coolDown = coolDown;}
    public void setManaUse(int manaUse) {this.manaUse = manaUse;}
    public void setPower(int power) {this.power = power;}

    // Method
    public Spell generateSpell(int level, String type) {
        /*
        Generate a spell with a level
        */
        Spell newSpell = new Spell();
        newSpell.power +=level;
        newSpell.coolDown+=0.75*level;
        newSpell.manaUse+=0.5*level;
        newSpell.type=type;
        return newSpell;
    }
    public  void getStatus(){
        System.out.println("Name: "+name);
        System.out.println("CoolDown: "+coolDown);
        System.out.println("ManaUse: "+manaUse);
        System.out.println("Damage: "+ power);
    }
}
