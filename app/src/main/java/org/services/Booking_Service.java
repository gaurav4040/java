package org.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.entities.Train;
import org.entities.User;
import org.util.User_Service_Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Booking_Service  {

    private User user;

    private List<User> userList;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final String USERS_PATH = "app/src/main/java/org/local_db/users.json";

    public Booking_Service()throws IOException {
        load_users();
    }

    public  Booking_Service(User user)throws IOException{
        this.user = user;
        load_users();
    }

    public List<User> load_users()throws IOException{
        File users = new File(USERS_PATH);
        return userList = OBJECT_MAPPER.readValue(users, new TypeReference<List<User>>() {});
    }

    public Boolean login_user(){
        Optional<User> user1 = userList.stream().filter(u -> {
            return u.getName().equals(user.getName())&& User_Service_Util.checkPass(user.getPassword(),u.getHash_pass());
        }).findFirst();

        return user1.isPresent();
    }

    public Boolean sign_up(User u){
        try{
            userList.add(u);
            saveUserListToFile();
            return true;
        }catch (IOException ex){
            return false;
        }
    }

    private void saveUserListToFile() throws IOException{
        File userFile = new File(USERS_PATH);
        OBJECT_MAPPER.writeValue(userFile,userList);
    }

    public void fetch_bookings(){
        Optional<User> userFetched = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && User_Service_Util.checkPass(user.getPassword(), user1.getHash_pass());
        }).findFirst();
        if(userFetched.isPresent()){
            userFetched.get().printTickets();
        }
    }

    public Boolean cancel_bookings(String ticketId){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the ticket id to cancel");
        ticketId = s.next();

        if (ticketId == null || ticketId.isEmpty()) {
            System.out.println("Ticket ID cannot be null or empty.");
            return Boolean.FALSE;
        }

        String finalTicketId1 = ticketId;  //Because strings are immutable
        boolean removed = user.getTickets_booked().removeIf(ticket -> ticket.getTicket_id().equals(finalTicketId1));

        String finalTicketId = ticketId;
        user.getTickets_booked().removeIf(Ticket -> Ticket.getTicket_id().equals(finalTicketId));
        if (removed) {
            System.out.println("Ticket with ID " + ticketId + " has been canceled.");
            return Boolean.TRUE;
        }else{
            System.out.println("No ticket found with ID " + ticketId);
            return Boolean.FALSE;
        }
    }

    public List<Train> get_trains(String src ,String dest){
        try{
            Train_Service trainService = new Train_Service();
            return trainService.search_trains(src,dest);
        }catch (IOException e){
            return new ArrayList<>();
        }
    }

    public List<List<Integer>> fetch_seats(Train train){
        return train.getTrain_seats();
    }

    public Boolean book_train_seat(Train train, int row, int seat) {
        try{
            Train_Service trainService = new Train_Service();
            List<List<Integer>> seats = train.getTrain_seats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setTrain_seats(seats);
                    trainService.add_train(train);
                    return true; // Booking successful
                } else {
                    return false; // Seat is already booked
                }
            } else {
                return false; // Invalid row or seat index
            }
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }
}
