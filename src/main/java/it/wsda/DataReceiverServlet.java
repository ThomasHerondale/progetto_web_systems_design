package it.wsda;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
public class DataReceiverServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idImpianto = Integer.parseInt(request.getParameter("facility_id"));
        int idPalinsesto = Integer.parseInt(request.getParameter("schedule_id"));
        int idCartellone = Integer.parseInt(request.getParameter("adv_id"));
        int durata = Integer.parseInt(request.getParameter("duration"));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nomeDatabase", "user", "password")) {
            String sql = "INSERT INTO signals (facility_id, schedule_id, adv_id, duration, timestamp) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, idImpianto);
                statement.setInt(2, idPalinsesto);
                statement.setInt(3, idCartellone);
                statement.setInt(4, durata);
                statement.setTimestamp(5, timestamp);
                statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);  //statement.close() e conn.close() non serve, chiude tutto dopo il try in teoria
        }
    }

}
