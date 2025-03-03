package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class LoadSave {

    /**
     * Loads an array of images for animation.
     *
     * @param path       The formatted string path (e.g., "/res/Idle/cuphead_idle_%04d.png")
     * @param frameCount The number of frames in the animation
     * @return An array of BufferedImage containing the loaded frames
     */
    public static BufferedImage[] loadAnimation(String path, int frameCount) {  // ✅ Corrected
        BufferedImage[] images = new BufferedImage[frameCount];

        for (int i = 0; i < frameCount; i++) {
            String fileName = String.format(path, i + 1); // Format filename
            try (InputStream is = LoadSave.class.getResourceAsStream(fileName)) {
                if (is == null) {
                    System.err.println("Could not load: " + fileName);
                    continue;
                }
                images[i] = ImageIO.read(is);
            } catch (Exception e) {
                System.err.println("Error loading: " + fileName);
                e.printStackTrace();
            }
        }

        return images;
    }

    public static BufferedImage GetLayerAtlas(String path) {
        try (InputStream is = LoadSave.class.getResourceAsStream(path)) {
            if (is == null) {
                System.err.println("Could not load map layer: " + path);
                return null;
            }
            return ImageIO.read(is);
        } catch (IOException e) {
            System.err.println("Error loading map layer: " + path);
            e.printStackTrace();
            return null;
        }
    }
}
