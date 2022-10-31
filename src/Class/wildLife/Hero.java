package Class.wildLife;

import java.util.*;

import Class.World.Map;
import Class.World.Tile;
import Class.aroundLife.Equipment;
import Class.aroundLife.Weapon;

public class Hero extends Entity {
    //Hero's position
    private int [] pos;
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
    public Hero(String name,int strength,int[]pos)
    {
        super(name, strength);
        //inventory[0] = new Equipment("Sword", 1, 1, 1);
        this.listOfSpell.add(new Spell("FireBall", 3, 5, 25, "Damage"));
        inventory[0] = new Weapon(2,2,2,"Sword","Sword of the Hero");
        inventory[1] = new Weapon(4,2,2,"Bow","Bow of the Hero");
        this.pos=pos;
        System.out.println("A hero named " + name + " Appeared !");
    }
    // Getters
    public int getStrength() {return this.stat.getStrength();}
    public int getDexterity() {return this.stat.getDexterity();}

    // Setters
    public void setExperience(int xp){
        /*
        Manage the XP of the character and his level
        */
        int xpToLevelUp = 10;
        this.experience+=xp;
        if(this.experience>=xpToLevelUp){
            int over_experience=this.experience/xpToLevelUp;
            this.experience-=over_experience*xpToLevelUp;
            this.stat.level+=over_experience;
        }
    }

    // Methods for basic
    public String catchAnswer() {
        /*
        Return the answer of the player
        */
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        return answer;
    }

    // Methods for the health
    public void catchHeal(float heal){
        /*
        Heal the hero
        */
        this.stat.health+=heal; // Heal the hero
        // Check if the hero is not over-healed
        if(this.stat.health>this.stat.maxHealth){
            this.stat.health=this.stat.maxHealth;
        }
    }

    public void catchMana(float mana){
        /*
        Catch the mana of the hero
        */
        this.stat.mana+=mana; // Catch the mana of the hero
        // Check if the hero is not over-healed
        if(this.stat.mana>this.stat.maxMana){
            this.stat.mana=this.stat.maxMana;
        }
    }

    public void addSpell(Spell spell){
        /*
        Add a spell to the list of spell
        */
        this.listOfSpell.add(spell);
    }
    // Methods for the inventory
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
                this.inventory[i].getName();
            }
            else {
                System.out.println("Slot "+i+": Empty");
            }
        }
    }

    // Methods for the fight using Spell
    public boolean displaySpell(){
        /*
        Display the list of spell of the hero
        */
        if(this.listOfSpell.size()==0){
            System.out.println("You don't have any spell sorry");
            return false;
        }
        else {
            System.out.println("List of spell:");
            for (int i = 0; i < this.listOfSpell.size(); i++) {
                System.out.println("Spell " + i + ":");
                this.listOfSpell.get(i).getStatus();
            }
            return true;
        }
    }
    public Spell chooseSpell(){
        /*
        Choose a spell to use
        */
        boolean correctInput=false;
        System.out.println("Which spell do you want to use?");
        if(this.displaySpell()) { // check if the hero has any spell
            while (!correctInput) {
                try {
                    String answer = this.catchAnswer();
                    int answerInt = Integer.parseInt(answer);
                    if (answerInt >= 0 && answerInt < this.listOfSpell.size()) {
                        correctInput = true;
                        return this.listOfSpell.get(answerInt);
                    } else {
                        System.out.println("You didn't answer correctly");
                    }
                } catch (Exception e) {
                    System.out.println("You didn't answer correctly");
                }
            }
        }
        return null;
    }
    public void useSpell(Entity target){
        /*
        Use a spell on an Entity
        */
        Spell spell = this.chooseSpell(); // Choose the spell to use
        // Check type of the spell
        if(spell.getType().equals("Heal")){
            this.catchHeal(spell.getPower());
        }
        else{
            target.getAttack(spell.getPower());
        }
    }

    // Methods for the fight using Equipment
    public Equipment chooseEquipment(){
        /*
        Choose equipment to use
        */
        boolean correctInput=false;
        System.out.println("Which equipment do you want to use?");
        this.displayInventory();
        while(!correctInput){
            try{
                String answer = this.catchAnswer();
                int answerInt = Integer.parseInt(answer);
                if(answerInt>=0 && answerInt<maxSlot){
                    correctInput=true;
                    return this.inventory[answerInt];
                }
                else{
                    System.out.println("You didn't answer correctly");
                }
            }
            catch(Exception e){
                System.out.println("You didn't answer correctly");
            }
        }
        return null;
    }
    public void longAttack(Entity target, Weapon bow){
        /*
        Use a long attack on an Entity
        */
        target.getAttack(bow.getWeaponDamage()+this.getDexterity());
    }
    public void shortAttack(Entity target, Weapon sword){
        /*
        Use a short attack on an Entity
        */
        target.getAttack(sword.getWeaponDamage()+this.getStrength());
    }
    public void handAttack(Entity target){
        /*
        Use a hand attack on an Entity
        */
        target.getAttack(this.getStrength());
        System.out.println("You hit the "+target.getName()+" with your hand");
    }
    public void useEquipment(Entity target){
        /*
        Use equipment on an Entity
        */
        Equipment equipment = this.chooseEquipment(); // Choose the equipment to use
        // Check type of the equipment
        if(equipment.getType().equals("Bow")){
            this.longAttack(target, (Weapon) equipment);
        }
        else if(equipment.getType().equals("Sword")){
            this.shortAttack(target, (Weapon) equipment);
        }
        else{
            System.out.println("God is pleased to know that you are using your hands");
            target.getAttack(this.getStrength());
        }
    }
    // Override
    @Override
    public void attack(Entity target){
        /*
        Attack an Entity
        */
        this.useEquipment(target);
    }
    /*public void Attack(Entity target) {

        // Check if the hero has a bow
        Equipment weapon = this.chooseEquipment();
        if(weapon!=null){
            switch (weapon.getType()) {
                case "Sword":
                    target.getAttack(this.getStrength() + weapon.getDamage());
                    break;
                case "Bow":
                    target.getAttack(this.getDexterity() + weapon.getDamage());
                    break;
                default:
                    System.out.println("God knows what you have in your hand (Error in Attack)");
                    target.getAttack(weapon.getDamage()+this.getStrength());
                    break;
            }
        }
        else{
            System.out.println("You choose to attack with your bare hands");
            target.getAttack(this.getStrength());
        }
    }
    */

    //Hero's initialisation in the map
    public static int[] SpawnHero(Map map){
        Tile Spawn = Tile.Spawn;
        int [] pos;
        //Get the position of the spawn
        pos = map.PosTile(map,Spawn);
        System.out.println("Hero spawn at " + Arrays.toString(pos));
        //Return the position of the spawn
        return pos;
    }

    //Hero's movement on map
    public Tile MoveHero(Hero hero, Map map){
        System.out.println("Choose a direction by typing : up, down, left or right (or z,q,s,d)");

        //Get hero's position
        int [] pos;
        pos = hero.pos;
        //x and y are the coordinates of the hero after choices
        int x = pos[0];
        int y = pos[1];

        //Tile that we use (position is hero's tile and empty is the tile that be placed after hero cleared a tile)
        Tile tile = Tile.Position;
        Tile Empty = Tile.Empty;
        //Move is used to know if the hero can move or not, can't move if there is a wall
        boolean move = false;
        while(!move) {
            //Scanner to get the choice of the player and change the position of the hero
            Scanner sc = new Scanner(System.in);
            String direction = sc.nextLine();
            switch (direction) {
                case "up", "z" -> {
                    move = true;
                    map.ChangeTile(map, pos, Empty);
                    y = y - 1;
                }
                case "down", "s" -> {
                    move = true;
                    map.ChangeTile(map, pos, Empty);
                    y = y + 1;
                }
                case "left", "q" -> {
                    move = true;
                    map.ChangeTile(map, pos, Empty);
                    x = x - 1;
                }
                case "right", "d" -> {
                    move = true;
                    map.ChangeTile(map, pos, Empty);
                    x = x + 1;
                }
            }
        }
        // Hero's new position (tile)
        Tile New_location = map.GetTileMap(map,x,y);
        //If the hero is on a wall, he can't go there
        if (Objects.equals(New_location.GetTile(), "Wall")){
            System.out.println("You can't go there, it's a wall !");
            MoveHero(hero,map);
        }
        //Everything is fine hero can change his position
        else {
            //
            hero.pos[0] = x;
            hero.pos[1] = y;
            //Change tile to position
            map.ChangeTile(map,pos,tile);
            //Show map with the new position of the hero
            map.ShowMap(map);
            //Return the new location
            return New_location;
        }
        //else return tile
        return tile;
    }

}
