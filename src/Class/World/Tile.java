package Class.World;

//Tile enum
public enum Tile {

    Wall ("Wall"),
    Empty ("Empty"),
    Enemy ("Enemy"),
    Chest ("Chest"),
    Spawn ("Spawn"),
    End ("End"),
    Position ("Position"),
    Unknown ("Unknown");

    private final String Category;

    Tile(String Category_Tile){
        Category = Category_Tile;
    }
    public String GetTile(){
        return Category;
    }
}

