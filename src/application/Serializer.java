package application;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Serializer {

	private final String fileName = "guests.xml";
	
	public Serializer() {}
	
	public String getFileName() {
		return fileName;
	}

	public void writeObject(ArrayList<Guest> guests) {
		
		try {
			FileOutputStream fos = new FileOutputStream(getFileName());
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
	
	public ArrayList<Guest> readObject() {
		ArrayList<Guest> guests = new ArrayList<>();
		try {
			FileInputStream fis = new FileInputStream(getFileName());	
			XMLDecoder xmlDecoder = new XMLDecoder(fis);
			while (true) {
				guests.add((Guest) xmlDecoder.readObject());				
			}
		} catch(IOException ex) {
			System.out.println("Something went wrong with reading Objects: " + ex.getMessage());
		} catch(ArrayIndexOutOfBoundsException ex2) {
		}
		
		return guests;
	}
 }
