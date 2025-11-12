# T2T-Session01-Assignment-02
🚀 Behavioral Design Patterns in Java
This document details a Java project demonstrating three fundamental behavioral design patterns. Each implementation is self-contained in its own package (CommandPattern, ObserverPattern, StrategyPattern) and can be run via the console.

🕹️ Command Pattern

📡 Observer Pattern

♟️ Strategy Pattern

🕹️ Command Pattern
Intent: To encapsulate a request as an object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations.

Key Participants
Command: The abstract interface (e.g., Command) declaring execute() and undo().

Concrete Commands: The implementations (e.g., LightOnCommand, FanOffCommand) that wrap a "receiver."

Receiver: The object with the actual business logic (e.g., Light, Fan) that performs the work when execute() is called.

Invoker: The object that triggers the command (e.g., RemoteControl). It doesn't know what the command does, only that it can be executed.

Execution Flow
The client constructs receiver objects (Light, Fan).

The client wraps these receivers in concrete command instances (e.g., LightOnCommand(light)).

The RemoteControl (Invoker) is configured with these commands via setCommand().

When RemoteControl.pressButton() is called, it delegates the call to the active command's execute() method.

RemoteControl.pressUndo() calls the corresponding undo() method to revert the last action.

Extension Points
Add new device actions by implementing the Command interface.

Enhance the RemoteControl with a stack to support a multi-level undo history.

Implement a "Macro Command" that composes and executes multiple Command objects in sequence.

📡 Observer Pattern
Intent: To define a one-to-many dependency between objects so that when one object (the subject) changes state, all its dependents (the observers) are notified and updated automatically.

Key Participants
Subject: The interface for managing observers (e.g., Subject), with methods like addObserver(), removeObserver(), and notifyObservers().

Observer: The interface defining the update contract (e.g., Observer), typically with an update() method.

Concrete Subject: The object that maintains the state (e.g., WeatherStation). It notifies its observers when its state changes.

Concrete Observer: An object that receives updates (e.g., DisplayDevice). It registers with the subject and implements the update() method.

Execution Flow
Multiple DisplayDevice (Observers) subscribe to the WeatherStation (Subject) via addObserver().

The WeatherStation's data changes through a method call like setTemperature().

Inside this method, the WeatherStation calls notifyObservers().

This method iterates through its list of subscribers and calls the update() method on each one.

Each DisplayDevice then renders the latest readings to the console.

Extension Points
Implement new observers (e.g., an SMSAlertObserver) by simply implementing the Observer interface.

Change the WeatherStation to "push" only specific data, or have observers "pull" the data they need from the subject.

♟️ Strategy Pattern
Intent: To define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

Key Participants
Strategy: The common interface for all algorithms (e.g., PaymentStrategy) which exposes a method like pay(int amount).

Concrete Strategies: The different algorithm implementations (e.g., CreditCardPayment, PayPalPayment, CryptoPayment).

Context: The object that holds a reference to a strategy object (e.g., ShoppingCart). It delegates the "pay" behavior to the currently selected strategy.

Execution Flow
The client builds a ShoppingCart (Context) and adds Product instances.

The client selects a payment method and instantiates the corresponding strategy (e.g., new PayPalPayment()).

The client passes this strategy object to the ShoppingCart via setPaymentStrategy().

The client calls shoppingCart.checkout().

The ShoppingCart computes the total and then delegates the final payment logic to its strategy object by calling paymentStrategy.pay(total).

Extension Points
Introduce new payment providers by simply creating a new class that implements the PaymentStrategy interface.

The context (ShoppingCart) can add validation, discounts, or logging before delegating to the strategy.

Strategy selection can be externalized (e.g., read from a config file or user input) to change behavior without recompiling.

⚙️ Running the Examples
Prerequisites
JDK 8+

Apache Maven

Steps
Compile the project:

Bash
mvn compile
Run a specific demo:

Command Pattern

Bash
mvn exec:java -Dexec.mainClass="CommandPattern.CommandDemo"
Observer Pattern

Bash
mvn exec:java -Dexec.mainClass="ObserverPattern.ObserverDemo"
Strategy Pattern

Bash
mvn exec:java -Dexec.mainClass="StrategyPattern.StratergyDemo"
🧪 Testing and Validation
Currently, validation is performed by observing the console output to verify correct command execution, observer notifications, and strategy delegation.

For more robust validation, JUnit tests should be added under Assignment/src/test/java to automate the verification of each pattern's behavior.

🔧 Maintenance Notes
Independence: The pattern packages are independent. Changes to one should not impact the others.

Loose Coupling: Always program to the interfaces (Command, Observer, PaymentStrategy) when extending functionality.

Refactoring: Consider renaming getTotatl() and StratergyDemo to correct typos if this code is refactored for production use.
