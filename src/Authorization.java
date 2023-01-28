import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Random;

public class Authorization extends User {
    public String login_input;
    public String password_input;
    public String captcha_input;
    private int attempt_counter = 3;
    private String captchaText;
    private BufferedImage captchaImage;
    private String generateCaptchaText() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append((char) (r.nextInt(26) + 'a'));
        }
        return sb.toString();
    }

    private BufferedImage generateCaptchaImage(String captchaText) {
        int width = 1920;
        int height = 1080;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 200));
        g2d.drawString(captchaText, 250, 540);
        g2d.dispose();
        return img;
    }


    public Authorization(String username, String ID, String password) {
        super(username, ID, password);
        this.captchaText = generateCaptchaText();
        this.captchaImage = generateCaptchaImage(captchaText);
    }

    public int getAttempt_counter() {
        return attempt_counter;
    }

    public void attempt_reset() {
        attempt_counter = 3;
        this.captchaText = generateCaptchaText();
        this.captchaImage = generateCaptchaImage(captchaText);
    }

    public void attempt_decrease() {
        attempt_counter--;
    }

    public static boolean isNumeric(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean user_input() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter username or IIN: ");
        this.login_input = input.nextLine();

        if (isNumeric(this.login_input)) {
            System.out.println("Enter password (IIN): ");
            this.password_input = input.nextLine();
            return this.login_input.equals(getIIN()) && this.password_input.equals(getPassword());
        } else {
            System.out.println("Enter password (login): ");
            this.password_input = input.nextLine();
            return (this.login_input.equals(getUsername()) && this.password_input.equals(getPassword()));
        }
    }

    public boolean captcha_input_check() {
        Scanner input = new Scanner(System.in);
        File outputfile = new File("captcha.jpg");
        try {
            ImageIO.write(captchaImage, "jpg", outputfile);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(outputfile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Input captcha: ");
        this.captcha_input = input.nextLine();
        return this.captcha_input.equals(captchaText);
    }
}
