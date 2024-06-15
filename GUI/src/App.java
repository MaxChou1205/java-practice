import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

public class App extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.RED);
        g.fillOval(100, 100, 100, 100);

        g.setColor(Color.yellow);
        g.drawRect(200, 200, 100, 100);

        g.drawString("Hello, World!", 100, 100);

        ImageIcon image = new ImageIcon("30x30.jpg");
        image.paintIcon(null, g, 100, 100);
    }

    public static void main(String[] args) throws Exception {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(300, 300);
        window.setContentPane(new App());
        window.setVisible(true);
    }
}
