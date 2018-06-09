package com.kane.restaurant.controllers;

import com.kane.restaurant.db.DBHelper;
import com.kane.restaurant.models.Customer;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;

public class BookingController {
    public BookingController(){ this.setUpEndPoints(); }

    private void setUpEndPoints() {
        VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();

        get("/booking", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            GregorianCalendar today = new GregorianCalendar();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setCalendar(today);
            List<Customer> allCustomers = DBHelper.getAllByAscending("name", Customer.class);

            model.put("customers", allCustomers);
            model.put("todaysDate", sdf.format(today.getTime()));
            model.put("template", "/templates/bookings/new.vtl");
            return new ModelAndView(model, "/templates/layout.vtl");
        }, velocityTemplateEngine);
    }
}
