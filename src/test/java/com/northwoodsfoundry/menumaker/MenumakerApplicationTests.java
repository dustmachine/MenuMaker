package com.northwoodsfoundry.menumaker;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.northwoodsfoundry.menumaker.model.DayMenu;
import com.northwoodsfoundry.menumaker.repositories.MenuRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = MenumakerApplication.class)
@WebAppConfiguration
class MenumakerApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(MenumakerApplicationTests.class);

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Test
    void testReadMenuSpecificDay() {
        var result = menuRepository.findById("2023-07-01");
        assertTrue(result.isPresent());
        var dayMenu = result.get();
        logger.info("Here's the day menu: {}", dayMenu);
        assertThat(dayMenu.getId(), equalTo("2023-07-01"));
        assertThat(dayMenu.getLunch(), containsString("fries"));
        assertThat(dayMenu.getDinner(), containsString("walleye"));
    }

    @Test
    void testReadAll() {
        var results = menuRepository.findAll();
        assertThat(results, notNullValue());
        int counter = 0;
        for (DayMenu dayMenu : results) {
            logger.info("Here's the day menu: {}", dayMenu);
            counter++;
        }
        assertTrue(counter > 50);
    }

    @Test
    void testSaveDayMenu() {
        DayMenu dayMenu = new DayMenu("1999-12-31", new DayMenu.MealPlan("hot dogs", "steak"));
        menuRepository.save(dayMenu);

        var result = menuRepository.findById("1999-12-31");
        assertTrue(result.isPresent());
        var recentlySavedDayMenu = result.get();
        logger.info("Here's the day menu: {}", dayMenu);
        assertThat(recentlySavedDayMenu.getId(), equalTo("1999-12-31"));
        assertThat(recentlySavedDayMenu.getLunch(), containsString("hot dogs"));
        assertThat(recentlySavedDayMenu.getDinner(), containsString("steak"));
    }
}
