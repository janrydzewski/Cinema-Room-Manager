package cinema;

import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Cinema {
    static int choice, rowNumber, seatNumber, purchasedTickets = 0, currentIncome = 0, totalIncome = 0, screeningRoomSeatings;
    static int numberOfRows, numberOfSeats, firstHalf, secondHalf, totalSeats;
    static double percentage = 0;
    static Scanner scanner;
    static DecimalFormat df = new DecimalFormat("0.00");
    static String[][] screeningRoom;

    public static void printSeating() {
        System.out.println(" ");
        System.out.println("Cinema:");
        for (int i = 0; i < screeningRoom.length; i++) {
            for (int j = 0; j < screeningRoom[i].length; j++) {
                System.out.print(screeningRoom[i][j]);
            }
            System.out.println();
        }
    }

    public static void printMenu() {
        System.out.println(" ");
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");

    }

    public static void buyATicket(int a, int b) {
        System.out.println(" ");

        System.out.println("Enter a row number:");
        rowNumber = scanner.nextInt();

        System.out.println("Enter a seat number in that row:");
        seatNumber = scanner.nextInt();

        if(rowNumber > numberOfRows || seatNumber > numberOfSeats){
            System.out.println("Wrong input!");
            buyATicket(totalSeats, firstHalf);
        }
        else{
            if(screeningRoom[rowNumber][seatNumber] == "B "){
                System.out.println("That ticket has already been purchased!");
                buyATicket(totalSeats, firstHalf);
            }
            else{
                int ticketPrice;
                if (a > 60) {
                    if (rowNumber <= b) {
                        ticketPrice = 10;
                    } else {
                        ticketPrice = 8;
                    }
                } else {
                    ticketPrice = 10;
                }
                System.out.println("Ticket price: $" + ticketPrice);

                screeningRoom[rowNumber][seatNumber] = "B ";
                currentIncome += ticketPrice;
                purchasedTickets += 1;
                percentage = (double)purchasedTickets/totalSeats * 100;


            }
        }

    }

    public static void printStatistics(){
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.println("Percentage: " + df.format(percentage) + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    public static void main(String[] args) {



        scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        numberOfRows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        numberOfSeats = scanner.nextInt();

        firstHalf = (int) Math.floor(numberOfRows / 2);
        secondHalf = numberOfRows - firstHalf;
        totalSeats = numberOfSeats * numberOfRows;
        screeningRoomSeatings = totalSeats;


        screeningRoom = new String[numberOfRows + 1][numberOfSeats + 1];
        screeningRoom[0][0] = ("  ");
        for (int i = 1; i < numberOfSeats + 1; i++) {
            screeningRoom[0][i] = i + " ";
        }
        for (int i = 1; i < screeningRoom.length; i++) {
            for (int j = 0; j < screeningRoom[i].length; j++) {
                if (j == 0) {
                    screeningRoom[i][j] = i + " ";
                } else {
                    screeningRoom[i][j] = "S ";
                }
            }
        }
        if(totalSeats>60){
            totalIncome = firstHalf*numberOfSeats*10 + secondHalf*numberOfSeats*8;
        }
        else{
            totalIncome = numberOfRows * numberOfSeats * 10;
        }


        System.out.println(" ");

        while(true){
            printMenu();
            choice = scanner.nextInt();

            if (choice == 1) {
                printSeating();

            } else if (choice == 2) {
                buyATicket(totalSeats, firstHalf);

            } else if (choice == 3) {
                printStatistics();

            } else if (choice == 0) {
                return;
            }
        }

    }
}




