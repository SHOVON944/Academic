/*
33. Smart Home IoT System 
 Devices (lights, AC, heater) can be turned on/off. 
 Throw: 
o DeviceNotConnectedException if the device is offline. 
o InvalidDeviceCommandException if an unsupported action is requested. 
 Demonstrate exception propagation across multiple IoT controllers. 
*/


import java.util.*;

class DeviceNotConnectedException extends Exception {
    DeviceNotConnectedException(String msg) {
        super(msg);
    }
}

class InvalidDeviceCommandException extends Exception {
    InvalidDeviceCommandException(String msg) {
        super(msg);
    }
}

// Device class
class Device {
    String name;
    boolean connected;
    boolean on;

    Device(String name, boolean connected) {
        this.name = name;
        this.connected = connected;
        this.on = false;
    }

    void performAction(String action) throws DeviceNotConnectedException, InvalidDeviceCommandException {
        if (!connected)
            throw new DeviceNotConnectedException(name + " is not connected.");

        if (action.equalsIgnoreCase("on")) {
            on = true;
            System.out.println(name + " is turned ON.");
        } else if (action.equalsIgnoreCase("off")) {
            on = false;
            System.out.println(name + " is turned OFF.");
        } else {
            throw new InvalidDeviceCommandException("Invalid command: " + action + " for " + name);
        }
    }
}

class IoTController {
    ArrayList<Device> devices = new ArrayList<>();

    void addDevice(Device d) {
        devices.add(d);
    }

    void controlDevice(String deviceName, String action)
            throws DeviceNotConnectedException, InvalidDeviceCommandException {

        for (Device d : devices) {
            if (d.name.equalsIgnoreCase(deviceName)) {
                d.performAction(action); // exception propagate
                return;
            }
        }
        throw new InvalidDeviceCommandException("Device not found: " + deviceName);
    }
}


public class P_33 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IoTController controller = new IoTController();

        controller.addDevice(new Device("Light", true));
        controller.addDevice(new Device("AC", false));  // not connected
        controller.addDevice(new Device("Heater", true));

        System.out.println("Smart Home IoT System");
        System.out.println("Available devices: Light, AC, Heater\n");

        while (true) {
            System.out.print("Enter device name (or 'exit' to quit): ");
            String name = sc.nextLine();
            if (name.equalsIgnoreCase("exit"))
                break;

            System.out.print("Enter action (on/off): ");
            String action = sc.nextLine();

            try {
                controller.controlDevice(name, action);
            } 
            catch (DeviceNotConnectedException e) {
                System.out.println("Error: " + e.getMessage());
            } 
            catch (InvalidDeviceCommandException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("\nSystem shutting down...");
        sc.close();
    }
}
