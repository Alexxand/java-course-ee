package controllers;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    private final Logger log = LoggerFactory.getLogger(SessionListener.class);

    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        session.setAttribute("logIn",false);
        session.setAttribute("locale", "en");
        log.info("Session created: " + session.getId());
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        log.info("Session destroyed: " + event.getSession().getId());
    }
}
