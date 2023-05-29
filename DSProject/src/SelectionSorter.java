import javax.swing.*;
import java.util.concurrent.locks.ReentrantLock;
import java.awt.*;
import java.awt.geom.Line2D;

public class SelectionSorter {
    public SelectionSorter(int[] anArray, JComponent aComponent) {
        a = anArray;
        sortStateLock = new ReentrantLock();
        component = aComponent;
    }
    public void pause(int steps) throws InterruptedException {
        component.repaint();
        Thread.sleep(steps * DELAY);
    }
    public int minimumPosition(int from) throws InterruptedException {
        int minPos = from;
        for (int i = from + 1; i < a.length; i++) {
            sortStateLock.lock();
            try {
                if (a[i] < a[minPos])
                    minPos = i;
                markedPos = i;
            }
            finally {
                sortStateLock.unlock();
            }pause(2);
        }return minPos;
    }
    public void sort() throws InterruptedException {
        int out, min;
        for (out = 0; out < a.length; out++) {
            min = minimumPosition(out);
            swap(out, min);
            sorted = out;
        }
    }
    private void swap(int first, int second) {
        int temp = a[first];
        a[first] = a[second];
        a[second] = temp;
    }
    public void draw(Graphics2D g2) {
        sortStateLock.lock();
        try {
            int deltaX = component.getWidth() / a.length;
            for (int i = 0; i < a.length; i++) {
                if (i == markedPos)
                    g2.setColor(Color.RED);
                else if (i <= sorted)
                    g2.setColor(Color.BLUE);
                else
                    g2.setColor(Color.BLACK);
                g2.draw(new Line2D.Double(i * deltaX, 0, i * deltaX, a[i]));
            }
        }
        finally {
            sortStateLock.unlock();
        }
    }
    private int[] a;
    private int markedPos = -1;
    private int sorted = -1;
    private final int DELAY = 10;
    private ReentrantLock sortStateLock;
    private JComponent component;
}