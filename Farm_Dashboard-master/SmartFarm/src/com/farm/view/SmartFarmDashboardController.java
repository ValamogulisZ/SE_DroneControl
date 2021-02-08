package com.farm.view;

import javax.swing.JOptionPane;

import com.farm.farmdashboard.*;
import com.farm.farmdashboard.items.Item;
import com.farm.farmdashboard.items.ItemContainer;
import com.farm.farmdashboard.items.ItemLeaf;

import javafx.animation.PathTransition;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.scene.paint.Color;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.util.Duration;
import javafx.scene.text.Text;

/*
 * Controller class to handle user interaction with 
 * SmartFarmDashboard
 */

public class SmartFarmDashboardController {

	
    private static SmartFarmDashboardController INSTANCE;
    // Model

    public static SmartFarmDashboardController getInstance() {
            if(INSTANCE == null) {
                    INSTANCE = new SmartFarmDashboardController();
            }
            return INSTANCE;
    }
	
	// Model
	private ArrayList<Item> farmItems;
	/* TODO:
	 * @FXML annotations
	 *  initialize() method
	 */

	/*
	 * FXML annotations
	 */
	//Left side of Dashboard (Actions/Items)
	@FXML
	private SplitPane ControlsSplitPane;
	@FXML
	private AnchorPane ItemsAnchorPane;
	@FXML
	private AnchorPane ActionsAnchorPane;
	@FXML
	private Label ItemsLabel;
	@FXML
	private TreeView<Item> ItemsTreeView;
	@FXML
	private GridPane SelectedItemProperties;
	// Selected Item Properties
	@FXML
	private TextField SelectedName;
	@FXML
	private TextField SelectedPosX;
	@FXML
	private TextField SelectedPosY;
	@FXML
	private TextField SelectedLength;
	@FXML
	private TextField SelectedWidth;
	@FXML
	private TextField SelectedHeight;
	@FXML
	private TextField SelectedPrice;
	@FXML
	private TextField MarketPrice;
	// Drone Actions
	@FXML
	private Label DroneActionsLabel;
	@FXML
	private Button VisitItemAction;
	@FXML
	private Button ScanFarmAction;
	@FXML
	private Button AddDrone;
	@FXML
	private Label  CPP;
	@FXML
	private Label  CMP;


	//Right Side (Farm View)
	@FXML
	private AnchorPane FarmViewVBox;
	@FXML
	private ImageView drone;
	@FXML
	private ImageView crops;
	@FXML
	private ImageView barn;
	
	/*
	 * End of FXML annotations
	 */
	
	//Reference to Main
	private Main main;

	//Drone visits the item that is selected in the treeview then returns to it's location
	//Before visiting the item
	@FXML
	private void handleVisitItemAction(ActionEvent event) {
		double xPos = drone.getX(); //original drone x and y positions
		double yPos = drone.getY();
		
		Path scanpath = new Path();
		ArcTo arcpath = new ArcTo();
		
		scanpath.getElements().add(new MoveTo(xPos, yPos));
		scanpath.getElements().add(new VLineTo(getSelectedItem().getPosY() + 20));
		scanpath.getElements().add(new HLineTo(getSelectedItem().getPosX() + 20));
		scanpath.getElements().add(new VLineTo(yPos + 20));
		scanpath.getElements().add(new HLineTo(xPos + 20));

		PathTransition scantransition = new PathTransition();
		scantransition.setNode(drone);
		scantransition.setDuration(Duration.millis(10000));
		scantransition.setPath(scanpath); 
		scantransition.setCycleCount(1);
		scantransition.play();
	}
	
	@FXML
	private void handleScanFarmAction(ActionEvent event) {			
				
				/*
				 *  Farm is 800px tall and 600px wide
				 *  Drone is 50x50
				 *  For now I am just going to subtract 50 from where I actually want to go on the (x, y)
				 *  ArcTo will be good for flying directly to a specified coordinate. 
				 *  
				 */
				double xPos = drone.getX();
				double yPos = drone.getY();
				
				Path scanpath = new Path(); 
				ArcTo arcpath = new ArcTo();		
				
				//Path of the drone so that it travels over every pixel of the farm
				//Obviously needs to be changed but good enough for now
				scanpath.getElements().add(new MoveTo(50, 50)); //Initial position (0,0) taking into consideration the drone's 50x50 size
				scanpath.getElements().add(new HLineTo(550)); //Horizontal move to the top right corner
				scanpath.getElements().add(new VLineTo(750)); //Vertical move down to bottom right corner
				scanpath.getElements().add(new HLineTo(500)); //Horizontal move left 50
				scanpath.getElements().add(new VLineTo(50)); //Vertical move back to top
				scanpath.getElements().add(new HLineTo(450)); //Horizontal move left 50
				scanpath.getElements().add(new VLineTo(750)); //Vertical move back to bottom
				scanpath.getElements().add(new HLineTo(400)); //Horizontal move left 50
				scanpath.getElements().add(new VLineTo(50)); //Vertical move back to top
				scanpath.getElements().add(new HLineTo(350)); //Horizontal move left 50
				scanpath.getElements().add(new VLineTo(750)); //Vertical move back to bottom
				scanpath.getElements().add(new HLineTo(300)); //Horizontal move left 50
				scanpath.getElements().add(new VLineTo(50)); //Vertical move back to top
				scanpath.getElements().add(new HLineTo(250)); //Horizontal move left 50
				scanpath.getElements().add(new VLineTo(750)); //Vertical move back to bottom
				scanpath.getElements().add(new HLineTo(200)); //Horizontal move left 50
				scanpath.getElements().add(new VLineTo(50)); //Vertical move back to top
				scanpath.getElements().add(new HLineTo(150)); //Horizontal move left 50
				scanpath.getElements().add(new VLineTo(750)); //Vertical move back to bottom
				scanpath.getElements().add(new HLineTo(100)); //Horizontal move left 50
				scanpath.getElements().add(new VLineTo(50)); //Vertical move back to top
				scanpath.getElements().add(new HLineTo(50)); //Horizontal move left 50
				scanpath.getElements().add(new VLineTo(750)); //Vertical move back to bottom
				scanpath.getElements().add(new VLineTo(50)); //Vertical move back to top
				scanpath.getElements().add(new VLineTo(yPos + 50));
				scanpath.getElements().add(new HLineTo(xPos + 50));
				/*
				arcpath.setX(50); //Destination Horizontal (back to dock)
				arcpath.setY(50);// Destination Vertical (back to dock)
				arcpath.setRadiusX(550); //radius of the arc x,y nice and wide to pass over the middle of the farm
				arcpath.setRadiusY(550);
				scanpath.getElements().add(arcpath); //add arcpath to the scanpath
				*/
				PathTransition scantransition = new PathTransition();
				scantransition.setNode(drone);
				scantransition.setDuration(Duration.millis(10000));
				scantransition.setPath(scanpath); 
				scantransition.setCycleCount(1);
				scantransition.play();
				
				
				//for testing, would be nice to disable button and change it's text to "scanning" while it is running
				System.out.println("Current state: "+ scantransition.getStatus());
				//ScanFarmAction.setText("Scanning...");
				//ScanFarmAction.setText("Scan Farm");
	}

	//Adds the drone and the command center 
	@FXML
	private void addDrone(ActionEvent event) {
		drone.setVisible(true);
		int index = getSelectedItemIndex();
		farmItems.add(new ItemContainer("Command Center", 0,0, 5,5,130,130,130, new ArrayList<Item>(){
			{
				add(new ItemLeaf("Drone", 4,0, 20, 20, 50, 50, 50));
			}
		}));

		updateFarmItemsTree();
		MultipleSelectionModel msm = ItemsTreeView.getSelectionModel();
		msm.select(index);
	}
			
	

	//Add Item Leaf
	@FXML
	private void addNewFarmItemLeaf(ActionEvent event) {

		addNewFarmItem(new ItemLeaf("New_Item",0,0,0,0,0,0,0));
	}

	//Add Item Container
	@FXML
	private void addNewFarmItemContainer(ActionEvent event) {
		addNewFarmItem(new ItemContainer("New_ItemContainer",0,0,0,0,0,0,0, new ArrayList<Item>()));
	}

	//Add new item/itemcontainer to the farm
	private void addNewFarmItem(Item newItem) {
		int index = getSelectedItemIndex();
		Item selectedItem = getSelectedItem();
		if (selectedItem != null && selectedItem.getChildren() != null) {
			selectedItem.addChild(newItem);
		} else {
			farmItems.add(newItem);
		}
		updateFarmItemsTree();
		MultipleSelectionModel msm = ItemsTreeView.getSelectionModel();
		msm.select(index);
	}

	//Deletes selected item from farm
	@FXML
	private void deleteFarmItem(ActionEvent event) {
		Item itemToRemove = getSelectedItem();
		if (itemToRemove == null || String.valueOf(itemToRemove) == "Farm") return;
		TreeItem<Item> item = ItemsTreeView.getSelectionModel().getSelectedItem();
		TreeItem<Item> itemParent = item.getParent();

		if (String.valueOf(itemParent.getValue()) == "Farm") {
			farmItems.remove(itemToRemove);
		} else {
			itemParent.getValue().removeChild(itemToRemove);
		}

		updateFarmItemsTree();
	}
	
	//Saves the values for the current selected item
	//Checks to make sure inputs are valid for item
	@FXML
	private void saveSelectedItemChanges(ActionEvent event) {
		System.out.println("AM I HERE??????????");
		Item selectedItem = getSelectedItem();	
		int index = getSelectedItemIndex();
		selectedItem.setName(SelectedName.getText());
		selectedItem.setPosition(Integer.parseInt(SelectedPosX.getText()), Integer.parseInt(SelectedPosY.getText()));
		selectedItem.setDimensions(Integer.parseInt(SelectedLength.getText()),
				                   Integer.parseInt(SelectedWidth.getText()),
				                   Integer.parseInt(SelectedHeight.getText()));
		selectedItem.setPrice(Double.parseDouble(SelectedPrice.getText()));
		selectedItem.setMarketPrice(Double.parseDouble(MarketPrice.getText()));
		CPP.setText(String.valueOf(selectedItem.curPrice()));
		CMP.setText(String.valueOf(selectedItem.marketPrice()));
		/*selectedItem.setCPPVAL(selectedItem.getCPPVAL());
		selectedItem.setCMPVAL(selectedItem.getCMPVAL());*/
	/*	if (selectedItem != null && selectedItem.getChildren() != null)
		{
			ItemContainer ic = new ItemContainer(selectedItem.getPrice(),selectedItem.getMarketPrice(),ItemsTreeView.getSelectionModel().getSelectedItem());
			Pricing vis1 = new Pricing();
			ic.accept(vis1);

			selectedItem.setCPPVAL(vis1.CPvalue());
			selectedItem.setCMPVAL(vis1.CMvalue());
		}
		if (selectedItem != null && selectedItem.getChildren() == null)
		{*//*
			System.out.println("AM I here tooo??");
			ItemLeaf il = new ItemLeaf(selectedItem.getPrice(),selectedItem.getMarketPrice());
			Pricing vis = new Pricing();
			il.accept(vis);
			selectedItem.setCPPVAL(vis.CPvalue());
			selectedItem.setCMPVAL(vis.CMvalue());
			System.out.println(vis.CPvalue());
			System.out.println(vis.CMvalue());
		}
*/

		//Error checking to make sure item/container will not extend outside of the farm's x and y boundaries
		if (selectedItem.getPosX() + selectedItem.getLength() >= 600) 
		{
			JOptionPane.showMessageDialog(null, "Entered Values Not Saved\nBeginning X Value and Length of Item extends outside of farm area for Farm Item: " + selectedItem.getName() +
										"\nPlease add a valid X Value and Length that add up to be less than 600", 
										"InfoBox: X Coordinate Error", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if (selectedItem.getPosY() + selectedItem.getWidth() >= 800) 
		{
			JOptionPane.showMessageDialog(null, "Entered Values Not Saved\nBeginning Y Value and Width of Item extends outside of farm area for Farm Item: " + selectedItem.getName() +
										"\nPlease add a valid Y Value and Width that add up to be less than 800", 
										"InfoBox: X Coordinate Error", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		ItemsTreeView.refresh();
		updateFarmItemsTree();
		MultipleSelectionModel msm = ItemsTreeView.getSelectionModel();
		msm.select(index);
	}
	
	//Draws an itemLeaf
	private void drawItem(Item item) {
		Rectangle rect = new Rectangle(item.getPosX(), item.getPosY(), item.getLength(), item.getWidth());	
		rect.setAccessibleText(item.getName());
		rect.setStroke(Color.RED);
		rect.setStrokeWidth(2);
		rect.setFill(Color.TRANSPARENT);
		FarmViewVBox.getChildren().add(rect);
	}
	
	//Draws an ItemContainer
	private void drawItemContainer(Item itemContainer) {
		Rectangle rect = new Rectangle(itemContainer.getPosX(), itemContainer.getPosY(), itemContainer.getLength(), itemContainer.getWidth());
		rect.setAccessibleText(itemContainer.getName());
		rect.setStroke(Color.GREEN);
		rect.setStrokeWidth(5);
		rect.setFill(Color.TRANSPARENT);
		FarmViewVBox.getChildren().add(rect);
	}

	// Redraws the Farm Items Tree according to the farmItems ArrayList
	// Also redraws the visual items/containers in the farm
	private void updateFarmItemsTree() {
		ItemsTreeView.getRoot().getChildren().clear();
		FarmViewVBox.getChildren().clear();	
		farmItems.forEach(item -> {
			addChildrenToItemsTree(item, ItemsTreeView.getRoot());
			drawTreeItems(item, ItemsTreeView.getRoot());
		});
	}

	// If item is Leaf, add self. If item has children, add self and then recurse on children.
	void addChildrenToItemsTree(Item item, TreeItem<Item> parent) {
		TreeItem<Item> newTreeItem = new TreeItem<Item>(item);
		parent.getChildren().add(newTreeItem);
		while (item.hasNext()) {
			addChildrenToItemsTree(item.next(), newTreeItem);
		}
	}	
	
	//Loops through tree and redraws all containers/leafs
	void drawTreeItems(Item item, TreeItem<Item> parent) {


		TreeItem<Item> newTreeItem = new TreeItem<Item>(item);
		Text text = new Text(item.getPosX(), item.getPosY() - 5, item.getName());
		text.setAccessibleText(item.getName());
		FarmViewVBox.getChildren().add(text);


		if (item != null && item.getChildren() != null) 
		{
			drawItemContainer(item);
			/*ItemContainer ic = new ItemContainer();
			ic.accept(vis);
			selectedItem.setCPPVAL((vis.CPvalue()));
			selectedItem.setCMPVAL((vis.CMvalue()));*/
		} else {
			if (item.getName().equals("Drone")) {
				drone.setX(item.getPosX());
				drone.setY(item.getPosY());
				FarmViewVBox.getChildren().add(drone);
				}
			else {drawItem(item);}
		/*	ItemLeaf il = new ItemLeaf();
			il.accept(vis);
			selectedItem.setCPPVAL((vis.CPvalue()));
			selectedItem.setCMPVAL((vis.CMvalue()));*/
		}
		while (item.hasNext()) {
			drawTreeItems(item.next(), newTreeItem);
		}
	}
	
	@FXML
	public void initialize() {
		// Initialize farmItems
		farmItems = new ArrayList<Item>();
		// Establish a Root Item
		ItemContainer farm = new ItemContainer("Farm", 0,0, 0,0,0,0,0,farmItems);
		TreeItem<Item> root = new TreeItem<Item>(farm);
		root.setExpanded(true);
		ItemsTreeView.setRoot(root);

		// Add event listeners
		ItemsTreeView.getSelectionModel().selectedItemProperty().addListener((ChangeListener)
				(observable, oldValue, newValue) -> {
			Item selectedItem = getSelectedItem();
			if (selectedItem == null) {
				ItemsTreeView.getSelectionModel().select(ItemsTreeView.getRoot());
				selectedItem = getSelectedItem();
			};
			SelectedName.setText(selectedItem.getName());
			SelectedPosX.setText(String.valueOf(selectedItem.getPosX()));
			SelectedPosY.setText(String.valueOf(selectedItem.getPosY()));
			SelectedLength.setText(String.valueOf(selectedItem.getLength()));
			SelectedWidth.setText(String.valueOf(selectedItem.getWidth()));
			SelectedHeight.setText(String.valueOf(selectedItem.getHeight()));
			SelectedPrice.setText(String.valueOf(selectedItem.getPrice()));
			MarketPrice.setText(String.valueOf(selectedItem.getMarketPrice()));
			//CPPVAL.setText(String.valueOf(selectedItem.getCPPVAL()));
			//CMPVAL.setText(String.valueOf(selectedItem.getCMPVAL()));
					CPP.setText(String.valueOf(selectedItem.curPrice()));
					CMP.setText(String.valueOf(selectedItem.marketPrice()));
		});
	}

	//Gets the index of the selected item in treeview
	int getSelectedItemIndex() {
		int selectedTreeItemIndex = ItemsTreeView.getSelectionModel().getSelectedIndex();
		return selectedTreeItemIndex;
	}
	
	//Gets the value of the selected item in treeview
	Item getSelectedItem() {
		TreeItem<Item> selectedTreeItem = (TreeItem<Item>) ItemsTreeView.getSelectionModel().getSelectedItem();
		if (selectedTreeItem == null) { return null;}
		return selectedTreeItem.getValue();
	}

	/*******************************************TELLO***********************************************************/
	private static BufferedReader inp;
	private static BufferedWriter out;
	private static Process p;
	private static final String pathToPython = Constants.PYTHON_PATH + " ";
	private static final String filePath = Constants.FILE_PATH_DEVELOPER;

	/***
	 * Helper method for simplification of print statements
	 * @param s string to be printed
	 */
	public static void print(String s) {
		System.out.println(s);
	}

	/***
	 * This method pipes strings to the Tello's Python control program
	 * Strings are sent to the script and stripped of extra characters then sent to the drone
	 * @param msg String command message to be sent to Tello
	 * @return String representation of the Tello response message
	 */
	public static String pipe(String msg) {
		String ret;

		try {
			out.write(msg + "\n");
			out.flush();
			ret = inp.readLine();
			while (ret.equals("") || ret.equals("forced stop")) {
				ret = inp.readLine();
			}
			return ret;
		}
		catch (Exception err) {
			err.printStackTrace();
		}
		return "error";
	}

	/***
	 * A method that takes in an array of Tello commands and then passes them on to the drone via the single command method
	 * Only used by the sample programs below, not efficient or advised for flight routines beyond testing
	 * @param cmds String array of Tello command instructions
	 */
	public static void commandDrone(String[] cmds) {

		for (int i = 0; i < cmds.length; i++) {
			commandDrone(cmds[i]);
		}
	}

	/***
	 * A method that sends the Tello command strings on to the piping method and prints the call and response to verify correctness
	 * @param cmd String Tello command instruction
	 * @return String reponse of Tell can be used in conditionals for better flight control and informational messages
	 */
	public static String commandDrone(String cmd) {

		String call = "";

		String response = "";

		call = cmd;
		print(call);
		response = pipe(call);
		print(response);
		return response;
	}

	/***
	 * Method that contains all the necessary elements to set the drone to recieve additional commands
	 */
	public static void launchInitialization() {

		String cmd = pathToPython + filePath;

		try {

			print(cmd);
			print(System.getProperty("user.dir"));
			print("Tello Demo" + "\n");

			print("Tello: command takeoff land flip forward back left right" + "\n" + "       up down cw ccw speed speed?" + "\n");

			print("end -- quit demo" + "\n");

			p = Runtime.getRuntime().exec(cmd);

			inp = new BufferedReader( new InputStreamReader(p.getInputStream(), "UTF-8"));
			out = new BufferedWriter( new OutputStreamWriter(p.getOutputStream()));

			commandDrone("command");
		}


		catch (Exception err) {
			err.printStackTrace();
		}
	}

	/***
	 * Method that cleans up any remaing assets and terminates a flight sequence
	 */
	public static void launchCompletion() {

		try {

			commandDrone("end");

			p.destroy();
			print('\n' + "End Program");
			inp.close();
			out.close();
		}

		catch (Exception err) {
			err.printStackTrace();
		}
	}

	/***
	 * This is a demonstration program designed to show a subset of possible commands
	 * along with the required intial and ending commands required by the Tello SDK
	 * and the required ordering of the "takeoff" and "land" messages
	 * @param cmds
	 */
	public static void runProgramArray(String[] cmds) {

		String cmd = pathToPython + filePath;

		try {

			print(cmd);
			print(System.getProperty("user.dir"));
			print("Tello Demo" + "\n");

			print("Tello: command takeoff land flip forward back left right" + "\n" + "       up down cw ccw speed speed?" + "\n");

			print("end -- quit demo" + "\n");

			p = Runtime.getRuntime().exec(cmd);

			inp = new BufferedReader( new InputStreamReader(p.getInputStream(), "UTF-8"));
			out = new BufferedWriter( new OutputStreamWriter(p.getOutputStream()));

			commandDrone(cmds);

			p.destroy();
			print('\n' + "End Program");
			inp.close();
			out.close();
		}

		catch (Exception err) {
			err.printStackTrace();
		}
	}

	/***
	 * This is a demonstration program that is almost identical in function to the
	 * original python script execution but instead using a scanner to pipe strings to the Tello SDK
	 * Once running you must enter "command" in the console before you can access the other
	 * functions of the Tello SDK
	 */
	public static void runProgramScanner() {

		String cmd = pathToPython + filePath;

		try {

			print(cmd);
			print(System.getProperty("user.dir"));
			print("Tello Demo" + "\n");

			print("Tello: command takeoff land flip forward back left right" + "\n" + "       up down cw ccw speed speed?" + "\n");

			print("end -- quit demo" + "\n");

			Process p = Runtime.getRuntime().exec(cmd);

			inp = new BufferedReader( new InputStreamReader(p.getInputStream(), "UTF-8"));
			out = new BufferedWriter( new OutputStreamWriter(p.getOutputStream()) );
			Scanner scan = new Scanner(System.in);

			String command = scan.nextLine();

			while(!command.equals("end")) {
				commandDrone(command);
				command = scan.nextLine();
			}

			commandDrone(command);

			p.destroy();
			print('\n' + "End Program");
			inp.close();
			out.close();
			scan.close();
		}

		catch (Exception err) {
			err.printStackTrace();
		}
	}

}
