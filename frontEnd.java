import javax.swing.*;

public class frontEnd {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JButton b = new JButton("Enter");
        b.setBounds(150, 100, 100, 40);
        frame.add(b);
        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
