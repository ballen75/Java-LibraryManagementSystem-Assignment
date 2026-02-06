import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

class patronManager {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> patrons = new ArrayList<>();

        System.out.println("Please enter the filepath to the list of Patrons you wish to enter into the Library Management System");
        String path = scanner.nextLine(); //I created a data.txt file within the project to easily update and add Patrons


        // Reads the text file and add's the Patrons to the ArrayList
        StringBuilder currentPatron;
        try (FileReader fr = new FileReader(path)) {
            int c;

            currentPatron = new StringBuilder();


            //The file with Patron information will be read and the details displayed in the console
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
            if (currentPatron.length() > 0) {
                patrons.add(currentPatron.toString().trim());
                System.out.println(currentPatron);


            }

            //Menu will be displayed with options for user to add, remove, or list patrons in the LMS system.
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}






