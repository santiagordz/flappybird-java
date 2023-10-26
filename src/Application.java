import java.awt.EventQueue;
import java.io.Serial;
import javax.swing.JFrame;


public class Application extends JFrame {

    @Serial
    private static final long serialVersionUID = 156542312398L;

    public Application() {
        initUI();
    }

    private void initUI() {
        add(new Board());

        setTitle("Application");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });
    }
}