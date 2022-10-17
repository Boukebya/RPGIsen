package Class;

public enum Tile {

    Wall ("Wall"),
    Empty ("Empty"),
    Enemy ("Enemy"),
    Chest ("Chest"),
    Spawn ("Spawn"),
    End ("End"),
    Position ("Position");

    private String Category;

    private Tile(String Category_Tile){
        Category = Category_Tile;
    }
    public String GetTile(){
        return Category;
    }
}

