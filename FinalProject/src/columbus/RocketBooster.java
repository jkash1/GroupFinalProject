package columbus;

public class RocketBooster extends PowerUps{
	Abilities abilities;
	
	
	public RocketBooster(Abilities abilities) {
		this.abilities = abilities;
	}
	
	
	
	@Override
	public String getAbilities() {
		
		return abilities.getAbilities() + ", RocketBooster";
	}
	

}
