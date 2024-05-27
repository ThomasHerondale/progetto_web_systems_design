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
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Random;
public class DataReceiverServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idImpianto = Integer.parseInt(request.getParameter("facility_id"));
        int idPalinsesto = Integer.parseInt(request.getParameter("schedule_id"));
        String idCartellone = request.getParameter("adv_id");
        int durata = Integer.parseInt(request.getParameter("duration"));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // Imposta formato timestamp
        SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timestamp = Timestamp.valueOf(Format.format(timestamp));

        // Genera un signal_id casuale
        String signalId = generateRandomId(10);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto?serverTimezone=CET", "root", "root")) {
            String sql = "INSERT INTO signals (signal_id,facility_id, schedule_id, adv_id, duration, timestamp) VALUES (?,?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, signalId);
                statement.setInt(2, idImpianto);
                statement.setInt(3, idPalinsesto);
                statement.setString(4, idCartellone);
                statement.setInt(5, durata);
                statement.setTimestamp(6, timestamp);
                statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);  //statement.close() e conn.close() non serve, chiude tutto dopo il try in teoria
        }
    }
    public static String generateRandomId(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random rng = new SecureRandom();
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

}
