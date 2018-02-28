package columbus;

public class Booze extends PowerUps{
	Abilities abilities;
	
	public Booze(Abilities abilities) {
		this.abilities = abilities;
	}

	@Override
	public String getAbilities() {
		return abilities.getAbilities() + ", booze";
	}
	
	

}
