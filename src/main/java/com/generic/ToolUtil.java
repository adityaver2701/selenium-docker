package com.generic;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.stream.Collectors;

public class ToolUtil {
    public static final String TODAY = "today";
    public static final String YESTERDAY = "yesterday";
    public static final String LAST_WEEK = "last week";
    public static final String LAST_MONTH = "last month";
    public static final String LAST_YEAR = "last year";
    public static final String WEEK_TO_DATE = "week to date";
    public static final String MONTH_TO_DATE = "month to date";
    public static final long WAIT = 10;



    public static ArrayList<String> readFileLineByLine(File file) {
        ArrayList<String> lines = new ArrayList<String>();
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                lines.add(strLine);
            }
            br.close();
        } catch (IOException ioe) {

        }
        return lines;
    }

    public static String getDateFromStr(String range) {
        String dateRange = "dates[";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        Calendar calendar = Calendar.getInstance();
        if (range.equals(TODAY)) {
            dateRange += sdf.format(calendar.getTime()) + "," + sdf.format(calendar.getTime()) +
                    "]";
        } else if (range.equals(YESTERDAY)) {
            calendar.add(Calendar.DATE, -1);
            dateRange += sdf.format(calendar.getTime()) + "," + sdf.format(calendar.getTime()) +
                    "]";
        } else if (range.equals(LAST_WEEK)) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            calendar.add(Calendar.DATE, -7);
            dateRange += sdf.format(calendar.getTime()) + ",";
            calendar.add(Calendar.DATE, 6);
            dateRange += sdf.format(calendar.getTime()) + "]";
        } else if (range.equals(LAST_MONTH)) {
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.MONTH, -1);
            dateRange += sdf.format(calendar.getTime()) + ",";
            calendar.add(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE) - 1);
            dateRange += sdf.format(calendar.getTime()) + "]";
        } else if (range.equals(LAST_YEAR)) {
            calendar.set(Calendar.DAY_OF_YEAR, 1);
            calendar.add(Calendar.YEAR, -1);
            dateRange += sdf.format(calendar.getTime()) + ",";
            calendar.add(Calendar.YEAR, 1);
            calendar.add(Calendar.DATE, -1);
            dateRange += sdf.format(calendar.getTime()) + "]";
        } else if (range.equals(WEEK_TO_DATE)) {
            String today = sdf.format(calendar.getTime());
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            dateRange += sdf.format(calendar.getTime()) + "," + today + "]";
        } else if (range.equals(MONTH_TO_DATE)) {
            String today = sdf.format(calendar.getTime());
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            dateRange += sdf.format(calendar.getTime()) + "," + today + "]";
        }
        return dateRange;
    }

    public static boolean removeFolder(File removeFile) {

        if (removeFile.exists()) {
            File[] files = removeFile.listFiles();
            if (files != null) {
                for (File f : files) {
                    f.delete();
                }
            }
            return removeFile.delete();
        }
        return false;
    }

    public static String getDateStamp() {
        String stamp = new SimpleDateFormat("yyMMdd").format(Calendar.getInstance().getTime());
        return stamp;
    }

    public static String getTimeStamp() {
        String stamp = new SimpleDateFormat("yyMMddkkmm").format(Calendar.getInstance().getTime());
        return stamp;
    }
    public static String getTimeSecondsStamp() {
        String stamp = new SimpleDateFormat("yyMMddkkmmss").format(Calendar.getInstance().getTime());
        return stamp;
    }

    public static String getTodayDate() {
        String startDate = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance()
                .getTime());
        return startDate;
    }
    public static String getCurrentTime(){
        String currentTime = new SimpleDateFormat("hh:mm:ss a").format(Calendar.getInstance()
                .getTime());
        return currentTime;
    }
    public static String getNextDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        if(cal.get(Calendar.DAY_OF_WEEK)==1){
            cal.add(Calendar.DATE, 1);
        }
        if(cal.get(Calendar.DAY_OF_WEEK)==7){
            cal.add(Calendar.DATE, 2);
        }
        String nextDate = sdf.format(cal.getTime());
        return nextDate;
    }
    public static String getPreviousDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        if(cal.get(Calendar.DAY_OF_WEEK)==1){
            cal.add(Calendar.DATE, -2);
        }
        if(cal.get(Calendar.DAY_OF_WEEK)==7){
            cal.add(Calendar.DATE, -1);
        }
        String nextDate = sdf.format(cal.getTime());
        return nextDate;
    }
    public static Integer getRandomNumber() {

        return 100000 + new Random().nextInt(900000);
    }

    public static String generaterandString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String randomString = new Random().ints(length, 0, chars.length()).mapToObj(i -> "" + chars.charAt(i)).collect(Collectors.joining());
        return randomString;
    }

    public static String getRandomEmail() {
        final String email = "qubitaitesting"+"+" + ToolUtil.getTimeSecondsStamp() + "@mailinator.com";
        return email;
    }

    public static String getRandomPhoneNum() {
        String randomNumbers = RandomStringUtils.randomNumeric(5);
        String phNo = "79393"+randomNumbers;
        return phNo;
    }
    public Logger log() {
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
    }

}
