package Class.World;

public enum Weather {
    RAINY (0.8f),
    SUNNY (1f),
    FOGGY (0.9f);

    private float Accuracy;

    private Weather(float Accuracy_Weather){
        Accuracy = Accuracy_Weather;
    }
    public float GetAcc(){
        return Accuracy;
    }

}
