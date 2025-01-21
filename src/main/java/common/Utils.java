package common;

import context.ContextStore;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static common.Authentication.UserType;

public class Utils {

    private static final String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGIT_CHARACTERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*";
    private static final String ALPHANUMERIC_CHARACTERS = UPPERCASE_CHARACTERS + LOWERCASE_CHARACTERS + DIGIT_CHARACTERS;
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

    public static String generateRandomUser() {
        return generateRandomCredential(10, 14, false);
    }

    public static String generateRandomPassword() {
        return generateRandomCredential(8, 12, true);
    }

    public static String generateRandomCredential(int minLength, int maxLength, boolean specialCharacter) {
        if (minLength < 1) throw new IllegalArgumentException("The minimum length must be at least 1");
        int length = generateRandomNumberBetween(minLength, maxLength);
        int startIndex = 3;
        StringBuilder randomString = new StringBuilder(length);
        randomString.append(getRandomCharacter(UPPERCASE_CHARACTERS))
                .append(getRandomCharacter(LOWERCASE_CHARACTERS))
                .append(getRandomCharacter(DIGIT_CHARACTERS));
        if (specialCharacter) {
            randomString.append(getRandomCharacter(SPECIAL_CHARACTERS));
            startIndex = 4;
        }
        for (int i = startIndex; i < length; i++) {
            randomString.append(getRandomCharacter(ALPHANUMERIC_CHARACTERS));
        }
        return randomString.toString();
    }

    public static int generateRandomNumberBetween(int lowerBound, int upperBound) {
        if (lowerBound >= upperBound) {
            throw new IllegalArgumentException("Limits are not valid");
        }
        return random.nextInt(lowerBound, upperBound + 1);
    }

    public static char getRandomCharacter(String characters) {
        return characters.charAt(random.nextInt(characters.length()));
    }

    public static List<Integer> generateRandomListOfNumbers(int sizeList, int lowerBound, int upperBound, boolean uniqueValues) {
        List<Integer> resultList = new ArrayList<>();

        if (uniqueValues) {
            if (upperBound - lowerBound + 1 < sizeList) {
                throw new IllegalArgumentException("The range is insufficient to generate a list without repeated values.");
            }
            Set<Integer> uniqueValuesSet = new HashSet<>();
            while (uniqueValuesSet.size() < sizeList) {
                uniqueValuesSet.add(generateRandomNumberBetween(lowerBound, upperBound));
            }
            resultList.addAll(uniqueValuesSet);
        } else {
            for (int i = 0; i < sizeList; i++) {
                resultList.add(generateRandomNumberBetween(lowerBound, upperBound));
            }
        }
        return resultList;
    }

    public static List<Integer> generateRandomListOfIndexes(int numberOfIndexes, int upperBoundExcluded, boolean uniqueIndexes) {
        return generateRandomListOfNumbers(numberOfIndexes, 0, upperBoundExcluded - 1, uniqueIndexes);
    }

    public static int generateRandomPositiveInteger(int bound) {
        return random.nextInt(bound) + 1;
    }
}
