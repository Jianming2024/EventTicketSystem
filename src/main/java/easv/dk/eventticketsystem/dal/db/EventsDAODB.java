package easv.dk.eventticketsystem.dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import easv.dk.eventticketsystem.be.Events;
import easv.dk.eventticketsystem.dal.IEventsDAO;

import java.io.IOException;
import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventsDAODB implements IEventsDAO {
    private DBConnection con = new DBConnection();

    @Override
    public List<Events> getAllEvents() throws IOException {
        List<Events> allEvents = new ArrayList<>();
        String sql = "SELECT * FROM event";
        try (Connection connection = con.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int eventId = rs.getInt("event_id");
                String eventName = rs.getString("event_name");
                Date eventStartDateTime = rs.getDate("start_datetime");
                Date eventEndDateTime = rs.getDate("end_datetime");
                String eventLocation = rs.getString("location");
                String eventNotes = rs.getString("notes");
                String eventLocationGuidance = rs.getString("location_guidance");

                Events events = new Events(eventId, eventName, eventStartDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), eventEndDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), eventLocation, eventNotes, eventLocationGuidance);
                allEvents.add(events);
            }
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allEvents;
    }

}

