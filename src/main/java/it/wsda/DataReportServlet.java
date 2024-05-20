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
             ResultSet rs = stmt.executeQuery("SELECT facility_id, MAX(timestamp) as lastSignal FROM signals GROUP BY facility_id")) {

            // Calcola il timestamp di due minuti fa
            Date twoMinutesAgo = new Date(System.currentTimeMillis() - 2 * 60 * 1000);

            // Itera sui risultati della query
            while (rs.next()) {
                // Ottiene i dati dal risultato della query
                String facilityId = rs.getString("facility_id");
                Timestamp lastSignal = rs.getTimestamp("lastSignal");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("facility_id", facilityId);
                jsonObject.put("lastSignal", lastSignal != null ? lastSignal.getTime() : JSONObject.NULL);
                jsonObject.put("status", (lastSignal != null && lastSignal.getTime() > twoMinutesAgo.getTime()) ? "Attivo" : "Inattivo");

                jsonArray.put(jsonObject);
            }

            PrintWriter out = response.getWriter();
            out.print(jsonArray.toString());
            out.flush();
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Internal Server Error");
            PrintWriter out = response.getWriter();
            out.print(errorResponse.toString());
            out.flush();
        }
    }
}
