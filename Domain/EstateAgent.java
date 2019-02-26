package Domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import FileAccessLayer.EstateAgentDAL;
import Presentation.EstateAgentGUI;

/**
 * 
 * @author @author FatmaUysal-OsmanBozoðlu, 220201051_220201048
 *
 */
public class EstateAgent {
	public ArrayList<House> listHouse;
	private EstateAgentDAL estateAgentDAL;
	private EstateAgentGUI estateAgentGUI;

	public EstateAgent() {
		listHouse = new ArrayList<House>();
		estateAgentDAL = new EstateAgentDAL();
		listHouse = estateAgentDAL.getAllHouses("data/housing.txt");

		estateAgentGUI = new EstateAgentGUI(this);
		estateAgentGUI.createGUI();
		estateAgentGUI.populateJTableHouses();
	}

	public String getNewID() {
		int newID = 0;

		for (int i = 0; i < listHouse.size(); i++) {
			if (listHouse.get(i).getId() > newID) {
				newID = listHouse.get(i).getId();
			}
		}
		newID++;
		return String.valueOf(newID);
	}

	public boolean deleteHouse(int selectedID) {
		boolean result = false;

		for (int i = 0; i < listHouse.size(); i++) {
			if (listHouse.get(i).getId() == selectedID) {
				listHouse.remove(i);
				result = true;
				break;
			}
		}

		return result;
	}

	public boolean addHouse(int id, int price, int size, int rooms, int bathrooms, boolean airConditioner) {
		try {
			House house = new House(id, price, size, rooms, bathrooms, airConditioner);
			listHouse.add(house);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateHouse(int id, int price, int size, int rooms, int bathrooms, boolean airConditioner) {
		boolean result = false;

		for (int i = 0; i < listHouse.size(); i++) {
			if (listHouse.get(i).getId() == id) {
				listHouse.get(i).setPrice(price);
				listHouse.get(i).setSize(size);
				listHouse.get(i).setRooms(bathrooms);
				listHouse.get(i).setBathrooms(bathrooms);
				listHouse.get(i).setAirconditioner(airConditioner);
				result = true;
				break;
			}
		}

		return result;
	}

	public ArrayList<House> saveHousesToFile() {
		try {
			estateAgentDAL.saveAllHouses("data/housing_updated.txt", listHouse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listHouse;
	}

	public ArrayList<House> sortByPriceASC(ArrayList<House> list) {
		Collections.sort(list, new Comparator<House>() {
		    @Override
		    public int compare(House house01, House house02) {
		        return Integer.compare(house01.getPrice(), house02.getPrice());
		    }
		});
		
		return list;
	}

	public ArrayList<House> sortByPriceDES(ArrayList<House> list) {
		Collections.sort(list, new Comparator<House>() {
		    @Override
		    public int compare(House house01, House house02) {
		        return Integer.compare(house02.getPrice(), house01.getPrice());
		    }
		});
		
		return list;
	}

	public ArrayList<House> searchByRooms(int count) {
		ArrayList<House> searchedList = new ArrayList<House>();
		
		for (int i = 0; i < listHouse.size(); i++) {
			if (listHouse.get(i).getRooms() == count) {
				searchedList.add(listHouse.get(i));
			}
		}
		
		return searchedList;
	}

	public ArrayList<House> searchByPrice(int min, int max) {
		ArrayList<House> searchedList = new ArrayList<House>();
		int price = 0;
		
		for (int i = 0; i < listHouse.size(); i++) {
			price = listHouse.get(i).getPrice();
			if (price >= min && price <= max) {
				searchedList.add(listHouse.get(i));
			}
		}
		
		return searchedList;
	}

	public ArrayList<House> searchBySize(int min, int max) {
		ArrayList<House> searchedList = new ArrayList<House>();
		int size = 0;
		
		for (int i = 0; i < listHouse.size(); i++) {
			size = listHouse.get(i).getSize();
			if (size >= min && size <= max) {
				searchedList.add(listHouse.get(i));
			}
		}
		
		return searchedList;
	}
}