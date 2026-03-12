import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class ImageEditJava extends JFrame {

    private BufferedImage image;
    private Graphics2D g2d;

    private String currentTool = "Marker";
    private int penSize = 2;
    private float zoom = 1.0f;
    private Point lastPoint;

    private JPanel canvas;
    private Color currentColor = Color.BLACK;

    public ImageEditJava() {
        setTitle("ImageEdit");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== CANVAS =====
        canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.scale(zoom, zoom);
                    g2.drawImage(image, 0, 0, null);
                }
            }
        };
        canvas.setBackground(Color.WHITE);
        add(canvas, BorderLayout.CENTER);

        // ===== IMAGE =====
        image = new BufferedImage(800, 400, BufferedImage.TYPE_INT_ARGB);
        g2d = image.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
        g2d.setColor(currentColor);
        g2d.setStroke(new BasicStroke(penSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        // ===== MOUSE EVENTS =====
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                lastPoint = null;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (currentTool.equals("Text")) {
                    String text = JOptionPane.showInputDialog("Enter text:");
                    if (text != null && !text.isEmpty()) {
                        g2d.setComposite(AlphaComposite.SrcOver);
                        g2d.setColor(currentColor);
                        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
                        g2d.drawString(
                                text,
                                (int) (e.getX() / zoom),
                                (int) (e.getY() / zoom)
                        );
                        canvas.repaint();
                    }
                }
            }
        });

        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (lastPoint == null || currentTool.equals("Text")) return;

                int x1 = (int) (lastPoint.x / zoom);
                int y1 = (int) (lastPoint.y / zoom);
                int x2 = (int) (e.getX() / zoom);
                int y2 = (int) (e.getY() / zoom);

                if (currentTool.equals("Eraser")) {
                    g2d.setComposite(AlphaComposite.Clear);
                } else {
                    g2d.setComposite(AlphaComposite.SrcOver);
                    g2d.setColor(currentColor);
                }

                g2d.setStroke(new BasicStroke(
                        penSize,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND
                ));

                g2d.drawLine(x1, y1, x2, y2);
                lastPoint = e.getPoint();
                canvas.repaint();
            }
        });

        // ===== TOOLBAR =====
        JPanel tools = new JPanel(new GridLayout(0, 1, 5, 5));
        add(tools, BorderLayout.EAST);

        addButton(tools, "Marker", e -> {
            currentTool = "Marker";
            penSize = 2;
        });

        addButton(tools, "Brush", e -> {
            currentTool = "Brush";
            penSize = 6;
        });

        addButton(tools, "Eraser", e -> {
            currentTool = "Eraser";
            penSize = 10;
        });

        addButton(tools, "Text", e -> currentTool = "Text");

        addButton(tools, "Color", e -> {
            Color c = JColorChooser.showDialog(this, "Choose color", currentColor);
            if (c != null) currentColor = c;
        });

        addButton(tools, "FlipH", e -> flipHorizontal());
        addButton(tools, "FlipV", e -> flipVertical());

        addButton(tools, "Zoom +", e -> {
            zoom += 0.1f;
            canvas.repaint();
        });

        addButton(tools, "Zoom -", e -> {
            zoom = Math.max(0.1f, zoom - 0.1f);
            canvas.repaint();
        });

        setVisible(true);
    }

    private void addButton(JPanel panel, String text, ActionListener action) {
        JButton btn = new JButton(text);
        btn.addActionListener(action);
        panel.add(btn);
    }

    private void flipHorizontal() {
        int w = image.getWidth();
        int h = image.getHeight();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w / 2; x++) {
                int tmp = image.getRGB(x, y);
                image.setRGB(x, y, image.getRGB(w - 1 - x, y));
                image.setRGB(w - 1 - x, y, tmp);
            }
        }
        canvas.repaint();
    }

    private void flipVertical() {
        int w = image.getWidth();
        int h = image.getHeight();
        for (int y = 0; y < h / 2; y++) {
            for (int x = 0; x < w; x++) {
                int tmp = image.getRGB(x, y);
                image.setRGB(x, y, image.getRGB(x, h - 1 - y));
                image.setRGB(x, h - 1 - y, tmp);
            }
        }
        canvas.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ImageEditJava::new);
    }
}
