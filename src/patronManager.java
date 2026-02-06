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
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> patrons = new ArrayList<>();

        System.out.println("Please enter the filepath to the list of Patrons you wish to enter into the Library Management System");
        String path = scanner.nextLine();

        /*
          Allows user to enter file location to batch upload Patrons.
          Adds Patrons to ArrayList()
          Displays Patrons
         */
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
                System.out.println(currentPatron);


            }

            /*
              Provides console menu for user to select Add, Remove or List Patrons in the LMS System
             */
            int choice = 0;

            while (choice != 4) {

                System.out.println("Please select an option from the menu for the Library Management System");
                System.out.println("1. Add Patron");
                System.out.println("2. Remove Patron");
                System.out.println("3. List Patrons");
                System.out.println("4. Quit");

                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("Please enter the 7 digit Patron ID");
                        String patronID = scanner.nextLine();
                        System.out.println("Please enter the Patron Name");
                        String patronName = scanner.nextLine();
                        System.out.println("Please enter the Patron Address including City,State and Zip Code");
                        String patronAddress = scanner.nextLine();
                        System.out.println("Please enter the Patron Overdue Fee Amount");
                        String patronOverdueFee = scanner.nextLine();

                        String patronRecord = patronID + "-" + patronName + "-" + patronAddress + "-" + patronOverdueFee;
                        patrons.add(patronRecord);

                        System.out.println("Patron Added Successfully: " + patronRecord);
                        break;

                    case 2:
                        System.out.println("Please enter the 7 digit Patron ID");
                        String removeID = scanner.nextLine();
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

                    case 3:
                        System.out.println("Display all current Patrons in the Library Management System");
                        if (patrons.isEmpty()) {
                            System.out.println("No patrons in the Library Management System");
                        } else {
                            for (String patron : patrons) {
                                System.out.println(patron);
                            }
                        }

                }


            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}






