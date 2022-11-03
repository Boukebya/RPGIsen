package Class.gameMechanics;
import Class.wildLife.Enemy;
import Class.wildLife.Entity;
import Class.wildLife.Hero;

public class Fight {
    int turn=0;
    boolean isOver=false; // true if the fight is over
    Entity[] line = new Entity[4];

    // Constructor
    public Fight(Hero hero, Enemy enemy){
        beginFight(hero, enemy);
        while(!isOver){
            newTurn();
            int choice = displayHeroPossibilities(hero, enemy);
            if(enemy.getHP()<=0){
                System.out.println("You won!");
                isOver=true;

                endFight(hero,enemy);
                //set enemy hp to max hp
                enemy.setHP(enemy.stat.getMaxHealth());
            }
            else {
                enemy.spellManager(hero);
            }
            //if choice = 1, divide by 2 defense
            if(choice==1){
                hero.getArmor().setDefense(hero.getArmor().getDefense()/2);
            }

            if(hero.getHP()<=0){
                System.out.println("You lost!");
                isOver=true;
            }
            System.out.println("\n");
        }
    }

    //Getters
    public boolean isOver(){return this.isOver;}
    public int getTurn(){return this.turn;}


    //Methods
    public void beginFight(Hero hero, Enemy enemy){
        /*
        The fight begin
        */
        System.out.println("\nYou are going to fight this monster:");
        enemy.stat.introduce();
        System.out.println("\nYou are at this point: ");
        hero.stat.introduce();
        System.out.println("\nGood luck!\n");

        // init the lines
        for(int i=0;i<4;i++){line[i]=null;}
        line[0]=hero;
        line[3]=enemy;
    }

    public void endFight(Hero hero, Enemy enemy){
        int gold = enemy.dropGold();
        System.out.println(enemy.getName() + " dropped "+gold+" gold!");
        hero.modifyGold(gold);
        System.out.println("Hero has " + hero.getGold() + " gold in his purse");
        int exp = enemy.dropExp();
        System.out.println("You got "+exp+" exp!");
        hero.modifyExperience(exp);
        isOver=true;
    }
    public void newTurn(){
        //Manage the new turn
        turn++;
        //displayField();
        System.out.println("Turn "+turn);
    }
    // Display the possibilities of the hero
    public int displayHeroPossibilities(Hero hero, Enemy enemies){
        boolean correctInput=false;
        System.out.println("What do you want to do?");
        System.out.println("1. Attack with your weapon");
        System.out.println("2. Use a spell");
        System.out.println("3. Use a consumable");
        System.out.println("4. Protect yourself");

        String answer = hero.catchAnswer();
        int answerInt=0;
        while (!correctInput){
            try {
                answerInt = Integer.parseInt(answer);
                if (answerInt>= 1 && answerInt<=4) {
                    correctInput = true;
                }
            } catch (Exception e) {
                System.out.println("You didn't answer correctly");
                answer = hero.catchAnswer();
            }
        }
        if (answerInt==1){
            hero.attack(enemies);
        }
        else if (answerInt==2){
            hero.useSpell(enemies);
        }
        else if (answerInt==3){
            hero.consumableManagement();
        }
        else {
            System.out.println("You protect yourself");
            //double defense
            int armor = hero.getArmor().getDefense();
            armor = armor*2;
            hero.getArmor().setDefense(armor);
            return 1;
        }
        //Check if mana>maxMana
        if(hero.getMana()>hero.stat.getMaxMana()){
            hero.setMana(hero.stat.getMaxMana());
        }
        //Check if hp>maxHp
        if(hero.getHP()>hero.stat.getMaxHealth()){
            hero.setHP(hero.stat.getMaxHealth());
        }

        return 0;
    }
}
