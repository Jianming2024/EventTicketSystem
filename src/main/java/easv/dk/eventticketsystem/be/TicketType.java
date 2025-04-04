package easv.dk.eventticketsystem.be;

public class TicketType {
    private int ticketTypeId;
    private String typeName;
    private String category;
    private double price;

    public TicketType(int ticketTypeId, String typeName, String category, double price) {
        this.ticketTypeId = ticketTypeId;
        this.typeName = typeName;
        this.category = category;
        this.price = price;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TicketType{" +
                "ticketTypeId=" + ticketTypeId +
                ", typeName='" + typeName + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}