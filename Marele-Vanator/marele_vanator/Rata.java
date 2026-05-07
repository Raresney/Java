
class Rata extends Thread {

Teren teren;
int w, h;
public int x, y;
public int size;  // 1=small(far), 2=medium, 3=large(close)
public int dir;   // -1=left, 1=right
public int dw, dh;
public boolean activ = true;

Rata(Teren teren, int w, int h) {
    this.teren = teren;
    this.w = w;
    this.h = h;
    reset();
}

public void reset() {
    size = (int)(Math.random() * 3) + 1;
    dw = 30 + size * 15;  // 45, 60, 75
    dh = 15 + size * 8;   // 23, 31, 39
    if (size == 1)      y = 100 + (int)(Math.random() * 70);  // 100-170
    else if (size == 2) y = 170 + (int)(Math.random() * 80);  // 170-250
    else                y = 250 + (int)(Math.random() * 80);  // 250-330
    dir = Math.random() < 0.5 ? -1 : 1;
    x = dir == -1 ? w + 60 : -dw - 60;
}

public void run() {
    while (activ) {
        x += dir * (1 + size);
        if (dir == -1 && x < -dw - 60) reset();
        if (dir ==  1 && x >  w + 60)  reset();
        teren.repaint();
        try { Thread.sleep(12); } catch (Exception e) {}
    }
}

}
