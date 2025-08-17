class Time {
    int hours;
    int minutes;
    int seconds;

    Time(int h, int m, int s) {
        hours = h;
        minutes = m;
        seconds = s;
    }


    void displayTime() {
        System.out.printf("%02d:%02d:%02d\n", hours, minutes, seconds);
    }

    Time addTime(Time t) {
        int totalSeconds = seconds + t.seconds;
        int totalMinutes = minutes + t.minutes + totalSeconds / 60;
        int totalHours = hours + t.hours + totalMinutes / 60;

        int newSeconds = totalSeconds % 60;
        int newMinutes = totalMinutes % 60;
        int newHours = totalHours;

        return new Time(newHours, newMinutes, newSeconds);
    }
}

public class Eight {
    public static void main(String[] args) {
        Time t1 = new Time(2, 45, 50);
        Time t2 = new Time(1, 20, 30);

        Time result = t1.addTime(t2);
        System.out.print("Time 1: ");
        t1.displayTime();
        System.out.print("Time 2: ");
        t2.displayTime();
        System.out.print("Added Time: ");
        result.displayTime();
    }
}
