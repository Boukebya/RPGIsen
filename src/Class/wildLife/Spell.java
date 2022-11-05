package Class.wildLife;

public class Spell {
    String name = "IsenDiploma";
    int coolDown = 3;
    int manaUse = 5;
    int power = 10;
    int accuracy = 100;
    String type = "Damage"; // Damage or Heal or Effect
    String effect = "none";// example : none, stun, poison, burn, freeze, bleed, curse, blind, silence, fear, charm, sleep, slow, haste, drain, absorb, reflect, heal, shield, invulnerable, taunt, disarm, stun, silence, fear, charm, sleep, slow, haste, drain, absorb, reflect, heal, shield, invulnerable, taunt, disarm, stun, silence, fear, charm, sleep, slow, haste, drain, absorb, reflect, heal, shield, invulnerable, taunt, disarm, stun, silence, fear, charm, sleep, slow, haste, drain, absorb, reflect, heal, shield, invulnerable, taunt, disarm, stun, silence, fear, charm, sleep, slow, haste, drain, absorb, reflect, heal, shield, invulnerable, taunt, disarm, stun, silence, fear, charm, sleep, slow, haste, drain, absorb, reflect, heal, shield, invulnerable, taunt, disarm, stun, silence, fear, charm, sleep, slow, haste, drain, absorb, reflect, heal, shield, invulnerable, taunt, disarm, stun, silence, fear, charm, sleep, slow, haste, drain, absorb, reflect, heal, shield, invulnerable, taunt, disarm, stun, silence, fear, charm, sleep, slow, haste, drain, absorb, reflect, heal, shield, invulnerable, taunt, disarm, stun, silence, fear, charm, sleep, slow, haste, drain, absorb, reflect, heal, shield, invulnerable, taunt, disarm, stun, silence, fear, charm, sleep, slow, haste, drain, absorb, reflect, heal, shield, invulnerable, taunt, disarm, stun, silence, fear, charm, sleep, slow, haste, drain, absorb, reflect, heal, shield, invulnerable, taunt, disarm, stun, silence, fear, charm, sleep, slow, haste, drain, absorb, reflect, heal, shield, invulnerable, taunt, disarm, stun, silence, fear, charm, sleep, slow, haste, drain, absorb, reflect, heal, shield, invulnerable, taunt, disarm, stun, silence, fear, charm, sleep, slow, haste, drain, absorb, reflect, heal, shield, invulnerable, taunt, disarm, stun, silence,
    String target = "enemy"; // enemy,self

    // Constructors
    public Spell(String name, int coolDown, int manaUse, int power, String type, String effect, int accuracy, String target) {
        this.name = name;
        this.coolDown = coolDown;
        this.manaUse = manaUse;
        this.power = power;
        this.type = type;
        this.effect = effect;
        this.accuracy = accuracy;
        this.target = target;
    }
    public Spell(){}

    // Getters
    public String getName() {return name;}
    public float getCoolDown() {return coolDown;}
    public int getManaUse() {return manaUse;}
    public int getPower() {return power;}
    public String getType() {return type;}
    public String getEffect() {return effect;}
    public int getAccuracy() {return accuracy;}
    public String getTarget() {return target;}

    // Setters
    public void setName(String name) {this.name = name;}
    public void setCoolDown(int coolDown) {this.coolDown = coolDown;}
    public void setManaUse(int manaUse) {this.manaUse = manaUse;}
    public void setPower(int power) {this.power = power;}
    public void setType(String type) {this.type = type;}

    // Method
    //Display name,cd,mana use,power,type,effect,accuracy,target
    public  void getStatus(){
        System.out.println("Name: "+name);
        System.out.println("CoolDown: "+coolDown);
        System.out.println("ManaUse: "+manaUse);
        System.out.println("Power: "+ power);
        System.out.println("Type: "+type);
        System.out.println("Effect: "+effect);
        System.out.println("Accuracy: "+accuracy);
        System.out.println("Target: "+target);
    }
}
