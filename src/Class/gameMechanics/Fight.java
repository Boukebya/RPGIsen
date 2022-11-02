package Class.gameMechanics;
import Class.Equipments.Equipment;
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
            displayHeroPossibilities(hero, enemy);
            if(enemy.getHP()<=0){
                System.out.println("You won!");
                isOver=true;

                endFight(hero,enemy);
                //set enemy hp to max hp
                enemy.setHP(enemy.stat.getMaxHealth());
            }
            else {
                enemy.attack(hero);
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

    // Methods
    // Methods for the fight
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
    private void displayField() {
        /*
        Display the lines
        */
        System.out.println("The field is like :");
        System.out.print("[");
        for(int i=0;i<4;i++){
            if(line[i]==null){
                System.out.print(" _");
            }
            else if(line[i].getClass().getName().equals("wildLife.Hero")){
                System.out.print(" U");
            }
            else if(line[i].getClass().getName().equals("wildLife.Enemie")){
                System.out.print(" E");
            }
        }
        System.out.println(" ]\n");
    }
    public void endFight(Hero hero, Enemy enemy){

        //TODO: add gold and exp
        int gold = enemy.dropGold();
        System.out.println("You got "+gold+" gold!");
        hero.modifyGold(gold);

        int exp = enemy.dropExp();
        System.out.println("You got "+exp+" exp!");
        hero.setExperience(exp);

        // Drop item to the hero
        Equipment itemDropped = enemy.dropItem();
        hero.changeItem(itemDropped);
        //manageItemDrop(hero, enemy);
        isOver=true;
    }
    public void newTurn(){
        //Manage the new turn
        turn++;
        //displayField();
        System.out.println("Turn "+turn);
    }
    // Display the possibilities of the hero
    public void displayHeroPossibilities(Hero hero, Enemy enemies){
        /*
        Display Hero's possibilities
        */
        boolean correctInput=false;
        System.out.println("What do you want to do?");
        System.out.println("1. Attack with your weapon");
        System.out.println("2. Use a spell");
        System.out.println("3. Attack with your hands");
        //System.out.println("4. Change position");
        String answer = hero.catchAnswer();
        int answerInt=0;
        while (!correctInput){
            try {
                answerInt = Integer.parseInt(answer);
                if (answerInt>= 1 && answerInt<=3) {
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
            hero.handAttack(enemies);
        }
        /*
        else if (answerInt==4){
            changePosition(hero);
        }
        */
    }
    // Method to move entities in the line
    public void moveEntity(Entity entity, int position){
        /*
        Move an entity in the line
        */
        int oldPosition=0;
        for(int i=0;i<line.length;i++){
            if(line[i]==entity){
                oldPosition=i;
            }
        }
        line[oldPosition]=null;
        line[position]=entity;
    }
    public void moveForward(Entity entity){
        /*
        Move an entity forward
        */
        int oldPosition=0;
        for(int i=0;i<line.length;i++){
            if(line[i]==entity){
                oldPosition=i;
            }
        }
        if(oldPosition<3){
            line[oldPosition]=null;
            line[oldPosition+1]=entity;
        }
    }
    public void moveBackward(Entity entity){
        /*
        Move an entity backward
        */
        int oldPosition=0;
        for(int i=0;i<line.length;i++){
            if(line[i]==entity){
                oldPosition=i;
            }
        }
        if(oldPosition>0){
            line[oldPosition]=null;
            line[oldPosition-1]=entity;
        }
    }
    public void pushEntity(Entity entity, int force){
        /*
        Push an entity in the line
        */
        int oldPosition=0;
        for(int i=0;i<line.length;i++){
            if(line[i]==entity){
                oldPosition=i;
            }
        }
        if(oldPosition+force<line.length){
            line[oldPosition]=null;
            if(line[oldPosition+force]!=null) {
                line[oldPosition + force] = entity;
            }
            else {
                line[oldPosition + force - 1] = entity;
            }
        }
        else if (oldPosition+force>=line.length){
            line[oldPosition]=null;
            if(line[line.length-1]!=null) {
                line[line.length - 1] = entity;
            }
            else {
                line[line.length - 2] = entity;
            }//On pourrait ajouter des dégats comme il devait aller trop loin
        }
        else if(oldPosition+force<0){
            System.out.println("\nYou are trying to push an entity too far!\n\t(warning: impossible)");
            line[oldPosition]=null;
            if(line[0]!=null) {
                line[0] = entity;
            }
            else {
                line[1] = entity;
            }
            //On pourrait aussi ajouter des dégats comme il devait aller trop loin
        }
    }
    public void changePosition(Hero hero){
        /*
        Change the position of the hero
        */
        boolean correctInput=false;
        System.out.println("Where do you want to go?");
        System.out.println("1. Forward");
        System.out.println("2. Backward");
        String answer = hero.catchAnswer();
        int answerInt=0;
        while (!correctInput){
            try {
                answerInt = Integer.parseInt(answer);
                if (answerInt>= 1 && answerInt<=2) {
                    correctInput = true;
                }
            } catch (Exception e) {
                System.out.println("You didn't answer correctly");
                answer = hero.catchAnswer();
            }
        }
        if (answerInt==1){
            moveForward(hero);
        }
        else if (answerInt==2){
            moveBackward(hero);
        }
    }
    // Method to get some position on the line
    public int getDistance(Hero hero, Enemy enemy){
        /*
        Get the distance between two entities
        */
        int heroPosition=0;
        int enemiesPosition2=0;
        for(int i=0;i<line.length;i++){
            if(line[i]==hero){
                heroPosition=i;
            }
            if(line[i]==enemy){
                enemiesPosition2=i;
            }
        }
        return Math.abs(heroPosition-enemiesPosition2);
    }
    public boolean isLineEmpty(int position){
        if (line[position]==null){
            return true;
        }
        else {
            return false;
        }
    }
}
