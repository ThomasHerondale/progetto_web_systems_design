package it.wsda;
import java.io.IOException;
import java.sql.*;
import java.util.Date;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
public class DataReportServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/NomeDatabase", "user", "password");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT facility_id, MAX(timestamp) as lastSignal FROM signals GROUP BY facility_id")) {

            StringBuilder builder = new StringBuilder("<html><body>");
            builder.append("<h2>Stato degli impianti</h2>");
            Date twoMinutesAgo = new Date(System.currentTimeMillis() - 2 * 60 * 1000);

            while (rs.next()) {
                String idImpianto = rs.getString("idImpianto");
                Timestamp lastSignal = rs.getTimestamp("lastSignal");

                //id_impianti se hanno mandato inviato segnalazioni negli ultimi due minuti e quelli che non l'hanno fatto
                if (lastSignal != null && lastSignal.getTime() > twoMinutesAgo.getTime()) {
                    builder.append("<p>Attivi: ").append(idImpianto).append(" - Ultimo segnale: ").append(lastSignal).append("</p>");
                } else {
                    builder.append("<p>Inattivi: ").append(idImpianto).append(" - Ultimo segnale: ").append(lastSignal).append("</p>");
                }
            }

            builder.append("</body></html>");
            response.getWriter().write(builder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
