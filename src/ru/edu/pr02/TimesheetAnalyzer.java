package ru.edu.pr02;

public class TimesheetAnalyzer {

    
    public static final int WORK_HOURS_PER_DAY = 10;

    public static int[] parseHours(String line) {
        if (line == null || line.trim().isEmpty()) {
            throw new IllegalArgumentException("Строка пуста");
        }

        String[] parts = line.split(";");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Строка должна содержать ровно 5 значений");
        }

        int[] hours = new int[5];
        for (int i = 0; i < 5; i++) {
            try {
                int h = Integer.parseInt(parts[i].trim());
                if (h < 0 || h > 16) {
                    throw new IllegalArgumentException("Значение на позиции " + (i + 1) + " должно быть от 0 до 16");
                }
                hours[i] = h;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Значение на позиции " + (i + 1) + " не является целым числом");
            }
        }
        return hours;
    }

    public static int total(int[] hours) {
        int sum = 0;
        for (int h : hours) {
            sum += h;
        }
        return sum;
    }

    public static double average(int[] hours) {
        if (hours == null || hours.length == 0) return 0.0;
        return (double) total(hours) / hours.length;
    }

    public static int overtimeDays(int[] hours) {
        int count = 0;
        for (int h : hours) {
            if (h > WORK_HOURS_PER_DAY) {
                count++;
            }
        }
        return count;
    }

    public static int weeklyOvertime(int[] hours) {
        int sum = total(hours);
        int weeklyNorm = WORK_HOURS_PER_DAY * 5;
        return Math.max(0, sum - weeklyNorm);
    }
}