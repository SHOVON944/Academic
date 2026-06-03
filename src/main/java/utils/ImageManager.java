package utils;

import java.io.File; // Import File class

public class ImageManager {

    // Absolute path to your signature image on the mobile device (assuming /sdcard/S_Project structure)
    private static final String SIGNATURE_IMAGE_PATH = "E:\\SHOVON\\Document\\Project\\Attendance\\signature\\signature.png";

    public static String getSignatureImagePath() {
        File file = new File(SIGNATURE_IMAGE_PATH);
        if (file.exists()) {
            return SIGNATURE_IMAGE_PATH;
        } else {
            System.err.println("Signature file not found at: " + SIGNATURE_IMAGE_PATH);
            return null; // Return null if file does not exist
        }
    }
}
