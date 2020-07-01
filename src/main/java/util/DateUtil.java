package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static  String getCurrTime(){
        SimpleDateFormat sf = new SimpleDateFormat("hh:mm:ss");
       return sf.format(new Date());
    }
}
