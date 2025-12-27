/*
 Hotel Booking Application 
 A hotel has a limited number of rooms. 
 bookRoom(int nights) throws: 
o NoRoomAvailableException if fully booked. 
o InvalidStayDurationException if nights ≤ 0. 
 Ensure booking details are saved to file with proper exception handling. 
*/


import java.util.Scanner;

class NoRoomAvailableException extends Exception {
    NoRoomAvailableException(String message) {
        super(message);
    }
}

class InvalidStayDurationException extends Exception {
    InvalidStayDurationException(String message) {
        super(message);
    }
}

class Hotel {
    int availableRooms;
    Hotel(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public void bookRoom(int nights) throws NoRoomAvailableException, InvalidStayDurationException {
        if (nights <= 0) throw new InvalidStayDurationException("Stay duration must be > 0 nights.");
        if (availableRooms <= 0) throw new NoRoomAvailableException("No rooms available.");
        availableRooms--;  // this structure is made for one person can booked only one room...
    }
}

public class P_27 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Hotel hotel = new Hotel(3); // initially 3 rooms available

        System.out.print("Enter number of nights to stay: ");
        int nights = scan.nextInt();

        try {
            hotel.bookRoom(nights);
            System.out.println("Room booked successfully! Remaining rooms: " + hotel.availableRooms);
        } catch (InvalidStayDurationException e) {
            System.out.println("Invalid Input. " + e.getMessage());
        } catch (NoRoomAvailableException e) {
            System.out.println("Booking Failed. " + e.getMessage());
        } finally {
            scan.close();
        }
    }
}
