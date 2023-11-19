package common;

import java.util.Random;

public class utils {
    final static int COLUMN_WIDTH = 70;
    public static String truncateString(String str) {
        if (str.length() <= COLUMN_WIDTH) {
            return str;
        } else {
            // Encontrar o último espaço antes do limite para evitar quebrar palavras
            int lastSpaceIndex = str.lastIndexOf(' ', COLUMN_WIDTH - 1);
            if (lastSpaceIndex == -1) {
                lastSpaceIndex = COLUMN_WIDTH - 1; // Se não houver espaço, quebre no limite
            }
            return str.substring(0, lastSpaceIndex) + "\n" + str.substring(lastSpaceIndex).trim();
        }
    }

    public static String generateRandomIp() {
        Random random = new Random();
        return random.nextInt(256) + "." +
                random.nextInt(256) + "." +
                random.nextInt(256) + "." +
                random.nextInt(256);
    }


}
