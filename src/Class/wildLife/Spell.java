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
        System.out.print("Name: "+name);
        System.out.print(" / CoolDown: "+coolDown);
        System.out.print(" / ManaUse: "+manaUse);
        System.out.print(" / Power: "+ power);
        System.out.print(" / Type: "+type);
        System.out.print(" / Effect: "+effect);
        System.out.print(" / Accuracy: "+accuracy);
        System.out.println(" / Target: "+target);
    }


    //Function to get a random spell from the enemy
    public static void spellManager(Entity user,Entity target){
        //get random spell
        Spell spell = randomSpell(user);
        //We cast spell
        cast(spell,target,user);
    }
    public static Spell randomSpell(Entity user){
        //First we choose the spell
        Spell spell;
        Spell[] spells = user.getSpell();
        // get a random spell
        while (true) {
            int choice = (int) (Math.random() * spells.length);
            if (spells[choice] != null && spells[choice].getManaUse() <= user.getMana()) {
                spell = spells[choice];
                return spell;
            }
        }
    }
    //Cast spell and print it
    public static void cast(Spell spell, Entity target, Entity user){
        // We deduce the mana cost
        user.stat.mana -= spell.getManaUse();
        System.out.print(user.getName()+" use "+spell.getName());
        // cast spell depending on type
        if(spell.getType().equals("Basic")){
            //get the higher between strength and dexterity
            int stat = user.getStrength();
            if(user.getDexterity()>stat){
                stat = user.getDexterity();
            }

            //random with accuracy
            accuracyCheck(spell.getAccuracy(),target,user,stat);
        }
        if(spell.getType().equals("Multiple")){
            //get the higher between strength and dexterity
            int stat = user.getDexterity();

            //random with accuracy
            for(int i = 0;i< spell.getPower();i++){
                accuracyCheck(spell.getAccuracy(),target,user,stat);
            }

        }
        if(spell.getType().equals("Heal")){
            accuracyCheck(spell.getAccuracy(),user,user,spell.getPower());
        }
        if(spell.getType().equals("Buff")){
            user.increaseDexterity(spell.getPower());
            user.increaseStrength(spell.getPower());
        }
        if(spell.getType().equals("Huge")){
            //get the higher between strength and dexterity
            int stat = user.getStrength();
            if(user.getDexterity()>stat){
                stat = user.getDexterity();
            }
            //random with accuracy
            accuracyCheck(spell.getAccuracy(),target,user,stat*spell.getPower());
        }
        if(spell.getType().equals("Precision")){
            //get the higher between strength and dexterity
            int stat = user.getStrength();
            if(user.getDexterity()>stat){
                stat = user.getDexterity();
            }
            //random with accuracy
            accuracyCheck(spell.getAccuracy(),target,user,stat*spell.getPower()*2);
        }
        if(spell.getType().equals("Damage")){
            //random with accuracy
            accuracyCheck(spell.getAccuracy(),target,user,spell.getPower());
        }

    }
    //accuracy check
    public static void accuracyCheck(int accuracy, Entity target,Entity user, int damage){
        //if user is a hero

            if(user instanceof Hero){
            //add weapon damage to damage
            damage += ((Hero) user).getWeapon().getDamage();
            }

        System.out.print(" for " + damage+ " damages ");
        if(Math.random()*100 < accuracy){
            target.getHit(damage);
            System.out.println();
        }
        else{
            System.out.println(" and missed it's attack !");
        }
    }

}
