package com.kane.restaurant.controllers;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;

import static spark.Spark.get;

public class MainController {
    public static void main(String[] args) {
        VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();

        get("/", (req, res) -> {
            HashMap<String, Object> model = new HashMap<String, Object>();
            model.put("template", "/templates/home.vtl");
            return new ModelAndView(model, "/templates/layout.vtl");
        }, velocityTemplateEngine);
    }

}
