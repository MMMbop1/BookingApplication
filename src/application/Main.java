package application;
	
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
			
			Hotel hotel = new Hotel();
			ArrayList<Room> rooms = new ArrayList<>();
			
			rooms.addAll(Stream.generate(() -> 
				new Room(RoomProperties.SINGLE_ROOM.getRoomType(), hotel))
				.limit(RoomProperties.SINGLE_ROOMS_AVAILABLE.getValue())
				.collect(Collectors.toCollection(ArrayList::new)));
			
			rooms.addAll(Stream.generate(() -> 
				new Room(RoomProperties.DOUBLE_ROOM.getRoomType(), hotel))
				.limit(RoomProperties.DOUBLE_ROOMS_AVAILABLE.getValue())
				.collect(Collectors.toCollection(ArrayList::new)));
			
			rooms.addAll(Stream.generate(() -> 
				new Room(RoomProperties.EN_SUITE.getRoomType(), hotel))
				.limit(RoomProperties.EN_SUITE_AVAILABLE.getValue())
				.collect(Collectors.toCollection(ArrayList::new)));
			
			hotel.setRooms(rooms);			
			GuestBook guestBook = new GuestBook();
			hotel.setGuestBook(guestBook);			
			Booker booker = new Booker(hotel);
			while(true) {
				booker.addBooking();				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
