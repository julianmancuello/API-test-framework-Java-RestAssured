package common;

import java.security.SecureRandom;

public class Utils {

    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomIsbn() {
        long max = 9999999999999L;

        long randomIsbn = random.nextLong(max + 1);
        return String.format("%013d", randomIsbn);
    }
}
