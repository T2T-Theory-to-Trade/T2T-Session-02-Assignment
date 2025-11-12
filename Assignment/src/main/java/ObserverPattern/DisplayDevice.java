package ObserverPattern;

public class DisplayDevice implements Observer{

    private String name;

    public DisplayDevice(String name) {
        this.name = name;
    }

    @Override
    public void update(float temperature, float humidity) {
        System.out.println(name + " received Update: Temperature = " +  Math.round(temperature) + "°C || Humidity = " +  Math.round(humidity) + "%");
    }
}
