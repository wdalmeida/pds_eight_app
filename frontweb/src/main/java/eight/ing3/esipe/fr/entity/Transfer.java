package eight.ing3.esipe.fr.entity;

public class Transfer {

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
