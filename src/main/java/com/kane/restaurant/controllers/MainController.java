package com.kane.restaurant.controllers;

import com.kane.restaurant.db.DBHelper;
import com.kane.restaurant.db.Seeds;
import com.kane.restaurant.models.Booking;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.*;

import static spark.Spark.get;

public class MainController {
    public static void main(String[] args) {
        Seeds.seedData();
        VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();

        get("/", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            Calendar today = new GregorianCalendar();
            List<Booking> todaysBookings = DBHelper.getBookingsByDate(today);
            model.put("bookings", todaysBookings);
            model.put("template", "/templates/home.vtl");
            return new ModelAndView(model, "/templates/layout.vtl");
        }, velocityTemplateEngine);
    }

}
