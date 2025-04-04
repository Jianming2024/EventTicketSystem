package easv.dk.eventticketsystem.be;

public class TicketType {
    private int ticketTypeId;
    private String typeName;
    private String description;

    public TicketType(int ticketTypeId, String typeName, String description) {
        this.ticketTypeId = ticketTypeId;
        this.typeName = typeName;
        this.description = description;
    }

    public int getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(int ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TicketType{" +
                "ticketTypeId=" + ticketTypeId +
                ", typeName='" + typeName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
