package com.chemyoo.readrgb;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

public class RgbReader {

	private static Logger logger = Logger.getLogger(RgbReader.class);
	/**
	 * @param args
	 * @throws AWTException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		logger.info(getScreenPixel(1,1));
		logger.info(getImagePixel("C:/Users/chemyoo/Desktop/壁纸/30d396c3247b6309c94fb1704bec5e33.jpg"));
		//getImageGRB("C:/Users/chemyoo/Desktop/壁纸/01aac3e1709970a471845bd575a65f5d.jpg");
		//Thread.sleep(300); // 休眠300毫秒，模拟处理业务等代码生成ID可为避免重复，使用此法       
	}
	/**
	 * 读取一张图片的RGB值
	 * 
	 * @throws Exception
	 */
	public static  String  getImagePixel(String image) throws Exception 
	{
		int[] rgb = new int[3];
		File file = new File(image);
		BufferedImage bi = null;
		try 
		{
			bi = ImageIO.read(file);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		int width = bi.getWidth();
		int height = bi.getHeight();
		int minx = bi.getMinX();
		int miny = bi.getMinY();
		logger.info("width=" + width + ",height=" + height + ".");
		logger.info("minx=" + minx + ",miniy=" + miny + ".");
		int i=width/2,j=(height-100);
		int pixel = bi.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字
		rgb[0] = (pixel & 0xff0000) >> 16;
		rgb[1] = (pixel & 0xff00) >> 8;
		rgb[2] = (pixel & 0xff);
		logger.info("i=" + i + ",j=" + j + ":(" + rgb[0] + ","
				+ rgb[1] + "," + rgb[2] + ")");
		String rgbstr="RGB颜色取反色为：rgb(" + (~rgb[0]+(2<<7)+1) + ","+  (~rgb[1]+(2<<7)+1) + "," +  (~rgb[2]+(2<<7)+1) + ")";
		String rgbHex="#"+Integer.toHexString(rgb[0])+Integer.toHexString(rgb[1])+Integer.toHexString(rgb[2]);
		logger.info("16进制颜色代码："+rgbHex);
		String hexStr=Integer.toHexString(pixel+0xffffff+1);
		logger.info("16进制颜色代码：#"+hexStr);
		logger.info("16进制颜色代码：#"+hexStr.substring(hexStr.length()-6, hexStr.length()));
		return rgbstr; 
	}
	/**
	 * 返回屏幕色彩值
	 * 
	 * @param x
	 * @param y
	 * @return
	 * @throws AWTException
	 */
	public static int getScreenPixel(int x, int y) throws AWTException { // 函数返回值为颜色的RGB值。
		Robot rb = null; // java.awt.image包中的类，可以用来抓取屏幕，即截屏。
		rb = new Robot();
		Toolkit tk = Toolkit.getDefaultToolkit(); // 获取缺省工具包
		Dimension di = tk.getScreenSize(); // 屏幕尺寸规格
		System.out.println(di.width);
		System.out.println(di.height);
		Rectangle rec = new Rectangle(0, 0, di.width, di.height);
		BufferedImage bi = rb.createScreenCapture(rec);
		int pixelColor = bi.getRGB(x, y);

		return 16777216 + pixelColor; // pixelColor的值为负，经过实践得出：加上颜色最大值就是实际颜色值。
	}
	
	public static int[][] getImageGRB(String filePath) {
        File file  = new File(filePath);
        int[][] result = null;
        if (!file.exists()) {
             return result;
        }
        try {
             BufferedImage bufImg = ImageIO.read(file);
             int height = bufImg.getHeight();
             int width = bufImg.getWidth();
             result = new int[width][height];
             for (int i = 0; i < width; i++) {
                  for (int j = 0; j < height; j++) {
                        result[i][j] = bufImg.getRGB(i, j) & 0xFFFFFF;
                        logger.info(result[i][j]);
                  }
             }
        } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
        }
        return result;
  }

}
