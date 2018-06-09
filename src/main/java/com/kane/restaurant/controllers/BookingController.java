package com.kane.restaurant.controllers;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;

import static spark.Spark.get;

public class BookingController {
    public BookingController(){ this.setUpEndPoints(); }

    private void setUpEndPoints() {
        VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();

        get("/booking", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "/templates/bookings/new.vtl");
            return new ModelAndView(model, "/templates/layout.vtl");
        }, velocityTemplateEngine);
    }
}
