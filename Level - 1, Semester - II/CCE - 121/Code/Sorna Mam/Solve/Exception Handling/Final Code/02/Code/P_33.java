import java.util.*;

// Custom Exceptions
class DeviceNotConnectedException extends Exception {
    DeviceNotConnectedException(String message) {
        super(message);
    }
}

class InvalidDeviceCommandException extends Exception {
    InvalidDeviceCommandException(String message) {
        super(message);
    }
}

// Device Class
class Device {
    String name;
    boolean isConnected;
    boolean isOn;

    Device(String name, boolean isConnected) {
        this.name = name;
        this.isConnected = isConnected;
        this.isOn = false;
    }

    public void performAction(String action) throws DeviceNotConnectedException, InvalidDeviceCommandException {

        if (!isConnected)
            throw new DeviceNotConnectedException(name + " is not connected to network.");

        if (action.equalsIgnoreCase("on")) {
            isOn = true;
            System.out.println("✅ " + name + " turned ON.");
        } else if (action.equalsIgnoreCase("off")) {
            isOn = false;
            System.out.println("✅ " + name + " turned OFF.");
        } else {
            throw new InvalidDeviceCommandException("Invalid command for " + name + ": " + action);
        }
    }
}

// IoT Controller (intermediate class)
class IoTController {
    List<Device> devices = new ArrayList<>();

    public void addDevice(Device d) {
        devices.add(d);
    }

    // exception propagate kore main() e jabe
    public void controlDevice(String deviceName, String action)
            throws DeviceNotConnectedException, InvalidDeviceCommandException {

        for (Device d : devices) {
            if (d.name.equalsIgnoreCase(deviceName)) {
                d.performAction(action); // propagation from here
                return;
            }
        }
        throw new InvalidDeviceCommandException("Device '" + deviceName + "' not found!");
    }
}

// Main Class
public class P_33 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        IoTController controller = new IoTController();

        // কিছু ডিভাইস যুক্ত করা হলো
        controller.addDevice(new Device("Light", true));
        controller.addDevice(new Device("AC", false)); // disconnected
        controller.addDevice(new Device("Heater", true));

        System.out.println("🏠 Welcome to Smart Home IoT System!");
        System.out.println("Available devices: Light, AC, Heater\n");

        while (true) {
            System.out.print("Enter device name (or 'exit' to quit): ");
            String device = scan.nextLine();
            if (device.equalsIgnoreCase("exit"))
                break;

            System.out.print("Enter action (on/off): ");
            String action = scan.nextLine();

            try {
                controller.controlDevice(device, action);
            } catch (DeviceNotConnectedException e) {
                System.out.println("⚠️ Connection Error: " + e.getMessage());
            } catch (InvalidDeviceCommandException e) {
                System.out.println("❌ Invalid Command: " + e.getMessage());
            }
        }

        System.out.println("\n🏁 System shutting down...");
        scan.close();
    }
}
