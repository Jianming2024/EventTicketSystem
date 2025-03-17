package easv.dk.eventticketsystem.be;

public class TicketOnOrder {
    private int orderId;
    private String customerName;
    private String customerEmail;
    private String eventName;
    private int ticketId;
    private String ticketType;
    private String code;

    public TicketOnOrder(int orderId, String customerName, String customerEmail, String eventName, int ticketId, String ticketType, String code) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.eventName = eventName;
        this.ticketId = ticketId;
        this.ticketType = ticketType;
        this.code = code;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getEventName() {
        return eventName;
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getTicketType() {
        return ticketType;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "TicketOnOrder{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", eventName='" + eventName + '\'' +
                ", ticketId=" + ticketId +
                ", ticketType='" + ticketType + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
