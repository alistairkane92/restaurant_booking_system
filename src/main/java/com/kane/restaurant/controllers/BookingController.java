package com.kane.restaurant.controllers;

import com.kane.restaurant.db.DBHelper;
import com.kane.restaurant.models.Booking;
import com.kane.restaurant.models.Customer;
import com.kane.restaurant.models.Table;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static spark.Spark.get;
import static spark.Spark.post;

public class BookingController {
    public BookingController(){ this.setUpEndPoints(); }

    private void setUpEndPoints() {
        VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();
        GregorianCalendar tempCal = new GregorianCalendar();

        get("/booking", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            GregorianCalendar today = new GregorianCalendar();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setCalendar(today);
            List<Customer> allCustomers = DBHelper.getAllByAscending("name", Customer.class);
            List<Table> allTables = DBHelper.getAllByAscending("capacity", Table.class);

            model.put("todayHour", today.get(Calendar.HOUR_OF_DAY));
            model.put("biggestTableCapacity", allTables.get(allTables.size() - 1).getCapacity());
            model.put("customers", allCustomers);
            model.put("todaysDate", sdf.format(today.getTime()));
            model.put("template", "/templates/bookings/new.vtl");
            return new ModelAndView(model, "/templates/layout.vtl");
        }, velocityTemplateEngine);

        post("/booking", (req, res) -> {
            int numberToSeat = Integer.parseInt(req.queryParams("numberToSeat"));
            int hour = Integer.parseInt(req.queryParams("hour"));
            int minute = Integer.parseInt(req.queryParams("minute"));

            //date assigning
            String dateText = req.queryParams("date");
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date date = new Date();
            try{
                date = sdf.parse(dateText);
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }

            tempCal.setTime(date);
            tempCal.set(Calendar.HOUR_OF_DAY, hour);
            tempCal.set(Calendar.MINUTE, minute);

            if (DBHelper.isBookingPossible(tempCal, numberToSeat)){
                req.session().attribute("hour", hour);
                req.session().attribute("minute", minute);
                req.session().attribute("date", dateText);
                req.session().attribute("numberToSeat", numberToSeat);
                res.redirect("/booking/customer");
            }
            res.redirect("/failure");
            return null;
        }, velocityTemplateEngine);

        get("/failure", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "/templates/bookings/failure.vtl");
            return new ModelAndView(model, "/templates/layout.vtl");
        }, velocityTemplateEngine);

        get("/booking/customer", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("hour", req.session().attribute("hour"));
            model.put("minute", req.session().attribute("minute"));
            model.put("numberToSeat", req.session().attribute("numberToSeat"));
            model.put("date", req.session().attribute("date"));
            model.put("customers", DBHelper.getAllByAscending("name", Customer.class));
            model.put("template", "/templates/bookings/verify_customer.vtl");
            return new ModelAndView(model, "/templates/layout.vtl");
        }, velocityTemplateEngine);

        post("/booking/customer", (request, response) -> {
            Customer booker = DBHelper.find(Integer.parseInt(request.queryParams("customerId")), Customer.class);
            int numberToSeat = request.session().attribute("numberToSeat");
            String comment = request.queryParams("additionalComment");
            Booking booking = new Booking(booker, numberToSeat, tempCal, comment);
            DBHelper.makeBooking(booking);
            response.redirect("/");
            return null;
        }, velocityTemplateEngine);
    }
}
