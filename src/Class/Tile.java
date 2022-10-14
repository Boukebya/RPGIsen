package Class;

public class Tile {
    private String Category = "Wall";

    public Tile(String Category){
        this.Category = Category;
    }

    public String GetTile(Tile Category){
        return Category.Category;
    }


}
