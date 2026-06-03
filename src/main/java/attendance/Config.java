package attendance;

import java.io.File;

public class Config {

    private static final String LAPTOP_SIGNATURE_PATH = "E:\\SHOVON\\Document\\Project\\Attendance\\signature\\signature.png";
    private static final String LAPTOP_DATA_PATH = "E:\\SHOVON\\Document\\Project\\Attendance\\data.json";

    private static final String PHONE_SIGNATURE_PATH = "/sdcard/S_Project/signature/signature.png";
    private static final String PHONE_DATA_PATH = "/sdcard/S_Project/data.json";

    public static final String SIGNATURE_PATH;
    public static final String DATA_PATH;
    
    public static final String APP_DIR;
    public static final String SIGNATURE_DIR;


    static {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            // Laptop environment (Windows)
            SIGNATURE_PATH = LAPTOP_SIGNATURE_PATH;
            DATA_PATH = LAPTOP_DATA_PATH;
            APP_DIR = "E:\\SHOVON\\Document\\Project\\Attendance";
            SIGNATURE_DIR = "E:\\SHOVON\\Document\\Project\\Attendance\\signature";
        } else {
            // Assume phone environment (Android/Linux)
            SIGNATURE_PATH = PHONE_SIGNATURE_PATH;
            DATA_PATH = PHONE_DATA_PATH;
            APP_DIR = "/sdcard/S_Project";
            SIGNATURE_DIR = "/sdcard/S_Project/signature";
        }
    }

    public static void createDirs() {
        System.out.println("[DEBUG] Checking application directories...");
        File appDir = new File(APP_DIR);
        if (!appDir.exists()) {
            System.out.println("[DEBUG] Application directory not found. Creating: " + APP_DIR);
            if (appDir.mkdirs()) {
                System.out.println("[DEBUG] Application directory created successfully.");
            } else {
                System.out.println("[DEBUG] FAILED to create application directory.");
            }
        } else {
            System.out.println("[DEBUG] Application directory already exists: " + APP_DIR);
        }

        File signatureDir = new File(SIGNATURE_DIR);
        if (!signatureDir.exists()) {
            System.out.println("[DEBUG] Signature directory not found. Creating: " + SIGNATURE_DIR);
            if (signatureDir.mkdirs()) {
                System.out.println("[DEBUG] Signature directory created successfully.");
            } else {
                System.out.println("[DEBUG] FAILED to create signature directory.");
            }
        } else {
            System.out.println("[DEBUG] Signature directory already exists: " + SIGNATURE_DIR);
        }
    }
}
