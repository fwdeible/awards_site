package com.project.awardParser.image;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.project.awardParser.model.Award;

public class SwingUI {
	private static final Dimension RIBBON_SIZE = new Dimension(106, 30);

	private static java.util.List<Award> awardList = AwardFactory.getAwards(true);

	public static void main(String... args) throws IOException {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

	}

	private static void createAndShowGUI() {

		JFrame f = new JFrame();
		
//		for (Award award : awardList) {
//			System.out.println( "adding label for: " + award.getName());
			
//			JPanel panel = new JPanel();
			
		ArrayList<BufferedImage> imageList = new ArrayList<BufferedImage>();
		for (Award award : awardList) {
			//TODO FOR SWINGUI TO WORK
//			imageList.add(award.getRibbonImage());
		}
			
			JLabel label = new JLabel(new ImageIcon(ImageFormatter.createCombinedImage((imageList.toArray(new BufferedImage[imageList.size()])))));
//		JLabel label = new JLabel(new ImageIcon(imageList.toArray(new BufferedImage[imageList.size()])[0]));
			label.setPreferredSize(new Dimension(1000, 1000));
			label.setSize(new Dimension(1000, 1000));

//			panel.setPreferredSize(RIBBON_SIZE);
//			panel.setSize(RIBBON_SIZE);
			
//			panel.add(label);
//			f.getContentPane().add(panel);
			
			f.getContentPane().add(label);
			
//			f.getContentPane().add(new JLabel(award.getName()));
//		}

System.out.println("window?");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
//		f.setLocation(200, 200);
		f.setVisible(true);
	}


}
