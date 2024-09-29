package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet(name = "SignUp", urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();

        String mobile = request.getParameter("mobile");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        Part avatarImage = request.getPart("avatarImage");

        // Get the application path and adjust it to avoid "build" directory issues
        String applicationPath = request.getServletContext().getRealPath("");
        String newApplicationPath = applicationPath.replace("build" + File.separator + "web", "web");
        
        // Define the folder where images will be stored
        File folder = new File(newApplicationPath + File.separator + "AvatarImages");
        
        // Create folder if it doesn't exist
        if (!folder.exists()) {
            folder.mkdirs(); // Use mkdirs() to create the directory and any missing parent directories
        }
        
        // Define the path for the image file
        String avatarImagePath = folder.getAbsolutePath() + File.separator + mobile + ".png";
        System.out.println("Saving avatar image to: " + avatarImagePath);
        
        // Save the image
        File file = new File(avatarImagePath);
        Files.copy(avatarImage.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);

        // Prepare JSON response
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("message", "Server: Hello!");

        // Send JSON response
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(responseJson));
    }
}
