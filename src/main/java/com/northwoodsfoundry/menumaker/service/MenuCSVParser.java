package com.northwoodsfoundry.menumaker.service;

import com.northwoodsfoundry.menumaker.model.DayMenu;
import com.northwoodsfoundry.menumaker.repositories.MenuRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MenuCSVParser {
    private static final Logger logger = LoggerFactory.getLogger(MenuCSVParser.class);

    @Autowired
    private MenuRepository menuRepository;

    public static List<DayMenu> parseLocalCSV(String filePath) throws IOException {
        List<DayMenu> menus = new ArrayList<>();

        CSVFormat format = CSVFormat.DEFAULT;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        for (CSVRecord menuRecord : format.parse(reader)) {
            // skip the first row as it contains headers
            if (menuRecord.getRecordNumber() == 1) {
                continue;
            }
            // intentionally ignoring field in position 0

            // this is the date field
            String id = menuRecord.get(1);
            validateDateFormat(id);
            String lunch = menuRecord.get(2);
            String dinner = menuRecord.get(3);
            var menu = new DayMenu(id, new DayMenu.MealPlan(lunch, dinner));
            System.out.println(menu);
            menus.add(menu);
        }
        return menus;
    }

    private static void validateDateFormat(String id) {
        if (!id.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Invalid date format: " + id + ". Expected YYYY-MM-DD");
        }
    }


    @Scheduled(initialDelay = 5, timeUnit = TimeUnit.SECONDS, fixedDelay = Integer.MAX_VALUE)
    private void parseAndSave() throws IOException {
        String csvPath = "src/main/resources/menu-latest-12.csv";
        List<DayMenu> menus = MenuCSVParser.parseLocalCSV(csvPath);

        for (DayMenu dayMenu : menus) {
            //menuRepository.save(dayMenu);
            logger.info("Would save menu for {} as {}", dayMenu.getId(), dayMenu);
        }
    }
}
