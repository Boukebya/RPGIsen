package Class.Event_Manager;

public class Enemy_Fight extends Type_Events {
    String name_Object;
    //Constructor
    public Enemy_Fight(Type_Events type_Event, String name_Object){
        super(type_Event.name);
        this.name_Object= name_Object;
    }

}
