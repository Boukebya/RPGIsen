/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.aroundLife;

import Class.wildLife.Enemie;
import Class.wildLife.Hero;

/**
 *
 * @author theap
 */
public class Fight {
    int turn=0;
    boolean isOver=false; // true if the fight is over

    // Constructor
    public Fight(Hero hero, Enemie enemies){
        beginFight(hero, enemies);
        while(!isOver){
            turn++;
            System.out.println("Turn "+turn);
            hero.Attack(enemies);
            if(enemies.getHP()<=0){
                System.out.println("You won!");
                isOver=true;
                endFight(hero,enemies);
            }
            else {
                enemies.Attack(hero);
            }

            if(hero.getHP()<=0){
                System.out.println("You lost!");
                isOver=true;
            }
            System.out.println("\n");
        }
    }

    // Methods
    public void beginFight(Hero hero, Enemie enemies){
        /*
        The fight begin
        */
        System.out.println("\nYou are going to fight this monster:");
        enemies.stat.getStatus();
        System.out.println("\nYou are at this point: ");
        hero.stat.getStatus();
        System.out.println("good luck!\n");

    }

    public void endFight(Hero hero, Enemie enemies){
        /*
        Manage the end of the fight
         */
        // Give XP to the hero
        hero.setExperience(enemies.getLevel());
        // Drop item to the hero
        manageItemDrop(hero, enemies);
        isOver=true;
    }

    public Equipment chooseToKeepItem(Hero hero, Enemie enemies){
        /*
        Choose to keep the item or not
        */
        boolean correctInput=false;
        Equipment item =enemies.dropItem();
        System.out.println("You found an Item!");
        item.getStat();
        System.out.println("Do you want to keep it? (y/n)");
        while(!correctInput) {
            try {
                String answer = hero.getAnswer();
                if (answer.equals("y")) {
                    correctInput = true;
                    System.out.println("You keep the item");
                    return item;
                } else {
                    correctInput = true;
                    return null;
                }
            } catch (Exception e) {
                System.out.println("You didn't answer correctly");
                return null;
            }
        }
        return null;
    }

    public void manageItemDrop(Hero hero, Enemie enemies){
        /*
        Manage the item drop by the enemies
        */
        Equipment item = this.chooseToKeepItem(hero, enemies);
        if(item!=null){
            hero.addEquipment(item,0);
        }
        else {
            System.out.println("You didn't keep the item");
        }
    }

    // Getters and Setters
    public boolean isOver(){return this.isOver;}
    public int getTurn(){return this.turn;}
}
