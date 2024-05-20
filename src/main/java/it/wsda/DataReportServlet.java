package it.wsda;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/datareport")
public class DataReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Viene impostato il tipo di contenuto della risposta come JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Imposta gli header CORS per permettere richieste da qualsiasi origine
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // Creo un array JSON per memorizzare i risultati
        JSONArray jsonArray = new JSONArray();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/NomeDatabase", "user", "password");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT f.id as facility_id, f.latitude, f.longitude, MAX(s.timestamp) as lastSignal " +
                             "FROM facilities f " +
                             "INNER JOIN signals s ON f.id = s.facility_id " +
                             "GROUP BY f.id, f.latitude, f.longitude")) {

            // Calcola il timestamp di due minuti fa
            Date twoMinutesAgo = new Date(System.currentTimeMillis() - 2 * 60 * 1000);

            // Itera sui risultati della query
            while (rs.next()) {
                // Ottiene i dati dal risultato della query
                String facilityId = rs.getString("facility_id");
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");
                Timestamp lastSignal = rs.getTimestamp("lastSignal");

                // Crea un oggetto JSON per rappresentare ogni facility
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("facility_id", facilityId);
                jsonObject.put("latitude", latitude);
                jsonObject.put("longitude", longitude);
                jsonObject.put("lastSignal", lastSignal != null ? lastSignal.getTime() : JSONObject.NULL);
                jsonObject.put("status", (lastSignal != null && lastSignal.getTime() > twoMinutesAgo.getTime()) ? "Attivo" : "Inattivo");

                // Aggiunge l'oggetto JSON all'array JSON
                jsonArray.put(jsonObject);
            }

            // Scrive l'array JSON nella risposta
            PrintWriter out = response.getWriter();
            out.print(jsonArray.toString());
            out.flush();
        } catch (SQLException e) {
            // Stampa lo stack trace dell'eccezione
            e.printStackTrace();
            // Imposta lo status della risposta come errore interno del server
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            // Crea un oggetto JSON per rappresentare l'errore
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Internal Server Error");
            // Scrive l'oggetto JSON dell'errore nella risposta
            PrintWriter out = response.getWriter();
            out.print(errorResponse.toString());
            out.flush();
        }
    }
}

