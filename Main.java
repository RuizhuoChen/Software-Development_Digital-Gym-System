import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jf = new JFrame("Main Menu");
        jf.setSize(1280, 750);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);

        new AllCourse(); // Check Environment File, and generate it when not exist

        final JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Home", new ImageIcon("bb.jpg"), new HomePanel().homePanel(tabbedPane));

        tabbedPane.addTab("Video", new ImageIcon("bb.jpg"), new VideoPanel().scrollPanel());

        tabbedPane.addTab("Trainer", new ImageIcon("bb.jpg"), new TrainerPanel().scrollPanel());

        tabbedPane.addTab("User", new ImageIcon("bb.jpg"), new UserTabbedPane().userTabbedPane(tabbedPane));

        tabbedPane.setSelectedIndex(0);

        jf.setContentPane(tabbedPane);
        jf.setVisible(true);
    }

    public void MainPanel(){
        JFrame jf = new JFrame("Main Menu");
        jf.setSize(1280, 750);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);

        new AllCourse(); // Check Environment File, and generate it when not exist

        final JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Home", new ImageIcon("bb.jpg"), new HomePanel().homePanel(tabbedPane));

        tabbedPane.addTab("Video", new ImageIcon("bb.jpg"), new VideoPanel().scrollPanel());

        tabbedPane.addTab("Trainer", new ImageIcon("bb.jpg"), new TrainerPanel().scrollPanel());

        tabbedPane.addTab("User", new ImageIcon("bb.jpg"), new UserTabbedPane().userTabbedPane(tabbedPane));

        tabbedPane.setSelectedIndex(0);

        jf.setContentPane(tabbedPane);
        jf.setVisible(true);
    }
}