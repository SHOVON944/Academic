/*
Airline Ticket Reservation 
 A class Flight has a limited number of seats. 
 Implement a method bookTicket(int seats) that throws: 
o OverbookingException if requested seats exceed available seats. 
 Also handle exceptions if the user provides negative or zero seats. 
*/

import java.util.Scanner;

class OverBookingException extends Exception{
    OverBookingException(String message){
        super(message);
    }
}

class Flight{
    int available_seats;
    Flight(int available_seats){
        this.available_seats = available_seats;
    }
    public void bookTicket(int seats) throws OverBookingException{
        if(seats <= 0) throw new IllegalArgumentException("Seats must be > 0.");
        if(seats>available_seats) throw new OverBookingException("Requested exceeds availabe.");
        available_seats -= seats;
    }

}

public class P_15 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter number of seats to book? ");
        int booking = scan.nextInt();
        Flight fl8 = new Flight(5);     // Initiall seat fix 5.

        try{
            fl8.bookTicket(booking);
            System.out.println("Booked Successfully. Remaining: " + fl8.available_seats);
        } catch(IllegalArgumentException e){
            System.out.println("Invalid Input. " + e.getMessage());
        } catch(OverBookingException e){
            System.out.println("Can't Booking. " + e.getMessage());
        } finally{
            scan.close();
        }
    }
}