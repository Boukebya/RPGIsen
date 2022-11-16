package Class.FileManagement;

import Class.Equipments.Armor;
import Class.Equipments.Consumable;
import Class.Equipments.Equipment;
import Class.Equipments.Weapon;
import Class.WildLife.Enemy;
import Class.WildLife.Spell;
import Class.WildLife.Stat;
import Class.World.EventManager.Chest;
import Class.World.EventManager.Merchant;
import Class.World.EventManager.TypeEvents;

public abstract class GameObjects {
    //Function to initialize chests
    public static Chest[] InitChests() {
        //Create array of chests
        Chest[] chests = new Chest[4];
        //We create a new type of event
        TypeEvents chestEvent = new TypeEvents("Chest", 1);

        //We create a wooden chest
        Chest woodenChest = new Chest(chestEvent, "Wooden chest", new int[]{75, 10, 10, 5, 0}, "You enter a small cave, it seems that somebody left quickly..\nAfter some research, you find something interesting..", new String[]{"Open it", "Leave it"}, new String[]{"You found an item !", "You left it"});
        chests[0] = woodenChest;
        //modify rarity
        chests[0].setRarity(50);

        //We create a bloody chest
        Chest bloodyChest = new Chest(chestEvent, "bloody chest", new int[]{20, 30, 30, 15, 5}, "Lost in ruins, you find a corpse of an adventurer just like you.\nThe trail of blood left behind seems to lead somewhere..\nYou decide to follow it and after a moment you you come across a red chest.\nyou don't know why, but you instinctively understand what the chest wants..\nshivers run through your body.", new String[]{"Open", "Leave"}, new String[]{"The chest opens by itself, after a few seconds, it vomits an object...", "as you walk away, grunts echo through the ruins"});
        chests[1] = bloodyChest;
        //modify rarity
        chests[1].setRarity(20);

        //We create a golden chest
        Chest goldenChest = new Chest(chestEvent, "golden chest", new int[]{40, 30, 25, 5, 0}, ".You found a golden chest...", new String[]{"Open", "Leave"}, new String[]{"You found an item!", "You left it"});
        chests[2] = goldenChest;
        //modify rarity
        chests[2].setRarity(20);

        //We create a legendary chest
        Chest legendaryChest = new Chest(chestEvent, "legendary chest", new int[]{0, 15, 40, 25, 20}, ".You found a legendary chest!", new String[]{"Open", "Leave"}, new String[]{"You found an item!", "You left it"});
        chests[3] = legendaryChest;
        //modify rarity
        chests[3].setRarity(10);

        return chests;
    }

    //Function to initialize spells
    public static Spell[] InitEnemiesSpells() {
        // initialize array of spell
        Spell[] enemiesSpells = new Spell[5];
        enemiesSpells[0] = new Spell("Attack", 0, 0, 1, "Basic", "none", 80, "Enemy");
        enemiesSpells[1] = new Spell("Heal", 0, 5, 10, "Heal", "none", 100, "Self");
        enemiesSpells[2] = new Spell("Buff", 0, 5, 2, "Buff", "none", 100, "Self");
        enemiesSpells[3] = new Spell("Charge", 1, 2, 2, "Huge", "none", 50, "Enemy");
        enemiesSpells[4] = new Spell("Stab stab stab !", 1, 2, 4, "Multiple", "none", 75, "Enemy");

        return enemiesSpells;
    }

    //Function to initialize hero spells
    public static Spell[] InitHeroSpells() {
        // initialize array of spell
        Spell[] heroSpells = new Spell[6];
        heroSpells[0] = new Spell("Heavy strike", 0, 2, 2, "Huge", "none", 80, "Enemy");
        heroSpells[1] = new Spell("Precision strike", 0, 2, 1, "Precision", "none", 100, "Enemy");
        heroSpells[2] = new Spell("Heal", 0, 3, 10, "Heal", "none", 100, "Self");
        heroSpells[3] = new Spell("Super Heal", 0, 5, 20, "Heal", "none", 100, "Self");
        heroSpells[4] = new Spell("Whirlwind", 0, 5, 6, "Multiple", "none", 30, "Enemy");
        heroSpells[5] = new Spell("Triple hit", 0, 4, 3, "Multiple", "none", 75, "Enemy");

        return heroSpells;
    }

    //Function to initialize enemies
    public static Enemy[] InitEnemies(Spell[] enemiesSpells) {
        //Create array of enemies_fight
        Enemy[] enemies = new Enemy[10];

        //Create goblin stat
        Stat goblinStat = new Stat(1, 1, 5, 5, 10, 10, 1, "Goblin");
        //Create goblin enemies
        Spell[] spearGoblinSpells = new Spell[2];
        spearGoblinSpells[0] = enemiesSpells[0];
        spearGoblinSpells[1] = enemiesSpells[3];
        enemies[0] = new Enemy(goblinStat, "Spear goblin", spearGoblinSpells);
        enemies[0].setStrength(3);
        Spell[] daggerGoblinSpells = new Spell[2];
        daggerGoblinSpells[0] = enemiesSpells[0];
        daggerGoblinSpells[1] = enemiesSpells[4];
        enemies[1] = new Enemy(goblinStat, "Knife goblin", daggerGoblinSpells);
        enemies[1].setDexterity(2);

        //Create Skeleton stat
        Stat skeletonStat = new Stat(5, 1, 5, 5, 15, 15, 2, "Skeleton");
        //Create Skeleton enemies
        skeletonStat.setStrength(3);
        Spell[] swordSkeletonSpells = new Spell[1];
        swordSkeletonSpells[0] = enemiesSpells[0];
        enemies[2] = new Enemy(skeletonStat, "Bag of bones", swordSkeletonSpells);

        Spell[] daggerSkeletonSpells = new Spell[2];
        daggerSkeletonSpells[0] = enemiesSpells[0];
        daggerSkeletonSpells[1] = enemiesSpells[4];
        enemies[3] = new Enemy(skeletonStat, "Baguette Skeleton", daggerSkeletonSpells);
        skeletonStat.setDexterity(1);

        //Create human stat
        Stat humanStat = new Stat(7, 7, 6, 6, 22, 22, 3, "Human");
        //Create human enemies
        Spell[] banditSpells = new Spell[2];
        banditSpells[0] = enemiesSpells[0];
        banditSpells[1] = enemiesSpells[4];
        enemies[4] = new Enemy(humanStat, "Bandit", banditSpells);
        Spell[] hoodlumSpells = new Spell[2];
        hoodlumSpells[0] = enemiesSpells[0];
        hoodlumSpells[1] = enemiesSpells[1];
        enemies[5] = new Enemy(humanStat, "Hoodlum", hoodlumSpells);

        //Create elf stat
        Stat elfStat = new Stat(8, 8, 10, 10, 27, 27, 4, "Elf");
        //Create elf enemies
        Spell[] mageElfSpells = new Spell[2];
        mageElfSpells[0] = enemiesSpells[0];
        mageElfSpells[1] = enemiesSpells[1];
        enemies[6] = new Enemy(elfStat, "Mage elf", mageElfSpells);
        Spell[] archerElfSpells = new Spell[2];
        archerElfSpells[0] = enemiesSpells[0];
        archerElfSpells[1] = enemiesSpells[4];
        enemies[7] = new Enemy(elfStat, "Archer elf", archerElfSpells);

        //Create orc stat
        Stat orcStat = new Stat(10, 10, 10, 10, 35, 35, 5, "orc");
        //Create orc enemies
        Spell[] orcSpells = new Spell[2];
        orcSpells[0] = enemiesSpells[0];
        orcSpells[1] = enemiesSpells[3];
        enemies[8] = new Enemy(orcStat, "Orc", orcSpells);
        Spell[] assassinOrcSpells = new Spell[2];
        assassinOrcSpells[0] = enemiesSpells[0];
        assassinOrcSpells[1] = enemiesSpells[4];
        enemies[9] = new Enemy(orcStat, "Assassin orc", assassinOrcSpells);

        return enemies;
    }

    //Function to initialize bosses
    public static Enemy[] InitBosses(Spell[] enemiesSpells) {
        //Create array of enemies_fight
        Enemy[] bosses = new Enemy[8];

        //Create Ogre stat
        Stat ogreStat = new Stat(1, 6, 10, 10, 35, 35, 3, "Ogre");
        //First boss
        Spell[] ogreSpells = new Spell[2];
        ogreSpells[0] = new Spell("Smash", 0, 2, 2, "Huge", "none", 60, "Enemy");
        ogreSpells[1] = enemiesSpells[0];
        bosses[0] = new Enemy(ogreStat, "Bratirek", ogreSpells);
        //Create Breana stat
        Stat breanaStat = new Stat(5, 1, 10, 10, 30, 30, 3, "Human");
        //Create Breana boss
        Spell[] breanaSpells = new Spell[1];
        breanaSpells[0] = enemiesSpells[0];
        bosses[1] = new Enemy(breanaStat, "Breana, the silent", breanaSpells);

        //Create Ogre stat
        Stat centaurStat = new Stat(1, 8, 10, 10, 50, 50, 5, "Centaur");
        //First boss
        Spell[] eupenioSpells = new Spell[1];
        eupenioSpells[0] = enemiesSpells[0];
        bosses[2] = new Enemy(centaurStat, "Eupenio", eupenioSpells);
        //Create Breana stat
        Stat orgothStat = new Stat(8, 1, 20, 20, 50, 50, 5, "Orc");
        //Create Breana boss
        Spell[] orgothSpells = new Spell[1];
        orgothSpells[0] = enemiesSpells[0];
        bosses[3] = new Enemy(orgothStat, "Orgoth", orgothSpells);

        //Create Ogre stat
        Stat golemStat = new Stat(1, 9, 10, 10, 75, 75, 7, "Golem");
        //First boss
        Spell[] golemSpells = new Spell[1];
        golemSpells[0] = enemiesSpells[0];
        bosses[4] = new Enemy(golemStat, "Unon", golemSpells);
        //Create Breana stat
        Stat ciradylStat = new Stat(8, 4, 10, 10, 65, 65, 7, "Elf");
        //Create Breana boss
        Spell[] ciradylSpells = new Spell[1];
        ciradylSpells[0] = enemiesSpells[0];
        bosses[5] = new Enemy(ciradylStat, "Ciradyl", ciradylSpells);

        //Create Ogre stat
        Stat dragonStat = new Stat(1, 14, 10, 10, 100, 100, 9, "Dragon");
        //First boss
        Spell[] dragonSpells = new Spell[1];
        dragonSpells[0] = enemiesSpells[0];
        bosses[6] = new Enemy(dragonStat, "bralzranth, Eternal Fire", dragonSpells);
        //Create Breana stat
        Stat alvaxoth = new Stat(10, 10, 20, 20, 90, 90, 9, "Demon");
        //Create Breana boss
        Spell[] alvaxothSpells = new Spell[1];
        alvaxothSpells[0] = enemiesSpells[0];
        bosses[7] = new Enemy(alvaxoth, "Alvaxoth", alvaxothSpells);


        return bosses;
    }

    //Function to initialize merchants
    public static Merchant[] InitMerchants() {
        //Create array of merchants
        Merchant[] merchants = new Merchant[2];
        //Create type events merchant
        TypeEvents merchantEvent = new TypeEvents("Merchant", 1);
        //We create a new type of event
        merchants[0] = new Merchant(merchantEvent, "Andre, the smith", 1, "Goods");
        merchants[1] = new Merchant(merchantEvent, "Jean-Pierre, the potion merchant", 1, "Potions");

        return merchants;
    }

    //Function to initialize weapons
    public static Equipment[] InitEquipments() {
        //Create array of weapons
        Equipment[] equipments = new Equipment[26];

        equipments[0] = new Weapon(2, 0.05f, 1, "Club", "Broken club");
        equipments[1] = new Weapon(3, 0.1f, 2, "Club", "Common club");
        equipments[2] = new Weapon(5, 0.15f, 3, "Club", "Rare club");
        equipments[3] = new Weapon(8, 0.2f, 4, "Club", "Mythic club");
        equipments[4] = new Weapon(12, 0.3f, 5, "Club", "Legendary stick");

        equipments[5] = new Weapon(1, 0.1f, 1, "Sword", "Broken sword");
        equipments[6] = new Weapon(2, 0.15f, 2, "Sword", "Common sword");
        equipments[7] = new Weapon(5, 0.3f, 3, "Sword", "Rare sword");
        equipments[8] = new Weapon(7, 0.35f, 4, "Sword", "Mythic sword");
        equipments[9] = new Weapon(10, 0.4f, 5, "Sword", "Legendary sword");

        equipments[10] = new Weapon(2, 0.1f, 1, "Bow", "Broken bow");
        equipments[11] = new Weapon(3, 0.2f, 2, "Bow", "Common bow");
        equipments[12] = new Weapon(4, 0.4f, 3, "Bow", "Rare bow");
        equipments[13] = new Weapon(6, 0.5f, 4, "Bow", "Mythic bow");
        equipments[14] = new Weapon(8, 0.7f, 5, "Bow", "Legendary bow");

        equipments[15] = new Armor(1, "Leather armor");
        equipments[15].setRarity(1);
        equipments[16] = new Armor(2, "Iron armor");
        equipments[16].setRarity(2);
        equipments[17] = new Armor(4, "plate armor");
        equipments[17].setRarity(3);
        equipments[18] = new Armor(6, "Dragon slayer armor");
        equipments[18].setRarity(4);
        equipments[19] = new Armor(8, "Araqiel's armor");
        equipments[19].setRarity(5);

        equipments[20] = new Consumable("Potion of health", 1, "Health", 10);
        equipments[21] = new Consumable("Potion of mana", 1, "Mana", 10);
        equipments[22] = new Consumable("Great potion of health", 2, "Health", 25);
        equipments[23] = new Consumable("Great potion of mana", 2, "Mana", 50);
        equipments[24] = new Consumable("Legendary potion of health", 3, "Health", 50);
        equipments[25] = new Consumable("Legendary potion of mana", 3, "Mana", 50);

        return equipments;
    }
}
