package Class.World;

//Enum to define the weather
public enum Weather {
    RAINY(0.8f),
    SUNNY(1f),
    FOGGY(0.9f);

    private final float Accuracy;

    Weather(float Accuracy_Weather) {
        Accuracy = Accuracy_Weather;
    }

    public float getAccuracy() {
        return Accuracy;
    }
}
