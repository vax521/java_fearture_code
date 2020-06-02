package date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

public class LocalDateExample {
    public static void main(String[] args) throws InterruptedException {
        //LocalDate
        LocalDate localDate = LocalDate.of(2020,6,2);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfWeek());
        System.out.println(LocalDate.now());

        //LocalTime
        LocalTime localTime = LocalTime.of(13,45,20);
        int hour =  localTime.getHour();
        int min = localTime.getMinute();
        int second = localTime.getSecond();
        System.out.println(hour+":"+min+":"+second);
        System.out.println(LocalTime.now());

        LocalDate localDate1 = LocalDate.parse("2019-12-27");
        LocalTime localTime1 = LocalTime.parse("12:45:25");
        System.out.println(localDate1);
        System.out.println(localTime1);

        //LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.of(2020,2,20,20,20,2);
        System.out.println(localDateTime);

        LocalDateTime localDateTimeCombine = LocalDateTime.of(localDate,localTime);
        System.out.println(localDateTimeCombine);
        LocalDateTime localDateAtTime = localDate.atTime(localTime);
        System.out.println(localDateAtTime);
        LocalDateTime localTimeAtDate = localTime.atDate(localDate);
        System.out.println(localTimeAtDate);

        //机器时间
        System.out.println(Instant.now());
        Instant instant1 =   Instant.now();
        Thread.sleep(1000);
        Instant instant2 =   Instant.now();

        Duration duration =  Duration.between(instant1,instant2);
        System.out.println(duration);
        Duration duration1 =  Duration.between(localTime,localTime1);
        System.out.println(duration1);
        Duration duration2 =  Duration.between(localDateTime,localDateTimeCombine);
        System.out.println(duration2);

       // 如果你已经有一个LocalDate对象，想要创建它的一个修改版，最直接也最简单的方法是使用withAttribute方法。withAttribute方法会创建对象的一个副本，并按照需要修改它的属性。
        LocalDate date1 = LocalDate.of(2020,6,20);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        System.out.println(date1.format(dateTimeFormatter));
        LocalDate date2 = date1.withYear(2001);
        //不改变date1对象
        System.out.println(date1);
        System.out.println(date2);
        //TemporalAdjusters类的静态工厂方法
        System.out.println(date1.with(nextOrSame(DayOfWeek.SUNDAY)));
        System.out.println(date1.with(lastDayOfMonth()));
        //使用自定义的TemporalAdjusters
        System.out.println(date1.with(new NextWorkingDays()));
        //另一种实现方法
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.ofDateAdjuster(
                temporal ->{
                    DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
                    int dayAdd = 1;
                    if(dayOfWeek == DayOfWeek.FRIDAY){
                        dayAdd = 3;
                    }else if(dayOfWeek == DayOfWeek.SUNDAY){
                        dayAdd = 2;
                    }
                    return temporal.plus(dayAdd, ChronoUnit.DAYS);
                }
        );
        System.out.println(date1.with(temporalAdjuster));
        System.out.println(date1.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(date1.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(localDateAtTime.format(DateTimeFormatter.ISO_DATE_TIME));

        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(date1.format(dateTimeFormatter1));

        //本地化DateTimeFormatter
        DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        System.out.println(date1.format(italianFormatter));

        DateTimeFormatter italianFormatter2 = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);

        System.out.println(date1.format(italianFormatter2));

    }


}
