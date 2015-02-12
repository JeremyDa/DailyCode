package com.jeremy.util.image.freeCombi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;

import javax.imageio.ImageIO;

public class ImageUtil {
    public static void main(String[] args) throws IOException {
        freeCombi("d://", "1210016914", "d://b.jpg");
        int i =90;
        System.out.println(""+4*i+3*i);
    }

    public static void freeCombi(String filePath, String fileName,
            String outFile) throws IOException {
        int chunks = 5;

        int chunkWidth, chunkHeight;
        int type;

        // 读取图片文件
        File[] imgFiles = new File[chunks];
        imgFiles[0] = new File(filePath + fileName + "S1.jpg");
        imgFiles[1] = new File(filePath + fileName + "HC.jpg");
        imgFiles[2] = new File(filePath + fileName + "S2.jpg");
        imgFiles[3] = new File(filePath + fileName + "K1.jpg");
        imgFiles[4] = new File(filePath + fileName + "K2.jpg");

        // 缓存图片文件
        BufferedImage[] buffImages = new BufferedImage[chunks];
        for (int i = 0; i < chunks; i++) {
            buffImages[i] = ImageIO.read(imgFiles[i]);
        }
        type = buffImages[0].getType();
        chunkWidth = buffImages[3].getWidth();
        chunkHeight = buffImages[3].getHeight();

        // 初始化最终的图片缓存器
        BufferedImage finalImg = new BufferedImage(1500, 1000, type);

        Graphics2D g2d = finalImg.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 1500, 1000);

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("宋体",Font.BOLD,35));
        g2d.drawString("二代证照片：", 200, 150);
        g2d.drawImage(buffImages[0], 200, 200, 204, 252, null);
        g2d.drawString("联网核查照片：", 600, 150);
        g2d.drawImage(buffImages[1], 600, 200, 204, 252, null);
        g2d.drawString("现场照片：", 950, 150);
        g2d.drawImage(buffImages[2], 950, 200, 360, 270, null);
        g2d.drawString("身份证扫描件：", 170, 550);
        g2d.drawImage(buffImages[3], 170, 600, null);
        g2d.drawImage(buffImages[4], 800, 600, null);

        System.out.println("图片组合完");
        ImageIO.write(finalImg, "jpeg", new File(outFile));
    }
}
