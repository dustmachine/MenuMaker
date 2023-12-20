package com.northwoodsfoundry.menumaker.service;

import com.northwoodsfoundry.menumaker.model.DayMenu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class MenuCSVParserTest {

    @Test
    void parseLocalCSV() throws IOException {
        String csvPath = "src/main/resources/menu-latest-09.csv";

        List<DayMenu> result = MenuCSVParser.parseLocalCSV(csvPath);

        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(37));
    }

    @Test
    void validateDateFormat() {

        String csvPath = "src/main/resources/menu-bad-date-format.csv";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            MenuCSVParser.parseLocalCSV(csvPath);
        });

    }

}