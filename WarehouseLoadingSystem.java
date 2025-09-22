import java.util.*;

class Item {
	String code;
	String name;
	int quantity;
	
	Item(String code, String name, int quantity) {
		this.code = code;
		this.name = name;
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return code + " | " + name + " | " + quantity;
	}
}

class Truck {
	String plate;
	String driver;

	Truck(String plate, String driver) {
		this.plate = plate;
		this.driver = driver;
	}
	
	@Override
	public String toString() {
		return plate + " | " + driver;
	}
}

class WarehouseLoadingSystem {

private static ArrayDeque<Item> warehouseStack = new ArrayDeque<>();
private static ArrayDeque<Truck> truckQueue = new ArrayDeque<>();
private static Scanner scan = new Scanner(System.in);


//Store item
void storeItem() {
	
	System.out.println("\n--- Store Item ---");

        //Ask for code, name, and quantity
        System.out.print("Enter item code: ");
        String code = scan.next();
        
        scan.nextLine();

        System.out.print("Enter item name: ");
        String name = scan.nextLine();

        int quantity = 0;
        boolean validQuantity = false;
        while (!validQuantity) {
            System.out.print("Enter item quantity: ");
            try {
            	quantity = scan.nextInt();
                scan.nextLine();
                validQuantity = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine();
            }
        }

        // Push to warehouseStack
        Item item = new Item(code, name, quantity);
        warehouseStack.push(item); //

        System.out.println("\nStored: " + item + "\n");
}


//View stack
void viewWarehouseStack() {
	
	System.out.println("\n--- Warhouse Stack ---");

    if (warehouseStack.isEmpty()) {
        System.out.println("No items in the warehouse.");
    } else {
        System.out.println("TOP →"); //print "TOP →"

        ArrayDeque<Item> temp = new ArrayDeque<>();

        while (!warehouseStack.isEmpty()) { //loop through warehouseStack
            Item item = warehouseStack.pop();
            System.out.println(item); //print each item
            temp.push(item);
        }

        while (!temp.isEmpty()) {
        	warehouseStack.push(temp.pop());
        }

        System.out.println("← BOTTOM\n"); //print "← BOTTOM"
    }
}


//Register truck
void registerTruck() {
	
	System.out.println("\n--- Register Trucks ---");

	//ask user for plate, driver
    System.out.print("Enter vehicle license plate: ");
    String plate = scan.next();
    
    scan.nextLine();

    System.out.print("Enter driver name: ");
    String driver = scan.nextLine();

    //create Truck object
    Truck truck = new Truck(plate, driver); 
    truckQueue.offer(truck);	//truckQueue.offer(truck);

    System.out.println("\nRegistered: " + truck);
}


//View trucks
void viewWaitingTrucks() {
	
	System.out.println("\n--- Waiting Trucks ---");

    if (truckQueue.isEmpty()) {
        System.out.println("No trucks waiting.");
    } else {
        System.out.println("FRONT →");	//print "FRONT →"

        for (Truck truck : truckQueue) {	//loop through truckQueue
            System.out.println(truck);	//print each truck
        }

        System.out.println("← REAR");	//print "← REAR"
    }
}


//Load truck
void loadNextTruck() {
	
	System.out.println("\n--- Load Next Truck ---");

    if (warehouseStack.isEmpty()) {	//else → print "No items or trucks available"
        System.out.println("No items available to load.");
    } else if (truckQueue.isEmpty()) {
        System.out.println("No trucks available to be loaded."); 
    } else {
    	//pop item from warehouseStack
        Item item = warehouseStack.pop();

        //poll truck from truckQueue
        Truck truck = truckQueue.poll();

        //if both not null → print "Loaded: [item] → [truck]"
        System.out.println("\nLoaded: " + item + " → " + truck); 
        System.out.println("Remaining items: " + warehouseStack.size());
        System.out.println("Remaining trucks waiting: " + truckQueue.size());
    }
}


//Main menu
void run() {
	
	boolean isRunning = true;
	
	while (isRunning) {
		System.out.print("[1] - Store an item in the warehouse"
		+ "\n[2] - View the items in the warhouse"
		+ "\n[3] - Register a truck"
		+ "\n[4] - View waiting trucks"
		+ "\n[5] - Load the next truck"
		+ "\nEnter choice: ");

		int choice = scan.nextInt();

		switch(choice) {
			case 1: storeItem(); break;
			case 2: viewWarehouseStack(); break;
			case 3: registerTruck(); break;
			case 4: viewWaitingTrucks(); break;
			case 5: loadNextTruck(); break;
			case 0: System.out.println("Exiting..."); 
					isRunning = false;
					break;
		}	
	}
	
//	do {
//		
//		System.out.print("[1] - Store an item in the warehouse"
//				+ "\n[2] - View the items in the warhouse"
//				+ "\n[3] - Register a truck"
//				+ "\n[4] - View waiting trucks"
//				+ "\n[5] - Load the next truck"
//				+ "\nEnter choice: ");
//		
//		int choice = scan.nextInt();
//		
//		switch(choice) {
//			case 1: storeItem(); break;
//			case 2: viewWarehouseStack(); break;
//			case 3: registerTruck(); break;
//			case 4: viewWaitingTrucks(); break;
//			case 5: loadNextTruck(); break;
//			case 0: System.out.println("Exiting..."); break;
//		}	
//	} while(choice != 0);
	
}


public static void main(String[] args) {
	new WarehouseLoadingSystem().run();
	}
}






