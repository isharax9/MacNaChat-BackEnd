package controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.Chat;
import entity.User;
import entity.User_Status;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author isharaLakshitha
 */
@WebServlet(name = "LoadHomeData", urlPatterns = {"/LoadHomeData"})
public class LoadHomeData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("success", false);
        responseJson.addProperty("message", "Unable to process your request");

        Session session = null; // Declare session outside the try block
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            // Get user id from request parameters
            String userId = request.getParameter("id");

            // Get user object
            User user = (User) session.get(User.class, Integer.parseInt(userId));

            // Get user status = 1 (online)
            User_Status user_Status = (User_Status) session.get(User_Status.class, 1);

            // Update user status
            user.setUser_Status(user_Status);
            session.update(user);

            // Get other users
            Criteria criteria1 = session.createCriteria(User.class);
            criteria1.add(Restrictions.ne("id", user.getId()));
            List<User> otherUserList = criteria1.list();

            // Create JSON array for chats
            JsonArray jsonChatArray = new JsonArray();

            // Iterate over other users
            for (User otherUser : otherUserList) {
                // Get last conversation
                Criteria criteria2 = session.createCriteria(Chat.class);
                criteria2.add(Restrictions.or(
                        Restrictions.and(
                                Restrictions.eq("from_user", user),
                                Restrictions.eq("to_user", otherUser)
                        ),
                        Restrictions.and(
                                Restrictions.eq("from_user", otherUser),
                                Restrictions.eq("to_user", user)
                        )
                ));
                criteria2.addOrder(Order.desc("id"));
                criteria2.setMaxResults(1);

                // Create chat item JSON
                JsonObject chatItem = new JsonObject();
                chatItem.addProperty("other_user_id", otherUser.getId());
                chatItem.addProperty("other_user_mobile", otherUser.getMobile());
                chatItem.addProperty("other_user_name", otherUser.getFirst_name() + " " + otherUser.getLast_name());
                chatItem.addProperty("other_user_status", otherUser.getUser_Status().getId());

                // Check avatar image
                String serverPath = request.getServletContext().getRealPath("");
                String otherUserAvatarImagePath = serverPath + File.separator + "AvatarImages" + File.separator + otherUser.getMobile() + ".png";
                File otherUserAvatarImageFile = new File(otherUserAvatarImagePath);

                if (otherUserAvatarImageFile.exists()) {
                    chatItem.addProperty("avatar_image_found", true);
                } else {
                    chatItem.addProperty("avatar_image_found", false);
                    chatItem.addProperty("other_user_avatar_letters", otherUser.getFirst_name().charAt(0) + "" + otherUser.getLast_name().charAt(0));
                }

                // Get chat list
                List<Chat> dbChatList = criteria2.list();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy, MMM dd hh:mm a");

                if (dbChatList.isEmpty()) {
                    // No chat
                    chatItem.addProperty("message", "Let's Start New Conversation");
                    chatItem.addProperty("dateTime", dateFormat.format(user.getRegistered_date_time()));
                    chatItem.addProperty("chat_status_id", 1); // Seen = 1
                } else {
                    // Found last chat
                    chatItem.addProperty("message", dbChatList.get(0).getMessage());
                    chatItem.addProperty("dateTime", dateFormat.format(dbChatList.get(0).getDate_time()));
                    chatItem.addProperty("chat_status_id", dbChatList.get(0).getChat_Status().getId()); // Seen = 1
                }

                jsonChatArray.add(chatItem); // Add chat item to array
            }

            // Send users and chat array in response
            responseJson.addProperty("success", true);
            responseJson.addProperty("message", "Success");
            responseJson.add("user", gson.toJsonTree(user));
            responseJson.add("jsonChatArray", gson.toJsonTree(jsonChatArray));

            session.beginTransaction().commit(); // Commit transaction
        } catch (Exception e) {
            responseJson.addProperty("message", e.getMessage()); // Return error message
            e.printStackTrace(); // Log the exception for debugging
        } finally {
            if (session != null && session.isOpen()) {
                session.close(); // Ensure session is closed
            }
        }

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(responseJson)); // Write response
    }
}
