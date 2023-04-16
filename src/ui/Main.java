package ui;

import model.Controller;

import java.util.Scanner;

public class Main {
    private final Controller controller;
    public static Scanner sc = new Scanner(System.in);

    public Main() {
        controller = new Controller();
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.readGson();
        m.menu();
    }

    public void readGson() {
        controller.readGson();
    }

    public void menu() {
        int option;

        do {
            try {
                System.out.println("""
                        Select an option:\s
                        1.Add country\s
                        2.See ranking by medals\s
                        3.see ranking by total medals\s
                        4.see alphabetical ranking
                        0.exit""");
                option = sc.nextInt();
                sc.nextLine();
                switch (option) {
                    case 1:
                        addCountry();
                        break;
                    case 2:
                        System.out.println(controller.sortMedals());
                        break;
                    case 3:
                        System.out.println(controller.sortTotalMedals());
                        break;
                    case 4:
                        System.out.println(controller.sortByName());
                        break;
                    case 0:
                        System.out.println("Exit successfully");
                        controller.saveGson();
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid option");
                sc.nextLine();
                option=1;
            }
        } while (option != 0);

    }

    public void addCountry() {
        int g = 0;
        int s = 0;
        int b = 0;
        boolean add = true;
        String name = "";
        System.out.println("Enter the country data: ");
        try {
            String[] data = sc.nextLine().split("::");
            if (data.length < 3) {
                throw new Exception();
            }
            name = data[0];
            for (int i = 0; i < data.length; i++) {
                if (data[i].equalsIgnoreCase("golden")) {
                    g = Integer.parseInt(data[i + 1]);
                } else if (data[i].equalsIgnoreCase("silver")) {
                    s = Integer.parseInt(data[i + 1]);
                } else if (data[i].equalsIgnoreCase("bronze")) {
                    b = Integer.parseInt(data[i + 1]);
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid data");
            add = false;
        }
        if (add) {
            controller.addCountry(name, g, s, b);
        }
    }
}