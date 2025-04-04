package easv.dk.eventticketsystem.be;

public class Ticket {
    private final int id;
    private int ticketTypeId;
    private String uniqueCode;
    private int eventId;
    private int quantity;

    public Ticket(int id, int ticketTypeId, String uniqueCode, int eventId,int quantity) {
        this.id = id;
        this.ticketTypeId = ticketTypeId;
        this.uniqueCode = uniqueCode;
        this.eventId = eventId;
        this.quantity= quantity;

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getId() {
        return id;
    }

    public int getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(int ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    @Override
    public String toString() {
        return id + "," + ticketTypeId + "," + uniqueCode;
    }
}