package easv.dk.eventticketsystem.be;

import java.time.LocalDate;

public class Order {
    private final int id;
    private int customerId;
    private int eventId;
    private LocalDate orderDate;
    private String status;

    public Order(int id, int ticketTypeId, String uniqueCode) {
        this.id = id;
        this.customerId = ticketTypeId;

    }
}
