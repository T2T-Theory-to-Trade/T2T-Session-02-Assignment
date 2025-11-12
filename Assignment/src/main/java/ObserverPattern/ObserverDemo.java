package ObserverPattern;


public class ObserverDemo {
    public static void main(String[] args){
        WeatherStation weatherStation = new WeatherStation();

        Observer PhoneDisplay = new DisplayDevice("Phone Display");
        Observer TVDisplay = new DisplayDevice("TV Display");
        Observer WebDashboard = new DisplayDevice("Web Dashboard");

        //adding Observers
        weatherStation.addObserver(PhoneDisplay);
        weatherStation.addObserver(TVDisplay);
        weatherStation.addObserver(WebDashboard);



        weatherStation.setWeatherData(30.5f,65.0f);

      try {  //Simulate using Thread.sleep
          for (int i = 0; i < 3; i++) {

              float newTemperature = 25 + (float) (Math.random() * 10); // random temperature between 25-35
              float newHumidity = 35 + (float) (Math.random() * 10);
              weatherStation.setWeatherData(newTemperature,newHumidity);

              Thread.sleep(3000); //wait 3 second for next update
              System.out.println("\n--------------------------------------\n");
          }
      }catch (InterruptedException e){
          e.printStackTrace();
      }
    }
}

