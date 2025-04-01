package easv.dk.eventticketsystem.dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import easv.dk.eventticketsystem.be.Event;
import easv.dk.eventticketsystem.dal.IEventDAO;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

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
                String eventImagePath = rs.getString("event_image_path");

                Event event = new Event(eventId, eventName, startDatetime, endDatetime, eventLocation, eventNotes, eventLocationGuidance, eventImagePath);
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
    @Override



    public void createNewEvent(Event event) throws IOException {


        String sql = "INSERT INTO Event (event_name, start_datetime, end_datetime, location, notes, location_guidance, price, event_image_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


        try (Connection connection = con.getConnection();


             PreparedStatement ps = connection.prepareStatement(sql)) {


            ps.setString(1, event.getEventName());


            ps.setString(2, event.getStartDatetime().toString());


            ps.setString(3, event.getEndDatetime().toString());


            ps.setString(4, event.getLocation());


            ps.setString(5, event.getNotes());


            ps.setString(6, event.getLocationGuidance());


            ps.setString(7, event.getEventImagePath());


            ps.executeUpdate();


        }catch (SQLException e) {


            throw new RuntimeException("Error adding event to the database: " + e.getMessage(), e);


        }





    }





    @Override


    public void deleteEvent(Event event) throws IOException {


        String sql = "DELETE FROM event where event_id = ?";


        try (Connection connection = con.getConnection();


             PreparedStatement ps = connection.prepareStatement(sql)) {


            ps.setInt(1, event.getEventId());


            ps.executeUpdate();


        } catch (SQLException e) {


            throw new IOException("Error deleting event and its dependencies: " + e.getMessage(), e);


        }





    }





    @Override


    public void updateEvent(Event event) throws IOException {


        String sql = "UPDATE Event SET event_name = ?, start_datetime = ?, end_datetime = ?, location = ?, notes = ?, location_guidance = ?, event_img_path = ? WHERE event_id = ? ";


        try (Connection connection = con.getConnection();


             PreparedStatement ps = connection.prepareStatement(sql)) {


            ps.setString(1, event.getEventName());


            ps.setString(3, event.getStartDatetime().toString());


            ps.setString(4, event.getEndDatetime().toString());


            ps.setString(5, event.getLocation());


            ps.setString(6, event.getNotes());


            ps.setString(7, event.getLocationGuidance());


            ps.setString(2, event.getEventImagePath());


            ps.executeUpdate();





            int rowsUpdated = ps.executeUpdate();


            if (rowsUpdated > 0) {


                System.out.println("Record updated successfully!");


            }


        } catch (SQLException e) {


            throw new IOException("Error updating event in the database: " + e.getMessage(), e);


        }


    }

}

