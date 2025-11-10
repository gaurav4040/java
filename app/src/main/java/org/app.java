package org;

import org.entities.Train;
import org.entities.User;
import org.services.Booking_Service;
import org.util.User_Service_Util;

import java.io.IOException;
import java.util.*;

public class app {

    static void main(String[] args) {
        System.out.println("$~~ RUNNING TRAIN BOOKING SYSTEM ~~$");
        Scanner scanner = new Scanner(System.in);

        int option =0;

        Booking_Service booking_service;

        try{
            booking_service = new Booking_Service();

        } catch (IOException e){
            System.out.println("somethings wrong........");
            return;
        }
        while (option!=7){
            System.out.println("Choose one Option");
            System.out.println("1.signup");
            System.out.println("2.signin/login");
            System.out.println("3.Fetch Bookings");
            System.out.println("4.Search Trains");
            System.out.println("5.Book Seat/s");
            System.out.println("6.Cancel Bookings");
            System.out.println("7.Exit App");
            option = scanner.nextInt();
            Train trainSelectedForBooking = new Train();
            switch (option){
                case 1:
                    System.out.println("Enter name to signup");
                    String name_to_signup = scanner.next();
                    System.out.println("Enter the password");
                    String pass_to_signup = scanner.next();
                    User user_to_signup = new User(
                                UUID.randomUUID().toString(),
                                name_to_signup,
                                pass_to_signup,
                                User_Service_Util.hashPass(pass_to_signup),
                                new ArrayList<>()
                            );
                    booking_service.sign_up(user_to_signup);
                    break;

                case 2:
                    System.out.println("Enter name to signin/login");
                    String name_to_signin = scanner.next();
                    System.out.println("Enter the password");
                    String pass_to_signin = scanner.next();
                    User user_to_signin = new User(
                            UUID.randomUUID().toString(),
                            name_to_signin,
                            pass_to_signin,
                            User_Service_Util.hashPass(pass_to_signin),
                            new ArrayList<>()
                    );
                    try{
                        booking_service = new Booking_Service(user_to_signin);
                    }catch (IOException e){
                        return;
                    }
                    break;

                case 3:
                    System.out.println("Fetch Bookings");
                    booking_service.fetch_bookings();
                    return;

                case 4:
                    System.out.println("Type your source station");
                    String source = scanner.next();
                    System.out.println("Type your destination station");
                    String dest = scanner.next();
                    List<Train> trains = booking_service.get_trains(source,dest);

                    int index = 1;
                    for(Train t:trains){
                        System.out.println(index + "Train id : "+t.getTrain_id());
                        for (Map.Entry<String,String>entry:t.getStation_time().entrySet()){
                            System.out.println("station "+entry.getKey()+" time: "+ entry.getValue());
                        }
                    }
                    System.out.println("select a train by typing 1,2,3.....");
                    trainSelectedForBooking = trains.get(scanner.nextInt());
                    break;
                case 5:
                    System.out.println("Select a seat out of these seats");
                    List<List<Integer>> seats = booking_service.fetch_seats(trainSelectedForBooking);
                    for (List<Integer> row: seats){
                        for (Integer val: row){
                            System.out.print(val+" ");
                        }
                        System.out.println();
                    }
                    System.out.println("Select the seat by typing the row and column");
                    System.out.println("Enter the row");
                    int row = scanner.nextInt();
                    System.out.println("Enter the column");
                    int col = scanner.nextInt();
                    System.out.println("Booking your seat....");
                    Boolean booked = booking_service.book_train_seat(trainSelectedForBooking, row, col);
                    if(booked.equals(Boolean.TRUE)){
                        System.out.println("Booked! Enjoy your journey");
                    }else{
                        System.out.println("Can't book this seat");
                    }
                    break;
                default:
                    break;
            }



        }

    }
}
