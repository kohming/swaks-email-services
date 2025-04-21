package com.pratesis;


import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedReader;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/sendmail")
public class SendMailResource {

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response sendMail(String message) {
        System.out.println("Message " + message);
        sendEmail(message);
        return Response.ok("Email request processed.").build();
    }

    public void sendEmail(String message) {
        try {
            String[] command = {
                "swaks",
                "--to", "pfebrian@gmail.com",
                "--from", "scyllax.pratesis@gmail.com",
                "--server", "smtp.gmail.com",
                "--port", "587",
                "--auth", "LOGIN",
                "--auth-user", "scyllax.pratesis@gmail.com",
                "--auth-password", "yuxvpldheoyislsr",
                "--tls",
                "--header", "Subject: Halo dari Swaks via Gmail",
                "--body", message
            };
    
            Process process = Runtime.getRuntime().exec(command);
    
            // Tampilkan output dari proses
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
    
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Email sent successfully.");
            } else {
                System.out.println("Failed to send email. Exit code: " + exitCode);
            }
    
        } catch (Exception e) {
            System.out.println("Failed to send email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}



