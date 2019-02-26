package Presentation;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Domain.EstateAgent;
import Domain.House;

public class EstateAgentGUI implements ActionListener {
	public ArrayList<House> listHouseGrid;
	EstateAgent estateAgent;
	JFrame jFrameMain;
	Container containerMain;
	BorderLayout borderLayout;

	JTable jTBHouses;
	JLabel jLBID, jLBPrice, jLBSize, jLBRooms, jLBBathrooms, jLBAirConditioner;
	JTextField jTFID, jTFPrice, jTFSize, jTFRooms, jTFBathrooms;
	@SuppressWarnings("rawtypes")
	JComboBox jCBAirConditioner;
	JButton jBNAdd, jBNUpdate, jBNDelete, jBNApply, jBNCancel, jBNClear, jBNExit, jBNRefreshList;

	JLabel jLBOrdering;
	JRadioButton jRBAscending, jRBDescending;
	JButton jBNOrder;

	JRadioButton jRBSearchByPrice, jRBSearchBySize, jRBSearchByRooms;
	JTextField jTFSearchMin, jTFSearchMax;
	JButton jBNSearch;

	private int selectedID = 0;

	private enum ProcessType {
		ADD, UPDATE, DELETE, CLEAR, APPLY, CANCEL
	}

	private ProcessType processType;

	public EstateAgentGUI(EstateAgent estateAgent) {
		this.estateAgent = estateAgent;
		this.listHouseGrid = this.estateAgent.listHouse;

		jFrameMain = new JFrame();
		containerMain = jFrameMain.getContentPane();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createGUI() {
		borderLayout = new BorderLayout();
		containerMain.setLayout(borderLayout);

		jLBID = new JLabel("ID");
		jLBPrice = new JLabel("Price");
		jLBSize = new JLabel("Size");
		jLBRooms = new JLabel("Rooms");
		jLBBathrooms = new JLabel("Bathrooms");
		jLBAirConditioner = new JLabel("Air Conditioner");

		jTFID = new JTextField(20);
		jTFPrice = new JTextField(20);
		jTFSize = new JTextField(20);
		jTFRooms = new JTextField(20);
		jTFBathrooms = new JTextField(20);

		String[] transTypes = { "yes", "no" };
		jCBAirConditioner = new JComboBox(transTypes);
		jCBAirConditioner.setSelectedIndex(0);

		jBNAdd = new JButton("Add");
		jBNAdd.addActionListener(this);
		jBNUpdate = new JButton("Update");
		jBNUpdate.addActionListener(this);
		jBNDelete = new JButton("Delete");
		jBNDelete.addActionListener(this);
		jBNApply = new JButton("Apply");
		jBNApply.addActionListener(this);
		jBNCancel = new JButton("Cancel");
		jBNCancel.addActionListener(this);
		jBNClear = new JButton("Clear");
		jBNClear.addActionListener(this);
		jBNSearch = new JButton("Search");
		jBNSearch.addActionListener(this);
		jBNExit = new JButton("Exit");
		jBNExit.addActionListener(this);
		jBNRefreshList = new JButton("Refresh");
		jBNRefreshList.addActionListener(this);

		jLBOrdering = new JLabel("Order by Price");
		jRBAscending = new JRadioButton("Ascending");
		jRBAscending.setSelected(true);
		jRBDescending = new JRadioButton("Descending");
		jBNOrder = new JButton("Order");
		jBNOrder.addActionListener(this);
		ButtonGroup buttonGroupOrder = new ButtonGroup();
		buttonGroupOrder.add(jRBAscending);
		buttonGroupOrder.add(jRBDescending);

		jRBSearchByPrice = new JRadioButton("Search by Price");
		jRBSearchBySize = new JRadioButton("Search by Size");
		jRBSearchByRooms = new JRadioButton("Search by Rooms");
		jRBSearchByPrice.setSelected(true);
		ButtonGroup buttonGroupSearch = new ButtonGroup();
		buttonGroupSearch.add(jRBSearchByPrice);
		buttonGroupSearch.add(jRBSearchBySize);
		buttonGroupSearch.add(jRBSearchByRooms);
		jRBSearchByPrice.addActionListener(this);
		jRBSearchBySize.addActionListener(this);
		jRBSearchByRooms.addActionListener(this);
		jTFSearchMin = new JTextField(20);
		jTFSearchMax = new JTextField(20);
		jBNSearch = new JButton("Search");
		jBNSearch.addActionListener(this);

		JPanel panelTop = new JPanel();
		panelTop.setLayout(new BorderLayout());
		panelTop.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel panelCRUD = new JPanel();
		panelCRUD.setLayout(new GridLayout(1, 0, 10, 0));
		panelCRUD.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		panelCRUD.add(jBNAdd);
		panelCRUD.add(jBNUpdate);
		panelCRUD.add(jBNDelete);
		panelCRUD.add(jBNClear);
		panelTop.add(panelCRUD, BorderLayout.PAGE_START);

		JPanel panelDATA = new JPanel();
		panelDATA.setLayout(new GridLayout(0, 4, 10, 10));
		Border etchedLoweredBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border compoundBorder = BorderFactory.createCompoundBorder(etchedLoweredBorder,
				new EmptyBorder(10, 10, 10, 10));
		panelDATA.setBorder(compoundBorder);
		panelDATA.add(jLBID);
		panelDATA.add(jTFID);
		panelDATA.add(jLBPrice);
		panelDATA.add(jTFPrice);
		panelDATA.add(jLBSize);
		panelDATA.add(jTFSize);
		panelDATA.add(jLBRooms);
		panelDATA.add(jTFRooms);
		panelDATA.add(jLBBathrooms);
		panelDATA.add(jTFBathrooms);
		panelDATA.add(jLBAirConditioner);
		panelDATA.add(jCBAirConditioner);
		JPanel panelEmpty01 = new JPanel();
		panelDATA.add(panelEmpty01);
		JPanel panelEmpty02 = new JPanel();
		panelDATA.add(panelEmpty02);
		panelDATA.add(jBNApply);
		panelDATA.add(jBNCancel);
		panelTop.add(panelDATA, BorderLayout.CENTER);

		JPanel panelOrderSearch = new JPanel();
		panelOrderSearch.setLayout(new BorderLayout());

		JPanel panelOrdering = new JPanel();
		panelOrdering.setLayout(new GridLayout(1, 0, 10, 0));
		panelOrdering.add(jLBOrdering);
		panelOrdering.add(jRBAscending);
		panelOrdering.add(jRBDescending);
		panelOrdering.add(jBNOrder);
		etchedLoweredBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		compoundBorder = BorderFactory.createCompoundBorder(new EmptyBorder(10, 0, 0, 0), etchedLoweredBorder);
		compoundBorder = BorderFactory.createCompoundBorder(compoundBorder, new EmptyBorder(10, 10, 10, 10));
		panelOrdering.setBorder(compoundBorder);
		panelOrderSearch.add(panelOrdering, BorderLayout.PAGE_START);

		JPanel panelSearching = new JPanel();
		panelSearching.setLayout(new GridLayout(0, 3, 10, 10));
		panelSearching.add(jRBSearchByPrice);
		panelSearching.add(jRBSearchBySize);
		panelSearching.add(jRBSearchByRooms);
		panelSearching.add(jTFSearchMin);
		panelSearching.add(jTFSearchMax);
		panelSearching.add(jBNSearch);
		etchedLoweredBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		compoundBorder = BorderFactory.createCompoundBorder(new EmptyBorder(10, 0, 0, 0), etchedLoweredBorder);
		compoundBorder = BorderFactory.createCompoundBorder(compoundBorder, new EmptyBorder(10, 10, 10, 10));
		panelSearching.setBorder(compoundBorder);
		panelOrderSearch.add(panelSearching, BorderLayout.PAGE_END);

		panelTop.add(panelOrderSearch, BorderLayout.PAGE_END);

		containerMain.add(panelTop, BorderLayout.PAGE_START);

		jTBHouses = new JTable();
		jTBHouses.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if (jTBHouses.getSelectedRow() > -1) {
					// print first column value from selected row
					String ID = jTBHouses.getValueAt(jTBHouses.getSelectedRow(), 0).toString();
					String price = jTBHouses.getValueAt(jTBHouses.getSelectedRow(), 1).toString();
					String size = jTBHouses.getValueAt(jTBHouses.getSelectedRow(), 2).toString();
					String rooms = jTBHouses.getValueAt(jTBHouses.getSelectedRow(), 3).toString();
					String bathrooms = jTBHouses.getValueAt(jTBHouses.getSelectedRow(), 4).toString();
					String airConditioner = jTBHouses.getValueAt(jTBHouses.getSelectedRow(), 5).toString();
					selectedID = Integer.parseInt(ID);
					jTFID.setText(ID);
					jTFPrice.setText(price);
					jTFSize.setText(size);
					jTFRooms.setText(rooms);
					jTFBathrooms.setText(bathrooms);
					if (airConditioner.equals("yes"))
						jCBAirConditioner.setSelectedIndex(0);
					else
						jCBAirConditioner.setSelectedIndex(1);
				}
			}
		});
		JScrollPane jSPHouses = new JScrollPane(jTBHouses);
		JPanel panelHouses = new JPanel();
		panelHouses.setLayout(new BorderLayout());
		panelHouses.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		panelHouses.add(jSPHouses, BorderLayout.CENTER);
		containerMain.add(panelHouses, BorderLayout.CENTER);

		JPanel panelRefresh = new JPanel();
		panelRefresh.setLayout(new GridLayout(1, 0, 0, 0));
		panelRefresh.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		panelRefresh.add(jBNRefreshList);
		containerMain.add(panelRefresh, BorderLayout.PAGE_END);

		jFrameMain.setTitle("Estate Agent");
		jFrameMain.setSize(640, 800);
		jFrameMain.setLocation(200, 100);
		jFrameMain.setResizable(false);
		jFrameMain.setVisible(true);
		jFrameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		enableDataForm(false);
	}

	public void actionPerformed(ActionEvent e) {
		String message = "";
		if (e.getSource() == jBNRefreshList) {
			this.listHouseGrid = estateAgent.listHouse;
			populateJTableHouses();
			populateJTableHouses();
		} else if (e.getSource() == jBNOrder) {
			if (jRBAscending.isSelected()) {
				this.listHouseGrid = estateAgent.sortByPriceASC(this.listHouseGrid);
			} else {
				this.listHouseGrid = estateAgent.sortByPriceDES(this.listHouseGrid);
			}
			populateJTableHouses();
		} else if (e.getSource() == jBNSearch) {
			String typedMin = jTFSearchMin.getText().toString();
			String typedMax = jTFSearchMax.getText().toString();
			if (jRBSearchByRooms.isSelected()) {
				if (!typedMin.equals("")) {
					try {
						int count = Integer.parseInt(typedMin);
						this.listHouseGrid = estateAgent.searchByRooms(count);
						populateJTableHouses();
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
						message = "Room count must be integer!";
						JOptionPane.showMessageDialog(null, message);
					}
				} else {
					message = "Please, type room count for search!";
					JOptionPane.showMessageDialog(null, message);
				}
			} else {
				if (!typedMin.equals("") && !typedMax.equals("")) {
					try {
						int min = Integer.parseInt(typedMin);
						int max = Integer.parseInt(typedMax);
						if (jRBSearchByPrice.isSelected()) {
							this.listHouseGrid = estateAgent.searchByPrice(min, max);
						} else if (jRBSearchBySize.isSelected()) {
							this.listHouseGrid = estateAgent.searchBySize(min, max);
						}
						populateJTableHouses();
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
						message = "Min and Max must be integer!";
						JOptionPane.showMessageDialog(null, message);
					}
				} else {
					message = "Please, type min and max for search!";
					JOptionPane.showMessageDialog(null, message);
				}
			}
		} else if (e.getSource() == jBNAdd) {
			processType = ProcessType.ADD;
			clearDataForm();
			String newID = estateAgent.getNewID();
			jTFID.setText(newID);
			enableDataForm(true);
		} else if (e.getSource() == jBNUpdate) {
			if (!jTFID.getText().toString().equals("")) {
				processType = ProcessType.UPDATE;
				enableDataForm(true);
			} else {
				message = "Please, select a record from grid!";
				JOptionPane.showMessageDialog(null, message);
			}
		} else if (e.getSource() == jBNDelete) {
			if (!jTFID.getText().toString().equals("")) {
				processType = ProcessType.DELETE;
				enableDataForm(true);
				jBNClear.setEnabled(false);
				jTFPrice.setEnabled(false);
				jTFSize.setEnabled(false);
				jTFRooms.setEnabled(false);
				jTFBathrooms.setEnabled(false);
				jCBAirConditioner.setEnabled(false);
			} else {
				message = "Please, select a record from grid!";
				JOptionPane.showMessageDialog(null, message);
			}
		} else if (e.getSource() == jBNClear) {
			if (processType == ProcessType.ADD || processType == ProcessType.UPDATE) {
				clearDataForm();
			}
		} else if (e.getSource() == jBNApply) {
			String typedID = jTFID.getText().toString();
			String typedPrice = jTFPrice.getText().toString();
			String typedSize = jTFSize.getText().toString();
			String typedRooms = jTFRooms.getText().toString();
			String typedBathrooms = jTFBathrooms.getText().toString();
			switch (processType) {
			case ADD:
				if (!typedID.equals("") && !typedPrice.equals("") && !typedSize.equals("") && !typedRooms.equals("")
						&& !typedBathrooms.equals("")) {
					try {
						int id = Integer.parseInt(typedID);
						int price = Integer.parseInt(typedPrice);
						int size = Integer.parseInt(typedSize);
						int rooms = Integer.parseInt(typedRooms);
						int bathrooms = Integer.parseInt(typedBathrooms);
						String temp = jCBAirConditioner.getSelectedItem().toString();
						boolean airConditioner = false;
						if (temp.equals("yes"))
							airConditioner = true;
						boolean result = estateAgent.addHouse(id, price, size, rooms, bathrooms, airConditioner);
						if (result) {
							this.listHouseGrid = estateAgent.saveHousesToFile();
							populateJTableHouses();
							processType = ProcessType.CANCEL;
							cancelProcess();
						} else {
							message = "Record could not created!";
							JOptionPane.showMessageDialog(null, message);
						}
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
						message = "Data must be integer!";
						JOptionPane.showMessageDialog(null, message);
					}
				} else {
					message = "Data must type!";
					JOptionPane.showMessageDialog(null, message);
				}
				break;
			case UPDATE:
				if (!typedID.equals("") && !typedPrice.equals("") && !typedSize.equals("") && !typedRooms.equals("")
						&& !typedBathrooms.equals("")) {
					try {
						int id = Integer.parseInt(typedID);
						int price = Integer.parseInt(typedPrice);
						int size = Integer.parseInt(typedSize);
						int rooms = Integer.parseInt(typedRooms);
						int bathrooms = Integer.parseInt(typedBathrooms);
						String temp = jCBAirConditioner.getSelectedItem().toString();
						boolean airConditioner = false;
						if (temp.equals("yes"))
							airConditioner = true;
						boolean result = estateAgent.updateHouse(id, price, size, rooms, bathrooms, airConditioner);
						if (result) {
							this.listHouseGrid = estateAgent.saveHousesToFile();
							populateJTableHouses();
							processType = ProcessType.CANCEL;
							cancelProcess();
						} else {
							message = "Record could not updated!";
							JOptionPane.showMessageDialog(null, message);
						}
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
						message = "Data must be integer!";
						JOptionPane.showMessageDialog(null, message);
					}
				} else {
					message = "Data must type!";
					JOptionPane.showMessageDialog(null, message);
				}
				break;
			case DELETE:
				boolean result = estateAgent.deleteHouse(selectedID);
				if (result) {
					this.listHouseGrid = estateAgent.saveHousesToFile();
					populateJTableHouses();
					processType = ProcessType.CANCEL;
					cancelProcess();
				} else {
					message = "Record could not deleted!";
					JOptionPane.showMessageDialog(null, message);
				}
				break;
			default:
				break;
			}
		} else if (e.getSource() == jBNCancel) {
			processType = ProcessType.CANCEL;
			cancelProcess();
		} else if (e.getSource() == jRBSearchByPrice) {
			jTFSearchMin.setEnabled(true);
			jTFSearchMax.setEnabled(true);
		} else if (e.getSource() == jRBSearchBySize) {
			jTFSearchMin.setEnabled(true);
			jTFSearchMax.setEnabled(true);
		} else if (e.getSource() == jRBSearchByRooms) {
			jTFSearchMin.setEnabled(true);
			jTFSearchMax.setEnabled(false);
		}
	}

	private void cancelProcess() {
		clearDataForm();
		enableDataForm(false);
		jBNClear.setEnabled(true);
		jTBHouses.getSelectionModel().clearSelection();
	}

	private void clearDataForm() {

		switch (processType) {
		case ADD:
		case UPDATE:
			jTFPrice.setText("");
			jTFSize.setText("");
			jTFRooms.setText("");
			jTFBathrooms.setText("");
			jCBAirConditioner.setSelectedIndex(0);
			break;
		case DELETE:
			break;
		case APPLY:
		case CANCEL:
			jTFID.setText("");
			jTFPrice.setText("");
			jTFSize.setText("");
			jTFRooms.setText("");
			jTFBathrooms.setText("");
			jCBAirConditioner.setSelectedIndex(0);
			break;
		default:
			break;
		}
	}

	private void enableDataForm(boolean value) {
		jTFID.setEnabled(false);
		jTFPrice.setEnabled(value);
		jTFSize.setEnabled(value);
		jTFRooms.setEnabled(value);
		jTFBathrooms.setEnabled(value);
		jCBAirConditioner.setEnabled(value);
		jBNApply.setEnabled(value);
		jBNCancel.setEnabled(value);

		jBNAdd.setEnabled(!value);
		jBNUpdate.setEnabled(!value);
		jBNDelete.setEnabled(!value);
		jTBHouses.setEnabled(!value);
	}

	public void populateJTableHouses() {
		DefaultTableModel defaultTableModel = getTableData(this.listHouseGrid);
		jTBHouses.setModel(defaultTableModel);
		// defaultTableModel.fireTableDataChanged();
	}

	private DefaultTableModel getTableData(ArrayList<House> listHouse) {
		String columns[] = { "ID", "Price ($)", "Size (m^2)", "Rooms", "Bathrooms", "Air Conditioner" };
		DefaultTableModel defaultTableModel = new DefaultTableModel(columns, 0);

		for (int i = 0; i < listHouse.size(); i++) {
			int id = listHouse.get(i).getId();
			int price = listHouse.get(i).getPrice();
			int size = listHouse.get(i).getSize();
			int rooms = listHouse.get(i).getRooms();
			int bathrooms = listHouse.get(i).getBathrooms();
			String airConditioner = listHouse.get(i).isAirconditionerAsText();

			Object[] data = { id, price, size, rooms, bathrooms, airConditioner };
			defaultTableModel.addRow(data);
		}

		return defaultTableModel;
	}
}