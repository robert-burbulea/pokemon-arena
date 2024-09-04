package main;

import main.strategy.Pokemon;

public class Player2 implements Runnable{
    private Pokemon pokemon1;
    private Pokemon pokemon2;

    public Player2() {
    }

    public Player2(Pokemon pokemon2, Pokemon pokemon1) {
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
    }

    @Override
    public void run() {
        Main.arena.player2VSPlayer1(pokemon2, pokemon1);
    }

    public Pokemon getPokemon1() {
        return pokemon1;
    }

    public void setPokemon1(Pokemon pokemon1) {
        this.pokemon1 = pokemon1;
    }

    public Pokemon getPokemon2() {
        return pokemon2;
    }

    public void setPokemon2(Pokemon pokemon2) {
        this.pokemon2 = pokemon2;
    }
}
