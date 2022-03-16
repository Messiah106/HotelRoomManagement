
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Hotel {
    public static Scanner scanner = new Scanner(System.in);   //Calling a Global Scanner hence it doesn't have to be called again in methods
    public static Room[] hotels = new Room[8];   //Declared Global Variable firstName as an array to accept 8 elements which could now be used across all methods
    public static ArrayList<String> queue = new ArrayList<String>();


    public static void queueCheck() {
        int x = 0;
        for (int i = 0; i < hotels.length; i++) {
            if (!(hotels[i].firstName.equals("e"))) {   //Checking if there are any empty rooms available
                x = x + 1;
            }
        }
        if (x == 8) {
            System.out.println("Since All the Rooms are Occupied You will be Added to the waiting list. \nPlease enter your name to add in the waiting list: ");
            queue.add(scanner.next());  //Requesting user for their name to add to the waiting list
        }
    }

    public static void add() {  //Method to add customer names to rooms
        System.out.println("Enter room number (0-7)");
        int index = scanner.nextInt();   //Users are allowed to enter in which room they want the customer to stay
        if (index < 0 || index > 7) {
            System.out.println("Invalid room number");  //If room Number is not between the range 0 - 7 current action ends
        } else {
            if (!hotels[index].firstName.equals("e")) {
                System.out.println("Room is Occupied");   //Program checks if the desired room is free before entering name, if not prints message
                queueCheck();  //The method queueCheck is called
            } else {
                System.out.print("Enter Customer's First Name : ");
                String firstname = scanner.next();
                hotels[index].firstName(firstname);         //Users are asked to enter details which is added to the class array

                System.out.println("Enter Customer's Surname : ");
                String sName = scanner.next();
                hotels[index].surNAME(sName);

                System.out.println("Enter Customer's Credit Card Number : ");
                String creditCard = scanner.next();
                hotels[index].ccNumber(creditCard);

                System.out.println("Number of Guests Staying : ");
                String guests = scanner.next();
                hotels[index].noOfGuests(guests);

                System.out.println("\nCustomer Added Successfully");
            }

        }
    }

    public static void initialise() {   //method to initialise all rooms as empty
        for (int i = 0; i < hotels.length; i++) {
            hotels[i] = new Room("e");
        }
    }

    public static void view() {      //method to view details of currently occupied rooms and customer name
        for (int i = 0; i < 8; i++) {
            System.out.println("\nroom " + i + " occupied by " + hotels[i].firstName + " " + hotels[i].surName);  //Shows which room is occupied by whom
            System.out.println("Number of Guests Staying are : " + hotels[i].noGuests);
            if (hotels[i].ccNum.equals("0")) {
                continue;
            } else {
                System.out.println("Credit Card Number : " + hotels[i].ccNum);   //Only print CC Number when it is not empty
            }
        }
    }

    public static void empty() {   //method to show rooms only which are empty
        for (int i = 0; i < hotels.length; i++) {
            if (hotels[i].firstName.equals("e")) System.out.println("room " + i + " is empty");
        }
    }

    public static void findRoom() {   //method to find room details by using customer name
        System.out.println("Enter Customer's First Name");
        String name = scanner.next().toLowerCase();
        System.out.println("Enter Customer's Surname");
        String sName = scanner.next().toLowerCase();
        for (int i = 0; i < hotels.length; i++) {
            if (hotels[i].firstName.equals(name) && hotels[i].surName.equals(sName)) {
                System.out.println(name + " " + sName + " is in Room Number : " + i); //if the entered customer is available it shows the room he/she is using
                System.out.println("Credit Number is : " + hotels[i].ccNum);
                System.out.println("Number of Guests Staying are : " + hotels[i].noGuests);
                break;
            }
        }
    }

    public static void delete() {  //method to delete customer from room using customer name
        int x = 0;
        System.out.println("Enter room number to delete");
        int roomNo = scanner.nextInt();

        for (int i = 0; i < hotels.length; i++) {

            if (!(hotels[i].firstName.equals("e"))) {   //Checking if there are any empty rooms available
                x = x + 1;
            }
            if (i == roomNo) {
                System.out.println(hotels[i].firstName + " Staying in Room Number " + i + " has been removed");
                hotels[i].firstName = "e";    //assigns the string e to mentioned room to make it empty
                hotels[i].surName = "";
                hotels[i].ccNum = "0";
                hotels[i].noGuests = "0";
                System.out.println("Deleted customer from room " + i + " successfully");
            }
            if (x == 8) {
                String wName = queue.get(0);
                hotels[roomNo].firstName = wName;
                System.out.println("A room has been freed, you shall be added to the room");
                System.out.println("Number of Guests Staying : ");
                String wGuests = scanner.next();
                hotels[roomNo].noGuests = wGuests;
                System.out.println("Enter Customer's Surname : ");
                String srName = scanner.next();
                hotels[roomNo].surName = srName;
            }
        }
    }

    public static void store() throws IOException {  //method to store details saved in the program
        File myObj = new File("firstName.txt");
        myObj.createNewFile();  //A new file is created using the method
        FileWriter myWriter = new FileWriter(myObj.getName());
        for (int x = 0; x < hotels.length; x++) {
            myWriter.write(hotels[x].firstName + "\n");
        }
        myWriter.close();

        File myObj1 = new File("surName.txt");
        myObj1.createNewFile();  //A new file is created using the method
        FileWriter myWriter1 = new FileWriter(myObj1.getName());
        for (int x = 0; x < hotels.length; x++) {
            myWriter1.write(hotels[x].surName + "\n");
        }
        myWriter1.close();

        File myObj2 = new File("ccNum.txt");
        myObj2.createNewFile();  //A new file is created using the method
        FileWriter myWriter2 = new FileWriter(myObj2.getName());
        for (int x = 0; x < hotels.length; x++) {
            myWriter2.write(hotels[x].ccNum + "\n");
        }
        myWriter2.close();

        File myObj3 = new File("noOfGuests.txt");
        myObj3.createNewFile();  //A new file is created using the method
        FileWriter myWriter3 = new FileWriter(myObj3.getName());
        for (int x = 0; x < hotels.length; x++) {
            myWriter3.write(hotels[x].noGuests + "\n");
        }
        myWriter3.close();
        System.out.println("Added Successfully");  //Once the file is created a message is printed
    }


    public static void load() throws IOException {  //method to call the save data file
        int x = 0;
        int i = 0;
        File myObj = new File("firstName.txt");  //The stored files are called back to read
        File myObj1 = new File("surName.txt");
        File myObj2 = new File("ccNum.txt");
        File myObj3 = new File("noOfGuests.txt");
        Scanner myReader = new Scanner(myObj);
        Scanner myReader1 = new Scanner(myObj1);
        Scanner myReader2 = new Scanner(myObj2);
        Scanner myReader3 = new Scanner(myObj3);

        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            hotels[x].firstName = data;  //data taken from file is stored to array
            x++;
        }

        while (myReader1.hasNextLine()) {
            String data = myReader1.nextLine();
            hotels[i].surName = data;  //data taken from file is stored to array
            i++;
        }

        int j = 0;
        while (myReader2.hasNextLine()) {
            String data2 = myReader2.nextLine();
            hotels[j].ccNum = data2;  //data taken from file is stored to array
            j++;
        }

        int l = 0;
        while (myReader3.hasNextLine()) {
            String data3 = myReader3.nextLine();
            hotels[l].noGuests = data3;  //data taken from file is stored to array
            l++;
        }
        myReader.close();
        myReader1.close();
        myReader2.close();
        myReader3.close();
        System.out.println("Loaded Successfully");  //prints message when successfully loaded
    }

    public static void order() {  //method to arrange the customer details in alphabetical order
        for (int i = 0; i < hotels.length; i++) {
            for (int j = i + 1; j < hotels.length; j++) {
                if (hotels[i].firstName.compareTo(hotels[j].firstName) > 0) //compares two different elements of the array at a time
                {
                    String temp = hotels[i].firstName;
                    String temp1 = hotels[i].surName;
                    hotels[i].firstName = hotels[j].firstName;
                    hotels[i].surName = hotels[j].surName;
                    hotels[j].firstName = temp;  //rearranged in a way to make sure it is in alphabetical order
                    hotels[j].surName = temp1;
                }
            }
        }
        for (int x = 0; x < hotels.length; x++) {
            System.out.println("room " + x + " occupied by " + hotels[x].firstName + " " + hotels[x].surName);  //Alphabetical Order is printed
        }
    }

    public static String menu() {  //method showing th possible options the user can choose from
        System.out.println("Choose an option from the menu: ");
        System.out.println("\nA: Add a customer");
        System.out.println("V: View all rooms");
        System.out.println("E: Display Empty rooms");
        System.out.println("D: Delete Customer from room");
        System.out.println("F: Find room from customer name");
        System.out.println("S: Store program data into file");
        System.out.println("L: Load program data from file");
        System.out.println("O: View guests Ordered alphabetically by name");
        String option = scanner.next().toLowerCase();  //users choice is saved in a variable

        return option;  //the saved variable is returned
    }

    public static void main(String[] args) {
        initialise();  //The method initialise is called

        while (true) {
            String option = menu(); //users choice from the method menu is saved in variable

            if (option.equals("a")) {  //users choice is checked and methods accordingly called
                add(); //The method add is called
            } else if (option.equals("v")) {
                view();  //The method view is called
            } else if (option.equals("e")) {
                empty();    //The method empty is called
            } else if (option.equals("f")) {
                findRoom();   //The method findRoom is called
            } else if (option.equals("d")) {
                delete();   //The method delete is called
            } else if (option.equals("o")) {
                order();  //The method order is called
            } else if (option.equals("s")) {
                try {
                    store();    //The method store is called
                } catch (IOException e) {
                    e.printStackTrace();  //method passed to handle exceptions and error
                }
            } else if (option.equals("l")) {
                try {
                    load(); //The method load is called
                } catch (IOException e) {
                    e.printStackTrace();  //method passed to handle exceptions and error
                }
            } else {
                System.out.println("Invalid Option");
            }

            System.out.print("\nEnter y to continue or Any other character to end program : ");
            String userInput = scanner.next().toLowerCase();   // user is asked if he wants to continue using the program

            if (!userInput.equals("y")) {
                System.out.println("Programs Ending.....");   //if user doesnt enter y program ends
                break;
            }
        }
    }
}