package Domain;

/**
 * 
 * @author @author FatmaUysal-OsmanBozoðlu, 220201051_220201048
 *
 */
public class House {
	private int ID, price, size, rooms, bathrooms;
	private boolean airconditioner;

	public House(int id, int price, int size, int rooms, int bathrooms, boolean airconditioner) {
		this.ID = id;
		this.price = price;
		this.size = size;
		this.rooms = rooms;
		this.bathrooms = bathrooms;
		this.airconditioner = airconditioner;
	}

	public int getId() {
		return ID;
	}

	public void setId(int id) {
		this.ID = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public int getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(int bathrooms) {
		this.bathrooms = bathrooms;
	}

	public boolean isAirconditioner() {
		return airconditioner;
	}

	public String isAirconditionerAsText() {
		if (airconditioner)
			return "yes";
		else
			return "no";
	}

	public void setAirconditioner(boolean airconditioner) {
		this.airconditioner = airconditioner;
	}

	public String toFile() {
		String acState = "";
		if (airconditioner)
			acState = "yes";
		else
			acState = "no";
		return ID + "," + price + "," + size + "," + rooms + "," + bathrooms + "," + acState;
	}

	@Override
	public String toString() {
		String acState = "";
		if (airconditioner)
			acState = "yes";
		else
			acState = "no";
		return "House [ID=" + ID + ", Price=" + price + ", Size=" + size + ", Rooms=" + rooms + ", Bathrooms="
				+ bathrooms + ", Airconditioner=" + acState + "]";
	}
}