package Class.wildLife;
import java.util.*;
import Class.Equipments.Consumable;
import Class.World.Map;
import Class.World.Tile;
import Class.Equipments.Armor;
import Class.Equipments.Equipment;
import Class.Equipments.Weapon;

public class Hero extends Entity {
    private int [] pos;
    int experience;
    private final int maxSlot = 5;
    List<Spell> listOfSpell = new ArrayList<>();
    Equipment[] inventory = new Equipment[maxSlot];
    Consumable[] consumableInventory = new Consumable[maxSlot];
    int gold;
    Weapon weapon =  new Weapon(1,2,1,"Sword","Sword of the Hero");
    Armor armor = new Armor(1,"Hero's armor");

    // Constructor
    public Hero(String name,Stat stat,int[]pos) {
        super(stat);
        this.listOfSpell.add(new Spell("FireBall", 3, 5, 5,"Damage", "none",80,"Enemy"));
        this.pos=pos;
        consumableInventory[0] = new Consumable("Welcome Potion",1,"Health",10);
        System.out.println("A hero named " + name + " Appeared !");
    }

    // Getters
    public int getStrength() {return this.stat.getStrength();}
    public int getDexterity() {return this.stat.getDexterity();}
    public int getLevel() {return this.stat.getLevel();}
    public Stat getStat() {return this.stat;}
    public int getExperience() {return this.experience;}
    public int[] getPos() {return this.pos;}
    public int getGold() {return this.gold;}
    public float getMaxHealth() {return this.stat.getMaxHealth();}
    public Weapon getWeapon() {return this.weapon;}
    public Armor getArmor() {return this.armor;}
    public Consumable[] getConsumables() {return this.consumableInventory;}

    //setters
    public void setGold(int gold){
        this.gold = gold;
    }
    public void setArmor(Armor armor){
        this.armor = armor;
    }
    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }
    public void setConsumableInventory(Consumable[] consumableInventory){
        this.consumableInventory = consumableInventory;
    }

    //Methods

    //Gold ans xp modifier
    public void modifyGold(int gold){
        this.gold+=gold;
    }
    public void modifyExperience(int xp){
        int xpToLevelUp = 10;
        this.experience+=xp;
        if(this.experience>=xpToLevelUp){
            int over_experience=this.experience/xpToLevelUp;
            this.experience-=over_experience*xpToLevelUp;
            this.stat.level+=over_experience;

            System.out.println("You leveled up !");
            System.out.println("You are now level " + (this.stat.level));
            System.out.println("Choose 2 stats to increase :");
            //print hero stat
            System.out.println("Strength : " + this.stat.strength);
            System.out.println("Dexterity : " + this.stat.dexterity);
            System.out.println("HP : " + this.stat.maxHealth);
            System.out.println("Mana : " + this.stat.maxMana);

            //scanner
            int i=0;
            while (i!=2) {
                System.out.println("type 1,2,3 or 4");
                Scanner sc = new Scanner(System.in);
                String answer = sc.nextLine();
                boolean good_answer = false;
                //Check if answer is 1,2,3 or 4
                while (!good_answer) {
                    if (answer.equals("1") || answer.equals("2") || answer.equals("3") || answer.equals("4")) {
                        good_answer = true;
                    } else {
                        System.out.println("Please type 1,2,3 or 4");
                        answer = sc.nextLine();
                    }
                }
                switch (answer) {
                    case "1" -> this.stat.strength += 1;
                    case "2" -> this.stat.dexterity += 1;
                    case "3" -> {
                        this.stat.maxHealth += 15;
                        this.stat.health += 15;
                    }
                    case "4" -> {
                        this.stat.maxMana += 10;
                        this.stat.mana += 10;
                    }
                    default -> System.out.println("You didn't choose a stat");
                }
                i++;
            }
        }
    }

    //Movement
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
        System.out.println("Choose a direction by typing : up, down, left or right (or z,q,s,d), or see your stat by typing : stat or equip something by typing equip" );

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
                case "see stat", "stat" -> {
                    System.out.println("Hero's stat :");
                    System.out.println("Health : "+ hero.getHP() + "/" +hero.stat.maxHealth);
                    System.out.println("Mana : "+ hero.getMana() + "/" +hero.stat.maxMana);
                    System.out.println("Strength : " + hero.getStrength());
                    System.out.println("Dexterity : " + hero.getDexterity());
                    System.out.println("Level : " + hero.getLevel());
                    System.out.println("Gold : " + hero.gold);
                    System.out.println("Experience : " + hero.experience);
                    System.out.println("Weapon : " + hero.weapon.getName() + ": "+ hero.weapon.getDamage() + " damage, "+ hero.weapon.getCriticChance() + " crit");
                    System.out.println("Armor : " + hero.armor.getName()+ ": "+ hero.armor.getDefense() + " defense");
                    System.out.println("Inventory :");
                    for (int i = 0; i < hero.inventory.length; i++) {
                        if (hero.inventory[i] != null) {
                            System.out.println(hero.inventory[i].getName());
                        }
                    }
                    System.out.println("Consumable Inventory :");
                    for (int i = 0; i < hero.consumableInventory.length; i++) {
                        if (hero.consumableInventory[i] != null) {
                            System.out.println(hero.consumableInventory[i].getName());
                        }
                    }
                    System.out.println("Spell :");
                    for (int i = 0; i < hero.listOfSpell.size(); i++) {
                        System.out.println(hero.listOfSpell.get(i).getName());
                    }
                }
                case "equip" -> changeEquipment();

                case "consumable" -> this.useConsumable();

            }
        }
        // Hero's new position (tile)
        Tile New_location = map.GetTileMap(map,x,y);
        //If the hero is on a wall, he can't go there
        if (Objects.equals(New_location.GetTile(), "Wall")){
            System.out.println("You can't go there, it's a wall !");
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

    //equipment
    public void changeEquipment(){
        displayInventory();
        //create a scanner
        Scanner sc = new Scanner(System.in);
        //ask the user to choose an equipment
        System.out.println("Choose an equipment to equip");
        //get the choice
        int choice = sc.nextInt();
        choice -=1;
        //get equipment at position choice
        Equipment equipment = inventory[choice];
        System.out.println("You choose to equip " + equipment.getName());

        //if the equipment is a weapon
        if(equipment instanceof Weapon){
            //if the hero has already a weapon
            if(this.weapon != null){
                Weapon lastWeapon = this.weapon;
                removeEquipment(choice);
                //equip the weapon
                setWeapon((Weapon) equipment);
                //add the weapon to the inventory
                this.inventory[choice] = lastWeapon;
            }
            else{
                removeEquipment(choice);
                //equip the weapon
                setWeapon((Weapon) equipment);
            }
        }
        //if the equipment is an armor
        if(equipment instanceof Armor){
            //if the hero has already a weapon
            if(this.armor != null){
                Armor lastArmor = this.armor;
                removeEquipment(choice);
                //equip the weapon
                setArmor((Armor) equipment);
                //add the weapon to the inventory
                this.inventory[choice] = lastArmor;
            }
            else{
                removeEquipment(choice);
                //equip the weapon
                setArmor((Armor) equipment);
            }
        }

        System.out.println("Please choose another action to do (move, see stat or equip something)");
    }
    public void equipmentManagement(Equipment equipment){
        //find the first empty slot in the inventory
        int emptySlot = findEmptySlot();
        //if there is an empty slot
        if(emptySlot != -1){
            //add the equipment to the inventory
            addEquipment(equipment, emptySlot);
        }
        //if there is no empty slot
        else{
            //ask the user to choose equipment to remove
            displayInventory();
            System.out.println("Choose an equipment to remove, or type 0 to throw the equipment");
            //create a scanner
            Scanner sc = new Scanner(System.in);
            //get the choice
            int choice = sc.nextInt();
            choice -=1;
            //remove the equipment
            removeEquipment(choice);
            //add the equipment to the inventory
            addEquipment(equipment, choice);
        }

    }
    public int findEmptySlot(){
        int emptySlot = 0;
        for(int i = 0; i < inventory.length; i++){
            if(inventory[i] == null){
                emptySlot = i;
                break;
            }
        }
        return emptySlot;
    }
    public void changeItem(Equipment item){
        /*
        Add an item to the inventory if player want
        */
        //System.out.println("You found a " + item.getName() + " !");
        item.getStat();
        //System.out.println("\nIt can be replaced by your " + item.getType() + " !");
        if(chooseToKeepItem(item)){
            for(int i=0;i<inventory.length;i++){
                if(inventory[i].getType().equals(item.getType())){
                    inventory[i]=item;
                    break;
                }
            }
        }

    } //not used
    public boolean chooseToKeepItem(Equipment item){
        /*
        Choose to keep the item or not
         */
        System.out.println("In your inventory, you have :");
        getItemStats(item.getType());
        System.out.println("Do you want to change it ? (Y/N)");
        try {
            Scanner sc = new Scanner(System.in);
            String answer = sc.nextLine();
            return answer.equals("Y");
        }catch (Exception e){
            System.out.println("You will not change your item");
            return false;
        }
    } // not used
    public void getItemStats(String type){
        /*
        Display the stats of the item
         */
        switch (type) {
            case "Sword" -> {
                System.out.print("A Sword (" + inventory[0].getName() + "): ");
                System.out.println("Damage: " + ((Weapon) inventory[0]).getDamage() + " Crit chance: " + ((Weapon) inventory[0]).getCriticChance());
            }
            case "Bow" -> {
                System.out.print("A Bow (" + inventory[0].getName() + "): ");
                System.out.println("Damage: " + ((Weapon) inventory[1]).getDamage() + " Crit chance: " + ((Weapon) inventory[0]).getCriticChance());
            }
            case "Armor" -> {
                System.out.print("An Armor (" + inventory[0].getName() + "): ");
                System.out.println("Defence: " + ((Armor) inventory[2]).getDefense());
            }
            default -> System.out.println("You don't have this type of item ! \n\t(Error Item type)");
        }
    } //not used for the moment
    public void addEquipment(Equipment equipment, int slot) {
        // Add equipment to the inventory
        this.inventory[slot] = equipment;

    }
    public void removeEquipment(int slot){
        if (slot > maxSlot) {
            System.out.println("Slot not available");
        } else {
            this.inventory[slot] = null;
        }
    }
    public void displayInventory(){
        /*
        Display the inventory of the hero
        */
        System.out.println("Inventory:");
        for(int i=0;i<maxSlot;i++){
            if(this.inventory[i]!=null){
                System.out.println("Slot "+(i+1)+": "+this.inventory[i].getName());
            }
            else {
                System.out.println("Slot "+(i+1)+": Empty");
            }
        }
    }

    //Consumable
    public void useConsumable(Consumable[] potions){
    //To do
    }

    //Spells
    public void addSpell(Spell spell){
        /*
        Add a spell to the list of spell
        */
        this.listOfSpell.add(spell);
    }
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

    // Methods for basic
    public String catchAnswer() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    // Methods for the health
    public void catchHeal(int heal){
        /*
        Heal the hero
        */
        this.stat.health+=heal; // Heal the hero
        // Check if the hero is not over-healed
        if(this.stat.health>this.stat.maxHealth){
            this.stat.health=this.stat.maxHealth;
        }
    }
    public void catchMana(int mana){
        /*
        Catch the mana of the hero
        */
        this.stat.mana+=mana; // Catch the mana of the hero
        // Check if the hero is not over-healed
        if(this.stat.mana>this.stat.maxMana){
            this.stat.mana=this.stat.maxMana;
        }
    }

    // Methods used during a fight
    public void useSpell(Entity target){
        Spell spell = this.chooseSpell(); // Choose the spell to use
        // Check type of the spell
        if(spell.getType().equals("Heal")){
            this.catchHeal(spell.getPower());
        }
        else{
            //verify if the hero has enough mana
            if(this.stat.mana>=spell.getManaUse()){
                this.catchMana(-spell.getManaUse());
                target.getHit(spell.getPower());
            }
            else{
                System.out.println("You don't have enough mana!");
            }
        }
    }
    public void defend(){
        System.out.println("You protect yourself");
        //double defense
        int armor = this.getArmor().getDefense();
        armor = armor*2;
        this.getArmor().setDefense(armor);
    }
    public void reduceDefense(){
        int armor = this.getArmor().getDefense();
        armor = armor/2;
        this.getArmor().setDefense(armor);
    }
    public void useConsumable() {
        boolean available = false;
        for (Consumable value : this.consumableInventory) {
            if (value != null) {
                available = true;
                break;
            }
        }

        if(available) {
            for (Consumable consumable : this.consumableInventory) {
                if (consumable != null) {
                    System.out.println(consumable.getName());
                }
            }
            //choose consumable
            System.out.println("Choose a consumable to use");
            Scanner sc2 = new Scanner(System.in);
            String consumableName = sc2.nextLine();
            //if 1 is chosen, use the consumable
            if (consumableName.equals("1")) {
                this.consumableInventory[0].use(this);
                this.consumableInventory[0] = null;
            }
            if (consumableName.equals("2")) {
                this.consumableInventory[1].use(this);
                this.consumableInventory[1] = null;
            }
            if (consumableName.equals("3")) {
                this.consumableInventory[2].use(this);
                this.consumableInventory[2] = null;
            }
            if (consumableName.equals("4")) {
                this.consumableInventory[3].use(this);
                this.consumableInventory[3] = null;
            }
            if (consumableName.equals("5")) {
                this.consumableInventory[4].use(this);
                this.consumableInventory[4] = null;
            }

        }
    }
    @Override
    public void attack(Entity target){
        int damage;
        Weapon weapon = this.weapon;
        damage = weapon.getDamage();
        //if type sword
        if(Objects.equals(weapon.getType(), "Sword")){
            damage += this.getDexterity()+this.getStrength();
        }
        else if(Objects.equals(weapon.getType(), "Bow")){
            damage += this.getDexterity()*2;
        }
        if(Objects.equals(weapon.getType(), "Club")){
            damage += this.getStrength()*2;
        }

        boolean isCrit=false;
        //apply crit chance
        if(Math.random() >= weapon.getCriticChance()){
            damage *= 2;
            isCrit = true;
        }

        //Apply accuracy
        if(Math.random() <= (0.8 + (this.getDexterity()*2))){
            System.out.print("You hit "+target.getName());
            if(isCrit){
                System.out.println(" (Critical hit!)");
            }
            target.getHit(damage);
        }
        else{
            System.out.println("You missed");
        }
    }
    @Override
    public void getHit(float damage){
            //reduce damage with armor
            int armor = this.armor.getDefense();
            damage -= (armor);

            if(damage<0){
                damage=0;
            }

        if (this.stat.health - damage <= 0) {
            this.killed();
        } else {
            this.stat.health -= damage;
            System.out.println(this.stat.name + "'s HP = " + this.stat.health);
        }
    }
}
