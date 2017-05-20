/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elastic.explorer.framework;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author andre
 */
public class MainQuickTests {
    public static void main(String [] args) throws ParseException{
        DateFormat format = new SimpleDateFormat("dd MMM yyy", Locale.ENGLISH);
        Date date = null;
        String dateString = "10 Jan 2017";
        date = format.parse(dateString);
        System.out.println();
    }
}
