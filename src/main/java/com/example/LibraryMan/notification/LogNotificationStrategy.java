package com.example.LibraryMan.notification;

import org.springframework.stereotype.Component;

@Component("log")
public class LogNotificationStrategy implements NotificationStrategy {

    @Override
    public void notify(Long userId, String bookTitle) {
        System.out.println("Notification prepared for user_id: " + userId + ": Book [" + bookTitle + "] is now available.");
    }

}
