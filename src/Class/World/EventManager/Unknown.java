package Class.World.EventManager;
import Class.WildLife.Hero;
import Class.WildLife.Spell;

public class Unknown extends TypeEvents {
    String name_Object;

    //Constructor
    public Unknown(TypeEvents type_Event,String name_Object) {
        super(type_Event.name, type_Event.rarity);
        this.name_Object= name_Object;
    }


    public void interact(Hero hero, Spell [] spells) {
        //get a random action between 0 and 2
        int random_action = (int) (Math.random() * 4);
        //if random action is 0, nothing happens
        if (random_action == 0) {
            //get random int between 0 and 2
            int random = (int) (Math.random() * 3);
            //if random is 0, nothing happens
            if (random == 0) {
                System.out.println("You found an old altar but you don't know it's purpose, so you leave it alone.");
            }
            else if (random == 1) {
                System.out.println("As you walk, you realise that you are lost. You don't know where you are going.");
            }
             else{
                System.out.println("you trip over a stone and fall on the ground. You get up and continue your journey, but you feel miserable.");
            }
        }
        //if random action is 1, you get a random spell
        else if (random_action == 1) {
            System.out.println("You found an " + this.name_Object + ", in a small book you found a spell!");
            //get a random spell
            int random_spell = (int) (Math.random() * spells.length);
            //get the spell
            Spell spell = spells[random_spell];
            //add the spell to the hero
            hero.addSpell(spell);
        }
        //if random action is 2, you get a random amount of gold
        else if (random_action == 2){
            //get a random amount of gold
            int random_gold = (int) (Math.random() * 30);
            //print that you found a random amount of gold
            System.out.println("You found " + random_gold + " gold!");
            //add the gold to your inventory
            hero.setGold(hero.getGold() + random_gold);
        }
        //if random action is 3, you get heal
        else if (random_action == 3){
            System.out.println("You found a blind woman walking alone in the dungeon. She is lost and you help her find her way.");
            System.out.println("She thanks you and blesses you. You feel way better and get your hp and mana back.");
            //set hero hp and mana to max
            hero.setHP((int) hero.getMaxHealth());
            hero.setMana(hero.getMana());
        }

    }


}
