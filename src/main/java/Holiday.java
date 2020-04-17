import java.util.Calendar;

public class Holiday {

    private Holiday() {}

    public static boolean isFourthOfJuly(Calendar calendar) {
        boolean isFourthOfJuly = false;
        if( (calendar.get(Calendar.MONTH) == Calendar.JULY ) &&
                (calendar.get(Calendar.DAY_OF_MONTH) == 4)) {
            isFourthOfJuly = true;
        }
        return isFourthOfJuly;
    }

    public static boolean isLaborDay(Calendar calendar) {
        boolean isLaborDay = false;
        if( (calendar.get(Calendar.MONTH) == Calendar.SEPTEMBER) &&
                (calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 1) &&
                (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY )) {
            isLaborDay = true;
        }
        return isLaborDay;
    }

}
