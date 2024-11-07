import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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