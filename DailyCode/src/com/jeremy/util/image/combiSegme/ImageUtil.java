package com.jeremy.util.image.combiSegme;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Jeremy
 * @time   下午2:29:11 2015年1月5日
 * @todo   图片分割和组合
 */
public class ImageUtil {
    public static void main(String[] args) {
        
        try {
            segme();
            combi();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void combi() throws IOException{
        int rows = 4;   //初始化有小图片的数量 
        int cols = 4;  
        int chunks = rows * cols;  
  
        int chunkWidth, chunkHeight;  
        int type;  
        //读取图片文件 
        File[] imgFiles = new File[chunks];  
        for (int i = 0; i < chunks; i++) {  
            imgFiles[i] = new File("d://img" + i + ".jpg");  
        }  
  
       //缓存图片文件
        BufferedImage[] buffImages = new BufferedImage[chunks];  
        for (int i = 0; i < chunks; i++) {  
            buffImages[i] = ImageIO.read(imgFiles[i]);  
        }  
        type = buffImages[0].getType();  
        chunkWidth = buffImages[0].getWidth();  
        chunkHeight = buffImages[0].getHeight();  
  
        //初始化最终的图片缓存器
        BufferedImage finalImg = new BufferedImage(chunkWidth*cols, chunkHeight*rows, type);  
  
        int num = 0;  
        for (int i = 0; i < rows; i++) {  
            for (int j = 0; j < cols; j++) {  
                finalImg.createGraphics().drawImage(buffImages[num], chunkWidth * j, chunkHeight * i, null);  
                num++;  
            }  
        }  
        System.out.println("图片组合完");  
        ImageIO.write(finalImg, "jpeg", new File("d://b.jpg"));  
    }
    
    public static void segme() throws IOException{
        File file = new File("d://test.jpg"); // 项目目录下有名为btg.jpg的图片  
        FileInputStream fis = new FileInputStream(file);  
        BufferedImage image = ImageIO.read(fis); //把文件读到图片缓冲流中
  
        int rows = 4; //定义图片要切分成多少块  
        int cols = 4;  
        int chunks = rows * cols;  
  
        int chunkWidth = image.getWidth() / cols; // 计算每一块小图片的高度和宽度 
        int chunkHeight = image.getHeight() / rows;  
        int count = 0;  
        BufferedImage imgs[] = new BufferedImage[chunks];  
        for (int x = 0; x < rows; x++) {  
            for (int y = 0; y < cols; y++) {  
                //初始化BufferedImage 
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());  
  
                //画出每一小块图片
                Graphics2D gr = imgs[count++].createGraphics();  
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);  
                gr.dispose();  
            }  
        }  
        System.out.println("切分完成");  
  
        //保存小图片到文件中
        for (int i = 0; i < imgs.length; i++) {  
            ImageIO.write(imgs[i], "jpg", new File("d://img" + i + ".jpg"));  
        }  
        System.out.println("小图片创建完成");  
    }
}
