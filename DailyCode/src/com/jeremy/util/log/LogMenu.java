package com.jeremy.util.log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jeremy
 * @time   上午10:18:31 2014年12月30日
 * @todo   如果当天树级目录不存在，则生成目录
 */
public class LogMenu {
    public static void makeDirs() {
        Date date = new Date();
        String path = "D:/work/" + new SimpleDateFormat("yyyy/MM/dd").format(date);
        File f = new File(path);
        if (!f.exists())
            f.mkdirs();
    }
    
    public static void main(String[] args) {
        LogMenu.makeDirs();
    }
    
}
