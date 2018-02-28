package columbus;

public class Paddle extends PowerUps{
	Abilities abilities;
	
	public Paddle(Abilities abilities) {
		this.abilities = abilities;
	}
	
	@Override
	public String getAbilities() {
		return abilities.getAbilities() + ", Paddle";
	}
	

}
