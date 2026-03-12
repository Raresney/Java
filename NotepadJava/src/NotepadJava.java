import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NotepadJava extends JFrame {

    JTextArea textArea;

    public NotepadJava() {
        setTitle("Notepad");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(textArea);
        add(scroll);

        JMenuBar menuBar = new JMenuBar();
        JMenu editMenu = new JMenu("Edit");

        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");
        JMenuItem deleteItem = new JMenuItem("Delete");
        JMenuItem selectAllItem = new JMenuItem("Select All");

        cutItem.addActionListener(e -> textArea.cut());
        copyItem.addActionListener(e -> textArea.copy());
        pasteItem.addActionListener(e -> textArea.paste());

        deleteItem.addActionListener(e -> {
            int start = textArea.getSelectionStart();
            int end = textArea.getSelectionEnd();
            if (start != end) {
                textArea.replaceRange("", start, end);
            }
        });

        selectAllItem.addActionListener(e -> textArea.selectAll());

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.add(deleteItem);
        editMenu.add(selectAllItem);

        menuBar.add(editMenu);
        setJMenuBar(menuBar);

        setVisible(true);
    }

    public static void main(String[] args) {
        new NotepadJava();
    }
}
