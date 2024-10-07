package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entity.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import org.hibernate.Session;

@WebServlet(name = "UserProfile", urlPatterns = {"/UserProfile"})
public class UserProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Gson gson = new Gson();
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("success", false);

        try {
            // Assuming you have a mechanism (like sessions) to get the logged-in user ID
            String userIdString = request.getParameter("userId"); // Or however you retrieve it

            if (userIdString == null || userIdString.isEmpty()) {
                responseJson.addProperty("message", "User ID not provided");
            } else {
                int userId = Integer.parseInt(userIdString);

                Session session = HibernateUtil.getSessionFactory().openSession();
                User user = (User) session.get(User.class, userId);
                session.close(); // Close the session immediately after use

                if (user != null) {
                    responseJson.addProperty("success", true);
                    responseJson.add("user", gson.toJsonTree(user));
                } else {
                    responseJson.addProperty("message", "User not found");
                }
            } 

        } catch (NumberFormatException e) {
            responseJson.addProperty("message", "Invalid user ID format");
        } catch (Exception e) {
            responseJson.addProperty("message", "An error occurred while fetching user data");
            e.printStackTrace(); 
        }

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(responseJson));
    }
}