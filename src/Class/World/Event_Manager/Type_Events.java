package Class.World.Event_Manager;
import Class.Equipments.Equipment;
import Class.wildLife.Hero;

//Mother's class for every event, define the basic function of an event and what kind it is
public class Type_Events {
    //name of the event
    String name;
    //rarity of the event
    int rarity;

    //Constructor
    public Type_Events(String name, int rarity){
        this.name = name;
        this.rarity = rarity;
    }

    //Getters
    public float getRarity() {
        return rarity;
    }

    //Setters
    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    //Methods
    //Function to get an event from rarity
    public static Type_Events GetEventFromRarity(Type_Events[] events) {
        //Create new random array with same size as events
        int[] randoms = new int[events.length];
        //limit is the sum of every rarity
        int limit = 0;
        //for every element, get rarity of events and add it to randoms
        for (int i = 0; i < events.length; i++) {
            randoms[i] = (int) events[i].getRarity();
            //System.out.println(randoms[i]);
            limit+= randoms[i];
        }
        //Create random number between 0 and limit
        int random_number = (int) (Math.random() * limit);
        //System.out.println("random = "+random_number);
        int count = 0;
        //for every element, if random is smaller than the rarity, return the event
        for (Type_Events event : events) {
            count += (int) event.getRarity();
            if (random_number < count) {
                //System.out.println("event " + i + " selected");
                return event;
            }
        }
        //System.out.println("default event");
        return events[0];
    }
    //By default, print interact, else, it's override by the child class
    public void Interact(Hero hero, Equipment[] weapons) {
        System.out.println("interact with "+ this.name);
    }
}
