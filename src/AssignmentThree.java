import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class AssignmentThree extends JPanel {
    public final int xPictureCoordinate = 400;
    public final int yPictureCoordinate = 400;
    public final int memberFibonacci = 8;

    public void paintComponent(Graphics g) {
        setBackground(Color.BLACK);
        g.setColor(Color.WHITE);

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("src/files/ass3/fibonacci.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(image, 0, 0, this.xPictureCoordinate, this.yPictureCoordinate,null);

        g.setFont(new Font("Ariel", Font.BOLD, 35));
        g.drawString("ФибоНачи", 120, 30);

        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        queue.offer(1);
        int count = 1;
        StringBuilder message = new StringBuilder();
        message.append("Като зема 1 дърво...");
        message.append("\n");
        message.append("Като зема 1 дърво...");
        message.append("\n");
        while (count < memberFibonacci - 1) {
            //8 член на редицата минус 1, само променяме 8 с който член искаме от редицата
            queue.offer(queue.poll() + queue.peek());
            message.append(String.format("Като зема %d дървета...", queue.peekLast()));
            message.append("\n");
            count++;
        }
        g.setFont(new Font("Ariel", Font.BOLD, 25));
        int desiredHeightForRect = getDesiredHeightForRect(g, message.toString(), yPictureCoordinate);
        g.setColor(Color.BLACK);
        g.fillRect(0, this.yPictureCoordinate, this.xPictureCoordinate, desiredHeightForRect);
        g.setColor(Color.WHITE);
        drawString(g, message.toString(), 30, this.yPictureCoordinate);
    }

    public int getDesiredHeightForRect(Graphics g, String text, int y) {
        for (String line : text.split("\n")) {
            y += g.getFontMetrics().getHeight();
        }
        return y;
    }

    public void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n")) {
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
        }
    }

}
