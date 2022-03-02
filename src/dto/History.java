package dto;

public class History {
    private String cardId;
    private String inTime;
    private String outTime;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public History(String cardId, String inTime, String outTime) {
        this.cardId = cardId;
        this.inTime = inTime;
        this.outTime = outTime;
    }

    public History() {
    }
}
