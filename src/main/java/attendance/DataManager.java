package attendance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import java.time.LocalDateTime; // Import LocalDateTime
import attendance.LocalDateTimeAdapter;

public class DataManager {
    private final String dataFilePath;
    private final Gson gson;

    public DataManager(String dataFilePath) {
        this.dataFilePath = dataFilePath;
        // Configure Gson to handle LocalDate, LocalTime, and LocalDateTime
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()) // Register LocalDateTimeAdapter
                .setPrettyPrinting()
                .create();
    }

    public void saveData(DataStorage data) {
        System.out.println(ConsoleColors.BLUE + "[DEBUG] Attempting to save data to file: " + dataFilePath + ConsoleColors.RESET);
        try {
            File dataFile = new File(dataFilePath);
            File parentDir = dataFile.getParentFile();

            // 1. Check if parent directory exists, if not, create it.
            if (parentDir != null && !parentDir.exists()) {
                System.out.println(ConsoleColors.YELLOW + "[DEBUG] Parent directory does not exist. Creating: " + parentDir.getAbsolutePath() + ConsoleColors.RESET);
                if (!parentDir.mkdirs()) {
                    System.err.println(ConsoleColors.RED_BOLD + "CRITICAL ERROR: Could not create parent directory. Please check storage permissions." + ConsoleColors.RESET);
                    return;
                }
            }

            // 2. Check for write permissions on the directory.
            if (parentDir == null || !parentDir.canWrite()) {
                System.err.println(ConsoleColors.RED_BOLD + "CRITICAL ERROR: Cannot write to directory: " + (parentDir != null ? parentDir.getAbsolutePath() : "null") + ConsoleColors.RESET);
                System.err.println(ConsoleColors.YELLOW + "On Termux, you may need to run the 'termux-setup-storage' command and grant permissions." + ConsoleColors.RESET);
                return;
            }

            // 3. Try to write to the file.
            try (FileWriter writer = new FileWriter(dataFile)) {
                gson.toJson(data, writer);
                System.out.println(ConsoleColors.GREEN + "Data saved successfully to " + dataFilePath + ConsoleColors.RESET);
            }

        } catch (IOException e) {
            System.err.println(ConsoleColors.RED_BOLD + "[DEBUG] CRITICAL: An I/O error occurred while saving data to " + dataFilePath + ConsoleColors.RESET);
            System.err.println(ConsoleColors.RED + "Error Message: " + e.getMessage() + ConsoleColors.RESET);
            System.err.println(ConsoleColors.YELLOW + "This could be a file permission issue. On Termux, please ensure you have run 'termux-setup-storage' and granted storage access." + ConsoleColors.RESET);
            System.err.println(ConsoleColors.RED + "Stack trace:" + ConsoleColors.RESET);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(ConsoleColors.RED_BOLD + "[DEBUG] CRITICAL: An unexpected error occurred during save." + ConsoleColors.RESET);
            e.printStackTrace();
        }
    }

    public DataStorage loadData() {
        try (FileReader reader = new FileReader(dataFilePath)) {
            Type dataType = new TypeToken<DataStorage>(){}.getType();
            DataStorage data = gson.fromJson(reader, dataType);
            if (data == null) {
                System.out.println(ConsoleColors.YELLOW + "Data file empty or malformed. Initializing new DataStorage." + ConsoleColors.RESET);
                return new DataStorage();
            }
    
            return data;
        } catch (IOException e) {
            System.out.println(ConsoleColors.YELLOW + "Data file not found or error reading ("+ dataFilePath +"). Creating new DataStorage." + ConsoleColors.RESET);
            return new DataStorage();
        }
    }
}