# Calculator 🧮

Simple and fully functional calculator application built in Java using Swing framework.

## Features

- **Basic Operations**: Addition (+), Subtraction (-), Multiplication (×), Division (÷)
- **Decimal Support**: Perform calculations with decimal numbers
- **Clear Functions**: 
  - C: Clear all (reset calculator)
  - CE: Clear entry (clear current input only)
  - ←: Backspace (delete last digit)
- **Error Handling**: Division by zero detection
- **Responsive UI**: Window resizes smoothly
- **Color-coded Buttons**: 
  - White: Number buttons
  - Orange: Operation buttons
  - Green: Equals button
  - Red: Clear buttons

## Requirements

- Java JDK 8 or higher
- No external libraries required (uses built-in Swing)

## Installation & Setup

1. **Clone or download the repository**
```bash
git clone https://github.com/YOUR_USERNAME/Calculator.git
cd Calculator
```

2. **Compile the code**
```bash
javac Calculator.java
```

3. **Run the calculator**
```bash
java Calculator
```

## How to Use

1. Click number buttons (0-9) to enter numbers
2. Click an operation button (+, -, ×, ÷)
3. Enter second number
4. Click = to get result
5. Use ← to delete last digit
6. Use C to reset everything
7. Use CE to clear just current entry
8. Use . for decimal numbers

## Example Calculations

- `5 + 3 = 8`
- `10 × 2.5 = 25`
- `100 ÷ 4 = 25`
- `7.5 - 2.3 = 5.2`

## Project Structure

```
Calculator/
├── Calculator.java    (Main source code)
└── README.md         (This file)
```

## Code Details

- **Language**: Java
- **Framework**: Swing (javax.swing)
- **Main Class**: Calculator
- **Total Lines**: ~260 lines
- **OOP Concepts Used**: Classes, Inheritance, Event Handling

## Compilation & Execution

**Windows (Command Prompt/PowerShell):**
```bash
javac Calculator.java
java Calculator
```

**Linux/Mac (Terminal):**
```bash
javac Calculator.java
java Calculator
```

## Features Implementation

- Event-driven programming with ActionListener
- String-based calculation engine
- Proper number formatting (removes trailing zeros)
- Input validation and error messages

## Author

College Project - Calculator Application

## License

Free to use for educational purposes.


## Future Enhancements

- Square root, percentage operations
- History of calculations
- Keyboard support
- Themes/Dark mode
- Memory functions (M+, M-, MR, MC)

---

**Happy Calculating!** 🎉
