package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entity.User;
import entity.User_Status;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.HibernateUtil;
import model.Validations;
import org.hibernate.Session;

@MultipartConfig
@WebServlet(name = "SignUp", urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();

        // Prepare JSON response
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("success", "false");

        String mobile = request.getParameter("mobile");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        Part avatarImage = request.getPart("avatarImage");

        // Validate inputs
        if (mobile == null || mobile.trim().isEmpty()) {
            responseJson.addProperty("message", "Please fill in your mobile number");
        } else if (!Validations.isMobileNumberValid(mobile)) {
            responseJson.addProperty("message", "Invalid mobile number");
        } else if (firstName == null || firstName.trim().isEmpty()) {
            responseJson.addProperty("message", "Please fill in your first name");
        } else if (lastName == null || lastName.trim().isEmpty()) {
            responseJson.addProperty("message", "Please fill in your last name");
        } else if (password == null || password.isEmpty()) {
            responseJson.addProperty("message", "Please fill in your password");
        } else if (password.length() < 8) {
            responseJson.addProperty("message", "Password must be at least 8 characters long");
        } else {
            // Data validated
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            User user = new User();
            user.setFirst_name(firstName);
            user.setLast_name(lastName);
            user.setMobile(mobile);
            user.setPassword(password);

            // Get the current local time in Asia/Colombo timezone
            ZonedDateTime localDateTime = ZonedDateTime.now(ZoneId.of("Asia/Colombo"));
            user.setRegistered_date_time(java.sql.Timestamp.valueOf(localDateTime.toLocalDateTime())); // Convert to SQL Timestamp

            // Get user status (assumed 2 means offline)
            User_Status user_Status = (User_Status) session.get(User_Status.class, 2);
            user.setUser_Status(user_Status);

            session.save(user);
            session.getTransaction().commit();

            // Check if avatar image was uploaded
            if (avatarImage != null) {
                // Get the application path and adjust it to avoid "build" directory issues
                String applicationPath = request.getServletContext().getRealPath("");
                String newApplicationPath = applicationPath.replace("build" + File.separator + "web", "web");

                // Define the folder where images will be stored
                File folder = new File(newApplicationPath + File.separator + "AvatarImages");

                // Define the path for the image file
                String avatarImagePath = folder.getAbsolutePath() + File.separator + mobile + ".png";
                System.out.println("Saving avatar image to: " + avatarImagePath);

                // Save the image
                File file = new File(avatarImagePath);
                Files.copy(avatarImage.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            responseJson.addProperty("success", "true");
            responseJson.addProperty("message", "Registration complete");

            session.close();
        }

        // Send JSON response
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(responseJson));
    }
}
