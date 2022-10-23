package moodstoreframework.moodstoreframeworkthirdparty.service;

import org.springframework.stereotype.Service;


public interface EmailService {
    public void sendSimpleMail(String from, String to, String subject, String text);
}
