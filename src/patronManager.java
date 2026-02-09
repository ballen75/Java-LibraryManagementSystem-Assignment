/**
 * Brianna Allen
 * CEN 3024 - Software Development 1
 * February 6, 2025
 * patronManager.java
 * This application will allow users to upload text files with Patron's for a LMS System. Manually add Patrons, remove Patrons, and list all patrons.
 */

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;



/**
 * method : patronManager
 * parameters: none
 * purpose : Software application to allow management of Patrons.
 */
class patronManager {
    /**
     * Validates that the PatronID entered is equal to 7 digits.
     *
     * @param scanner
     * @return
     */
    public static String patronIDValidation(Scanner scanner) {
        String patronID;

        while (true) {
            System.out.print("Please enter the Patron ID");
            patronID = scanner.nextLine();

            if (patronID.matches("\\d{7}")) {
                return patronID;
            }
            System.out.println("Invalid Patron ID");
        }
    }


    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> patrons = new ArrayList<>();



            /*
              Provides console menu for user to select Add, Remove or List Patrons in the LMS System
             */
        int choice = 0;

        while (choice != 5) {

            System.out.println("Please select an option from the menu for the Library Management System");
            System.out.println("1. Add Patron");
            System.out.println("2. Add Patrons using bulk text file upload");
            System.out.println("3. Remove Patron");
            System.out.println("4. List Patrons");
            System.out.println("5. Quit");

            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {


                case 1:
                    System.out.println("Please enter the 7 digit Patron ID");
                    String patronID = patronIDValidation(scanner);

                    System.out.println("Please enter the Patron Name");
                    String patronName = scanner.nextLine();

                    System.out.println("Please enter the Patron Address including City,State and Zip Code");
                    String patronAddress = scanner.nextLine();

                    float patronOverdueFee;

                    while (true) {
                        System.out.println("Please enter the Patron Overdue Fee Amount (0 - 250):");
                        try {
                            patronOverdueFee = Float.parseFloat(scanner.nextLine().trim());
                            if (patronOverdueFee >= 0 && patronOverdueFee <= 250) {
                                break; // valid input, exit loop
                            } else {
                                System.out.println("Error: Amount must be between 0 and 250. Try again.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Invalid number format. Try again.");
                        }
                    }


                    String patronRecord = patronID + "-" + patronName + "-" + patronAddress + "-" + patronOverdueFee;
                    patrons.add(patronRecord);

                    System.out.println("Patron Added Successfully: " + patronRecord);
                    break;

                case 2:
                    System.out.println("Please enter the filepath to the list of Patrons you wish to enter into the Library Management System");
                    String path = scanner.nextLine();

                    StringBuilder currentPatron;
                    try (FileReader fr = new FileReader(path)) {
                        int c;

                        currentPatron = new StringBuilder();


                        while ((c = fr.read()) != -1) {
                            char ch = (char) c;


                            if (ch == '\n') {
                                patrons.add(currentPatron.toString().trim());
                                System.out.println(currentPatron);
                                currentPatron.setLength(0);
                            } else {
                                currentPatron.append(ch);
                            }
                        }
                        if (!currentPatron.isEmpty()) {
                            patrons.add(currentPatron.toString().trim());
                        }
                        System.out.println("Patrons loaded successfully.");
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found.");
                    } catch (IOException e) {
                        System.out.println("Error reading file.");
                    }
                    break;

                case 3:
                    System.out.println("Please enter the 7 digit Patron ID");
                    String removeID = patronIDValidation(scanner);
                    boolean removed = false;

                    for (int i = 0; i < patrons.size(); i++) {
                        if (patrons.get(i).startsWith(removeID)) {
                            patrons.remove(i);
                            removed = true;
                            System.out.println("Patron removed successfully " + removeID);
                            break;
                        }


                    }
                    if (!removed) {
                        System.out.println("Patron ID does not exist");
                    }
                    break;

                case 4:
                    System.out.println("Display all current Patrons in the Library Management System");
                    if (patrons.isEmpty()) {
                        System.out.println("No patrons in the Library Management System");
                    } else {
                        for (String patron : patrons) {
                            System.out.println(patron);
                        }
                    }
                    break;


                case 5:
                    System.out.println("Quitting the program");
                    break;
            }
        }
        scanner.close();
    }
}
