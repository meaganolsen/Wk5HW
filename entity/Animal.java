package entity;

public class Animal {

	private int animalId;
	private String animalName;
	
	public Animal(int animalId, String animalName) {
		this.setAnimalId(animalId);
		this.setAnimalName(animalName);
	}

	public int getAnimalId() {
		return animalId;
	}

	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}

	public String getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}
}
