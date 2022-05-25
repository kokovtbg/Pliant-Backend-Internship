import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AssignmentThreeMain {
    public static void main(String[] args) throws IOException, AWTException {
        AssignmentThree assignmentThree = new AssignmentThree();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(assignmentThree);

        //Hardcode for the size of Frame, height must be extended for longer Fibonacci
        //730 = 730 - 400(end of Y coordinate of image) = 330 / 8(member of Fibonacci) = 41.25
        // 41.25 / 25(Ariel, Bold, 25) = 1.65
        //
        frame.setSize(400, 730);
        frame.setVisible(true);


//        BufferedImage image = new BufferedImage(assignmentThree.xPictureCoordinate, 800, BufferedImage.TYPE_INT_RGB);

        BufferedImage image = new Robot().createScreenCapture(assignmentThree.bounds());
        Graphics2D graphics = image.createGraphics();
        assignmentThree.paint(graphics);
        graphics.dispose();
        ImageIO.write(image, "png", new File("src/files/ass3/fibonacci_out.png"));
    }
}
