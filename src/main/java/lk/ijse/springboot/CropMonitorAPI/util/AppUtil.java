package lk.ijse.springboot.CropMonitorAPI.util;

import java.util.UUID;

public class AppUtil {
    public static String generateId(String prefix) {
        return prefix + "-" + UUID.randomUUID();
    }
}
