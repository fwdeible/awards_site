package com.project.awardParser.image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.project.awardParser.model.Award;

public class ImageFormatter {

	/*
	 * Colors
	 */

	private static final Color OD_GREEN = new Color(85, 107, 47);
	private static final Color CLEAR = new Color(0,0,0,0);

	private static final int RIBBONS_PER_ROW = 4;
	private static final int MARGIN = 5;

	/*
	 * Helper Method - convert byte[] to BufferedImage
	 */
	public static BufferedImage getBufferedImage(byte[] imgBytes) {
		InputStream in = new ByteArrayInputStream(imgBytes);
		BufferedImage i;
		try {
			i = ImageIO.read(in);
			return i;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * Helper method - convert BufferedImage to byte[]
	 */
	public static byte[] getByteArray(BufferedImage image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] imageInByte = null;
		try {
			ImageIO.write(image, "png", baos);

			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return imageInByte;
	}

	public static byte[] createCombinedImage(List objects) {

		List<BufferedImage> imageList = new ArrayList<BufferedImage>();
		for (Object o : objects) {
			byte[] imgBytes;
			if (o instanceof Award) {
				imgBytes = ((Award) o).getRibbonImage();
			} else if (o instanceof byte[]) {
				imgBytes = (byte[]) o;
			} else {
				// log.error TODO: add log
				return null;
			}

			imageList.add(getBufferedImage(imgBytes));
//			InputStream in = new ByteArrayInputStream(imgBytes);
//
//			try {
//				imageList.add(ImageIO.read(in));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
		return createCombinedImage(imageList.toArray(new BufferedImage[imageList.size()]));

	}

	public static byte[] createCombinedImage(BufferedImage... bufferedImages) {

		int newImageWidth = 0;

		for (int i = 0; (i < bufferedImages.length) && (i < RIBBONS_PER_ROW); i++) {
			newImageWidth += bufferedImages[i].getWidth();
			newImageWidth += MARGIN;
			System.out.println(newImageWidth);
		}
		
		int newImageHeight = (bufferedImages[0].getHeight()+MARGIN) * (1+(bufferedImages.length / RIBBONS_PER_ROW));

//		BufferedImage newImage = new BufferedImage(newImageWidth, 500, BufferedImage.TYPE_INT_ARGB);
		BufferedImage newImage = new BufferedImage(newImageWidth, newImageHeight, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2 = newImage.createGraphics();

		g2.setPaint(CLEAR);
		g2.fillRect(0, 0, newImage.getWidth(), newImage.getHeight());

		int x = MARGIN;
		int y = MARGIN;
		for (int i = 0; i < bufferedImages.length; i++) {
			System.out.println("x=" + x);
			BufferedImage img = (BufferedImage) bufferedImages[i];
			Graphics g = newImage.getGraphics();

			g.drawImage(img, x, y, null);

			if ((i + 1) % RIBBONS_PER_ROW == 0) {
				x = MARGIN;
				y += img.getHeight() + MARGIN;
			} else {
				x += img.getWidth() + MARGIN;
			}
		}


		
		return getByteArray(newImage);
	}

}
