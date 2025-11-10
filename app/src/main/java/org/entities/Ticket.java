package org.entities;

import java.util.Date;

public class Ticket {

    private String ticket_id;

    private String user_id;

    private String source;

    private String dest;

    private Date date_of_travel;

    private Train train;

    private String ticket_info;

    public String getTicket_info() {
        return ticket_info;
    }

    public void setTicket_info(String ticket_info) {
        this.ticket_info = String.format("Ticket ID: %s belongs to %S from %s to %s on %s",ticket_id,user_id,source,dest,date_of_travel);
    }

    public String getTicketInfo(){
        return ticket_info;
    }

    public Ticket() {
    }

    public Ticket(String ticket_id, String user_id, String source, String dest, Date date_of_travel, Train train) {
        this.ticket_id = ticket_id;
        this.user_id = user_id;
        this.source = source;
        this.dest = dest;
        this.date_of_travel = date_of_travel;
        this.train = train;
    }

    public String getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public Date getDate_of_travel() {
        return date_of_travel;
    }

    public void setDate_of_travel(Date date_of_travel) {
        this.date_of_travel = date_of_travel;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}
