package Class.fileManagement;

import Class.Equipments.Armor;
import Class.Equipments.Consumable;
import Class.Equipments.Equipment;
import Class.Equipments.Weapon;
import Class.World.Event_Manager.Chest;
import Class.World.Event_Manager.Merchant;
import Class.World.Event_Manager.Type_Events;
import Class.wildLife.Enemy;
import Class.wildLife.Spell;
import Class.wildLife.Stat;

public class gameObjects {
    //Function to initialize chests
    public static Chest[] InitChests(){
        //Create array of chests
        Chest[] chests = new Chest[4];
        //We create a new type of event
        Type_Events Chest_Event = new Type_Events("Chest",1);

        //We create a wooden chest
        Chest wooden_Chest = new Chest(Chest_Event,"Wooden chest", new int[]{75, 10, 10, 5, 0},"You enter a small cave, it seems that somebody left quickly..\nAfter some research, you find something interesting..",new String[]{"Open it","Leave it"},new String[]{"You found an item !","You left it"});
        chests[0] = wooden_Chest;
        //modify rarity
        chests[0].setRarity(50);

        //We create a bloody chest
        Chest bloody_Chest = new Chest(Chest_Event,"bloody chest",new int[]{20, 30, 30, 15, 5},"Lost in ruins, you find a corpse of an adventurer just like you.\nThe trail of blood left behind seems to lead somewhere..\nYou decide to follow it and after a moment you you come across a red chest.\nyou don't know why, but you instinctively understand what the chest wants..\nshivers run through your body.",new String[]{"Open","Leave"},new String[]{"The chest opens by itself, after a few seconds, it vomits an object...","as you walk away, grunts echo through the ruins"});
        chests[1] = bloody_Chest;
        //modify rarity
        chests[1].setRarity(20);

        //We create a golden chest
        Chest golden_Chest = new Chest(Chest_Event,"golden chest",new int[]{40, 30, 25, 5, 0},".You found a golden chest...",new String[]{"Open","Leave"},new String[]{"You found an item!","You left it"});
        chests[2] = golden_Chest;
        //modify rarity
        chests[2].setRarity(20);

        //We create a legendary chest
        Chest legendary_Chest = new Chest(Chest_Event,"legendary chest",new int[]{0, 15, 40, 25, 20},".You found a legendary chest!",new String[]{"Open","Leave"},new String[]{"You found an item!","You left it"});
        chests[3] = legendary_Chest;
        //modify rarity
        chests[3].setRarity(10);

        return chests;
    }
    //Function to initialize spells
    public static Spell[] InitEnemiesSpells(){
        // initialize array of spell
        Spell[] enemiesSpells = new Spell[5];
        enemiesSpells[0] = new Spell("Attack", 0, 0, 1,"Basic", "none",80,"Enemy");
        enemiesSpells[1] = new Spell("Heal", 0, 5, 10,"Heal", "none",100,"Self");
        enemiesSpells[2] = new Spell("Buff", 0, 5, 2,"Buff", "none",100,"Self");
        enemiesSpells[3] = new Spell("Charge", 1, 2, 2,"Huge", "none",50,"Enemy");
        enemiesSpells[4] = new Spell("Stab stab stab !", 1, 2, 4,"Multiple", "none",75,"Enemy");

        return enemiesSpells;
    }
    //Function to initialize hero spells
    public static Spell[] InitHeroSpells(){
        // initialize array of spell
        Spell[] heroSpells = new Spell[6];
        heroSpells[0] = new Spell("Heavy strike", 0, 2, 2,"Huge", "none",80,"Enemy");
        heroSpells[1] = new Spell("Precision strike", 0, 2, 1,"Precision", "none",100,"Enemy");
        heroSpells[2] = new Spell("Heal", 0, 3, 10,"Heal", "none",100,"Self");
        heroSpells[3] = new Spell("Super Heal", 0, 5, 20,"Heal", "none",100,"Self");
        heroSpells[4] = new Spell("Whirlwind", 0, 5, 6,"Multiple", "none",30,"Enemy");
        heroSpells[5] = new Spell("Triple hit", 0, 4, 3,"Multiple", "none",75,"Enemy");

        return heroSpells;
    }
    //Function to initialize enemies
    public static Enemy[] InitEnemies(Spell[] enemiesSpells){
        //Create array of enemies_fight
        Enemy[] enemies = new Enemy[10];

        //Create goblin stat
        Stat goblinStat = new Stat(1,1,5,5,10,10,1,"Goblin");
        //Create goblin enemies
        Spell[] spearGoblinSpells = new Spell[2];
        spearGoblinSpells[0] = enemiesSpells[0];
        spearGoblinSpells[1] = enemiesSpells[3];
        enemies[0] = new Enemy(goblinStat,"Spear goblin",spearGoblinSpells);
        enemies[0].setStrength(3);
        Spell[] daggerGoblinSpells = new Spell[2];
        daggerGoblinSpells[0] = enemiesSpells[0];
        daggerGoblinSpells[1] = enemiesSpells[4];
        enemies[1] = new Enemy(goblinStat,"Knife goblin",daggerGoblinSpells);
        enemies[1].setDexterity(2);

        //Create Skeleton stat
        Stat skeletonStat = new Stat(5,1,5,5,15,15,2,"Skeleton");
        //Create Skeleton enemies
        skeletonStat.setStrength(3);
        Spell[] swordSkeletonSpells = new Spell[1];
        swordSkeletonSpells[0] = enemiesSpells[0];
        enemies[2] = new Enemy(skeletonStat,"Bag of bones",swordSkeletonSpells);

        Spell[] daggerSkeletonSpells = new Spell[2];
        daggerSkeletonSpells[0] = enemiesSpells[0];
        daggerSkeletonSpells[1] = enemiesSpells[4];
        enemies[3] = new Enemy(skeletonStat,"Baguette Skeleton",daggerSkeletonSpells);
        skeletonStat.setDexterity(1);

        //Create human stat
        Stat humanStat = new Stat(7,7,6,6,22,22,3,"Human");
        //Create human enemies
        Spell[] BanditSpells = new Spell[2];
        BanditSpells[0] = enemiesSpells[0];
        BanditSpells[1] = enemiesSpells[4];
        enemies[4] = new Enemy(humanStat,"Bandit",BanditSpells);
        Spell[] hoodlumSpells = new Spell[2];
        hoodlumSpells[0] = enemiesSpells[0];
        hoodlumSpells[1] = enemiesSpells[1];
        enemies[5] = new Enemy(humanStat,"Hoodlum",hoodlumSpells);

        //Create elf stat
        Stat elfStat = new Stat(8,8,10,10,27,27,4,"Elf");
        //Create elf enemies
        Spell[] mageElfSpells = new Spell[2];
        mageElfSpells[0] = enemiesSpells[0];
        mageElfSpells[1] = enemiesSpells[1];
        enemies[6] = new Enemy(elfStat,"Mage elf",mageElfSpells);
        Spell[] archerElfSpells = new Spell[2];
        archerElfSpells[0] = enemiesSpells[0];
        archerElfSpells[1] = enemiesSpells[4];
        enemies[7] = new Enemy(elfStat,"Archer elf",archerElfSpells);

        //Create orc stat
        Stat orcStat = new Stat(10,10,10,10,35,35,5,"orc");
        //Create orc enemies
        Spell[] OrcSpells = new Spell[2];
        OrcSpells[0] = enemiesSpells[0];
        OrcSpells[1] = enemiesSpells[3];
        enemies[8] = new Enemy(orcStat,"Orc",OrcSpells);
        Spell[] assassinOrcSpells = new Spell[2];
        assassinOrcSpells[0] = enemiesSpells[0];
        assassinOrcSpells[1] = enemiesSpells[4];
        enemies[9] = new Enemy(orcStat,"Assassin orc",assassinOrcSpells);

        return enemies;
    }
    //Function to initialize bosses
    public static Enemy[] InitBosses(Spell[] enemiesSpells){
        //Create array of enemies_fight
        Enemy[] Bosses = new Enemy[8];

        //Create Ogre stat
        Stat ogreStat = new Stat(1,6,10,10,35,35,3,"Ogre");
        //First boss
        Spell[] ogreSpells = new Spell[2];
        ogreSpells[0] = new Spell("Smash", 0, 2, 2,"Huge", "none",60,"Enemy");
        ogreSpells[1] = enemiesSpells[0];
        Bosses[0] = new Enemy(ogreStat,"Bratirek",ogreSpells);
        //Create Breana stat
        Stat BreanaStat = new Stat(5,1,10,10,30,30,3,"Human");
        //Create Breana boss
        Spell[] BreanaSpells = new Spell[1];
        BreanaSpells[0] = enemiesSpells[0];
        Bosses[1] = new Enemy(BreanaStat,"Breana, the silent",BreanaSpells);

        //Create Ogre stat
        Stat centaurStat = new Stat(1,8,10,10,50,50,5,"Centaur");
        //First boss
        Spell[] EupenioSpells = new Spell[1];
        EupenioSpells[0] = enemiesSpells[0];
        Bosses[2] = new Enemy(centaurStat,"Eupenio",EupenioSpells);
        //Create Breana stat
        Stat OrgothStat = new Stat(8,1,20,20,50,50,5,"Orc");
        //Create Breana boss
        Spell[] OrgothSpells = new Spell[1];
        OrgothSpells[0] = enemiesSpells[0];
        Bosses[3] = new Enemy(OrgothStat,"Orgoth",OrgothSpells);

        //Create Ogre stat
        Stat golemStat = new Stat(1,9,10,10,75,75,7,"Golem");
        //First boss
        Spell[] golemSpells = new Spell[1];
        golemSpells[0] = enemiesSpells[0];
        Bosses[4] = new Enemy(golemStat,"Unon",golemSpells);
        //Create Breana stat
        Stat CiradylStat = new Stat(8,4,10,10,65,65,7,"Elf");
        //Create Breana boss
        Spell[] CiradylSpells = new Spell[1];
        CiradylSpells[0] = enemiesSpells[0];
        Bosses[5] = new Enemy(CiradylStat,"Ciradyl",CiradylSpells);

        //Create Ogre stat
        Stat dragonStat = new Stat(1,14,10,10,100,100,9,"Dragon");
        //First boss
        Spell[] dragonSpells = new Spell[1];
        dragonSpells[0] = enemiesSpells[0];
        Bosses[6] = new Enemy(dragonStat,"bralzranth, Eternal Fire",dragonSpells);
        //Create Breana stat
        Stat Alvaxoth = new Stat(10,10,20,20,90,90,9,"Demon");
        //Create Breana boss
        Spell[] AlvaxothSpells = new Spell[1];
        AlvaxothSpells[0] = enemiesSpells[0];
        Bosses[7] = new Enemy(Alvaxoth,"Alvaxoth",AlvaxothSpells);



        return Bosses;
    }
    //Function to initialize merchants
    public static Merchant[] InitMerchants(){
        //Create array of merchants
        Merchant[] merchants = new Merchant[2];
        //Create type events merchant
        Type_Events Merchant_Event = new Type_Events("Merchant",1);
        //We create a new type of event
        merchants[0] = new Merchant(Merchant_Event,"Andre, the smith",1,"Goods");
        merchants[1] = new Merchant(Merchant_Event,"Jean-Pierre, the potion merchant",1,"Potions");


        return merchants;
    }
    //Function to initialize weapons
    public static Equipment[] InitEquipments(){
        //Create array of weapons
        Equipment[] equipment = new Equipment[26];

        equipment[0] = new Weapon(2,0.05f,1,"Club","Broken club");
        equipment[1] = new Weapon(3,0.1f,2,"Club","Common club");
        equipment[2] = new Weapon(5,0.15f,3,"Club","Rare club");
        equipment[3] = new Weapon(8,0.2f,4,"Club","Mythic club");
        equipment[4] = new Weapon(12,0.3f,5,"Club","Legendary stick");

        equipment[5] = new Weapon(1,0.1f,1,"Sword","Broken sword");
        equipment[6] = new Weapon(2,0.15f,2,"Sword","Common sword");
        equipment[7] = new Weapon(5,0.3f,3,"Sword","Rare sword");
        equipment[8] = new Weapon(7,0.35f,4,"Sword","Mythic sword");
        equipment[9] = new Weapon(10,0.4f,5,"Sword","Legendary sword");

        equipment[10] = new Weapon(2,0.1f,1,"Bow","Broken bow");
        equipment[11] = new Weapon(3,0.2f,2,"Bow","Common bow");
        equipment[12] = new Weapon(4,0.4f,3,"Bow","Rare bow");
        equipment[13] = new Weapon(6,0.5f,4,"Bow","Mythic bow");
        equipment[14] = new Weapon(8,0.7f,5,"Bow","Legendary bow");

        equipment[15] = new Armor(1,"Leather armor");
        equipment[15].setRarity(1);
        equipment[16] = new Armor(2,"Iron armor");
        equipment[16].setRarity(2);
        equipment[17] = new Armor(4,"plate armor");
        equipment[17].setRarity(3);
        equipment[18] = new Armor(6,"Dragon slayer armor");
        equipment[18].setRarity(4);
        equipment[19] = new Armor(8,"Araqiel's armor");
        equipment[19].setRarity(5);

        equipment[20] = new Consumable("Potion of health",1,"Health",10);
        equipment[21] = new Consumable("Potion of mana",1,"Mana",10);
        equipment[22] = new Consumable("Great potion of health",2,"Health",25);
        equipment[23] = new Consumable("Great potion of mana",2,"Mana",50);
        equipment[24] = new Consumable("Legendary potion of health",3,"Health",50);
        equipment[25] = new Consumable("Legendary potion of mana",3,"Mana",50);

        return equipment;
    }
}
