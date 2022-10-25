package Class.wildLife;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Class.aroundLife.Equipment;

public class Hero extends Entity {

    int experience;
    private final int maxSlot = 4; // Max number of slot for the inventory
    // Inventory of the hero:
    // - 0 slot for the sword
    // - 1 slot for the bow
    // - 2 slot for the chest-plate
    // - 3 slot for the helmet

    List<Spell> listOfSpell = new ArrayList<Spell>();
    Equipment[] inventory = new Equipment[maxSlot];

    // Constructor
    public Hero(String name,int strength)
    {
        super(name, strength);
        //inventory[0] = new Equipment("Sword", 1, 1, 1);
    }

    public void setExperience(int xp){
        /*
        Manage the XP of the character and his level
        */
        this.experience+=xp;
        if(this.experience>=10){
            this.experience-=10;
            this.stat.level+=1;
        }
    }

    public String getAnswer() {
        /*
        Return the answer of the player
        */
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        return answer;
    }

    public void addSpell(Spell spell){
        /*
        Add a spell to the list of spell
        */
        this.listOfSpell.add(spell);
    }

    public void addEquipment(Equipment equipment, int slot) {
        /*
        Add equipment to the inventory
        */
        if (equipment != null) {
            if (slot > maxSlot) {
                System.out.println("Slot not available");
            } else {
                this.inventory[slot] = equipment;
            }
            this.inventory[slot] = equipment;
        }
    }

    public void displayInventory(){
        /*
        Display the inventory of the hero
        */
        System.out.println("Inventory:");
        for(int i=0;i<maxSlot;i++){
            if(this.inventory[i]!=null){
                System.out.println("Slot "+i+":");
                this.inventory[i].getStat();
            }
        }
    }
}
