package easv.dk.eventticketsystem.dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import easv.dk.eventticketsystem.be.Event;
import easv.dk.eventticketsystem.dal.IEventDAO;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventDAODB implements IEventDAO {
    private DBConnection con = new DBConnection();

    @Override
    public List<Event> getAllEvents() throws IOException {
        List<Event> allEvents = new ArrayList<>();
        String sql = "SELECT * FROM event";
        try (Connection connection = con.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int eventId = rs.getInt("event_id");
                String eventName = rs.getString("event_name");
                // Use getTimestamp to retrieve both date and time
                Timestamp startTimestamp = rs.getTimestamp("start_datetime");
                Timestamp endTimestamp = rs.getTimestamp("end_datetime");
                LocalDateTime startDatetime = startTimestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime endDatetime = endTimestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                String eventLocation = rs.getString("location");
                String eventNotes = rs.getString("notes");
                String eventLocationGuidance = rs.getString("location_guidance");

                Event event = new Event(eventId, eventName, startDatetime, endDatetime, eventLocation, eventNotes, eventLocationGuidance);
                allEvents.add(event);
                allEvents.add(event);
            }
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allEvents;
    }

}

