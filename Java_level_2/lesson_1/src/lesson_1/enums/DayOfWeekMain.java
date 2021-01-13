package lesson_1.enums;

public class DayOfWeekMain {

    public static void main(String[] args) {
        System.out.println(getWorkingHours(DayOfWeek.Понедельник));
    }

    static String getWorkingHours(DayOfWeek dayOfWeek) {
        int hours = (DayOfWeek.Суббота.ordinal() - dayOfWeek.ordinal()) * 8;
        if (hours <= 0) return "Сегодня выходной!";
        else return "До конца недели осталось " + hours + " рабочих часов";
    }
}
