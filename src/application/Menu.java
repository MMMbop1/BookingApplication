package application;

import java.util.ArrayList;

public class Menu {
	private Booker booker;
	
	public Menu() {}

	public Menu(Booker booker) {
		this.booker = booker;
	}
	
	public Booker getBooker() {
		return booker;
	}

	public void setBooker(Booker booker) {
		this.booker = booker;
	}
	
	public void userOptions() {
		int choice;
		boolean iterate = true;
		
		while(iterate) {
			try {
				System.out.println(printOptions());
				choice = getBooker().userInputInt();
				switch(choice) {
				case 0 -> iterate = false;
				case 1 -> booker.addBooking();
				case 2 -> {
					BookingTicket bookingTicket = booker.getBookingByBookingId();
					if (bookingTicket == null) {
						System.out.println("Could not find booking");
					} else {
						System.out.println(bookingTicket.toString());
					}					
				}
				case 3 -> {
					ArrayList<BookingTicket> bookingTickets = booker.getAllBookings();
					if (bookingTickets.size() == 0) {
						System.out.println("No bookings made so far");
					} else {
						for (BookingTicket bookingTicket : bookingTickets) {
							System.out.println(bookingTicket.toString());
						}
					}
				}
				case 4 -> {
					Guest guest = booker.getBookingByName();
					if (guest == null) {
						System.out.println("No booking found for given name");
					} else {
						System.out.println(guest.getBookingTicket().toString());						
					}					
				}
				case 5 -> booker.removeBookingByBookingNumber();
				case 6 -> System.out.println(booker.roomsAvailableTimePeriod());
				case 7 -> booker.saveToFile();
				case 8 -> booker.readObject();
				}
			} catch(RuntimeException ex) {
				System.out.println(ex.getMessage());
			}
		}
		
		System.out.println("Booking is terminated");
		System.exit(0);	
	}
	
	
	public String printOptions() {
		String instructions =  """
				
				
				***********************************************************************************************
				
				Bokningsapplikation
				Vi har 100 Rum varav 55 är Enkelrum, 43 Dubbelrum och 2 sviter.
				Här erbjuder vi följande tjänster och ni anger med ett heltal för det val som önskas:
				
				0) Avsluta program
				1) Göra en bokning
				2) Sök bokning efter bokningsnummer
				3) Se alla bokningar
				4) Sök bokning efter namn
				5) Ta bort bokning efter bokningsnummer
				6) Lediga rum för datum
				7) Överför aktuella bokningar till fil
				8) Läs fil och överför till Aktuella bokningar i programmet
			
				***********************************************************************************************
				
				
				""";
		
		return instructions;

	}
	
}
