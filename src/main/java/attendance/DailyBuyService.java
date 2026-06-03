package attendance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class DailyBuyService {
    private DataStorage dataStorage;
    private DataManager dataManager;

    public DailyBuyService(DataStorage dataStorage, DataManager dataManager) {
        this.dataStorage = dataStorage;
        this.dataManager = dataManager;
    }

    public void addDailyBuy(String itemName, double price, LocalDate date, LocalTime time) {
        dataStorage.getDailyBuys().add(new DailyBuy(itemName, price, date, time));
        dataManager.saveData(dataStorage);
        System.out.println(ConsoleColors.GREEN + "Daily buy record added successfully: " + itemName + " - " + price + " on " + date + ConsoleColors.RESET);
    }

    public List<DailyBuy> getAllDailyBuys() {
        return dataStorage.getDailyBuys();
    }

    public void editDailyBuy(DailyBuy oldBuy, String newItemName, double newPrice, LocalDate newDate, LocalTime newTime) {
        oldBuy.setItemName(newItemName);
        oldBuy.setPrice(newPrice);
        oldBuy.setDate(newDate);
        oldBuy.setTime(newTime);
        dataManager.saveData(dataStorage);
        System.out.println(ConsoleColors.GREEN + "Daily buy record updated successfully." + ConsoleColors.RESET);
    }

    public void removeDailyBuy(DailyBuy buyToRemove) {
        dataStorage.getDailyBuys().remove(buyToRemove);
        dataManager.saveData(dataStorage);
        System.out.println(ConsoleColors.GREEN + "Daily buy record removed successfully." + ConsoleColors.RESET);
    }
    
    public double calculateTotalExpenditure(List<DailyBuy> buys) {
        return buys.stream().mapToDouble(DailyBuy::getPrice).sum();
    }

    public List<DailyBuy> getDailyBuysForDate(LocalDate date) {
        return dataStorage.getDailyBuys().stream()
                .filter(buy -> buy.getDate().isEqual(date))
                .collect(Collectors.toList());
    }
}