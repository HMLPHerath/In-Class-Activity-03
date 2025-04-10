


package com.nsbm.dao;

import com.nsbm.model.Participant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/eventdb";
    private String jdbcUsername = "root"; 
    private String jdbcPassword = "";     

    private static final String INSERT_SQL = "INSERT INTO participants (name, email, event) VALUES (?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM participants";
    private static final String SELECT_BY_ID = "SELECT * FROM participants WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM participants WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE participants SET name=?, email=?, event=? WHERE id = ?";
    private static final String FILTER_SQL = "SELECT * FROM participants WHERE event = ?";

    // Create a DB connection
    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Don't forget to add MySQL connector to your project
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    // INSERT participant
    public void insertParticipant(Participant participant) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {
            stmt.setString(1, participant.getName());
            stmt.setString(2, participant.getEmail());
            stmt.setString(3, participant.getEvent());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // SELECT all participants
    public List<Participant> listParticipants() {
        List<Participant> participants = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL)) {
            while (rs.next()) {
                Participant p = new Participant();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setEmail(rs.getString("email"));
                p.setEvent(rs.getString("event"));
                participants.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception E) {
            E.printStackTrace();
        }
        return participants;
    }

    // SELECT participant by ID
    public Participant getById(int id) {
        Participant p = null;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                p = new Participant();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setEmail(rs.getString("email"));
                p.setEvent(rs.getString("event"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    // UPDATE participant
    public void updateParticipant(Participant participant) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL)) {
            stmt.setString(1, participant.getName());
            stmt.setString(2, participant.getEmail());
            stmt.setString(3, participant.getEvent());
            stmt.setInt(4, participant.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE participant
    public void deleteParticipant(int id) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // FILTER participants by event
    public List<Participant> filterByEvent(String eventName) {
        List<Participant> participants = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(FILTER_SQL)) {
            stmt.setString(1, eventName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Participant p = new Participant();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setEmail(rs.getString("email"));
                p.setEvent(rs.getString("event"));
                participants.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participants;
    }
}
