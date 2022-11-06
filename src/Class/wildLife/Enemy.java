package Class.wildLife;
import Class.Equipments.Equipment;
import Class.Equipments.Weapon;
import Class.wildLife.Spell;

public class Enemy extends Entity{
    String name;
    Spell[] spell = new Spell[5];

    //constructor
    public Enemy(Stat stat,String name,Spell[] spells) {
        super(stat);
        this.name = name;
        this.spell = spells;
    }

    //Getters
    public String getName() {
        return name;
    }
    public int getMaxHealth() {
        return this.stat.maxHealth;
    }
    public Spell[] getSpell() {
        return spell;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }


    //Methods

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

    //Function to get a random enemy of the same lvl as player, and boss with fix level
    public static Enemy GetEnemy(Enemy[] enemies,int level) {
        while (true) {
            if(level > enemies[enemies.length-1].getLevel()){
                //get enemy with max level
                return enemies[enemies.length-1];
            }
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
    public static Enemy GetBoss(Enemy[] bosses,int heroLevel) {
        while (true) {

            for (Enemy boss : bosses) {
                //if boss level is more than 2 times the hero level
                if (boss.getLevel() < heroLevel*2.5f) {
                    //random chance to spawn boss
                    if (Math.random() < 0.2) {
                        return boss;
                    }
                }

            }
        }
    }

    //Function to get a random spell from the enemy
    public void spellManager(Hero hero){
        //get random spell
        Spell spell = randomSpell();
        //We cast spell
        cast(spell,hero);
    }
    public Spell randomSpell(){
        //First we choose the spell
        Spell spell = new Spell();
        Spell[] spells = this.getSpell();
        // get a random spell
        while (true) {
            int choice = (int) (Math.random() * spells.length);
            if (spells[choice] != null && spells[choice].getManaUse() <= this.getMana()) {
                spell = spells[choice];
                return spell;
            }
        }
    }
    //Cast spell and print it
    public void cast(Spell spell,Entity target){
        // We deduce the mana cost
        this.stat.mana -= spell.getManaUse();
        System.out.println(this.getName()+" use "+spell.getName());
        // cast spell depending on type
        if(spell.getType() == "Basic"){
            //get the higher between strength and dexterity
            int stat = this.getStrength();
            if(this.getDexterity()>stat){
                stat = this.getDexterity();
            }

            //random with accuracy
            accuracyCheck(spell.getAccuracy(),target,stat);
        }
        if(spell.getType() == "Multiple"){
            //get the higher between strength and dexterity
            int stat = this.getDexterity();

            //random with accuracy
            for(int i = 0;i< spell.getPower();i++){
                accuracyCheck(spell.getAccuracy(),target,stat);
            }

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
            accuracyCheck(spell.getAccuracy(),target,stat*spell.getPower());
        }
    }
    //accuracy check
    public void accuracyCheck(int accuracy,Entity target, int damage){
        System.out.println(damage+ " damage");
        if(Math.random()*100 < accuracy){
            target.getHit(damage);
        }
        else{
            System.out.println(this.name + " Missed attack !");
        }
    }

    //Damage management
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
