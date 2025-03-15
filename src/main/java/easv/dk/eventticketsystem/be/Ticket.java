package easv.dk.eventticketsystem.be;

public class Ticket {
    private final int id;
    private int ticketTypeId;
    private String uniqueCode;

    public Ticket(int id, int ticketTypeId, String uniqueCode) {
        this.id = id;
        this.ticketTypeId = ticketTypeId;
        this.uniqueCode = uniqueCode;
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
        return "Ticket{" + "id=" + id + ", ticketTypeId=" + ticketTypeId;
    }
}
