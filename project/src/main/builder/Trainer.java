package main.builder;

import main.strategy.Pokemon;
import main.items.Item;

import java.util.HashMap;
import java.util.Map;

public class Trainer implements TrainerPlan{
    private String name;
    private int age;
    private HashMap<String, Pokemon> pokemons;
    private HashMap<String, Item> items;
    private static HashMap<String, Trainer> allTrainers;


    public Trainer(String name, int age, HashMap<String, Pokemon> pokemons, HashMap<String, Item> items) {
        this.name = name;
        this.age = age;
        this.pokemons = pokemons;
        this.items = items;
    }

    public Trainer() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public HashMap<String, Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(HashMap<String, Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public HashMap<String, Item> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Item> items) {
        this.items = items;
    }

    public static Map<String, Trainer> getAllTrainers() {
        return allTrainers;
    }

    public static void setAllTrainers(HashMap<String, Trainer> allTrainers) {
        Trainer.allTrainers = allTrainers;
    }

    @Override
    public String toString() {
//        String pokemonsString = "";
//        String itemsString = "";
//        for (String pokemonName : pokemons) {
//            pokemonsString += Pokemon.getAllPokemons().get(pokemonName);
//        }
//
//        for (String itemName : items) {
//            itemsString += Item.getAllItems().get(itemName);
//        }
        return "\nTrainer" +
                "\nname: " + name +
                "\nage: " + age +
                "\npokemons:" + pokemons +
                "\nitems:" + items +
                '\n';
    }
}
