package utils;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Auther By Nait
 * @Date: 2021/3/4
 * @version: 1.0
 */
public class JsonUtil {

    private static final AtomicLong COUNT = new AtomicLong(0);
    private static final int GSON_SIZE = 16;
    private static final Gson[] GSONS = new Gson[GSON_SIZE];


    public static String toJson(Object o) {
        if (o == null) {
            return "null";
        }
        int idx = randomIdx();
        return GSONS[idx].toJson(o);
    }

    public static <T> T fromJson(String json, Class<T> clazz) throws JsonSyntaxException {
        int idx = randomIdx();
        return GSONS[idx].fromJson(json, clazz);
    }

    public static <T> List<T> fromListJson(String json, Class<T> clazz) throws JsonSyntaxException {
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(json).getAsJsonArray();
        int idx = randomIdx();
        List<T> ls = new ArrayList<T>();
        for (final JsonElement js : array) {
            T entity = GSONS[idx].fromJson(js, clazz);
            ls.add(entity);
        }
        return ls;
    }

    private static int randomIdx() {
        long andIncrement = COUNT.getAndIncrement();
        return (int) (andIncrement % GSON_SIZE);
    }

    public static void main(String[] args) {
        String str = "{\"applicationAckReqType\":0,\"bltMac\":\"1918BDFFF029\",\"buildId\":\"200036\",\"content\":\"{\\\"activeRptIntervalOutoffRange\\\":60,\\\"positionRptCtrlSwtich\\\":0,\\\"sleepRptIntervalOutoffRange\\\":3600}\"}";

    }

}
