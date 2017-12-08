package tout;

import java.util.UUID;


public class FileResponse {

    final private UUID uuid = UUID.randomUUID();

    public FileResponse() {
    }

    public final UUID getUuid() {
        return uuid;
    }
}
