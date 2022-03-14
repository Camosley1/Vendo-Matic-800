package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.Product;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String EXIT_VENDINGMACHINE = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, EXIT_VENDINGMACHINE };
	private static final String FEED_MONEY_OPTION = "Feed Money";
	private static final String PURCHASE_OPTION = "Select Product";
	private static final String END_TRANSACTION = "Finish Transaction";
	private static final String []PURCHASE_MENU_OPTION = {FEED_MONEY_OPTION,PURCHASE_OPTION,END_TRANSACTION};

	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_RESET = "\u001B[0m";


	Scanner input = new Scanner(System.in);
	VendingMachine vendingMachine = new VendingMachine();

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
		vendingMachine.establishInventory();
	}

	public void run ()throws NumberFormatException {
		while (true) {

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS) || choice.equals("1")) {
				DisplayMenuItem(vendingMachine);
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				mainMenuOptionPurchase();
			} else if (choice.equals(EXIT_VENDINGMACHINE) || choice.equals("3")) {
				System.exit(1);
			}
		}
	}

	private void mainMenuOptionPurchase() {
		String select = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTION);
		if (select.equals(FEED_MONEY_OPTION) || select.equals("1")) {
			System.out.println(ANSI_GREEN + "Enter the amount to feed" + ANSI_RESET);
			double amount = input.nextDouble();
			input.nextLine();
			vendingMachine.feedMoney(amount);
		} else if (select.equals(PURCHASE_OPTION) || select.equals("2")) {
			purchaseOption();
		} else if (select.equals(ANSI_GREEN + "Finish Transaction" + ANSI_RESET) || select.equals("3")) {
			vendingMachine.change();
		}
	}

	private void purchaseOption() {
		DisplayMenuItem(vendingMachine);
		System.out.println(ANSI_BLUE + "Select a product" + ANSI_RESET);
		String productId = input.nextLine();
		if (vendingMachine.getItems().containsKey(productId)){
			if(vendingMachine.getItems().get(productId).getItems_quantity() >= 1){
				if(vendingMachine.getBalance() >= vendingMachine.getItems().get(productId).getPrice()){
					vendingMachine.purchaseValidation(productId);
				}else{
					System.out.println(ANSI_RED + "You do not have enough money for this purchase." + ANSI_RESET);
				}
			}else {
				System.out.println(vendingMachine.getItems().get(productId).getName()+ ANSI_RED + " is currently sold out." + ANSI_RESET);
			}
		} else {
			System.out.println(ANSI_RED + "This product does not exist" + ANSI_RESET);
		}
	}

	private void DisplayMenuItem(VendingMachine vendingMachine) {
		System.out.println("---------------------------------------------------------");
		System.out.println("---------------------------------------------------------");
		for (Map.Entry<String, Product> item : vendingMachine.getItems().entrySet()) {
			System.out.println(item.getKey() + "  " + item.getValue().getName() + "  " + item.getValue().getPrice()+" | "+item.getValue().getItems_quantity());
		}
		System.out.println("---------------------------------------------------------");
		System.out.println("---------------------------------------------------------");
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}