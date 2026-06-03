package attendance;

public class ConfigManager {
    public static String getDataFilePath() {
        Config.createDirs();
        return Config.DATA_PATH;
    }

    public static String getSignaturePath() {
        Config.createDirs();
        return Config.SIGNATURE_PATH;
    }
}

