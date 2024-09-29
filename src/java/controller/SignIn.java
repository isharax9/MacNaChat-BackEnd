/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import model.Validations;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author isharaLakshitha
 */
@WebServlet(name = "SignIn", urlPatterns = {"/SignIn"})
public class SignIn extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("success", false);

        JsonObject requestJson = gson.fromJson(request.getReader(), JsonObject.class);
        String mobile = requestJson.get("mobile").getAsString();
        String password = requestJson.get("password").getAsString();

        if (mobile.isEmpty()) {
            responseJson.addProperty("message", "Please Fill Your Mobile Number");
        } else if (!Validations.isMobileNumberValid(mobile)) {
            responseJson.addProperty("message", "Invalid Mobile Number");
        } else if (password.isEmpty()) {
            responseJson.addProperty("message", "Please Fill Your Password");
        } else if (password.length() < 8) {
            responseJson.addProperty("message", "Password must include at least one uppercase letter, number, special character and be at least eight characters long");
        } else {
            // Validations passed
            Session session = HibernateUtil.getSessionFactory().openSession();

            //search mobile number and password
            Criteria criterial = session.createCriteria(User.class);
            criterial.add(Restrictions.eq("mobile", mobile));
            criterial.add(Restrictions.eq("password", password));

            System.out.println("Searching for user with mobile: " + mobile + " and password: " + password); // Added console log

            if (!criterial.list().isEmpty()) {       //user found
                System.out.println("User found!"); // Added console log
                User user = (User) criterial.uniqueResult();

                responseJson.addProperty("success", true);
                responseJson.addProperty("message", "Sign In Success");
                responseJson.add("user", gson.toJsonTree(user));
            } else {                                 //user not found
                System.out.println("User not found!"); // Added console log
                responseJson.addProperty("message", "Invalid Credentials!");
            }
            session.close();
        }

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(responseJson));
    }

}
