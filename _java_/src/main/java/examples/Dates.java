package examples;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dates {
    private static final int Q1 = 1;
    private static final int Q2 = 2;
    private static final int Q3 = 3;
    private static final int Q4 = 4;

    private static final Map<Integer, int[]> quarters = new HashMap<>();

    static {
        int[] q1 = {1, 2, 3};
        int[] q2 = {4, 5, 6};
        int[] q3 = {7, 8, 9};
        int[] q4 = {10, 11, 12};
        quarters.put(Q1, q1);
        quarters.put(Q2, q2);
        quarters.put(Q3, q3);
        quarters.put(Q4, q4);
    }

    public static void main(String[] args) {
        List<LocalDateTime> dates = new ArrayList<>();
        dates.add(LocalDateTime.of(2020, 1, 25, 12, 24));
        dates.add(LocalDateTime.of(2020, 3, 14, 12, 24));
        dates.add(LocalDateTime.of(2020, 4, 28, 12, 24));
        dates.add(LocalDateTime.of(2020, 5, 1, 12, 24));
        dates.add(LocalDateTime.of(2020, 12, 31, 12, 24));
        dates.add(LocalDateTime.of(2018, 6, 15, 12, 24));
        dates.add(LocalDateTime.of(2021, 1, 5, 12, 24));

        for (LocalDateTime date : dates) {
            System.out.println("----- currentDate = " + date + "-----");

            System.out.println();
            System.out.println("Day");
            DatesPair datesForPreviousDay = getDatesForPreviousDay(date);
            System.out.println(datesForPreviousDay.startDate);
            System.out.println(datesForPreviousDay.endDate);

            System.out.println();
            System.out.println("Week");
            DatesPair datesForPreviousWeek = getDatesForPreviousWeek(date);
            System.out.println(datesForPreviousWeek.startDate);
            System.out.println(datesForPreviousWeek.endDate);

            System.out.println();
            System.out.println("Month");
            DatesPair datesForPreviousMonth = getDatesForPreviousMonth(date);
            System.out.println(datesForPreviousMonth.startDate);
            System.out.println(datesForPreviousMonth.endDate);

            System.out.println();
            System.out.println("Quarter");
            DatesPair datesForPreviousQuarter = getDatesForPreviousQuarter(date);
            System.out.println(datesForPreviousQuarter.startDate);
            System.out.println(datesForPreviousQuarter.endDate);

            System.out.println();
            System.out.println("Year");
            DatesPair datesForPreviousYear = getDatesForPreviousYear(date);
            System.out.println(datesForPreviousYear.startDate);
            System.out.println(datesForPreviousYear.endDate);

            System.out.println("--------------------------------------");
        }

        System.out.println(getListOfDays(dates.get(2), dates.get(3)));

        System.out.println(LocalDateTime.now(Clock.systemUTC()));
        System.out.println(LocalDateTime.now(ZoneId.of("UTC")));
    }

    private static List<LocalDate> getListOfDays(LocalDateTime start, LocalDateTime end) {
        LocalDate startWithoutTime = start.toLocalDate();
        LocalDate endWithoutTime = end.toLocalDate();

        List<LocalDate> listOfDays = new ArrayList<>();
        if (startWithoutTime.isAfter(endWithoutTime)) {
            return listOfDays;
        }

        listOfDays.add(startWithoutTime);
        if (startWithoutTime.isEqual(endWithoutTime)) {
            return listOfDays;
        }

        LocalDate temp = startWithoutTime;
        while (temp.isBefore(endWithoutTime)) {
            temp = temp.plusDays(1);
            listOfDays.add(temp);
        }

        return listOfDays;
    }

    private static DatesPair getDatesForPreviousDay(LocalDateTime date) {
        LocalDateTime startDate = date
                .minusHours(date.getHour() - 1)
                .minusDays(1)
                .withHour(0).withMinute(0);

        LocalDateTime endDate = startDate.plusDays(1).minusMinutes(1);
        return new DatesPair(startDate, endDate);
    }

    private static DatesPair getDatesForPreviousWeek(LocalDateTime date) {
        LocalDateTime startDate = date
                .minusDays(date.getDayOfWeek().getValue() - 1)
                .minusWeeks(1)
                .withHour(0).withMinute(0);

        LocalDateTime endDate = startDate.plusWeeks(1).minusMinutes(1);
        return new DatesPair(startDate, endDate);
    }

    private static DatesPair getDatesForPreviousMonth(LocalDateTime date) {
        LocalDateTime startDate = date
                .minusDays(date.getDayOfMonth() - 1)
                .minusMonths(1)
                .withHour(0).withMinute(0);

        LocalDateTime endDate = startDate.plusMonths(1).minusMinutes(1);
        return new DatesPair(startDate, endDate);
    }

    private static DatesPair getDatesForPreviousQuarter(LocalDateTime date) {
        int prevQuarterValue = getPrevQuarterByMonth(date.getMonthValue());
        int[] prevQuarter = quarters.get(prevQuarterValue);
        int year = prevQuarterValue == Q4 ? date.getYear() - 1 : date.getYear();

        LocalDateTime startDate = LocalDateTime.of(year, prevQuarter[0], 1, 0, 0);
        LocalDateTime endDate = startDate.withMonth(prevQuarter[2]).plusMonths(1).minusMinutes(1);
        return new DatesPair(startDate, endDate);
    }

    private static DatesPair getDatesForPreviousYear(LocalDateTime date) {
        LocalDateTime startDate = date
                .minusDays(date.getDayOfYear() - 1)
                .minusYears(1)
                .withHour(0).withMinute(0);

        LocalDateTime endDate = startDate.plusYears(1).minusMinutes(1);
        return new DatesPair(startDate, endDate);
    }

    private static int getPrevQuarterByMonth(int month) {
        int prevQuarter = getQuarterByMonth(month) - 1;
        if (prevQuarter == 0) {
            return Q4;
        }
        return prevQuarter;
    }

    private static int getQuarterByMonth(int month) {
        month--;
        return (month >= Calendar.JANUARY && month <= Calendar.MARCH) ? Q1 :
                (month >= Calendar.APRIL && month <= Calendar.JUNE) ? Q2 :
                        (month >= Calendar.JULY && month <= Calendar.SEPTEMBER) ? Q3 :
                                Q4;
    }

    public static class DatesPair {

        private final LocalDateTime startDate;

        private final LocalDateTime endDate;

        public DatesPair(LocalDateTime startDate, LocalDateTime endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }
    }
}
