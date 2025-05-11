package atm;

import java.util.HashMap;
import java.util.Scanner;

public class ATM {
    private HashMap<Integer, BankAccount> accounts;
    private Scanner scanner;

    public ATM() {
        accounts = new HashMap<>();
        scanner = new Scanner(System.in);
        loadAccounts();
    }

    private void loadAccounts() {
        accounts.put(12345, new BankAccount(12345, 1111, 1000.0));
        accounts.put(67890, new BankAccount(67890, 2222, 500.0));
    }

    public void start() {
        System.out.println("=== Bienvenido al Cajero Automático ===");

        System.out.print("Ingrese su número de cuenta: ");
        int accountNumber = scanner.nextInt();

        System.out.print("Ingrese su PIN: ");
        int pin = scanner.nextInt();

        BankAccount account = accounts.get(accountNumber);

        if (account != null && account.validatePin(pin)) {
            System.out.println("Inicio de sesión exitoso.");
            showMenu(account);
        } else {
            System.out.println("Número de cuenta o PIN incorrecto.");
        }
    }

    private void showMenu(BankAccount account) {
        int choice;
        do {
            System.out.println("\n1. Consultar saldo");
            System.out.println("2. Depositar dinero");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.printf("Su saldo es: $%.2f\n", account.getBalance());
                    break;
                case 2:
                    System.out.print("Ingrese monto a depositar: ");
                    double depAmount = scanner.nextDouble();
                    account.deposit(depAmount);
                    System.out.println("Depósito exitoso.");
                    break;
                case 3:
                    System.out.print("Ingrese monto a retirar: ");
                    double withAmount = scanner.nextDouble();
                    if (account.withdraw(withAmount)) {
                        System.out.println("Retiro exitoso.");
                    } else {
                        System.out.println("Fondos insuficientes.");
                    }
                    break;
                case 4:
                    System.out.println("Gracias por usar el cajero.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (choice != 4);
    }
}
