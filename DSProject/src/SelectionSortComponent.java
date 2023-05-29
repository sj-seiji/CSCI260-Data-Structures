import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JComponent;

public class SelectionSortComponent extends JComponent {

    public void paintComponent(Graphics g) {
        if (sorter == null) return;
        Graphics2D g2 = (Graphics2D) g;
        sorter.draw(g2);
    }
    public void startAnimation() {
        int[] values = randomIntArray(30, 300);
        sorter = new SelectionSorter(values, this);
        class AnimationRunnable implements Runnable {
            public void run() {
                try {
                    sorter.sort();
                }
                catch(InterruptedException exception) {

                }
            }
        }
        Runnable run = new AnimationRunnable();
        Thread t = new Thread(run);
        t.start();
    }
    public int[] randomIntArray(int length, int size) {
        nums = new int[length];
        Random rand = new Random();
        for(int i = 0; i < length; i++) {
            nums[i] = rand.nextInt(size + 1);
        }
        return nums;
    }
    private SelectionSorter sorter;
    private int [] nums;
}