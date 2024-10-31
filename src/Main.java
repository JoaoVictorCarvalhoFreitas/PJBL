import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Login login = new Login();
        Frame frame = new Frame();
        frame.add(login.criarPainelLogin());
        frame.setVisible(true);
    }
}