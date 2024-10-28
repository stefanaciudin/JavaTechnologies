package com.example.lab3.homework;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "clientBean")
@SessionScoped
public class ClientBean implements Serializable {
    private List<Client> clients;
    private Client selectedClient; // For adding or editing

    // Database connection properties
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "meow1234";

    // Get all clients
    public List<Client> getClients() {
        if (clients == null) {
            loadClients();
        }
        return clients;
    }

    // Load clients from the database
    private void loadClients() {
        clients = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM clients");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Client client = new Client();
                client.setClientId(resultSet.getInt("client_id"));
                client.setName(resultSet.getString("name"));
                client.setxCoordinate(resultSet.getInt("x_coordinate"));
                client.setyCoordinate(resultSet.getInt("y_coordinate"));
                client.setAvailableDays(resultSet.getString("available_days"));
                client.setAvailableTimeIntervals(resultSet.getString("available_time_intervals"));
                clients.add(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Save the client (either add a new one or update an existing one)
    public void saveClient() {
        if (selectedClient != null) {
            if (selectedClient.getClientId() == 0) {
                addClient();
            } else {
                updateClient();
            }
        }
    }

    // Add a new client to the database
    private void addClient() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO clients (name, x_coordinate, y_coordinate, available_days, available_time_intervals) VALUES (?, ?, ?, ?, ?)")) {

            statement.setString(1, selectedClient.getName());
            statement.setInt(2, selectedClient.getxCoordinate());
            statement.setInt(3, selectedClient.getyCoordinate());
            statement.setString(4, selectedClient.getAvailableDays());
            statement.setString(5, selectedClient.getAvailableTimeIntervals());
            statement.executeUpdate();

            // Reload clients list after adding
            loadClients();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update an existing client in the database
    private void updateClient() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE clients SET name = ?, x_coordinate = ?, y_coordinate = ?, available_days = ?, available_time_intervals = ? WHERE client_id = ?")) {

            statement.setString(1, selectedClient.getName());
            statement.setInt(2, selectedClient.getxCoordinate());
            statement.setInt(3, selectedClient.getyCoordinate());
            statement.setString(4, selectedClient.getAvailableDays());
            statement.setString(5, selectedClient.getAvailableTimeIntervals());
            statement.setInt(6, selectedClient.getClientId());
            statement.executeUpdate();

            // Reload clients list after updating
            loadClients();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Getter and setter for selectedClient (for use with dialogs)
    public Client getSelectedClient() {
        if (selectedClient == null) {
            selectedClient = new Client();
        }
        return selectedClient;
    }

    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;
    }
}
