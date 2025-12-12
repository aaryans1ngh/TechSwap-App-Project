# TechSwap-App-Project
Built a custom application using Java and SQL. It serves as an interactive virtual store that buys or sells any tech product for value calculated by the device's condition and it's retail price.

## Project Description
TechSwap Store is a robust Java application designed to solve the problem of inconsistent trade-in pricing and manual inventory tracking for electronic resale stores. The application provides a command-line interface (CLI) for employees to register customers, generate automated price quotes for used electronics (Smartphones and Laptops), and manage the store's inventory database. By standardizing the valuation logic and persisting data using SQLite, the system ensures fair pricing for customers and reliable stock tracking for management.

## Technologies Used
* **Language:** Java (JDK 21)
* **Database:** SQLite (JDBC)
* **Concepts:** Object-Oriented Programming (OOP)
* **Tools:** VS Code, Git, GitHub

## Features & Implementation Mapping

| Feature / Concept | Implementation in Project |
| :--- | :--- |
| **Inheritance** | The `Smartphone` and `Laptop` classes inherit shared properties (model, age, base price) from the `ElectronicDevice` superclass. |
| **Abstraction** | `ElectronicDevice` is an **abstract class** that defines the structure for all devices but prevents generic devices from being created. |
| **Polymorphism** | The `calculateValue()` method is defined abstractly in the parent class and overridden in `Smartphone` and `Laptop` to apply unique valuation logic (e.g., storage bonuses vs. RAM bonuses). |
| **Interfaces** | The `Tradeable` interface ensures that any class implementing it (like our devices) must have a `calculateValue()` method. |
| **Encapsulation** | All class properties are `private` or `protected` and accessed via public getters/setters or constructors (Access Specifiers). |
| **Composition** | The `Transaction` class demonstrates composition by containing a `Customer` object and a list of `ElectronicDevice` objects. |
| **Database Integration** | The `TechSwapDb` class uses **JDBC** to perform full **CRUD** (Create, Read, Update, Delete) operations on a SQLite database. |
| **File I/O** | The `Logger` class writes activity logs (startups, new quotes, deletions) to a text file (`activity_log.txt`) for audit purposes. |

## How to Run
1.  Ensure **Java** and the **SQLite JDBC Driver** are added to your classpath.
2.  Compile all `.java` files.
3.  Run `App.java`.
4.  Follow the on-screen menu prompts to add customers, generate quotes, and manage inventory.

## Reflection
This project successfully demonstrates the power of OOP in building scalable business software. The use of polymorphism allowed for easy expansion (adding a "Tablet" class would be simple), while the database integration ensures data persistence beyond a single session. The menu-driven interface makes the tool intuitive for end-users, satisfying the requirement for a realistic application structure.