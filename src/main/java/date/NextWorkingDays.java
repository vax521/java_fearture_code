package date;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextWorkingDays implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
        DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int dayAdd = 1;
        if(dayOfWeek == DayOfWeek.FRIDAY){
            dayAdd = 3;
        }else if(dayOfWeek == DayOfWeek.SUNDAY){
            dayAdd = 2;
        }
        return temporal.plus(dayAdd, ChronoUnit.DAYS);
    }
}
