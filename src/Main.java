import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Login login = new Login();
        Frame fm = new Frame();
        fm.add(login.criarPainelLogin());
        fm.setVisible(true);
    }
}