# ♠️ Blackjack Console Game

A console-based implementation of the classic Blackjack card game developed in **Java**, with a focus on **Object-Oriented Programming (OOP)** principles and clean project structure.

---

## 🎮 Features

-  Standard 52-card deck
-  Automatic deck shuffling
-  Player vs Dealer gameplay
-  Automatic Blackjack detection
-  Dynamic Ace value (1 or 11)
-  Dealer follows official Blackjack rules (draws until at least 17)
-  Win / Draw / Loss statistics
-  User input validation
-  Multiple rounds without restarting the application

---

## 🏗️ Project Structure

| Class | Responsibility |
|--------|----------------|
| `Card` | Represents a playing card |
| `Deck` | Creates, shuffles and manages the deck |
| `Player` | Abstract class containing shared player logic |
| `HumanPlayer` | Represents the human player |
| `Dealer` | Represents the dealer |
| `Game` | Controls the entire game flow |
| `GameStatistics` | Tracks wins, losses and draws |
| `Main` | Application entry point |

---

## 🛠️ Technologies

- Java
- Object-Oriented Programming (OOP)
- Git & GitHub
- IntelliJ IDEA

---

## ▶️ How to Run
1. Clone the repository
2. Open the project in IntelliJ IDEA
3. Run `Main.java`

Or compile manually:
```bash
javac src/*.java -d out
java -cp out Main
```
---

## 🧠 What I Learned

During the development of this project, I practiced and improved my understanding of:

- Object-Oriented Programming (abstraction, inheritance, polymorphism and encapsulation)
- Class design and responsibility separation
- Game flow implementation
- User input validation
- Git version control and commit history
- Java collections and object interaction

---

## 🔮 Future Improvements

- Unit tests
- Betting system
- Split and Double Down actions
- Improved console UI


