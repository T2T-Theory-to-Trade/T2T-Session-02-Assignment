# Behavioral Design Patterns in Java
## Technical Documentation

---

## 📋 Table of Contents

1. [Project Overview](#project-overview)
2. [Architecture](#architecture)
3. [Design Patterns Implemented](#design-patterns-implemented)
   - [Command Pattern](#command-pattern)
   - [Observer Pattern](#observer-pattern)
   - [Strategy Pattern](#strategy-pattern)
4. [Getting Started](#getting-started)
5. [Project Structure](#project-structure)
6. [Usage Examples](#usage-examples)
7. [Extension Guidelines](#extension-guidelines)
8. [Best Practices](#best-practices)

---

## 🎯 Project Overview

This project is a comprehensive demonstration of three fundamental **behavioral design patterns** in Java. Behavioral patterns focus on communication between objects, defining how they interact and distribute responsibilities while maintaining loose coupling and high cohesion.

### Purpose

The project serves as both an educational resource and a practical reference implementation for:
- Understanding behavioral design patterns through working code
- Learning how to apply SOLID principles in real-world scenarios
- Building flexible, maintainable, and extensible Java applications

### Key Features

- **Modular Architecture**: Each pattern is isolated in its own package
- **Console Execution**: Run each pattern independently for testing and demonstration
- **Real-World Examples**: Practical implementations using familiar scenarios
- **Extensibility**: Designed for easy addition of new behaviors and features

---

## 🏗️ Architecture

The project follows a clean, modular structure with clear separation of concerns:

```
src/
├── CommandPattern/
│   ├── Command.java (interface)
│   ├── ConcreteCommands/
│   ├── Receivers/
│   ├── Invoker/
│   └── Main.java
├── ObserverPattern/
│   ├── Observer.java (interface)
│   ├── Subject.java (interface)
│   ├── ConcreteObservers/
│   ├── ConcreteSubject/
│   └── Main.java
└── StrategyPattern/
    ├── Strategy.java (interface)
    ├── ConcreteStrategies/
    ├── Context/
    └── Main.java
```

### Design Principles Applied

- **Open/Closed Principle**: Open for extension, closed for modification
- **Dependency Inversion**: Depend on abstractions, not concrete implementations
- **Single Responsibility**: Each class has one reason to change
- **Interface Segregation**: Client-specific interfaces over general-purpose ones

---

## 🎨 Design Patterns Implemented

### Command Pattern

#### 🎯 Intent

Encapsulate a request as an object, allowing you to parameterize clients with different requests, queue or log them, and support undoable operations.

#### Problem It Solves

- Decouples the object that invokes an operation from the one that knows how to perform it
- Enables request queuing, logging, and undo/redo functionality
- Supports macro commands (composite commands)

#### 🧩 Components

| Component | Responsibility | Example |
|-----------|---------------|---------|
| **Command** | Declares the execute() and undo() interface | `Command.java` |
| **ConcreteCommand** | Implements command by binding to a receiver | `LightOnCommand`, `FanOffCommand` |
| **Receiver** | Performs the actual business logic | `Light`, `Fan` |
| **Invoker** | Triggers commands without knowing details | `RemoteControl` |
| **Client** | Creates and configures command objects | `Main.java` |

#### ⚙️ Execution Flow

1. **Create Receivers**: Instantiate the objects that perform actual work
   ```java
   Light livingRoomLight = new Light("Living Room");
   Fan ceilingFan = new Fan("Bedroom");
   ```

2. **Wrap in Commands**: Encapsulate receiver actions in command objects
   ```java
   Command lightOn = new LightOnCommand(livingRoomLight);
   Command fanOff = new FanOffCommand(ceilingFan);
   ```

3. **Configure Invoker**: Set commands in the remote control
   ```java
   RemoteControl remote = new RemoteControl();
   remote.setCommand(lightOn);
   ```

4. **Execute and Undo**: Trigger actions and revert them
   ```java
   remote.pressButton();  // Executes command
   remote.pressUndo();    // Reverts last action
   ```

#### 📊 Class Diagram

```
┌─────────────┐
│   Client    │
└──────┬──────┘
       │ creates
       ↓
┌─────────────┐      ┌──────────────┐
│   Invoker   │─────→│   Command    │
│             │      │  «interface» │
└─────────────┘      └──────┬───────┘
                            △
                            │ implements
                     ┌──────┴───────┐
                     │              │
            ┌────────┴────────┐ ┌───┴──────────┐
            │ LightOnCommand  │ │ FanOffCommand│
            └────────┬────────┘ └───┬──────────┘
                     │              │
                     └──────┬───────┘
                            │ uses
                            ↓
                     ┌─────────────┐
                     │  Receiver   │
                     │ (Light/Fan) │
                     └─────────────┘
```

#### 💡 Use Cases

- **Remote Controls**: Home automation systems
- **Transaction Systems**: Banking operations with rollback
- **GUI Actions**: Menu items, buttons with undo/redo
- **Task Scheduling**: Job queues and batch processing
- **Macro Recording**: Recording and replaying user actions

#### 🔧 Extension Ideas

- Implement a **Command History** with multi-level undo/redo stack
- Create **MacroCommand** to execute multiple commands in sequence
- Add **command logging** for audit trails
- Implement **command serialization** for persistence
- Create **parameterized commands** with runtime arguments

---

### Observer Pattern

#### 🎯 Intent

Define a one-to-many dependency between objects so that when one object (the Subject) changes state, all its dependents (Observers) are automatically notified and updated.

#### Problem It Solves

- Maintains consistency between related objects without tight coupling
- Implements event-driven architectures
- Enables broadcast communication from one to many

#### 🧩 Components

| Component | Responsibility | Example |
|-----------|---------------|---------|
| **Subject** | Manages observers and triggers notifications | `Subject.java` interface |
| **Observer** | Defines update interface for notifications | `Observer.java` interface |
| **ConcreteSubject** | Maintains state and notifies on changes | `WeatherStation` |
| **ConcreteObserver** | Subscribes and reacts to updates | `DisplayDevice` |

#### ⚙️ Execution Flow

1. **Create Subject**: Initialize the observable object
   ```java
   WeatherStation station = new WeatherStation();
   ```

2. **Register Observers**: Subscribe display devices
   ```java
   DisplayDevice phoneDisplay = new DisplayDevice("Phone");
   DisplayDevice tvDisplay = new DisplayDevice("TV");
   station.addObserver(phoneDisplay);
   station.addObserver(tvDisplay);
   ```

3. **Update State**: Change subject data
   ```java
   station.setTemperature(25.5f);
   station.setHumidity(65);
   ```

4. **Automatic Notification**: All observers receive updates automatically
   ```java
   // Both displays update without explicit calls
   ```

#### 📊 Class Diagram

```
┌──────────────┐         ┌──────────────┐
│   Subject    │←────────│   Observer   │
│  «interface» │         │  «interface» │
└──────┬───────┘         └──────△───────┘
       △                        │
       │                        │
       │                        │ implements
┌──────┴──────────┐    ┌────────┴────────┐
│ WeatherStation  │    │ DisplayDevice   │
│                 │    │                 │
│ - temperature   │    │ - deviceName    │
│ - humidity      │    │                 │
│ + setTemp()     │    │ + update()      │
│ + notifyObs()   │    └─────────────────┘
└─────────────────┘
```

#### 💡 Use Cases

- **Event Systems**: GUI event handling, publish-subscribe systems
- **Model-View Architectures**: MVC, MVVM patterns
- **Real-Time Updates**: Stock tickers, sports scores, news feeds
- **Sensor Networks**: IoT devices, monitoring systems
- **Social Media**: Notification systems, follower updates

#### 🔧 Extension Ideas

- Add **selective updates** (observers specify what data they want)
- Implement **pull model** where observers fetch data on demand
- Create **priority-based notifications** for critical observers
- Add **asynchronous notifications** using threading
- Implement **weak references** to prevent memory leaks
- Create specialized observers: `SMSAlertObserver`, `EmailNotificationObserver`

---

### Strategy Pattern

#### 🎯 Intent

Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

#### Problem It Solves

- Eliminates conditional statements for selecting algorithms
- Enables runtime algorithm selection
- Makes it easy to add new algorithms without modifying existing code

#### 🧩 Components

| Component | Responsibility | Example |
|-----------|---------------|---------|
| **Strategy** | Common interface for all algorithms | `PaymentStrategy.java` |
| **ConcreteStrategy** | Implements specific algorithm | `CreditCardPayment`, `PayPalPayment` |
| **Context** | Maintains strategy reference and delegates | `ShoppingCart` |

#### ⚙️ Execution Flow

1. **Create Context**: Initialize the shopping cart
   ```java
   ShoppingCart cart = new ShoppingCart();
   ```

2. **Add Items**: Populate the cart with products
   ```java
   cart.addItem(new Product("Laptop", 999.99));
   cart.addItem(new Product("Mouse", 29.99));
   ```

3. **Select Strategy**: Choose payment method
   ```java
   PaymentStrategy paypal = new PayPalPayment("user@email.com");
   cart.setPaymentStrategy(paypal);
   ```

4. **Execute**: Checkout delegates to the strategy
   ```java
   cart.checkout();  // Uses PayPal strategy
   ```

5. **Switch Strategy**: Change payment method dynamically
   ```java
   cart.setPaymentStrategy(new CreditCardPayment("1234-5678"));
   cart.checkout();  // Now uses credit card
   ```

#### 📊 Class Diagram

```
┌──────────────────┐
│ PaymentStrategy  │
│   «interface»    │
│                  │
│ + pay(amount)    │
└────────△─────────┘
         │
         │ implements
    ┌────┴────┬─────────────┬──────────────┐
    │         │             │              │
┌───┴────┐ ┌──┴─────┐ ┌────┴─────┐ ┌──────┴──────┐
│Credit  │ │PayPal  │ │  Crypto  │ │   Future    │
│Card    │ │Payment │ │ Payment  │ │  Strategies │
└────────┘ └────────┘ └──────────┘ └─────────────┘
                          △
                          │ uses
                   ┌──────┴──────┐
                   │ ShoppingCart│
                   │             │
                   │ - strategy  │
                   │ + checkout()│
                   └─────────────┘
```

#### 💡 Use Cases

- **Payment Systems**: Multiple payment gateways
- **Sorting Algorithms**: QuickSort, MergeSort, BubbleSort
- **Compression**: ZIP, RAR, GZIP formats
- **Routing**: Different navigation algorithms (fastest, shortest, scenic)
- **Validation**: Different validation rules for forms
- **Pricing**: Standard, discount, promotional pricing

#### 🔧 Extension Ideas

- Add new payment providers: `BitcoinPayment`, `ApplePayPayment`
- Implement **strategy selection from configuration** (properties/JSON)
- Add **strategy validation** before execution
- Create **composite strategies** (payment + loyalty points)
- Implement **discount strategies** that can be chained
- Add **logging decorators** for strategies
- Create **fallback mechanisms** if primary strategy fails

---

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher
- **IDE** (Optional but recommended): IntelliJ IDEA, Eclipse, or VS Code
- **Build Tool** (Optional): Maven or Gradle

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/T2T-Theory-to-Trade/T2T-Session-02-Assignment.git
   cd T2T-Session-02-Assignment
   ```

2. **Compile the project**
   ```bash
   # Using javac
   javac -d bin src/**/*.java
   
   # Or using your IDE's build function
   ```

3. **Run individual patterns**
   ```bash
   # Command Pattern
   java -cp bin CommandPattern.Main
   
   # Observer Pattern
   java -cp bin ObserverPattern.Main
   
   # Strategy Pattern
   java -cp bin StrategyPattern.Main
   ```

### Quick Start

For each pattern, navigate to its Main class and run it:

```java
// CommandPattern/Main.java
public class Main {
    public static void main(String[] args) {
        // Demo code for Command Pattern
    }
}
```

---

## 📁 Project Structure

```
T2T-Session-02-Assignment/
│
├── src/
│   ├── CommandPattern/
│   │   ├── Command.java                    # Command interface
│   │   ├── LightOnCommand.java            # Concrete command
│   │   ├── LightOffCommand.java
│   │   ├── FanOnCommand.java
│   │   ├── FanOffCommand.java
│   │   ├── Light.java                      # Receiver
│   │   ├── Fan.java                        # Receiver
│   │   ├── RemoteControl.java              # Invoker
│   │   └── Main.java                       # Demo application
│   │
│   ├── ObserverPattern/
│   │   ├── Observer.java                   # Observer interface
│   │   ├── Subject.java                    # Subject interface
│   │   ├── WeatherStation.java             # Concrete subject
│   │   ├── DisplayDevice.java              # Concrete observer
│   │   └── Main.java                       # Demo application
│   │
│   └── StrategyPattern/
│       ├── PaymentStrategy.java            # Strategy interface
│       ├── CreditCardPayment.java          # Concrete strategy
│       ├── PayPalPayment.java
│       ├── CryptoPayment.java
│       ├── ShoppingCart.java               # Context
│       ├── Product.java                    # Helper class
│       └── Main.java                       # Demo application
│
├── bin/                                     # Compiled classes
├── docs/                                    # Additional documentation
└── README.md                                # Project overview
```

---

## 💻 Usage Examples

### Command Pattern Example

```java
// Create receivers
Light kitchenLight = new Light("Kitchen");
Fan bedroomFan = new Fan("Bedroom");

// Create commands
Command lightOn = new LightOnCommand(kitchenLight);
Command lightOff = new LightOffCommand(kitchenLight);
Command fanOn = new FanOnCommand(bedroomFan);

// Configure remote
RemoteControl remote = new RemoteControl();

// Turn on light
remote.setCommand(lightOn);
remote.pressButton();  // Output: Kitchen Light is ON

// Turn off light
remote.setCommand(lightOff);
remote.pressButton();  // Output: Kitchen Light is OFF

// Undo last command
remote.pressUndo();    // Output: Kitchen Light is ON
```

### Observer Pattern Example

```java
// Create weather station
WeatherStation station = new WeatherStation();

// Create observers
DisplayDevice mobileApp = new DisplayDevice("Mobile App");
DisplayDevice webDashboard = new DisplayDevice("Web Dashboard");
DisplayDevice ledScreen = new DisplayDevice("LED Screen");

// Register observers
station.addObserver(mobileApp);
station.addObserver(webDashboard);
station.addObserver(ledScreen);

// Update weather data
station.setTemperature(28.5f);
// All three displays automatically update

station.setHumidity(70);
// All displays show new humidity

// Remove one observer
station.removeObserver(ledScreen);
station.setTemperature(30.0f);
// Only mobile and web update
```

### Strategy Pattern Example

```java
// Create shopping cart
ShoppingCart cart = new ShoppingCart();

// Add products
cart.addItem(new Product("Laptop", 1299.99));
cart.addItem(new Product("Mouse", 49.99));
cart.addItem(new Product("Keyboard", 89.99));

// Pay with credit card
PaymentStrategy creditCard = new CreditCardPayment(
    "1234-5678-9012-3456",
    "John Doe",
    "123",
    "12/25"
);
cart.setPaymentStrategy(creditCard);
cart.checkout();

// Switch to PayPal
PaymentStrategy paypal = new PayPalPayment(
    "john.doe@email.com",
    "securePassword123"
);
cart.setPaymentStrategy(paypal);
cart.checkout();

// Switch to cryptocurrency
PaymentStrategy crypto = new CryptoPayment(
    "1A2B3C4D5E6F7G8H9I0J",
    "Bitcoin"
);
cart.setPaymentStrategy(crypto);
cart.checkout();
```

---

## 🔧 Extension Guidelines

### Adding New Commands

To add a new device and commands:

1. Create a receiver class:
```java
public class AirConditioner {
    private String location;
    private int temperature;
    
    public AirConditioner(String location) {
        this.location = location;
        this.temperature = 24;
    }
    
    public void turnOn() {
        System.out.println(location + " AC is ON at " + temperature + "°C");
    }
    
    public void turnOff() {
        System.out.println(location + " AC is OFF");
    }
    
    public void setTemperature(int temp) {
        this.temperature = temp;
        System.out.println(location + " AC set to " + temp + "°C");
    }
}
```

2. Create concrete commands:
```java
public class ACOnCommand implements Command {
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
```

### Adding New Observers

To add a new observer type:

```java
public class EmailAlertObserver implements Observer {
    private String emailAddress;
    
    public EmailAlertObserver(String email) {
        this.emailAddress = email;
    }
    
    @Override
    public void update(float temperature, float humidity) {
        if (temperature > 35 || humidity > 80) {
            sendEmail("Weather Alert: Extreme conditions detected!");
        }
    }
    
    private void sendEmail(String message) {
        System.out.println("Sending email to " + emailAddress + ": " + message);
    }
}
```

### Adding New Strategies

To add a new payment method:

```java
public class ApplePayPayment implements PaymentStrategy {
    private String deviceId;
    private String appleId;
    
    public ApplePayPayment(String deviceId, String appleId) {
        this.deviceId = deviceId;
        this.appleId = appleId;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Processing Apple Pay payment of $" + amount);
        System.out.println("Device: " + deviceId);
        System.out.println("Apple ID: " + appleId);
        // Payment processing logic
        System.out.println("Payment successful via Apple Pay!");
    }
}
```

---

## 📚 Best Practices

### General Guidelines

1. **Favor Composition Over Inheritance**: Use interfaces and delegation rather than deep inheritance hierarchies

2. **Program to Interfaces**: Depend on abstractions, not concrete implementations

3. **Keep It Simple**: Don't over-engineer. Add patterns only when they solve real problems

4. **Single Responsibility**: Each class should have one reason to change

5. **Open/Closed**: Open for extension, closed for modification

### Pattern-Specific Tips

#### Command Pattern
- Keep commands lightweight and focused
- Store state for undo in the command, not the receiver
- Consider using a command queue for asynchronous execution
- Implement proper error handling in execute() and undo()

#### Observer Pattern
- Avoid memory leaks by properly removing observers
- Consider using weak references for observer lists
- Be cautious of update cycles (observer updating subject)
- Keep update() methods efficient as they're called frequently

#### Strategy Pattern
- Validate strategy before execution
- Provide sensible default strategies
- Consider strategy factories for complex creation logic
- Keep strategies stateless when possible

### Code Quality

- **Documentation**: Add Javadoc comments for all public APIs
- **Testing**: Write unit tests for each pattern component
- **Naming**: Use descriptive names that reflect intent
- **Error Handling**: Handle edge cases and invalid states gracefully

---

## 🧪 Testing Recommendations

### Unit Testing Structure

```java
// Example test for Command Pattern
public class LightOnCommandTest {
    private Light light;
    private LightOnCommand command;
    
    @Before
    public void setUp() {
        light = new Light("Test Room");
        command = new LightOnCommand(light);
    }
    
    @Test
    public void testExecute() {
        command.execute();
        assertTrue(light.isOn());
    }
    
    @Test
    public void testUndo() {
        command.execute();
        command.undo();
        assertFalse(light.isOn());
    }
}
```

### Integration Testing

Test pattern interactions:
- Command pattern with multiple commands in sequence
- Observer pattern with multiple observers and state changes
- Strategy pattern with runtime strategy switching

---

## 🤝 Contributing

To contribute to this project:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/NewPattern`)
3. Follow the existing code style and patterns
4. Add tests for new functionality
5. Update documentation
6. Submit a pull request

---

## 📖 Learning Resources

### Books
- "Design Patterns: Elements of Reusable Object-Oriented Software" - Gang of Four
- "Head First Design Patterns" - Freeman & Freeman
- "Effective Java" - Joshua Bloch

### Online Resources
- [Refactoring.Guru - Design Patterns](https://refactoring.guru/design-patterns)
- [SourceMaking - Design Patterns](https://sourcemaking.com/design_patterns)

---

## 📄 License

This project is part of the T2T (Theory to Trade) educational program by TRACE.

---

## 👥 Authors

**T2T - Theory to Trade Program**  
Session 02 Assignment - Behavioral Design Patterns

---

## 🙏 Acknowledgments

- Gang of Four for defining design patterns
- TRACE organization for the T2T program
- All contributors and learners in the program

---

**Happy Coding! 🚀**
