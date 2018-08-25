package com.highbury5.blog.util;

import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUtil {

    public  String getDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(date.toString());
        System.out.println(formatter.format(date));
        return formatter.format(date);

    }

}
