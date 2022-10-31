package Class.aroundLife.Weapon;

public class Sword extends Weapon{
    public Sword(String name, int damage){
        super();
        this.setName(name);
        this.damage = damage;
    }
    @Override
    public int getWeaponDamage(){
        System.out.println("Sword damage");
        return this.damage;
    }
}
