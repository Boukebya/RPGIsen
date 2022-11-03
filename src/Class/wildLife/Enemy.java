package Class.wildLife;
import Class.Equipments.Equipment;
import Class.Equipments.Weapon;
import Class.wildLife.Spell;

public class Enemy extends Entity{
    int dropChance;// on a range 0 to 10    
    String name;
    //Up to five spell for monsters
    Spell[] spell = new Spell[5];

    //constructor
    public Enemy(Stat stat,String name,Spell[] spells) {
        super(stat);
        this.name = name;
        this.spell = spells;
    }

    //Getters
    public int getDropChance() {
        return dropChance;
    }
    public String getName() {
        return name;
    }
    //get max health
    public float getMaxHealth() {
        return this.stat.maxHealth;
    }
    //get spell
    public Spell[] getSpell() {
        return spell;
    }

    //Setters
    public void setDropChance(int dropChance) {
        this.dropChance = dropChance;
    }
    public void setName(String name) {
        this.name = name;
    }

    //Methods
    public Equipment dropItem(){
        int rarity = this.getLevel();
        rarity/=5;
        if(rarity==0){rarity=1;}
        if(rarity>5){rarity=5;}
        return Weapon.dropWeapon(rarity);
    }
    //Drop gold
    public int dropGold(){
        int gold = (int) ((Math.random() * this.stat.level*10) + this.stat.level*4);
        return gold;
    }
    //Drop experience
    public int dropExp(){
        int exp = (int) ((Math.random() * this.stat.level*5) + this.stat.level);
        return exp;
    }

    //Function to get a random enemy of the same lvl as player
    public static Enemy GetEnemy(Enemy[] enemies,int level) {

        while (true) {
            //if enemy is same level as player
            for (Enemy enemy : enemies) {
                if (enemy.getLevel() == level) {
                    //random chance to spawn enemy
                    if (Math.random() < 0.2) {
                        return enemy;
                    }
                }
            }
        }
    }
    public static Enemy GetBoss(Enemy[] bosses) {
        while (true) {
            //if enemy is same level as player
            for (Enemy boss : bosses) {
                    if (Math.random() < 0.1) {
                        return boss;
                    }
            }
        }
    }

    public void spellManager(Hero hero){
        //First we choose the spell
        Spell spell = new Spell();
        Spell[] spells = this.getSpell();
        // get a random spell
        while (true) {
            int choice = (int) (Math.random() * spells.length);
            if (spells[choice] != null && spells[choice].getManaUse() <= this.getMana()) {
                spell = spells[choice];
                break;
            }
        }

        // We deduce the mana cost
        this.stat.mana -= spell.getManaUse();

        // cast spell
        if(spell.getType() == "Basic"){
            //get the higher between strength and dexterity
            int stat = this.getStrength();
            if(this.getDexterity()>stat){
                stat = this.getDexterity();
            }
            //random with accuracy
            accuracyCheck(spell.getAccuracy(),hero,stat);
        }
        if(spell.getType() == "Heal"){
            accuracyCheck(spell.getAccuracy(),this,spell.getPower());
        }
        if(spell.getType() == "Buff"){
            this.increaseDexterity(spell.getPower());
            this.increaseStrength(spell.getPower());
        }
        if(spell.getType() == "Huge"){
            //get the higher between strength and dexterity
            int stat = this.getStrength();
            if(this.getDexterity()>stat){
                stat = this.getDexterity();
            }
            //random with accuracy
            accuracyCheck(spell.getAccuracy(),hero,stat*spell.getPower());
        }
    }
    //accuracy check
    public void accuracyCheck(int accuracy,Entity target, int damage){
        if(Math.random()*100 < accuracy){
            target.getHit(damage);
        }
        else{
            System.out.println(this.name + " Missed attack !");
        }
    }

    @Override
    public void attack(Entity target) {
        target.getHit(this.stat.strength);
    }
    //Target get hit
    public void getHit(float damage) {
        if (this.stat.health - damage <= 0) {
            this.killed();
        } else {
            this.stat.health -= damage;
            System.out.println(" : HP = " + this.stat.health + "/" + this.stat.maxHealth);
        }
    }

}
