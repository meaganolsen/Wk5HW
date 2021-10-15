package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.AnimalDao;
import entity.Animal;

public class Menu {
	private AnimalDao animalDao = new AnimalDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> userOptions = Arrays.asList("Display Animals","Display Animal", "Create Animal","Update Animal", "Delete Animal");
	
	public void start() {
		String selection = "";
		
		do {
		   printMenu();
		   selection = scanner.nextLine();
		   try {
			   if(selection.equals("1")) {
				  displayAnimals();
			   } else if(selection.equals("2")) {
			       displayAnimal();   
			   } else if(selection.equals("3")) {
			       createAnimal();   
			   } else if(selection.equals("4")) {
			       updateAnimal();   
			   } else if(selection.equals("5")) {
			       deleteAnimal();   
			   }
		   }catch (SQLException e) {
			   e.printStackTrace();
				   
			   }
		   System.out.println("Press Enter to Continue!");
		   scanner.nextLine();
		}while(!selection.equals("-1"));
	}
	
	private void printMenu() {
		System.out.println("Select an Option:\n----------");
		for (int i = 0; i < userOptions.size(); i++) {
			System.out.println(i + 1 + ")" + userOptions.get(i));
		}
	}
	
	private void displayAnimals() throws SQLException {
		List<Animal> animals = animalDao.getAnimals();
		for(Animal animal : animals) {
			System.out.println(animal.getAnimalId() + ":" + animal.getAnimalName());
		}
	}
	
	private void displayAnimal() throws SQLException {
		System.out.println("Enter Animal ID:");
		int id = Integer.parseInt(scanner.nextLine());
		Animal animal = animalDao.getAnimalById(id);
		System.out.println(animal.getAnimalId() + ":" + animal.getAnimalName());
		
		}
	private void createAnimal() throws SQLException {
		System.out.print("Enter new animal name:");
		String animalName = scanner.nextLine();
		animalDao.createAnimal(animalName);
		System.out.println("New Animal Added");
	}
	private void updateAnimal() throws SQLException {
		System.out.println("Enter animal name that you want:");
		String animalName = scanner.nextLine();
		System.out.println("Enter animal ID you want updated:");
		int id = Integer.parseInt(scanner.nextLine());
		animalDao.updateAnimalByID(animalName, id);
		System.out.println("1 record updated successfully!");
	}
	private void deleteAnimal() throws SQLException {
		System.out.println("Enter animal ID that you want deleted");
		int id = Integer.parseInt(scanner.nextLine());
		animalDao.deleteAnimalById(id);
	}
}
