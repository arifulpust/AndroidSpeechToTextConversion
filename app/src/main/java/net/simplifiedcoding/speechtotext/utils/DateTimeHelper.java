package net.simplifiedcoding.speechtotext.utils;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by bipulkhan on 4/25/17.
 */

public class DateTimeHelper {


    /**
     * method to get system date and time
     *
     * @param type
     * @return date_time
     */
    public static String getSystemDateTime(String type) {

        SimpleDateFormat dateFormat = null;

        if (type.equals("date_time")) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else if (type.equals("time")) {
            dateFormat = new SimpleDateFormat("HH:mm:ss");
        } else if (type.equals("date")) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }else if (type.equals("date_time_b")) {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        }else if (type.equals("date_b")) {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        }else if (type.equals("mmddyy")) {
            dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        }else if (type.equals("galary")) {
            dateFormat = new SimpleDateFormat("yyyy MM dd HH mm ss");
        }

        // get current date time with Calendar
        // ()
        Calendar cal = Calendar.getInstance();

        String date_time = dateFormat.format(cal.getTime());

        return date_time;
    }
    //DateTimeHelper.getUserDateTime("","date")
    public static String getUserDateTime(String date, String type) {

        SimpleDateFormat dateFormat = null;
        String date_time = null;
        if(type == "date_time") {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateTime = null;
            try {
                dateTime = dateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            date_time = dateFormat.format(dateTime.getTime());
        } else if(type == "date") {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date dateTime = null;
            try {
                dateTime = dateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            date_time = dateFormat.format(dateTime.getTime());

        }
        else if(type == "time") {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateTime = null;
            try {
                dateTime = dateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dateFormat = new SimpleDateFormat("HH:mm");
            date_time = dateFormat.format(dateTime.getTime());

        }

        else if(type == "full_month") {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateTime = null;
            try {
                dateTime = dateFormat.parse(date);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            java.text.DateFormat dateFormat1 = java.text.DateFormat.getDateInstance(java.text.DateFormat.LONG);//new SimpleDateFormat("EEE, dd MMM yyyy");
            date_time = dateFormat1.format(dateTime.getTime());
        } else if(type == "day_month") {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateTime = null;
            try {
                dateTime = dateFormat.parse(date);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            Format formatter = new SimpleDateFormat("MM");
            //java.text.DateFormat dateFormat1 = java.text.DateFormat.getDateInstance(java.text.DateFormat.LONG);//new SimpleDateFormat("EEE, dd MMM yyyy");
            String s1 = getNameOfMonth(Integer.valueOf(formatter.format(dateTime.getTime())) - 1);
            Format format = new SimpleDateFormat("dd");
            String s = format.format(dateTime.getTime());
            Format format2 = new SimpleDateFormat("yyyy");
            String s2 = format2.format(dateTime.getTime());
            date_time = s + " " + s1 + " "+ s2;

        } else if(type == "day") {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateTime = null;
            try {
                dateTime = dateFormat.parse(date);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            Format formatter = new SimpleDateFormat("MM");
            //java.text.DateFormat dateFormat1 = java.text.DateFormat.getDateInstance(java.text.DateFormat.LONG);//new SimpleDateFormat("EEE, dd MMM yyyy");
            String s1 = getNameOfMonth(Integer.valueOf(formatter.format(dateTime.getTime())) - 1);
            Format format = new SimpleDateFormat("dd");
            String s = format.format(dateTime.getTime());
            Format format2 = new SimpleDateFormat("yyyy");
            String s2 = format2.format(dateTime.getTime());
            date_time = s;
        }
        return date_time;
    }




    /**
     * This method returns the name of the Month
     *
     * @param monthOfYear - integer value of the month returned by the system
     * @return - name of the month
     */
    public static String getNameOfMonth(int monthOfYear) {

        String nameOfMonth;

        switch (monthOfYear) {
            case 0:
                nameOfMonth = "January";
                break;
            case 1:
                nameOfMonth = "February";
                break;
            case 2:
                nameOfMonth = "March";
                break;
            case 3:
                nameOfMonth = "April";
                break;
            case 4:
                nameOfMonth = "May";
                break;
            case 5:
                nameOfMonth = "June";
                break;
            case 6:
                nameOfMonth = "July";
                break;
            case 7:
                nameOfMonth = "August";
                break;
            case 8:
                nameOfMonth = "September";
                break;
            case 9:
                nameOfMonth = "October";
                break;
            case 10:
                nameOfMonth = "November";
                break;
            case 11:
                nameOfMonth = "December";
                break;
            default:
                nameOfMonth = "?";
                break;
        }

        return nameOfMonth;
    }
    public static String getNameOfMonthSort(int monthOfYear) {

        String nameOfMonth;

        switch (monthOfYear) {
            case 0:
                nameOfMonth = "Jan";
                break;
            case 1:
                nameOfMonth = "Feb";
                break;
            case 2:
                nameOfMonth = "Mar";
                break;
            case 3:
                nameOfMonth = "Apr";
                break;
            case 4:
                nameOfMonth = "May";
                break;
            case 5:
                nameOfMonth = "June";
                break;
            case 6:
                nameOfMonth = "July";
                break;
            case 7:
                nameOfMonth = "Aug";
                break;
            case 8:
                nameOfMonth = "Sept";
                break;
            case 9:
                nameOfMonth = "Oct";
                break;
            case 10:
                nameOfMonth = "Nov";
                break;
            case 11:
                nameOfMonth = "Dec";
                break;
            default:
                nameOfMonth = "?";
                break;
        }

        return nameOfMonth;
    }

}