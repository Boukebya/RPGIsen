package Class.World;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Hero {
    private int [] pos;

    public Hero (String name,int[]pos){
        this.pos=pos;
        System.out.println("A hero named " + name + " Appeared !");
    }

    public static int[] SpawnHero(Map map){
        Tile Spawn = Tile.Spawn;
        int [] pos;
        pos = map.PosTile(map,Spawn);
        System.out.println("Hero spawn at " + Arrays.toString(pos));
        return pos;
    }

    public Tile MoveHero(Hero hero, Map map){
        System.out.println("Choose a direction by typing : up, down, left or right (or z,q,s,d)");

        int [] pos;
        pos = hero.pos;
        int x = pos[0];
        int y = pos[1];

        Tile tile = Tile.Position;
        Tile Empty = Tile.Empty;

        boolean move = false;
        while(move == false) {
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
        Tile New_location = map.GetTileMap(map,x,y);
        if (Objects.equals(New_location.GetTile(), "Wall")){
            System.out.println("You can't go there, it's a wall !");
            MoveHero(hero,map);
        }
        else {
            hero.pos[0] = x;
            hero.pos[1] = y;
            map.ChangeTile(map,pos,tile);
            map.ShowMap(map);
            return New_location;
        }
        return tile;
    }
}
