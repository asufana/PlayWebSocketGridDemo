package ddd.application.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日付ユーティリティ
 */
public class DateUtil {
    
    private DateUtil() { }
    
    public static Date toDate(final String yyyymmdd) {
        return toDate(yyyymmdd, "0000");
    }
    
    public static Date toDate(final String yyyymmdd, final String hhmm) {
        try {
            return new SimpleDateFormat("yyyyMMdd HHmm").parse(yyyymmdd + " " + hhmm);
        } catch(ParseException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Date yyyymmddDate(final Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    
    public static String yyyymmdd(final Date date){
        return dateFormat("yyyyMMdd", date);
    }
    
    public static String yyyymm(final Date date){
        return dateFormat("yyyyMM", date);
    }
    
    private static String dateFormat (final String format, final Date date) {
        if (date == null) throw new IllegalArgumentException();
        return new SimpleDateFormat(format).format(date);
    }
    
    /**
     * 今日の取得
     */
    public static Date today() {
        return yyyymmddDate(new Date());
    }
    
    /**
     * 日付計算
     * @param baseDate 基準日
     * @param diff 追加・削除日数
     */
    public static Date dateCalc(final Date baseDate, final int diff){
        Calendar base = Calendar.getInstance();
        base.setTime(baseDate);
        base.add(Calendar.DATE, diff);
        return yyyymmddDate(base.getTime());
    }
    
    /**
     * 月末の取得
     */
    public static Date getEndOfMonth(final Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(getYear(date)));
        calendar.set(Calendar.MONTH, Integer.parseInt(getMonth(date)) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        
        calendar.add(Calendar.MONTH, 1); //翌月
        calendar.set(Calendar.DAY_OF_MONTH, 1); //翌月1日
        calendar.add(Calendar.DAY_OF_MONTH, -1); //月末日
        return calendar.getTime();
    }
    
    public static String getYear(final Date date) {
        return new SimpleDateFormat("yyyy").format(date);
    }
    
    public static String getMonth(final Date date) {
        return new SimpleDateFormat("MM").format(date);
    }
    
    public static String getDay(final Date date) {
        return new SimpleDateFormat("dd").format(date);
    }
}
