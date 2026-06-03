package attendance;

import java.time.LocalDate;
import java.time.LocalTime;

public class DailyBuy {
    private String itemName;
    private double price;
    private LocalDate date;
    private LocalTime time;
    private String status; // Added status field

    public DailyBuy(String itemName, double price, LocalDate date, LocalTime time) {
        this.itemName = itemName;
        this.price = price;
        this.date = date;
        this.time = time;
        this.status = "Pending"; // Default status
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getStatus() {
        if (status == null) {
            return "Pending";
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DailyBuy dailyBuy = (DailyBuy) o;

        if (Double.compare(dailyBuy.price, price) != 0) return false;
        if (itemName != null ? !itemName.equals(dailyBuy.itemName) : dailyBuy.itemName != null) return false;
        if (date != null ? !date.equals(dailyBuy.date) : dailyBuy.date != null) return false;
        if (time != null ? !time.equals(dailyBuy.time) : dailyBuy.time != null) return false;
        return status != null ? status.equals(dailyBuy.status) : dailyBuy.status == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = itemName != null ? itemName.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DailyBuy{" +
                "itemName='" + itemName + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", time=" + time +
                ", status='" + getStatus() + '\'' +
                '}';
    }
}