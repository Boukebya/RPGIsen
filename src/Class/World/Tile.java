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
    Boss ("Boss"),
    Merchant ("Merchant"),
    Unknown ("Unknown");

    private final String Category;

    //Constructor
    Tile(String Category_Tile){
        Category = Category_Tile;
    }

    //Getters
    public String getTile(){
        return Category;
    }

}

