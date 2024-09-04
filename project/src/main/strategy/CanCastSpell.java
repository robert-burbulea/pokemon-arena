package main.strategy;

import main.Ability;

public class CanCastSpell implements CastSpell {
    public boolean castSpell(Pokemon pokemon, Pokemon enemy, Ability ability) {
        // deal dmg
        int damage = ability.getDmg();
        if (enemy.isDodge()) {
            damage = 0;
            System.out.println("Enemy dodged your ability!");
        }
        enemy.setHP(enemy.getHP() - damage);
        // stun
        enemy.setStunned(ability.isStun());
        // set dodge for next attack
        pokemon.setDodge(ability.isDodge());

        System.out.print("Damage done: " + damage);
        System.out.print(ability.isStun() ? "Ability stunned the enemy!\n" : "");
        System.out.print(ability.isDodge() ? "You will dodge the next attack!\n" : "");
        System.out.println();
        enemy.setDodge(false);


        // resetting cooldown
        if (ability.equals(pokemon.getAbility1())) {
            pokemon.getAbility1().setCd(pokemon.getAbility1().getInitialCooldown());
        } else {
            // resetting cooldown
            pokemon.getAbility2().setCd(pokemon.getAbility2().getInitialCooldown());
        }

        return true;

    }
}
