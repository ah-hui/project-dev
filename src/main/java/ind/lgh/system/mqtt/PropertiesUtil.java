package ind.lgh.system.mqtt;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    public static String MQTT_HOST;
    public static String MQTT_CLIENTID;
    public static String MQTT_USER_NAME;
    public static String MQTT_PASSWORD;
    public static int MQTT_TIMEOUT;
    public static int MQTT_KEEP_ALIVE;
    public static String MQTT_TOPIC;


//    public static final String ELASTIC_SEARCH_HOST;
//
//    public static final int ELASTIC_SEARCH_PORT;
//
//    public static final String ELASTIC_SEARCH_CLUSTER_NAME;

    static {
        MQTT_HOST = loadMqttProperties().getProperty("host");
        MQTT_CLIENTID = loadMqttProperties().getProperty("clientid");
        MQTT_USER_NAME = loadMqttProperties().getProperty("username");
        MQTT_PASSWORD = loadMqttProperties().getProperty("password");
        MQTT_TIMEOUT = Integer.valueOf(loadMqttProperties().getProperty("timeout"));
        MQTT_KEEP_ALIVE = Integer.valueOf(loadMqttProperties().getProperty("keepalive"));
        MQTT_TOPIC = loadMqttProperties().getProperty("topic");
    }

//    static {
//        ELASTIC_SEARCH_HOST = loadEsProperties().getProperty("ES_HOST");
//        ELASTIC_SEARCH_PORT = Integer.valueOf(loadEsProperties().getProperty("ES_PORT"));
//        ELASTIC_SEARCH_CLUSTER_NAME = loadEsProperties().getProperty("ES_CLUSTER_NAME");
//    }

    private static Properties loadMqttProperties() {
        InputStream inputstream = PropertiesUtil.class.getResourceAsStream("/mqtt.yml");
        Properties properties = new Properties();
        try {
            properties.load(inputstream);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (inputstream != null) {
                    inputstream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static Properties loadEsProperties() {
        InputStream inputstream = PropertiesUtil.class.getResourceAsStream("/elasticsearch.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputstream);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (inputstream != null) {
                    inputstream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
