package com.project.awardParser.model;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Award {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String awardName;

	private String ribbonImageName;
	
	@Lob
	private byte[] ribbonImage;
	private String description;
	// private String category;
	// private Color[] colors;
	private String wikiPage;
	// TODO private Component component; service component

	public Award() {
		super();
	}

	public Award(int i, String string, BufferedImage image) {
		super();
		// this.ribbonImage = ribbonImage.set;
	}

	public Award(Long id, String awardName, String ribbonImageName, BufferedImage ribbonImage, String description,
			String wikiPage) {
		super();
		this.id = id;
		this.awardName = awardName;
		// this.ribbonImageName = ribbonImageName;
		// this.ribbonImage = ribbonImage;
		this.description = description;
		this.wikiPage = wikiPage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAwardName() {
		return awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	public byte[] getRibbonImage() {
		return ribbonImage;
	}

	public void setRibbonImage(byte[] ribbonImage) {
		this.ribbonImage = ribbonImage;
	}
	// public String getRibbonImageName() {
	// return ribbonImageName;
	// }
	// public void setRibbonImageName(String imageName) {
	// this.ribbonImageName = imageName;
	// }
	// public String getWikiPage() {
	// return wikiPage;
	// }
	// public void setWikiPage(String wikiPage) {
	// this.wikiPage = wikiPage;
	// }
	// public BufferedImage getRibbonImage() {
	// return ribbonImage;
	// }
	// public void setRibbonImage(BufferedImage ribbonImage) {
	// this.ribbonImage = ribbonImage;
	// }

}
