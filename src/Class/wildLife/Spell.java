package Class.wildLife;

public class Spell {
    String name = "IsenDiploma";
    float coolDown = 3F;
    int manaUse = 5;
    int damage = 25;

    public static Spell giveSpell(int level){
        Spell newSpell = new Spell();
        newSpell.damage+=level;
        newSpell.coolDown+=0.75*level;
        newSpell.manaUse+=0.5*level;
        return newSpell;
    }
}
