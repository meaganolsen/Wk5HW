package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Animal;

public class AnimalDao {

	private Connection connection;
	private final String GET_ANIMALS_QUERY = "SELECT * FROM animals";
	private final String GET_ANIMAL_BY_ID_QUERY = "SELECT * FROM animals WHERE id = ?";
	private final String CREATE_NEW_ANIMAL_QUERY = "INSERT INTO animals(animal_name) values(?)";
	private final String UPDATE_ANIMAL_BY_ID_QUERY = "UPDATE animals SET animal_name = ? WHERE id = ?";
	private final String DELETE_ANIMAL_BY_ID_QUERY = "DELETE FROM animals WHERE id = ?";
	
	
	public AnimalDao() {
		connection = DBConnect.getConnection();
		
	}
	
	public List<Animal> getAnimals() throws SQLException{
		ResultSet display = connection.prepareStatement(GET_ANIMALS_QUERY).executeQuery();
		List<Animal> animals = new ArrayList<Animal>();
		
		while (display.next()) {
			animals.add(populateAnimal(display.getInt(1),display.getString(2)));
		}
		
		return animals;
	}
	
	public Animal getAnimalById(int id) throws SQLException {
		PreparedStatement displayById = connection.prepareStatement(GET_ANIMAL_BY_ID_QUERY);
		displayById.setInt(1, id);
		ResultSet displayAnimal = displayById.executeQuery();
		displayAnimal.next();
		return populateAnimal(displayAnimal.getInt(1), displayAnimal.getString(2));
	}
	public void createAnimal(String animalName) throws SQLException {
		PreparedStatement create = connection.prepareStatement(CREATE_NEW_ANIMAL_QUERY);
		create.setString(1, animalName);
		create.executeUpdate();
	}
	
	public void updateAnimalByID(String animalName, int id) throws SQLException {
		PreparedStatement update = connection.prepareStatement(UPDATE_ANIMAL_BY_ID_QUERY);
		update.setString(1, animalName);
		update.setInt(2, id);
		update.executeUpdate();
	}
	
	public void deleteAnimalById(int id) throws SQLException {
		PreparedStatement delete = connection.prepareStatement(DELETE_ANIMAL_BY_ID_QUERY);
		delete.setInt(1, id);
		delete.executeUpdate();
		
	}
	
	private Animal populateAnimal(int id, String name) {
		return new Animal(id, name);
	}
}
