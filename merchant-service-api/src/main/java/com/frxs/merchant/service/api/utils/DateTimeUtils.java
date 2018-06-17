package com.frxs.merchant.service.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wushuo
 * @version $Id: DateTimeUtils.java,v 0.1 2018年03月26日 20:16 $Exp
 */
public class DateTimeUtils {

    public static String getTime(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(date);
        return dateString;
    }
}
