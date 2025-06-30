package com.example.LibraryMan.notification;

public interface NotificationStrategy {
    void notify(Long userId, String bookTitle);
}
