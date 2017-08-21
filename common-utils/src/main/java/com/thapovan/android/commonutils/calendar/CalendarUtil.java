package com.thapovan.android.commonutils.calendar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;

import com.thapovan.android.commonutils.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {

    private static final String TAG = "CalendarUtil";

    /**
     * Utility method to return the formatted date of an calendar
     * @param dateFormat
     * @param calendar
     * @return
     */
    public static String format(SimpleDateFormat dateFormat,Calendar calendar){
        return dateFormat.format(calendar.getTime());
    }

    /**
     * It will create and show an DatePickerDialog
     * @param context
     * @param calendar
     * @param dateSetListener
     */
    public static void showDatePickerDialog(Context context, Calendar calendar, final DatePickerListener dateSetListener){
        showDatePickerDialog(context, calendar,null, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar calendar1=Calendar.getInstance();
                calendar1.set(Calendar.YEAR,i);
                calendar1.set(Calendar.MONTH,i1);
                calendar1.set(Calendar.DAY_OF_MONTH,i2);
                dateSetListener.onDatePicked(calendar1);
            }
        });
    }

    /**
     * It will create and show an DatePickerDialog
     * @param context
     * @param calendar
     * @param dateSetListener
     */
    public static void showDatePickerDialog(Context context, Calendar calendar,Calendar maxDate, final DatePickerListener dateSetListener){
        showDatePickerDialog(context, calendar, maxDate, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar calendar1=Calendar.getInstance();
                calendar1.set(Calendar.YEAR,i);
                calendar1.set(Calendar.MONTH,i1);
                calendar1.set(Calendar.DAY_OF_MONTH,i2);
                dateSetListener.onDatePicked(calendar1);
            }
        });
    }


    /**
     * It will create and show an DatePickerDialog
     * @param context
     * @param selectedCalendar
     * @param dateSetListener
     */
    public static void showDatePickerDialog(Context context, Calendar selectedCalendar, Calendar maxDate, DatePickerDialog.OnDateSetListener dateSetListener){

        if(selectedCalendar == null) {
            selectedCalendar = Calendar.getInstance();
        }
        DatePickerDialog dialog = new DatePickerDialog(context, R.style.DatePickerStyle
                ,dateSetListener
                ,selectedCalendar.get(Calendar.YEAR)
                ,selectedCalendar.get(Calendar.MONTH)
                ,selectedCalendar.get(Calendar.DAY_OF_MONTH));
        if(maxDate != null){
            dialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());
        }
        dialog.show();
    }

    public static int getYear(Calendar calendar) {
        return calendar.get(Calendar.YEAR);
    }

    public static int getDay(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMonth(Calendar calendar) {
        return calendar.get(Calendar.MONTH);
    }

    public static Calendar toCalendar(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day);
        return calendar;
    }

    public static Calendar toCalendar(String date, SimpleDateFormat dateFormat){
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFormat.parse(date));
            return calendar;
        } catch (ParseException e) {
            Log.e(TAG, "toCalendar: ", e);
            return Calendar.getInstance();
        }
    }

    /**
     * Simplified version of OnDateSetListener
     */
    public interface DatePickerListener{
        void onDatePicked(Calendar calendar);
    }

    public static String getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        dob.set(year, month, day);
        return getAge(dob);
    }

    public static String getAge(Calendar dob){

        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        return String.valueOf(age);
    }

    public static boolean isPastDay(Date date) {
        Calendar c = Calendar.getInstance();

        // set the calendar to start of today
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        // and get that as a Date
        Date today = c.getTime();

        // test your condition, if Date specified is before today
        return date.before(today);
    }

    public static String parse(String dateStr, SimpleDateFormat fromFormat, SimpleDateFormat dateFormat){
        Date date = null;
        try {
            date = fromFormat.parse(dateStr);
            return dateFormat.format(date);
        } catch (ParseException e) {
            Log.e(TAG, "parse: ", e);
        }
        return "";
    }

}
