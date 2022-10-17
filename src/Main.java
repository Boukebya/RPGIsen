import Class.*;

import java.util.Arrays;
import java.util.Objects;

import static Class.Hero.SpawnHero;

public class Main {


    //Ici on teste
    public static void Test(){
        //We create weather
        Weather actual = Weather.FOGGY;
        //We declare all tiles to create map after
        Tile Chest = Tile.Chest;
        Tile Enemy = Tile.Enemy;
        Tile Wall = Tile.Wall;
        Tile Empty = Tile.Empty;
        Tile Spawn = Tile.Spawn;
        Tile End = Tile.End;

        //Here we create map
        Tile[][] intro = new Tile[9][9];
        for (int y = 0; y < intro.length; y++) {
            for (int x = 0; x < intro.length; x++) {
                intro[x][y] = Wall;
            }
        }
        intro[1][1] = Empty ;
        intro[2][1] = Spawn ;
        intro[3][1] = Empty ;
        intro[4][1] = Enemy ;
        intro[4][2] = Empty ;
        intro[5][1] = Chest ;
        intro[3][2] = Empty ;
        intro[5][2] = Empty ;
        intro[4][3] = End ;

        //Create Map object, with its weather and its tiles
        Map goblins_Forest = new Map(actual,intro);

        //On print pour vérifier
        System.out.println(actual.GetAcc());
        System.out.println(Chest.GetTile());

        System.out.println(goblins_Forest.GetWeather(goblins_Forest));
        System.out.println(Arrays.deepToString(goblins_Forest.GetMap(goblins_Forest)));
        Tile tile1 = goblins_Forest.GetTileMap(goblins_Forest,0,0);
        System.out.println(tile1.GetTile());

        //Spawn Hero
        Hero character = new Hero("Blanc-Louis",SpawnHero(goblins_Forest));
        goblins_Forest.ShowMap(goblins_Forest);


        //We create a new Tile to know when we have to manage an event
        Tile Event_Manager = Tile.Position;
        //While Event_Manager != End, we continue to travel through the map
        while(!Event_Manager.GetTile().equals("End")){
            //Move player with scanner parameters and get the tile where he is
            Event_Manager = character.MoveHero(character, goblins_Forest);
            //If new location is a chest
            if (Objects.equals(Event_Manager.GetTile(), "Chest")){
                System.out.println("You found a chest !");
                //Open chest

            }
            //Else If new location is an enemy
            else if (Objects.equals(Event_Manager.GetTile(), "Enemy")){
                System.out.println("You found an enemy !");
                //Fight enemy

            }


            //Clear console
            System.out.print("\033[H\033[2J");
            System.out.flush();



        }
        //If Event_Manager == End, we print the end of the game
        System.out.println("End of map !");
    }


    public static void main(String[] args) {
        System.out.println("Begin");
        Test();
    }
}
