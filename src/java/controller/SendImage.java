package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author isharaLakshitha
 */
@WebServlet(name = "SendImage", urlPatterns = {"/SendImage"})
@MultipartConfig
public class SendImage extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String loggedUserId = request.getParameter("logged_user_id");
        String chatId = request.getParameter("chat_id");

        // Get the uploaded image file
        Part filePart = request.getPart("image");
        if (filePart != null && filePart.getSize() > 0) {
            // Create a folder if not exists for chat images
            String uploadDir = getServletContext().getRealPath("") + File.separator + "ChatImages";
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }

            // Save the image file
            String imageName = loggedUserId + "_" + chatId + ".jpeg"; // Dynamically naming the file
            File imageFile = new File(uploadDir, imageName);
            try (InputStream fileContent = filePart.getInputStream()) {
                Files.copy(fileContent, imageFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            // Dynamically construct the image URL to send back
            String imageUrl = request.getContextPath() + "/ChatImages/" + imageName;

            // Send the image URL back as response
            response.setContentType("application/json");
            response.getWriter().write("{\"image_url\": \"" + imageUrl + "\"}");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No image file received.");
        }
    }
}
