Behavioral Design Patterns in Java
Technical Documentation
📋 Table of Contents
Project Overview
Architecture
Design Patterns Implemented
Command Pattern
Observer Pattern
Strategy Pattern
Getting Started
Project Structure
Code Implementation Details
Running the Application
Extension Guidelines
Best Practices
🎯 Project Overview
This project is a comprehensive demonstration of three fundamental behavioral design patterns in Java. Behavioral patterns focus on communication between objects, defining how they interact and distribute responsibilities while maintaining loose coupling and high cohesion.

Purpose
The project serves as both an educational resource and a practical reference implementation for:

Understanding behavioral design patterns through working code
Learning how to apply SOLID principles in real-world scenarios
Building flexible, maintainable, and extensible Java applications
Key Features
Modular Architecture: Each pattern is isolated in its own package
Console Execution: Run each pattern independently via demo classes
Real-World Examples: Practical implementations using familiar scenarios
Extensibility: Designed for easy addition of new behaviors and features
Working Demos: Each pattern includes a runnable demo with console output
🏗️ Architecture
The project follows a clean, modular structure with clear separation of concerns:

src/
├── CommandPattern/
│   ├── Command.java               # Command interface
│   ├── ConcreteCommands.java      # All concrete command implementations
│   ├── Receiver.java              # Light and Fan receivers
│   └── CommandDemo.java           # Demo with RemoteControl invoker
│
├── ObserverPattern/
│   ├── Observer.java              # Observer interface
│   ├── Subject.java               # Subject interface
│   ├── DisplayDevice.java         # Concrete observer
│   ├── WeatherStation.java        # Concrete subject
│   └── ObserverDemo.java          # Demo with live updates
│
└── StrategyPattern/
    ├── PaymentStrategy.java       # Strategy interface
    ├── ConcreteStratergies.java   # All payment strategy implementations
    ├── ShoppingCart.java          # Context with Product class
    └── StratergyDemo.java         # Demo with multiple payments
Design Principles Applied
Open/Closed Principle: Open for extension, closed for modification
Dependency Inversion: Depend on abstractions, not concrete implementations
Single Responsibility: Each class has one reason to change
Interface Segregation: Client-specific interfaces over general-purpose ones
🎨 Design Patterns Implemented
Command Pattern
🎯 Intent
Encapsulate a request as an object, allowing you to parameterize clients with different requests, queue or log them, and support undoable operations.

Problem It Solves
Decouples the object that invokes an operation from the one that knows how to perform it
Enables request queuing, logging, and undo/redo functionality
Supports macro commands (composite commands)
🧩 Components in Your Implementation
Component	Class	Responsibility
Command Interface	Command.java	Declares execute() and undo() methods
Concrete Commands	LightOnCommand, LightOffCommand, FanOnCommand, FanOffCommand	Implement commands for specific actions
Receivers	Light, Fan	Perform actual business logic
Invoker	RemoteControl	Triggers commands without knowing details
Client	CommandDemo	Creates and configures command objects
📝 Code Structure
Command Interface:

java
public interface Command {
    void execute();
    void undo();
}
Concrete Command Example:

java
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }
}
Receiver:

java
class Light {
    void turnOn() {
        System.out.println("Light Is ON!");
    }
    void turnOff() {
        System.out.println("Light is OFF");
    }
}
Invoker:

java
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }
    
    public void pressButton() {
        command.execute();
    }

    public void pressUndo() {
        command.undo();
    }
}
⚙️ Execution Flow
Your implementation follows this flow:

Create Receivers
java
Light roomlight = new Light();
Fan roomFan = new Fan();
Wrap in Commands
java
Command lightOn = new LightOnCommand(roomlight);
Command lightOff = new LightOffCommand(roomlight);
Command fanOn = new FanOnCommand(roomFan);
Command fanOff = new FanOffCommand(roomFan);
Configure Invoker
java
RemoteControl remote = new RemoteControl();
remote.setCommand(lightOn);
Execute and Undo
java
remote.pressButton();  // Executes: "Light Is ON!"
remote.pressUndo();    // Undoes: "Light is OFF"
📊 Class Diagram
                    ┌─────────────────┐
                    │  CommandDemo    │
                    │    (Client)     │
                    └────────┬────────┘
                             │ creates
                             ↓
┌──────────────┐      ┌─────────────┐
│RemoteControl │─────→│   Command   │
│  (Invoker)   │      │ «interface» │
│              │      └──────┬──────┘
│- command     │             △
│+ setCommand()│             │ implements
│+ pressButton │        ┌────┴────┬─────────┬─────────┐
│+ pressUndo() │        │         │         │         │
└──────────────┘   ┌────┴────┐ ┌──┴───┐ ┌──┴───┐ ┌───┴────┐
                   │LightOn  │ │Light │ │FanOn │ │FanOff  │
                   │Command  │ │Off   │ │Cmd   │ │Command │
                   └────┬────┘ └──┬───┘ └──┬───┘ └───┬────┘
                        │         │        │         │
                        └─────────┴────────┴─────────┘
                                  │ uses
                                  ↓
                        ┌─────────────────┐
                        │ Light  │  Fan   │
                        │ (Receivers)     │
                        └─────────────────┘
💡 Features Implemented
✅ Four concrete commands (LightOn, LightOff, FanOn, FanOff)
✅ Two receivers (Light, Fan)
✅ Single remote control invoker
✅ Undo functionality
🔧 Extension Ideas
Add command history stack for multi-level undo/redo
Create MacroCommand to execute multiple commands in sequence
Add more receivers (AirConditioner, TV, Stereo)
Implement parameterized commands (e.g., FanSpeedCommand with speed levels)
Add command logging for debugging and audit trails
Observer Pattern
🎯 Intent
Define a one-to-many dependency between objects so that when one object (the Subject) changes state, all its dependents (Observers) are automatically notified and updated.

Problem It Solves
Maintains consistency between related objects without tight coupling
Implements event-driven architectures
Enables broadcast communication from one to many
🧩 Components in Your Implementation
Component	Class	Responsibility
Subject Interface	Subject.java	Declares observer management methods
Observer Interface	Observer.java	Declares update method
Concrete Subject	WeatherStation	Maintains state and notifies observers
Concrete Observer	DisplayDevice	Subscribes and displays weather updates
📝 Code Structure
Observer Interface:

java
public interface Observer {
    void update(float temperature, float humidity);
}
Subject Interface:

java
public interface Subject {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
Concrete Subject:

java
public class WeatherStation implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private float temperature;
    private float humidity;

    public void setWeatherData(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        System.out.println("WeatherStation: Temperature = " + Math.round(temperature) + 
                          "°C  | Humidity: " + Math.round(humidity) + "%");
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(temperature, humidity);
        }
    }
    
    // addObserver and removeObserver implementations...
}
Concrete Observer:

java
public class DisplayDevice implements Observer {
    private String name;

    public DisplayDevice(String name) {
        this.name = name;
    }

    @Override
    public void update(float temperature, float humidity) {
        System.out.println(name + " received Update: Temperature = " + 
                          Math.round(temperature) + "°C || Humidity = " + 
                          Math.round(humidity) + "%");
    }
}
⚙️ Execution Flow
Your demo includes a dynamic simulation with random weather updates:

Create Subject and Observers
java
WeatherStation weatherStation = new WeatherStation();

Observer PhoneDisplay = new DisplayDevice("Phone Display");
Observer TVDisplay = new DisplayDevice("TV Display");
Observer WebDashboard = new DisplayDevice("Web Dashboard");
Register Observers
java
weatherStation.addObserver(PhoneDisplay);
weatherStation.addObserver(TVDisplay);
weatherStation.addObserver(WebDashboard);
Update Weather Data
java
weatherStation.setWeatherData(30.5f, 65.0f);
// All three displays automatically receive updates
Simulated Updates (Your unique feature!)
java
for (int i = 0; i < 3; i++) {
    float newTemperature = 25 + (float) (Math.random() * 10);
    float newHumidity = 35 + (float) (Math.random() * 10);
    weatherStation.setWeatherData(newTemperature, newHumidity);
    Thread.sleep(3000); // 3-second intervals
}
📊 Class Diagram
┌──────────────┐         ┌──────────────┐
│   Subject    │◇────────│   Observer   │
│ «interface»  │         │ «interface»  │
│              │         │              │
│+ addObserver │         │+ update()    │
│+ removeObsrvr│         └──────△───────┘
│+ notifyObs() │                │
└──────△───────┘                │ implements
       │                        │
       │ implements       ┌─────┴──────┐
┌──────┴──────────┐      │            │
│ WeatherStation  │      │DisplayDevice│
│                 │      │             │
│- observers: List│      │- name       │
│- temperature    │      │             │
│- humidity       │      └─────────────┘
│                 │
│+ setWeatherData()│
└─────────────────┘
💡 Features Implemented
✅ Three display devices (Phone, TV, Web Dashboard)
✅ Real-time weather data updates
✅ Simulated updates with Thread.sleep() (unique feature!)
✅ Temperature and humidity tracking
✅ Rounded display values for better readability
🔧 Extension Ideas
Add selective updates (observers specify which data they want)
Implement different observer types (EmailAlertObserver, SMSObserver)
Add threshold-based alerts (notify only when temperature exceeds limit)
Implement removeObserver demo in the main method
Add pull-based updates where observers fetch data on demand
Create weather forecast observer that analyzes trends
Strategy Pattern
🎯 Intent
Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

Problem It Solves
Eliminates conditional statements for selecting algorithms
Enables runtime algorithm selection
Makes it easy to add new algorithms without modifying existing code
🧩 Components in Your Implementation
Component	Class	Responsibility
Strategy Interface	PaymentStrategy	Declares pay method
Concrete Strategies	CreditCardPayment, PayPalPayment, UPIPayment, CryptoPayment	Implement payment methods
Context	ShoppingCart	Maintains strategy reference and delegates payment
Product	Product	Helper class for cart items
📝 Code Structure
Strategy Interface:

java
public interface PaymentStrategy {
    void pay(int amount);
}
Concrete Strategies:

java
class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit card");
    }
}

class PayPalPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Paypal");
    }
}

class UPIPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using UPI");
    }
}

class CryptoPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using CryptoPayment");
    }
}
Product Class:

java
class Product {
    private int price;

    public Product(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
Context (ShoppingCart):

java
class ShoppingCart {
    private List<Product> products;
    private PaymentStrategy paymentStrategy;

    public ShoppingCart() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Added item worth " + product.getPrice());
    }

    public int getTotatl() {
        int total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout() {
        int total = getTotatl();
        paymentStrategy.pay(total);
    }
}
⚙️ Execution Flow
Your implementation demonstrates strategy switching at runtime:

Create Shopping Cart and Add Products
java
ShoppingCart shoppingCart = new ShoppingCart();
shoppingCart.addProduct(new Product(40));
shoppingCart.addProduct(new Product(60));
int total = shoppingCart.getTotatl(); // 100
Switch Between Payment Strategies
java
// Pay with Credit Card
shoppingCart.setPaymentStrategy(new CreditCardPayment());
shoppingCart.checkout(); // "Paid 100 using Credit card"

// Switch to PayPal
shoppingCart.setPaymentStrategy(new PayPalPayment());
shoppingCart.checkout(); // "Paid 100 using Paypal"

// Switch to UPI
shoppingCart.setPaymentStrategy(new UPIPayment());
shoppingCart.checkout(); // "Paid 100 using UPI"

// Switch to Crypto
shoppingCart.setPaymentStrategy(new CryptoPayment());
shoppingCart.checkout(); // "Paid 100 using CryptoPayment"
📊 Class Diagram
┌──────────────────┐
│ PaymentStrategy  │
│   «interface»    │
│                  │
│ + pay(amount)    │
└────────△─────────┘
         │ implements
    ┌────┴────┬───────────┬──────────┬──────────┐
    │         │           │          │          │
┌───┴────┐ ┌──┴─────┐ ┌──┴────┐ ┌───┴─────┐ ┌──┴────┐
│Credit  │ │PayPal  │ │  UPI  │ │ Crypto  │ │Future │
│Card    │ │Payment │ │Payment│ │ Payment │ │ ...   │
└────────┘ └────────┘ └───────┘ └─────────┘ └───────┘
                          △
                          │ uses
                   ┌──────┴──────┐
                   │ShoppingCart │
                   │             │
                   │- products   │
                   │- strategy   │
                   │+ checkout() │
                   └─────┬───────┘
                         │ contains
                         ↓
                   ┌─────────┐
                   │ Product │
                   │         │
                   │- price  │
                   └─────────┘
💡 Features Implemented
✅ Four payment strategies (Credit Card, PayPal, UPI, Crypto)
✅ Dynamic strategy switching at runtime
✅ Product management with pricing
✅ Total calculation
✅ Clean separation of payment logic
🔧 Extension Ideas
Add payment validation before processing
Implement transaction details (card numbers, account IDs)
Create discount strategies that can be combined
Add payment confirmation and receipt generation
Implement failed payment handling and retry logic
Add more strategies: Apple Pay, Google Pay, Bank Transfer
Create strategy factory for selecting payment method from user input
Add logging for all transactions
🚀 Getting Started
Prerequisites
Java Development Kit (JDK): Version 8 or higher
IDE (Optional but recommended): IntelliJ IDEA, Eclipse, or VS Code
Command Line: Terminal or Command Prompt
Installation
Clone or download the repository
bash
git clone https://github.com/T2T-Theory-to-Trade/T2T-Session-02-Assignment.git
cd T2T-Session-02-Assignment
Compile the project
Navigate to the source directory and compile:

bash
# Compile Command Pattern
javac CommandPattern/*.java

# Compile Observer Pattern
javac ObserverPattern/*.java

# Compile Strategy Pattern
javac StrategyPattern/*.java
Or compile all at once:

bash
javac *Pattern/*.java
Run the demos
bash
# Run Command Pattern Demo
java CommandPattern.CommandDemo

# Run Observer Pattern Demo
java ObserverPattern.ObserverDemo

# Run Strategy Pattern Demo
java StrategyPattern.StratergyDemo
📁 Project Structure
T2T-Session-02-Assignment/
│
├── CommandPattern/
│   ├── Command.java               # Command interface (execute, undo)
│   ├── ConcreteCommands.java      # LightOn/Off, FanOn/Off commands
│   ├── Receiver.java              # Light and Fan receivers
│   └── CommandDemo.java           # Main demo with RemoteControl
│
├── ObserverPattern/
│   ├── Observer.java              # Observer interface (update)
│   ├── Subject.java               # Subject interface (add/remove/notify)
│   ├── DisplayDevice.java         # Concrete observer implementation
│   ├── WeatherStation.java        # Concrete subject with weather data
│   └── ObserverDemo.java          # Main demo with 3 displays + simulation
│
└── StrategyPattern/
    ├── PaymentStrategy.java       # Strategy interface (pay)
    ├── ConcreteStratergies.java   # CreditCard, PayPal, UPI, Crypto
    ├── ShoppingCart.java          # Context + Product class
    └── StratergyDemo.java         # Main demo showing strategy switching
💻 Code Implementation Details
Command Pattern - CommandDemo Output
Light Is ON!
Fan Is ON!
Light is OFF
Fan is OFF
Fan Is ON!
Key Implementation Notes:

Uses simple receivers with console output
Invoker (RemoteControl) holds single command reference
Undo immediately reverses last command
All commands implement both execute() and undo()
Observer Pattern - ObserverDemo Output
WeatherStation: Temperature = 31°C  | Humidity: 65%

Phone Display received Update: Temperature = 31°C || Humidity = 65%
TV Display received Update: Temperature = 31°C || Humidity = 65%
Web Dashboard received Update: Temperature = 31°C || Humidity = 65%

[Simulated updates every 3 seconds with random values between 25-35°C]

--------------------------------------
Key Implementation Notes:

Uses ArrayList to manage observer list
Implements push model (subject pushes data to observers)
Includes Thread.sleep() for realistic simulation
Generates random weather data using Math.random()
Rounds values for cleaner display using Math.round()
Strategy Pattern - StratergyDemo Output
Added item worth 40
Added item worth 60
Total: 100
Paid 100 using Credit card
Paid 100 using Paypal
Paid 100 using UPI
Paid 100 using CryptoPayment
Key Implementation Notes:

Uses ArrayList for product management
Calculates total dynamically in getTotatl() method
Strategy reference can be changed multiple times
Same cart, different payment methods
Clean delegation pattern in checkout()
🎮 Running the Application
Option 1: Using IDE (IntelliJ IDEA / Eclipse)
Import the project into your IDE
Navigate to any demo class:
CommandPattern.CommandDemo
ObserverPattern.ObserverDemo
StrategyPattern.StratergyDemo
Right-click and select "Run"
Option 2: Using Command Line
bash
# From project root directory

# Command Pattern
cd src
javac CommandPattern/*.java
java CommandPattern.CommandDemo

# Observer Pattern (includes 3-second delays)
javac ObserverPattern/*.java
java ObserverPattern.ObserverDemo

# Strategy Pattern
javac StrategyPattern/*.java
java StrategyPattern.StratergyDemo
Option 3: Run All Demos
Create a batch script to run all demos sequentially:

Windows (run_all.bat):

batch
@echo off
echo Running Command Pattern Demo...
java CommandPattern.CommandDemo
echo.
echo Running Observer Pattern Demo...
java ObserverPattern.ObserverDemo
echo.
echo Running Strategy Pattern Demo...
java StrategyPattern.StratergyDemo
Linux/Mac (run_all.sh):

bash
#!/bin/bash
echo "Running Command Pattern Demo..."
java CommandPattern.CommandDemo
echo ""
echo "Running Observer Pattern Demo..."
java ObserverPattern.ObserverDemo
echo ""
echo "Running Strategy Pattern Demo..."
java StrategyPattern.StratergyDemo
🔧 Extension Guidelines
Adding a New Command
Step 1: Create a new receiver in Receiver.java:

java
class AirConditioner {
    void turnOn() {
        System.out.println("AC Is ON!");
    }
    void turnOff() {
        System.out.println("AC is OFF");
    }
    void setTemperature(int temp) {
        System.out.println("AC temperature set to " + temp);
    }
}
Step 2: Create commands in ConcreteCommands.java:

java
class ACOnCommand implements Command {
    private AirConditioner ac;
    
    public ACOnCommand(AirConditioner ac) {
        this.ac = ac;
    }
    
    @Override
    public void execute() {
        ac.turnOn();
    }
    
    @Override
    public void undo() {
        ac.turnOff();
    }
}
Step 3: Use in CommandDemo.java:

java
AirConditioner roomAC = new AirConditioner();
Command acOn = new ACOnCommand(roomAC);
remote.setCommand(acOn);
remote.pressButton();
Adding a New Observer Type
Step 1: Create new observer in a new file or DisplayDevice.java:

java
class EmailAlertObserver implements Observer {
    private String emailAddress;
    
    public EmailAlertObserver(String email) {
        this.emailAddress = email;
    }
    
    @Override
    public void update(float temperature, float humidity) {
        if (temperature > 35) {
            System.out.println("ALERT sent to " + emailAddress + 
                             ": High temperature detected!");
        }
    }
}
Step 2: Register in ObserverDemo.java:

java
Observer emailAlert = new EmailAlertObserver("user@email.com");
weatherStation.addObserver(emailAlert);
Adding a New Payment Strategy
Step 1: Add to ConcreteStratergies.java:

java
class GooglePayPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Google Pay");
    }
}

class ApplePayPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Apple Pay");
    }
}
Step 2: Use in StratergyDemo.java:

java
shoppingCart.setPaymentStrategy(new GooglePayPayment());
shoppingCart.checkout();
📚 Best Practices Demonstrated
Command Pattern Best Practices
✅ Interface Segregation: Simple Command interface with only necessary methods
✅ Encapsulation: Commands encapsulate all information needed for execution
✅ Reversibility: Every command implements undo
✅ Single Responsibility: Each command class does one thing

Observer Pattern Best Practices
✅ List-based Management: Uses ArrayList for flexible observer management
✅ Push Model: Subject actively pushes data to observers
✅ Decoupling: Observers don't know about each other
✅ Realistic Simulation: Thread.sleep() for real-time demonstration

Strategy Pattern Best Practices
✅ Runtime Flexibility: Strategy can be changed multiple times
✅ Clean Delegation: Context delegates to strategy without knowing details
✅ Easy Extension: New payment methods require only implementing the interface
✅ No Conditionals: No if/else chains for selecting algorithms

🧪 Testing Recommendations
Manual Testing Checklist
Command Pattern:

 Execute each command and verify output
 Test undo after each command
 Try multiple commands in sequence
 Verify undo reverts to correct state
Observer Pattern:

 Verify all observers receive updates
 Check rounded values display correctly
 Observe 3-second delays in simulation
 Test with different numbers of observers
Strategy Pattern:

 Add products and verify total calculation
 Try each payment strategy
 Verify strategy switching works
 Test with different product combinations
Suggested Unit Tests
java
// Command Pattern Test
@Test
public void testLightOnCommand() {
    Light light = new Light();
    Command cmd = new LightOnCommand(light);
    cmd.execute();
    // Verify light is on
}

// Observer Pattern Test
@Test
public void testWeatherStationNotification() {
    WeatherStation station = new WeatherStation();
    DisplayDevice display = new DisplayDevice("Test");
    station.addObserver(display);
    station.setWeatherData(25.0f, 50.0f);
    // Verify display received update
}

// Strategy Pattern Test
@Test
public void testPaymentStrategySwitch() {
    ShoppingCart cart = new ShoppingCart();
    cart.addProduct(new Product(100));
    cart.setPaymentStrategy(new CreditCardPayment());
    cart.checkout();
    // Verify payment processed
}
📖 Learning Outcomes
After studying this project, you will understand:

When to use each pattern
Command: When you need undo/redo or request queuing
Observer: When you need one-to-many communication
Strategy: When you need interchangeable algorithms
How patterns promote SOLID principles
Open/Closed: Easy to add new behaviors without modification
Dependency Inversion: Depend on abstractions (interfaces)
Single Responsibility: Each class has one clear purpose
Real-world applications
Home automation (Command)
Event-driven systems (Observer)
Payment processing (Strategy)
🤝 Contributing
Suggestions for improvements:

Command Pattern Enhancements
Add command history with multi-level undo/redo
Implement MacroCommand for composite operations
Add parameterized commands (e.g., SetTemperatureCommand)
Observer Pattern Enhancements
Add observer priorities
Implement pull model option
Add async notifications using threads
Create specialized observers (SMS, Email, Push notifications)
Strategy Pattern Enhancements
Add transaction IDs and receipts
Implement payment validation
Add discount strategies
Create strategy factory pattern
