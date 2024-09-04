package main.builder;

import main.items.Item;
import main.strategy.Pokemon;

import java.util.HashMap;

public class BasicTrainerBuilder implements TrainerBuilder {
    private Trainer trainer;

    public BasicTrainerBuilder() {
        this.trainer = new Trainer();
    }

    public BasicTrainerBuilder(Trainer trainer) {
        this.trainer = trainer;
    }

    public Trainer getTrainer() {
        return this.trainer;
    }

    @Override
    public Trainer readTrainer(String line) {
        String[] words = line.split(" ");
        String name = words[0];
        // read firstnames and surnames until we read the age
        int index = 1;
        String digits = "0123456789";
        while (true) {
            if (digits.contains(words[index].substring(0, 1))) {
                break;
            }
            name = name + " " + words[index];
            index++;
        }
        int age = Integer.parseInt(words[index]);
        index++;

        HashMap<String, Pokemon> pokemons = new HashMap<String, Pokemon>();
        HashMap<String, Item> items = new HashMap<String, Item>();
        String temporaryString = "";
        // automatically find pokemon or item in the static hashmaps
        while (index < words.length) {
            temporaryString = (temporaryString.equals("")) ? words[index] : (temporaryString + " " + words[index]);
            if (Pokemon.getAllPokemons().get(temporaryString) != null) {
                pokemons.put(temporaryString, Pokemon.getAllPokemons().get(temporaryString));
                temporaryString = "";
            } else if (Item.getAllItems().get(temporaryString) != null) {
                items.put(temporaryString, Item.getAllItems().get(temporaryString));
                temporaryString = "";
            }
            index++;
        }
        return new Trainer(name, age, pokemons, items);
    }
}
