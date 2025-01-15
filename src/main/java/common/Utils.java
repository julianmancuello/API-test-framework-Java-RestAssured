package common;

import context.ContextStore;

import java.security.SecureRandom;

public class Utils {

    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomIsbn() {
        long max = 9999999999999L;

        long randomIsbn = random.nextLong(max + 1);
        return String.format("%013d", randomIsbn);
    }

    public static String getTestUser() {
        return ContextStore.get("testUser");
    }

    public static String getTestPassword() {
        return ContextStore.get("testPassword");
    }

    public static String getTestUserId() {
        return ContextStore.get("testUserId");
    }
}
