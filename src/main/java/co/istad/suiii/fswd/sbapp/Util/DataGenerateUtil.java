package co.istad.suiii.fswd.sbapp.Util;

import java.util.Random;

public class DataGenerateUtil {
    public static String generateProductCode() {
        String prefix = "ISTADP";
        Random random = new Random();

        // Generates a number between 0 and 999,999
        int randomNumber = random.nextInt(1000000);

        // %s is the string prefix
        // %06d ensures the integer is at least 6 digits, padded with zeros
        return String.format("%s-%06d", prefix, randomNumber);
    }

}
