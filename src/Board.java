import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Board extends JPanel implements ActionListener {

    @Serial
    private static final long serialVersionUID = 156542314628L;

    private static final int DELAY = 10;
    private static final int B_WIDTH = 800;
    private static final int B_HEIGHT = 800;
    private Bird bird;
    private ArrayList<Pipe> pipes;
    private boolean inGame;
    private Timer timer;

    public Board() {
        initUI();
    }

    private void initUI() {
        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);

        bird = new Bird();
        pipes = new ArrayList<Pipe>();
        inGame = true;
        timer = new Timer(DELAY, this);
        timer.start();

        generatePipes();
    }

    public void generatePipes() {
        Random rand = new Random();
        int topPipeY = rand.nextInt(101) - 100;
        Pipe topPipe = new Pipe(800, topPipeY, false);

        int bottomPipeY = rand.nextInt(201) + 600;
        Pipe bottomPipe = new Pipe(800, bottomPipeY, true);

        pipes.add(topPipe);
        pipes.add(bottomPipe);
    }

    public void movePipes() {
        Iterator<Pipe> iterator = pipes.iterator();
        while (iterator.hasNext()) {
            Pipe pipe = iterator.next();
            pipe.move();
            if (!pipe.isVisible()) {
                iterator.remove();
            }
        }
        if (pipes.get(pipes.size() - 1).getX() <= 500) {
            generatePipes(); // Generate new pipes when the last pipe crosses a certain threshold
        }
    }

    public void checkCollisions() {
        for (Pipe pipe : pipes) {
            if (pipe.getBounds().intersects(bird.getBounds())) {
                gameOver();
            }
        }
    }

    public void gameOver() {
        inGame = false;
        timer.stop();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            doDrawing(g);
        } else {
            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawGameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 28);
        FontMetrics fm = getFontMetrics(small);
        g.setColor(Color.WHITE);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    private void doDrawing(Graphics g) {
        if (bird.isVisible()) {
            g.drawImage(bird.getImage(), bird.getX(), bird.getY(), null);
        }
        for (Pipe pipe : pipes) {
            if (pipe.isVisible()) {
                g.drawImage(pipe.getImage(), pipe.getX(), pipe.getY(), null);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateBird();
        movePipes();
        checkCollisions();
        repaint();
    }

    private void updateBird() {
        if (bird.isVisible()) {
            bird.move();
        }
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            try {
                bird.keyReleased(e);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        @Override
        public void keyPressed(KeyEvent e) {
            bird.keyPressed(e);
        }
    }
}