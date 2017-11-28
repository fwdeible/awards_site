package com.project.awardParser.component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.hibernate.dialect.function.PositionSubstringFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.project.awardParser.model.Award;
import com.project.awardParser.model.AwardRepository;

@Component
public class StartupDataRunner implements ApplicationRunner {

	static Logger log = Logger.getLogger(StartupDataRunner.class.getName());

	@Autowired
	AwardRepository awardRepo;

	@Autowired
	ServletContext servletContext;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		printRuntimeArgs(args);

		loadFileData("src/main/resources/data/awardList.txt");
		try {
			URL url = servletContext.getResource("/resources/data/awardList.txt");
			// loadFileData(url.getPath());
		} catch (Exception someException) {
			System.err.println("Oops, something didn't work loading award list");
			someException.printStackTrace();
		}

	}

	/**
	 * Empties the LOG_ENTRY table and repopulates it with data from local file
	 * 
	 * @param filename
	 */
	public void loadFileData(String filename) {

		log.debug("reload file: " + filename);

		awardRepo.deleteAll();

		// try (Stream<String> stream = Files
		// .lines(Paths.get(servletContext.getResource("/resources/data/awardList.txt").getPath())))
		// {

		try (Stream<String> stream = Files.lines(Paths.get("/srv/www/awards/awards_list.csv"))) {

			ArrayList<Award> entryList = new ArrayList<Award>();

			stream.forEach(line -> entryList.add(createAwardObject(line)));

			awardRepo.save(entryList);

			log.debug("loaded " + entryList.size() + " entries");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param String
	 *            line example format:
	 */
	public Award createAwardObject(String line) {

		String[] parsedLine = line.split(",");

		Award e = new Award();

		if (parsedLine.length > 0) {
			e.setAwardName(parsedLine[0]);
			
			String possibleCannonicalName = parsedLine[1];
			System.out.println(possibleCannonicalName);
			
			if(possibleCannonicalName != null && !possibleCannonicalName.isEmpty()) {
				e.setCannonicalName(possibleCannonicalName);
			} else {
				e.setCannonicalName("blank");
			}
			
			
			try {

				// e.setRibbonImage(Files.readAllBytes(Paths.get(servletContext.getResource("/resources/img/medals/air_force_cross.png").getPath())));
				// e.setRibbonImage(Files.readAllBytes(Paths.get(("src/main/resources/img/medals/air_force_cross.png"))));
//				e.setRibbonImage(Files.readAllBytes(Paths.get("/srv/www/awards/medal_images_106/legion_of_merit.png")));
				e.setRibbonImage(Files.readAllBytes(Paths.get("/srv/www/awards/medal_images_106/" + e.getCannonicalName() + ".png")));

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

			e.setPrecedence(Integer.parseInt(parsedLine[2]));
			
			// e.setWikiPage("https://en.wikipedia.org/wiki/Awards_and_decorations_of_the_United_States_Armed_Forces");
		}
		return e;

	}

	private void printRuntimeArgs(ApplicationArguments args) {

		for (String s : args.getNonOptionArgs()) {

			log.debug("non option arg: " + s);

		}

		for (String s : args.getOptionNames()) {
			log.debug("option name: " + s);

			for (String sa : args.getOptionValues(s)) {
				log.debug("option value: " + sa);
			}
		}
	}
}
