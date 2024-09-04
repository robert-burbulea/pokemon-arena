package main.strategy;

import main.Ability;

public interface CastSpell {
    public boolean castSpell (Pokemon pokemon, Pokemon enemy, Ability ability);
    // will return true if the turn finished properly and false otherwise
}
