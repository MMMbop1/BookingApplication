package application;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Serializer {

	private final String fileName = "guests.xml";
	
	public Serializer() {}

	public void writeObject(ArrayList<Guest> guests) {
		
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			XMLEncoder xmlEncoder = new XMLEncoder(fos);
			
			for (Guest guest : guests) {
				xmlEncoder.writeObject(guest);
			}
			
			xmlEncoder.flush();
			xmlEncoder.close();
			
		} catch(IOException ex) {
			System.out.println("Something went wrong with writing Objects: " + ex.getMessage());
		}
	}
}
