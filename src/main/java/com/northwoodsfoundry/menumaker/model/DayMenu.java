package com.northwoodsfoundry.menumaker.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@DynamoDBTable(tableName = "tower-menu-northwoods")
public class DayMenu {

    @DynamoDBHashKey
    @DynamoDBAttribute(attributeName = "Id")
    private String id;

    /**
     * A day's menu is a map:    { "lunch":"food,soup-or-salad,desserts", "dinner":"food,soup-or-salad,desserts" }
     */
    @DynamoDBAttribute
    private Map<String, String> menu = new HashMap<>(1);

    public DayMenu(String date, MealPlan mealPlan) {
        this();
        this.id = date;
        this.menu = Map.of(
                "lunch", mealPlan.lunch().trim() + ", soup or salad, and desserts",
                "dinner", mealPlan.dinner().trim() + ", soup or salad, and desserts"
        );
    }

    public DayMenu() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getMenu() {
        return menu;
    }

    public void setMenu(Map<String, String> menu) {
        this.menu = menu;
    }

    @DynamoDBIgnore
    public String getLunch() {
        Objects.requireNonNull(menu);
        return menu.getOrDefault("lunch", "NOTHING FOUND FOR LUNCH");
    }

    @DynamoDBIgnore
    public String getDinner() {
        Objects.requireNonNull(menu);
        return menu.getOrDefault("dinner", "NOTHING FOUND FOR DINNER");
    }

    @Override
    public String toString() {
        return String.format("%s lunch=%s, dinner=%s", id, truncate(getLunch()), truncate(getDinner()));
    }

    public String truncate(String str) {
        int maxLength = 20;
        if (str.length() <= maxLength) return str;
        return str.substring(0, maxLength) + "...";
    }

    public record MealPlan(String lunch, String dinner) {
    }
}