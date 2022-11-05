package Class.gameMechanics;
import Class.wildLife.Enemy;
import Class.wildLife.Hero;

public class Fight {
    int turn=0;
    boolean isOver=false; // true if the fight is over

    // Constructor
    public Fight(Hero hero, Enemy enemy){
        beginFight(hero, enemy);
        while(!isOver){
            newTurn();
            int choice = displayHeroPossibilities(hero, enemy);

            //if enemy is dead, end fight
            if(enemy.getHP()<=0){
                System.out.println("You won!");
                isOver=true;

                endFight(hero,enemy);
                //set enemy hp to max hp
                enemy.setHP(enemy.stat.getMaxHealth());
            }
            //else it attacks
            else {
                enemy.spellManager(hero);
            }

            //if choice = 1, divide by 2 defense (player defend himself so set back his armor to normal)
            if(choice==4){
                hero.reduceDefense();
            }

            //if hero is dead, end fight
            if(hero.getHP()<=0){
                System.out.println("You lost!");
                isOver=true;
            }
            System.out.println();
        }
    }

    //Getters
    public boolean isOver(){return this.isOver;}
    public int getTurn(){return this.turn;}

    //Methods
    //Increment turn by 1 and display it
    public void newTurn(){
        //Manage the new turn
        turn++;
        //displayField();
        System.out.println("Turn "+turn);
    }
    //Begin fight by displaying hero and enemy stats
    public void beginFight(Hero hero, Enemy enemy){
        //Display player and enemy stats
        System.out.println("\nYou are going to fight this monster:");
        enemy.stat.introduce();
        System.out.println("\nYou are at this point: ");
        hero.stat.introduce();
        System.out.println("\nGood luck!\n");
    }
    //End fight by managing xp and gold drop, display xp and gold drop
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

    // Display the possibilities of the hero
    public int displayHeroPossibilities(Hero hero, Enemy enemies){
        boolean correctInput=false;
        //Display possibilities
        displayChoice();

        //Get the choice of the player and check if it's correct
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
        //Manage choice
        int action = manageChoice(answerInt, hero, enemies);
        //Check if mana>maxMana
        hero.limitManager(hero);

        //return allows the program to set back value to default depending on the choice
        //for example, if the player chose to defend himself, the armor value is divided by 2
        return action;
    }
    //Manage 4 possibilities
    public int manageChoice(int answerInt, Hero hero, Enemy enemy){
        if (answerInt==1){
            hero.attack(enemy);
        }
        else if (answerInt==2){
            hero.useSpell(enemy);
        }
        else if (answerInt==3){
            hero.useConsumable();
        }
        else if (answerInt==4){
            hero.defend();
        }
        return answerInt;
    }

    //Display the choice of the hero
    public void displayChoice(){
        System.out.println("What do you want to do?");
        System.out.println("1. Attack with your weapon");
        System.out.println("2. Use a spell");
        System.out.println("3. Use a consumable");
        System.out.println("4. Protect yourself");
    }

}
