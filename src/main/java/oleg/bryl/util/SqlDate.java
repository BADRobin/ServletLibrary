package oleg.bryl.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SqlDate {

    public static Date currentDateAndTime() {
        return new Date(Calendar.getInstance().getTime().getTime());
    }

    public static Date stringToDate(String date) {
        Date sqlDate = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            sqlDate = new Date(dateFormat.parse(date).getTime());
            return sqlDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sqlDate;
    }

    private SqlDate() {
    }
}
