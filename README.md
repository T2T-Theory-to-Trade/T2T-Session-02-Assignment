🚀 Behavioral Design Patterns in Java
This project demonstrates three core behavioral design patterns implemented in Java — each neatly organized in its own package:

🕹️ CommandPattern

📡 ObserverPattern

♟️ StrategyPattern

Each pattern can be executed independently via the console, showcasing how flexibility, reusability, and loose coupling are achieved in real-world Java applications.

🕹️ Command Pattern
🎯 Intent
Encapsulate a request as an object, allowing you to parameterize clients with different requests, queue or log them, and support undoable operations.

🧩 Key Participants
Role	Description
Command	Declares the execute() and undo() operations.
ConcreteCommand	Implements the command by binding it to a specific receiver (e.g., LightOnCommand, FanOffCommand).
Receiver	Performs the actual business logic (e.g., Light, Fan).
Invoker	Triggers commands (e.g., RemoteControl), unaware of what the command actually does.
⚙️ Execution Flow
Create receivers (e.g., Light, Fan).

Wrap them inside concrete command objects (LightOnCommand(light)).

Configure the invoker (RemoteControl) using setCommand().

Execute via pressButton(), and revert using pressUndo().

💡 Extension Ideas
Add new device commands by implementing Command.

Introduce an undo stack for multi-level undo.

Create a MacroCommand to execute multiple commands in sequence.

📡 Observer Pattern
🎯 Intent
Define a one-to-many dependency between objects so that when one object (the Subject) changes state, all its dependents (Observers) are automatically notified.

🧩 Key Participants
Role	Description
Subject	Manages observers and provides addObserver(), removeObserver(), notifyObservers().
Observer	Defines the update() method that reacts to subject state changes.
ConcreteSubject	Maintains state and notifies observers (e.g., WeatherStation).
ConcreteObserver	Subscribes to updates and reacts accordingly (e.g., DisplayDevice).
⚙️ Execution Flow
Observers (DisplayDevice) register to the subject (WeatherStation).

When data changes (setTemperature()), the subject triggers notifyObservers().

Each observer’s update() is called to refresh displayed data.

💡 Extension Ideas
Add new observer types (e.g., SMSAlertObserver).

Modify the subject to push selective updates or allow pull-based updates.

♟️ Strategy Pattern
🎯 Intent
Define a family of algorithms, encapsulate each, and make them interchangeable — allowing the algorithm to vary independently from the client that uses it.

🧩 Key Participants
Role	Description
Strategy	Common interface for all algorithms (e.g., PaymentStrategy).
ConcreteStrategy	Implements the algorithm (e.g., CreditCardPayment, PayPalPayment, CryptoPayment).
Context	Holds a reference to a strategy (e.g., ShoppingCart) and delegates behavior.
⚙️ Execution Flow
Create a ShoppingCart and add products.

Select a payment strategy (e.g., new PayPalPayment()).

Set it in the context: cart.setPaymentStrategy().

Call cart.checkout() → delegates payment logic to the strategy.

💡 Extension Ideas
Add new payment providers easily by implementing PaymentStrategy.

Integrate discounts, logging, or externalized strategy selection (e.g., from a config file).

⚙️ Running the Examples
🧰 Prerequisites
Java JDK 8+

Apache Maven

🖥️ Compile
mvn compile
▶️ Run a Demo
Pattern	Run Command
Command Pattern	mvn exec:java -Dexec.mainClass="CommandPattern.CommandDemo"
Observer Pattern	mvn exec:java -Dexec.mainClass="ObserverPattern.ObserverDemo"
Strategy Pattern	mvn exec:java -Dexec.mainClass="StrategyPattern.StratergyDemo"
🧪 Testing & Validation
Currently, validation is console-based — observe printed outputs to confirm correct command execution, observer notifications, and strategy behavior.

For better reliability, implement JUnit tests under:

Assignment/src/test/java
Each test can verify:

Command execution and undo logic

Observer registration and update notifications

Strategy selection and payment logic

🔧 Maintenance Notes
🧱 Independence: Each pattern package is fully isolated — changes to one won't affect the others.

🪶 Loose Coupling: Always program to interfaces (Command, Observer, PaymentStrategy) for easy scalability.

🧹 Refactoring Tip: Rename typos like getTotatl() and StratergyDemo before moving to production.

🌟 Summary
This project offers a hands-on, modular guide to mastering behavioral design patterns in Java.
Each pattern demonstrates clean separation of concerns, reusable design, and extensible architecture — essential skills for writing robust, maintainable software.

