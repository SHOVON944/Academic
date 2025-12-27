/*
Space Mission Control (Fun / Creative) 
 A rocket launch simulation throws: 
o FuelShortageException if fuel < required level. 
o EngineFailureException if engine test fails. 
o WeatherNotSuitableException if launch conditions are unsafe. 
 Show how multiple exceptions are caught and handled before launch. 
*/

import java.util.*;

class FuelShortageException extends Exception {
    FuelShortageException(String message) {
        super(message);
    }
}

class EngineFailureException extends Exception {
    EngineFailureException(String message) {
        super(message);
    }
}

class WeatherNotSuitableException extends Exception {
    WeatherNotSuitableException(String message) {
        super(message);
    }
}

class Rocket {
    double fuelLevel; // in liters
    boolean engineTestPassed;
    String weather; // "clear", "stormy", "rainy"

    Rocket(double fuelLevel, boolean engineTestPassed, String weather) {
        this.fuelLevel = fuelLevel;
        this.engineTestPassed = engineTestPassed;
        this.weather = weather.toLowerCase();
    }

    void preLaunchCheck() throws FuelShortageException, EngineFailureException, WeatherNotSuitableException {
        if (fuelLevel < 1000) { // assume 1000 liters needed
            throw new FuelShortageException("Fuel insufficient! Required: 1000L, Available: " + fuelLevel + "L");
        }

        if (!engineTestPassed) {
            throw new EngineFailureException("Engine test failed! Check engine systems.");
        }

        if (!weather.equals("clear")) {
            throw new WeatherNotSuitableException("Weather not suitable for launch: " + weather);
        }

        System.out.println("All pre-launch checks passed! Ready for liftoff.");
    }
}

public class P_40 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter fuel level (liters): ");
        double fuel = sc.nextDouble();
        sc.nextLine(); // consume newline

        System.out.print("Did engine pass test? (yes/no): ");
        String engineInput = sc.nextLine();
        boolean engineTest = engineInput.equalsIgnoreCase("yes");

        System.out.print("Enter current weather (clear/rainy/stormy): ");
        String weather = sc.nextLine();

        Rocket rocket = new Rocket(fuel, engineTest, weather);

        System.out.println("\n--- Initiating Pre-Launch Checks ---");

        try {
            rocket.preLaunchCheck();
            System.out.println("Rocket has launched successfully!");
        } 
        catch (FuelShortageException e) {
            System.out.println("Fuel Error: " + e.getMessage());
        } 
        catch (EngineFailureException e) {
            System.out.println("Engine Error: " + e.getMessage());
        } 
        catch (WeatherNotSuitableException e) {
            System.out.println("Weather Error: " + e.getMessage());
        } 
        finally {
            System.out.println("--- Pre-Launch Sequence Complete ---");
        }

        sc.close();
    }
}



/*
        same but propagation tech

Space Mission Control (Fun / Creative)
--------------------------------------
 A rocket launch simulation throws:
   o FuelShortageException if fuel < required level.
   o EngineFailureException if engine test fails.
   o WeatherNotSuitableException if launch conditions are unsafe.
 Show how multiple exceptions are caught and handled before launch.


import java.util.*;

// Custom Exceptions
class FuelShortageException extends Exception {
    FuelShortageException(String message) {
        super(message);
    }
}

class EngineFailureException extends Exception {
    EngineFailureException(String message) {
        super(message);
    }
}

class WeatherNotSuitableException extends Exception {
    WeatherNotSuitableException(String message) {
        super(message);
    }
}

class SpaceMission {

    void checkFuel(double fuelLevel, double requiredFuel) throws FuelShortageException {
        if (fuelLevel < requiredFuel) {
            throw new FuelShortageException("⛽ Fuel shortage! Need at least " + requiredFuel + " liters.");
        }
        System.out.println("✅ Fuel check passed.");
    }

    void testEngine(boolean engineStatus) throws EngineFailureException {
        if (!engineStatus) {
            throw new EngineFailureException("🔥 Engine test failed! Abort mission.");
        }
        System.out.println("✅ Engine test passed.");
    }

    void checkWeather(String condition) throws WeatherNotSuitableException {
        if (!condition.equalsIgnoreCase("clear")) {
            throw new WeatherNotSuitableException("🌧️ Weather not suitable for launch!");
        }
        System.out.println("✅ Weather conditions are good for launch.");
    }

    void prepareForLaunch(double fuel, double requiredFuel, boolean engineOK, String weather)
            throws FuelShortageException, EngineFailureException, WeatherNotSuitableException {

        // Step-by-step system checks
        checkFuel(fuel, requiredFuel);
        testEngine(engineOK);
        checkWeather(weather);

        System.out.println("\n🚀 All systems GO! Launch sequence initiated...");
    }
}

public class P_40 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SpaceMission mission = new SpaceMission();

        System.out.println("=== 🌌 Space Mission Control Simulation ===");
        System.out.print("Enter current fuel level (liters): ");
        double fuel = sc.nextDouble();

        System.out.print("Enter required fuel level (liters): ");
        double requiredFuel = sc.nextDouble();

        System.out.print("Did the engine pass the test? (true/false): ");
        boolean engineStatus = sc.nextBoolean();

        System.out.print("Enter current weather (clear/cloudy/rainy): ");
        String weather = sc.next();

        System.out.println("\n--- Launch System Checks ---");

        try {
            mission.prepareForLaunch(fuel, requiredFuel, engineStatus, weather);
        } 
        catch (FuelShortageException e) {
            System.out.println(e.getMessage());
        } 
        catch (EngineFailureException e) {
            System.out.println(e.getMessage());
        } 
        catch (WeatherNotSuitableException e) {
            System.out.println(e.getMessage());
        } 
        finally {
            System.out.println("\n--- Mission control checks completed ---");
        }

        sc.close();
    }
}

*/