package Class.wildLife;

public class Enemy extends Entity{
    String name;
    Spell[] spell;

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
        return (int) ((Math.random() * this.stat.level*10) + this.stat.level*4);
    }
    //Drop experience
    public int dropExp(){
        return (int) ((Math.random() * this.stat.level*5) + this.stat.level);
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
               //get the first boss with level higher than hero
                if (boss.getLevel() > heroLevel && boss.getLevel() < heroLevel+3) {
                    //random chance to spawn boss
                    if (Math.random() < 0.5) {
                        return boss;
                    }
                }
            }

        }
    }

    @Override
    //Target get hit
    public void getHit(float damage) {
        if (this.stat.health - damage <= 0) {
            this.killed();
        } else {
            this.stat.health -= damage;
        }
    }
}
