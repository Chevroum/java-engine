import javax.swing.JFrame;

public class Main  extends Panel{

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Test");

        Panel p = new Panel();
        window.add(p);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        p.start();
        p.run();
    }
}
