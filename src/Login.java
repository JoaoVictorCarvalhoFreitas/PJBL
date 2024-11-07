import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Login {
    private JTextField email;
    private JButton BotaoEntrar;
    private JPasswordField senha;
    private JPanel PainelLoginUI;
    private JButton Cadastrar;
    private JPanel PainelBotoes;
    private JPanel Card;
    private JPanel Informacoes;
    private JLabel login_label;
    private JPanel dados;
    private JPanel imagem;
    private ImagePanel fundo
            ;

    Login() {
        imagem = new ImagePanel("src/fundo.png");
        // Configura PainelLoginUI para usar OverlayLayout
        PainelLoginUI.setLayout(new OverlayLayout(PainelLoginUI));

        // Adiciona o painel de imagem ao fundo
        PainelLoginUI.add(imagem);

        // Configura a ordem de renderização para que a imagem fique no fundo
        PainelLoginUI.setComponentZOrder(imagem, PainelLoginUI.getComponentCount() - 1);

        Card.setPreferredSize(new Dimension(650, 450));
        Card.setMaximumSize(new Dimension(650, 450));
        Informacoes.setPreferredSize(new Dimension(250, 450));
        Informacoes.setMaximumSize(new Dimension(250, 450));


        BotaoEntrar.setBorder(null);
        Cadastrar.setBorder(null);
        email.setBorder(null);
        senha.setBorder(null);


        String placeholdersenha = "Digite sua senha";

        String placeholderemail = "Digite seu email aqui";

        // Configura o estilo do placeholder
        email.setForeground(Color.GRAY);
        email.setText(placeholderemail);

        // Adiciona um FocusListener para alternar o placeholder
        email.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (email.getText().equals(placeholderemail)) {
                    email.setText("");
                    email.setForeground(Color.BLACK); // Altera a cor para texto normal
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (email.getText().isEmpty()) {
                    email.setText(placeholderemail);
                    email.setForeground(Color.GRAY); // Retorna à cor do placeholder
                }
            }
        });

        // Configura o estilo do placeholder
        senha.setForeground(Color.GRAY);
        senha.setText(placeholdersenha);
        senha.setEchoChar((char) 0);


        senha.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(senha.getPassword()).equals(placeholdersenha)) {
                    senha.setText("");
                    senha.setForeground(Color.BLACK);
                    senha.setEchoChar('•');

                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (senha.getPassword().length == 0) {
                    senha.setText(placeholdersenha);
                    senha.setForeground(Color.GRAY);
                    senha.setEchoChar((char) 0);
                }
            }

        });
    }
    public JPanel criarPainelLogin(){
        return this.PainelLoginUI;
    }

    public JButton getBotaoEntrar() {
        return this.BotaoEntrar;
    }

    public JButton getBotaoCadastrar() {
        return this.Cadastrar;
    }
    public JTextField getEmail() {
        return this.email;
    }
    public JPasswordField getSenha() {
        return this.senha;
    }

}

class ImagePanel extends JPanel {
    private BufferedImage image;

    public ImagePanel(String imagePath) {
        try {
            // Carrega a imagem a partir do caminho do arquivo
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a imagem.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            // Desenha a imagem no painel centralizando-a
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
