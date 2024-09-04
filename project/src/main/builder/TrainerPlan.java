package main.builder;

import main.items.Item;
import main.strategy.Pokemon;

import java.util.HashMap;

public interface TrainerPlan {

    public void setName(String name);

    public void setAge(int age);

    public void setPokemons(HashMap<String, Pokemon> pokemons);

    public void setItems(HashMap<String, Item> items);



}
