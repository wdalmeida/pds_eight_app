package entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transfer")
public class Transfer {

    @Id
    private String uuid;

    private String transfer;

    public Transfer(){

    }

    public Transfer(String uuid, String transfer) {
        this.uuid = uuid;
        this.transfer = transfer;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }
}
