package ind.lgh.system.mqtt;

public class Main {
    public static void main(String[] args) {
        PushPayload pushPayload = PushPayload.getPushPayloadBuider().setContent("test")
                .setMobile("119")
                .setType("2018")
                .bulid();
    }
}
