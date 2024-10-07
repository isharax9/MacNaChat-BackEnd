package controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import entity.User;
import entity.User_Status;
import javax.servlet.annotation.WebServlet;
import model.HibernateUtil;

@WebServlet(name = "SignOut", urlPatterns = {"/SignOut"})
public class SignOut extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId"); // Retrieve user ID from request

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Retrieve the user by ID
            User user = (User) session.get(User.class, Integer.parseInt(userId));

            if (user != null) {
                // Fetch the User_Status entity with ID 2 (Signed Out)
                User_Status signedOutStatus = (User_Status) session.get(User_Status.class, 2);

                // Set the user's status to Signed Out
                user.setUser_Status(signedOutStatus);

                // Update the user in the database
                session.update(user);

                // Commit the transaction
                transaction.commit();

                // Send a success response
                response.getWriter().write("User successfully signed out.");
            } else {
                response.getWriter().write("User not found.");
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback in case of failure
            }
            e.printStackTrace();
            response.getWriter().write("Error signing out.");
        } finally {
            session.close();
        }
    }
}
