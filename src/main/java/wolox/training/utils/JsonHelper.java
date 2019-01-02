package wolox.training.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {
    public static String asJson(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
