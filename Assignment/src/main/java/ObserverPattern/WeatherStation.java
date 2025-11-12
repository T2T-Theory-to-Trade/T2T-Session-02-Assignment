package ObserverPattern;
import java.util.ArrayList;
import java.util.List;

public class WeatherStation implements Subject{

    private List<Observer>observers = new ArrayList<>();
    private float temperature;
    private float humidity;

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        System.out.println("WeatherStation: New temperature = " + temperature);
        notifyObservers();

    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
        System.out.println("WeatherStation: New humidity = " + humidity);
        notifyObservers();
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
         observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers){
            o.update(temperature,humidity);
        }

    }

    public void setWeatherData(float temperature , float humidity){
        this.temperature = temperature;
        this.humidity = humidity;
        System.out.println();
        System.out.println("WeatherStation: Temperature = " + Math.round(temperature)  + "°C  | Humidity: " + Math.round(humidity) +"%");
        System.out.println();
        notifyObservers();
    }
}
