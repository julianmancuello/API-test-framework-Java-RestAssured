package common;

import context.ContextStore;

import java.security.SecureRandom;

import static common.Authentication.*;

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

    public static void loadUserId(UserType userType) {
        switch (userType) {
            case MAIN_USER -> ContextStore.put("testUserId", ContextStore.get("main-user-id"));
            case EMPTY_USER -> ContextStore.put("testUserId", ContextStore.get("empty-user-id"));
        }
    }
}
