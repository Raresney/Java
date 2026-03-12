# ☕ Java Projects

A collection of desktop GUI applications built with Java Swing, demonstrating event-driven programming, component layout, and user interaction.

> Copyright (c) 2026 Bighiu Rares — [github.com/Raresney](https://github.com/Raresney)

---

## 📁 Projects

| Project                                | Description                                                                      |
| -------------------------------------- | -------------------------------------------------------------------------------- |
| [🧮 CalculatorJava](./CalculatorJava/) | Functional calculator with basic operations (+, -, \*, /) and square root        |
| [🖼️ ImageEditJava](./ImageEditJava/)   | Image editor with marker, brush, eraser, text tool, color picker, flip, and zoom |
| [📝 NotepadJava](./NotepadJava/)       | Simple text editor with cut, copy, paste, delete, and select all                 |

---

## 🧮 CalculatorJava

Swing-based calculator with a clean grid layout.

**Features:** addition, subtraction, multiplication, division, square root, clear, decimal support, division-by-zero error handling.

```bash
javac CalculatorJava/src/CalculatorJava.java
java -cp CalculatorJava/src CalculatorJava
```

---

## 🖼️ ImageEditJava

A lightweight image editor built entirely with Java2D and Swing.

**Features:**

- Marker, Brush, Eraser tools (adjustable size)
- Text tool — click anywhere on canvas to add text
- Color picker (JColorChooser)
- Flip Horizontal / Flip Vertical
- Zoom in / Zoom out

```bash
javac ImageEditJava/src/ImageEditJava.java
java -cp ImageEditJava/src ImageEditJava
```

---

## 📝 NotepadJava

Minimal text editor with a scrollable text area and Edit menu.

**Features:** Cut, Copy, Paste, Delete selection, Select All.

```bash
javac NotepadJava/src/NotepadJava.java
java -cp NotepadJava/src NotepadJava
```

---

## ⚙️ Requirements

- Java 8+
- No external libraries — pure Java Swing

---

## 🛠️ Skills Demonstrated

- Java Swing GUI development
- Event-driven programming (ActionListener, MouseListener)
- Java2D graphics (BufferedImage, Graphics2D)
- Layout managers (BorderLayout, GridLayout)
- Object-oriented design
