package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SignUp", urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();

        JsonObject requestJson = gson.fromJson(request.getReader(), JsonObject.class);
        

        System.out.println(requestJson.get("mobile").getAsString());
        System.out.println(requestJson.get("firstName").getAsString());
        System.out.println(requestJson.get("lastName").getAsString());
        System.out.println(requestJson.get("password").getAsString());
        System.out.println(requestJson.get("message").getAsString());

        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("message", "Server: Hello!");

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(responseJson));
        
  
        
        
        
        
        
        
        
        
        
        
        
        

//        Gson gson = new Gson();
//
//        JsonObject requestJson = gson.fromJson(request.getReader(), JsonObject.class);
//        
//        System.out.println(requestJson.get("message").getAsString());
//        
//        
//        String mobile = request.getParameter("mobile");
//        String firstName = request.getParameter("firstName");
//        String lastName = request.getParameter("lastName");
//        String password = request.getParameter("password");
//        Part avatarImage = request.getPart("avatar-image");
//
//        String serverPath = request.getServletContext().getRealPath("");
//        String avatarImagePath = serverPath + File.separator + "AvatarImages" + File.separator + mobile + ".png";
//        System.out.println(avatarImagePath);
//        File file = new File(avatarImagePath);
//
//        Files.copy(avatarImage.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
//
//        JsonObject responseJson = new JsonObject();
//        responseJson.addProperty("message", "Server:Hello!");
//
//        response.setContentType("application/json");
//        response.getWriter().write(gson.toJson(responseJson));
    }
}
