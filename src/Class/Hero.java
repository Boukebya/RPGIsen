package Class;

import java.util.Arrays;

public class Hero {
    private String Name = "Hero";
    int [] pos;

    public Hero (String name,int[]pos){
        this.Name = name;
        this.pos=pos;
        System.out.println("A hero named " + name + " Appeared !");
    }

    public static int[] SpawnHero(Map map){
        Tile Spawn = new Tile("Spawn");
        int [] pos;
        pos = map.PosTile(map,Spawn);
        System.out.println("Hero spawn at " + Arrays.toString(pos));
        return pos;
    }
    public int[] GetPos(Hero hero){
        int [] pos;
        pos = hero.pos;
        return pos;
    }

    public void ShowHero(Hero hero, Map map){
        Tile Spawn = new Tile("Position");
        int [] pos;
        pos = map.PosTile(map,Spawn);

    }
}
