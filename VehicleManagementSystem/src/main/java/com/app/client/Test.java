
package com.app.client;

import java.util.Scanner;

import com.app.exception.InsuranceNotFoundException;
import com.app.exception.ServiceRecordNotFoundException;
import com.app.exception.VehicleNotFoundException;
import com.app.exception.VehicleOperationException;
import com.app.sericeImpl.RequirementsImpl;

public class Test {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		RequirementsImpl r = new RequirementsImpl();

		while (true) {

			System.out.println("\n===== VEHICLE MANAGEMENT SYSTEM =====");
			System.out.println("1. Insert Vehicle Details");
			System.out.println("2. Get Single Vehicle Details");
			System.out.println("3. Get All Vehicle Details");
			System.out.println("4. Update Vehicle Details");
			System.out.println("5. Delete Single Vehicle");
			System.out.println("6. Delete All Vehicles");
			System.out.println("7. Exit");
			System.out.print("Enter Your Choice: ");

			int choice = sc.nextInt();

			try {
				switch (choice) {

				case 1:
					r.insertData();
					break;

				case 2:
					r.getSingledata();
					break;

				case 3:
					r.getAllData();
					break;

				case 4:
					r.updateData();
					break;

				case 5:
					r.deleteData();
					break;

				case 6:
					System.out.print("Are you sure you want to delete all vehicles? (yes/no): ");

					String confirm = sc.next();

					if (confirm.equalsIgnoreCase("yes")) {
						r.deleteAllData();
					} else {
						System.out.println("Deletion Cancelled!");
					}
					break;

				case 7:
					System.out.println("Thank You!");
					sc.close();
					return;

				default:
					System.out.println("Please Enter a Valid Choice!");
				}

			} catch (VehicleNotFoundException | InsuranceNotFoundException | ServiceRecordNotFoundException e) {

				System.out.println(e.getMessage());

			} catch (VehicleOperationException e) {

				System.out.println("Error: " + e.getMessage());
			}
		}
	}
}
