import javax.swing.*;

class InterfaceBat {
    private JPanel Ibatalha;
    private JLabel PokeAdv;
    private JProgressBar progressBar1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button5;
    private JProgressBar progressBar3;

    public static void main(String[] args) {
        JFrame frame = new JFrame("InterfaceBatalha");
        frame.setContentPane(new InterfaceBat().Ibatalha);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
