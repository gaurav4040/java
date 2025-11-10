package org.entities;

import java.util.List;

public class User {

    private String user_id;

    private String name;

    private String password;

    private String hash_pass;

    private List<Ticket> tickets_booked;

    public void printTickets(){
        tickets_booked.forEach(ticket -> System.out.println(ticket.getTicketInfo ()));
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public User(String user_id, String name, String password, String hash_pass, List<Ticket> tickets_booked) {
        this.user_id = user_id;
        this.name = name;
        this.password = password;
        this.hash_pass = hash_pass;
        this.tickets_booked = tickets_booked;
    }

    public User() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHash_pass() {
        return hash_pass;
    }

    public void setHash_pass(String hash_pass) {
        this.hash_pass = hash_pass;
    }

    public List<Ticket> getTickets_booked() {
        return tickets_booked;
    }

    public void setTickets_booked(List<Ticket> tickets_booked) {
        this.tickets_booked = tickets_booked;
    }

}
