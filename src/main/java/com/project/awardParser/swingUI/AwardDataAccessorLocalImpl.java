package com.project.awardParser.swingUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.project.awardParser.model.Award;

public class AwardDataAccessorLocalImpl  {

	
	public static final String IMAGES_PATH = "src/images/";
	
	
	public List<Award> getAllAwards() {
		return getTestAwards();
	}
	
	//TODO: refactor data out of this class
	public List<Award> getTestAwards() {

		@SuppressWarnings("serial")
		List<Award> awardList = new ArrayList<Award>() {
			{
//				// TODO: SVG. Apache Batik?
//				// "Army_of_Occupation_of_Germany_ribbon.svg"
				add(new Award(1, "army service ribbon", LocalImageLoader.getImage("army_service_ribbon.png")));
				add(new Award(2, "gwot service ribbon", LocalImageLoader.getImage("army_service_ribbon.png")));
				add(new Award(3, "gwot service ribbon", LocalImageLoader.getImage("army_service_ribbon.png")));
				add(new Award(4, "gwot service ribbon", LocalImageLoader.getImage("army_service_ribbon.png")));
				add(new Award(5, "gwot service ribbon", LocalImageLoader.getImage("army_service_ribbon.png")));
				add(new Award(6, "gwot service ribbon", LocalImageLoader.getImage("army_service_ribbon.png")));
				add(new Award(7, "gwot service ribbon", LocalImageLoader.getImage("army_service_ribbon.png")));
				add(new Award(8, "gwot service ribbon", LocalImageLoader.getImage("army_service_ribbon.png")));
				add(new Award(9, "gwot service ribbon", LocalImageLoader.getImage("army_service_ribbon.png")));
				add(new Award(10, "gwot service ribbon", LocalImageLoader.getImage("army_service_ribbon.png")));
				add(new Award(11, "gwot service ribbon", LocalImageLoader.getImage("army_service_ribbon.png")));
				add(new Award(12, "gwot service ribbon", LocalImageLoader.getImage("army_service_ribbon.png")));
				add(new Award(13, "gwot service ribbon", LocalImageLoader.getImage("army_service_ribbon.png")));
				add(new Award(14, "gwot service ribbon", LocalImageLoader.getImage("army_service_ribbon.png")));
				add(new Award(15, "gwot service ribbon", LocalImageLoader.getImage("army_service_ribbon.png")));
				add(new Award(16, "gwot service ribbon", LocalImageLoader.getImage("army_service_ribbon.png")));
				
			}
		};

		return awardList;

	}
	

	private static class LocalImageLoader {
		public static BufferedImage getImage(String imageName) {
			try {
				byte[] image = Files.readAllBytes(new File("").toPath());
				return ImageIO.read(new File(AwardDataAccessorLocalImpl.IMAGES_PATH + imageName));
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}



}
