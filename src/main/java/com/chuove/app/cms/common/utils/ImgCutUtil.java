package com.chuove.app.cms.common.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;

import javax.imageio.ImageIO;

public class ImgCutUtil {
	/**
     * 截取图片
     * @param srcImageFile  原图片地址
     * @param x    截取时的x坐标
     * @param y    截取时的y坐标
     * @param desWidth   截取的宽度
     * @param desHeight   截取的高度
     */
    public static void imgCut(String srcImageFile, int x, int y, int desWidth,
                              int desHeight) {
        try {
            Image img;
            ImageFilter cropFilter;
            
            BufferedImage bi = ImageIO.read(new File(srcImageFile+"_src.jpg"));
            int srcWidth = bi.getWidth();
            int srcHeight = bi.getHeight();
            if (srcWidth >= desWidth && srcHeight >= desHeight) {
            				//创建此图像的缩放版本								下面的值是 1
                Image image = bi.getScaledInstance(srcWidth, srcHeight,Image.SCALE_DEFAULT);
                //构造一个矩形的新图像  用于裁剪图像的 ImageFilter 类
                cropFilter = new CropImageFilter(x, y, desWidth, desHeight);
                /*
                 * getDefaultToolkit获得默认工具包
                 * createImage 使用指定的图像生成器创建一幅图像
                 * new FilteredImageSource 根据现有的ImageProducer和过滤器对象构造一个ImageProducer对象。
                 * image.getSource() 获取生成图像像素的对象
                 */
                img = Toolkit.getDefaultToolkit().createImage(
                        new FilteredImageSource(image.getSource(), cropFilter));
                //构造一个类型为预定义图像类型之一的 BufferedImage。
                BufferedImage tag = new BufferedImage(desWidth, desHeight,
                        BufferedImage.TYPE_INT_RGB);
                //绘制图像
                Graphics g = tag.createGraphics();
                g.drawImage(img, 0, 0, null);
                //释放此图像的上下文以及它所用到的资源
                g.dispose();
                //输出文件   将一个图像写入文件
                ImageIO.write(tag, "JPEG", new File(srcImageFile+"_cut.jpg"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
