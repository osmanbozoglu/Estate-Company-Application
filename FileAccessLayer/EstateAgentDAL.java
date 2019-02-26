package FileAccessLayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import Domain.House;

public class EstateAgentDAL {

	public ArrayList<House> getAllHouses(String path) {
		ArrayList<House> listHouse = new ArrayList<House>();

		House house;
		Scanner scanner;
		StringTokenizer tokenizer;
		int id = 0, price = 0, size = 0, rooms = 0, bathrooms = 0;
		boolean airconditioner = false;

		try {
			scanner = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			System.out.println("File does not exist!");
			return listHouse;
		}

		int counter = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			tokenizer = new StringTokenizer(line, ",");

			if (counter > 0) {
				if (tokenizer.hasMoreTokens()) {
					id = Integer.parseInt(tokenizer.nextToken());
				}

				if (tokenizer.hasMoreTokens()) {
					price = Integer.parseInt(tokenizer.nextToken());
				}

				if (tokenizer.hasMoreTokens()) {
					size = Integer.parseInt(tokenizer.nextToken());
				}

				if (tokenizer.hasMoreTokens()) {
					rooms = Integer.parseInt(tokenizer.nextToken());
				}

				if (tokenizer.hasMoreTokens()) {
					bathrooms = Integer.parseInt(tokenizer.nextToken());
				}

				if (tokenizer.hasMoreTokens()) {
					if (tokenizer.nextToken().equals("yes"))
						airconditioner = true;
					else
						airconditioner = false;
				}

				house = new House(id, price, size, rooms, bathrooms, airconditioner);
				// System.out.println(house.toString());
				listHouse.add(house);
			}

			counter++;
		}
		scanner.close();

		return listHouse;
	}

	public void saveAllHouses(String path, ArrayList<House> listHouse) {
		PrintWriter outputStream;

		try {
			outputStream = new PrintWriter(new FileOutputStream(path));
		} catch (Exception e) {
			System.out.println("Cannot write to file!");
			return;
		}

		outputStream.println("id,price($),size(m^2),rooms,bathrooms,airconditioner");
		for (House house : listHouse) {
			outputStream.println(house.toFile());
		}

		outputStream.close();
	}

}
