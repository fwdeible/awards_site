package com.project.awardParser.component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
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

	@Override
	public void run(ApplicationArguments args) throws Exception {

		printRuntimeArgs(args);

		
		loadFileData("src/main/resources/data/awardList.txt");
		
	}

	/**
	 * Empties the LOG_ENTRY table and repopulates it with data from local file
	 * 
	 * @param filename
	 */
	public void loadFileData(String filename) {

		log.debug("reload file: " + filename);

		awardRepo.deleteAll();

		try (Stream<String> stream = Files.lines(Paths.get(filename))) {

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

		String[] parsedLine = line.split("\\|");

		Award e = new Award();
		e.setAwardName(parsedLine[0]);
		try {
			e.setRibbonImage(Files.readAllBytes(Paths.get(("src/main/resources/img/medals/army_service_ribbon.jpg"))));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		e.setRibbonImageName("");
//		e.setWikiPage("https://en.wikipedia.org/wiki/Awards_and_decorations_of_the_United_States_Armed_Forces");

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
