package controller;

import entity.Chat;
import entity.User;
import java.io.IOException;
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
@WebServlet(name = "LoadChat", urlPatterns = {"/LoadChat"})
public class LoadChat extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String logged_user_id = request.getParameter("logged_user_id");
        String other_user_id = request.getParameter("other_user_id");

        //get logged_user
        User logged_user = (User) session.get(User.class, Integer.parseInt(logged_user_id));

        //get other_user
        User other_user = (User) session.get(User.class, Integer.parseInt(other_user_id));

        //get chats
        Criteria criteria1 = session.createCriteria(Chat.class);
        criteria1.add(Restrictions.or(
                Restrictions.and(Restrictions.eq("from_user", logged_user), Restrictions.eq("to_user", other_user)),
                Restrictions.and(Restrictions.eq("from_user", other_user), Restrictions.eq("to_user", logged_user))
        )
        );

        //sort chats
        criteria1.addOrder(Order.asc("date_time"));

        //get chat list
        List<Chat> chat_list = criteria1.list();
        for(Chat chat : chat_list)
        System.out.println(chat.getMessage()); //loadChat?logged_user_id=1&other_user_id=2
    }

}
