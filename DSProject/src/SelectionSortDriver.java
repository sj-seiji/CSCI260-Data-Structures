import javax.swing.*;
public class SelectionSortDriver {
    public static void main(String[] args) throws Exception {
        JFrame frame1 = new JFrame();
        frame1.setSize(400, 450);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);

        SelectionSortComponent animation = new SelectionSortComponent();
        animation.startAnimation();
        frame1.add(animation);

    }
}