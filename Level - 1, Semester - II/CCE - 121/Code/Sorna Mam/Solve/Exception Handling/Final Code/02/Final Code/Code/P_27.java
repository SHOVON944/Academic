/*
27. Hotel Booking Application 
 A hotel has a limited number of rooms. 
 bookRoom(int nights) throws: 
o NoRoomAvailableException if fully booked. 
o InvalidStayDurationException if nights ≤ 0.
*/


import java.util.*;

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
    private int totalRooms;
    private int bookedRooms = 0;

    Hotel(int totalRooms) {
        this.totalRooms = totalRooms;
    }

    void bookRoom(int nights) throws NoRoomAvailableException, InvalidStayDurationException {
        if (nights <= 0) {
            throw new InvalidStayDurationException("Number of nights must be greater than 0.");
        }
        if (bookedRooms >= totalRooms) {
            throw new NoRoomAvailableException("Sorry, no rooms are available for booking.");
        }

        bookedRooms++;
        System.out.println("Room booked successfully for " + nights + " night(s).");
        System.out.println("Available rooms left: " + (totalRooms - bookedRooms));
    }
}

public class P_27 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter total number of rooms in the hotel: ");
        int totalRooms = scan.nextInt();

        Hotel hotel = new Hotel(totalRooms);

        while (true) {
            System.out.print("\nEnter number of nights to book (or 0 to exit): ");
            int nights = scan.nextInt();
            if (nights == 0) break;

            try {
                hotel.bookRoom(nights);
            } 
            catch (InvalidStayDurationException | NoRoomAvailableException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("\n Booking session ended. Thank you!");
        scan.close();
    }
}
