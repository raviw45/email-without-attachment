package com.email.services;

public interface EmailServices {
   public boolean sendEmail(String from,String to,String content,String subject);
}
