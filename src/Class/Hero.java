package Class;

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
        Scanner sc = new Scanner(System.in);
        String direction = sc.nextLine();

        int [] pos;
        pos = hero.pos;
        int x = pos[0];
        int y = pos[1];

        Tile tile = Tile.Position;
        Tile Empty = Tile.Empty;

        switch (direction) {
            case "up", "z" -> {
                map.ChangeTile(map, pos, Empty);
                y = y - 1;
            }
            case "down", "s" -> {
                map.ChangeTile(map, pos, Empty);
                y = y + 1;
            }
            case "left", "q" -> {
                map.ChangeTile(map, pos, Empty);
                x = x - 1;
            }
            case "right", "d" -> {
                map.ChangeTile(map, pos, Empty);
                x = x + 1;
            }
            default -> System.out.println("Wrong input, be serious you're on a duty !");
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
