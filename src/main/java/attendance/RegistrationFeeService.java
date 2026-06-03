package attendance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RegistrationFeeService {
    private DataStorage dataStorage;
    private DataManager dataManager;

    public RegistrationFeeService(DataStorage dataStorage, DataManager dataManager) {
        this.dataStorage = dataStorage;
        this.dataManager = dataManager;
    }

    public void addRegistrationFee(Semester semester, RegistrationFee.FeeType feeType, String description, double amount) {
        dataStorage.getRegistrationFees().computeIfAbsent(semester, k -> new ArrayList<>())
                   .add(new RegistrationFee(semester, feeType, description, amount));
        dataManager.saveData(dataStorage);
        System.out.println(ConsoleColors.GREEN + "Registration fee added successfully for " + semester.getDisplayValue() + ": " + description + " - " + amount + ConsoleColors.RESET);
    }

    public List<RegistrationFee> getRegistrationFeesForSemester(Semester semester) {
        return dataStorage.getRegistrationFees().getOrDefault(semester, new ArrayList<>());
    }

    public void editRegistrationFee(Semester semester, RegistrationFee oldFee, RegistrationFee.FeeType newFeeType, String newDescription, double newAmount) {
        List<RegistrationFee> fees = dataStorage.getRegistrationFees().get(semester);
        if (fees != null) {
            fees.remove(oldFee); // Remove by object equality
            fees.add(new RegistrationFee(semester, newFeeType, newDescription, newAmount)); // Add new version
            dataManager.saveData(dataStorage);
            System.out.println(ConsoleColors.GREEN + "Registration fee updated successfully for " + semester.getDisplayValue() + "." + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.RED + "Error: Semester not found for fee update." + ConsoleColors.RESET);
        }
    }

    public void removeRegistrationFee(Semester semester, RegistrationFee feeToRemove) {
        List<RegistrationFee> fees = dataStorage.getRegistrationFees().get(semester);
        if (fees != null) {
            fees.remove(feeToRemove);
            dataManager.saveData(dataStorage);
            System.out.println(ConsoleColors.GREEN + "Registration fee removed successfully for " + semester.getDisplayValue() + "." + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.RED + "Error: Semester not found for fee removal." + ConsoleColors.RESET);
        }
    }

    public double calculateTotalFeesForSemester(Semester semester) {
        return dataStorage.getRegistrationFees().getOrDefault(semester, new ArrayList<>())
                          .stream()
                          .mapToDouble(RegistrationFee::getAmount)
                          .sum();
    }

    public double calculateOverallTotalFees() {
        return dataStorage.getRegistrationFees().values().stream()
                          .flatMap(List::stream)
                          .mapToDouble(RegistrationFee::getAmount)
                          .sum();
    }
}