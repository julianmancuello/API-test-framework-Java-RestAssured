package context;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static common.LoggerUtils.info;

public class ContextStore {

    private static final Map<String, Object> context = new ConcurrentHashMap<>();

    public static synchronized void put(String key, Object value) {
        if (key == null || value == null) {
            info("Error: Key or Value cannot be null.");
            return;
        }
        context.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public static synchronized <T> T get(String key) {
        Object value = context.get(key);
        if (value == null) {
            throw new NoSuchElementException("Key '" + key + "' not found in ContextStore.");
        }
        return (T) value;
    }

    public static synchronized void loadProperties(String filePath) {
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            properties.load(inputStream);
            properties.forEach((key, value) -> put(key.toString(), value));
        } catch (IOException e) {
            info("Error: Could not load properties from " + filePath);
            e.printStackTrace();
        }
    }

    public static synchronized void remove(String key) {
        context.remove(key);
    }

    public static synchronized Set<String> keys() {
        return Collections.unmodifiableSet(context.keySet());
    }

    public static synchronized void clear() {
        context.clear();
    }
}
