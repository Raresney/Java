# 🖼️ ImageEditJava

A freehand image editor built with Java Swing and Java2D.

> Part of [Java Projects](../README.md) — Bighiu Rares

---

## ▶️ Run

```bash
javac ImageEditJava.java
java ImageEditJava
```

---

## 📌 Notes

- Canvas is a `BufferedImage` drawn with `Graphics2D`
- Eraser uses `AlphaComposite.Clear` to remove pixels
- Zoom scales mouse coordinates to keep drawing accurate
- Flip operations iterate pixel-by-pixel using `getRGB`/`setRGB`
