package Class.aroundLife.Weapon;

public class Bow extends Weapon{
    int range;
    public Bow(String name,int damage ,int range){
        super();
        this.range = range;
        this.damage = damage;
        this.setName(name);
    }
    @Override
    public int getWeaponDamage(){
        System.out.println("Bow damage");
        return this.damage;
    }
}
