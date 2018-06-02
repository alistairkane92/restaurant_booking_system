import com.kane.restaurant.models.Booking;
import com.kane.restaurant.models.Customer;
import com.kane.restaurant.models.Table;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Runner {
    public static void main(String[] args) {
        Customer customer1 = new Customer("ally@codeclan.com", "Ally");
        Customer customer2 = new Customer("alan@codeclan.com", "Alan");
        Customer customer3 = new Customer("upul@codeclan.com", "Upul");

        Table table1 = new Table(4);
        Table table2 = new Table(4);
        Table table3 = new Table(2);
        Table table4 = new Table(4);
        Table table5 = new Table(4);
        Table table6 = new Table(6);
        Table table7 = new Table(12);

        GregorianCalendar date1 = new GregorianCalendar();
        date1.set(2018, Calendar.OCTOBER, 20);
        Booking booking1 = new Booking(customer1, 5, date1);
    }
}
