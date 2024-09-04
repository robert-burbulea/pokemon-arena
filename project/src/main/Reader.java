package main;

import main.builder.BasicTrainerBuilder;
import main.builder.Trainer;
import main.builder.TrainerBuilder;
import main.builder.TrainerDirector;
import main.strategy.Pokemon;
import main.items.Item;
import main.items.ItemFactory;

import java.io.*;
import java.util.*;

public class Reader {
    String filename;
    public FileReader fr;
    public BufferedReader br;
    public Scanner sc;

    public Reader(){

    }

    public Reader(String filename) throws FileNotFoundException {
        this.filename = filename;
        fr = new FileReader(filename);
        br = new BufferedReader(fr);
    }

    public HashMap<String, Pokemon> readPokemons() throws IOException, NumberFormatException {
        String line;
        int i;
        HashMap<String, Pokemon> pokemons = new HashMap<String, Pokemon>();

        line = br.readLine(); // details row
        while (((line = br.readLine())) != null) {
            Pokemon newPokemon = Pokemon.readPokemon(line);
            pokemons.put(newPokemon.getName(), newPokemon);
        }
        return pokemons;
    }

    public HashMap<String, Item> readItems() throws IOException {
        String line;
        int i;
        HashMap<String, Item> items = new HashMap<String, Item>();

        // Factory design pattern
        ItemFactory itemFactory = new ItemFactory();
        line = br.readLine(); // details row
        while (((line = br.readLine())) != null) {
            Item newItem = itemFactory.makeItem(line);
            items.put(newItem.getName(), newItem);
        }
        return items;
    }

    //will use builder design pattern
    public HashMap<String, Trainer> readTrainers() throws IOException, NullPointerException {
        String line;
        int i;
        HashMap<String, Trainer> trainers = new HashMap<String, Trainer>();

        TrainerBuilder basicTrainerBuilder = new BasicTrainerBuilder();
        TrainerDirector trainerDirector = new TrainerDirector(basicTrainerBuilder);

        line = br.readLine(); // details row
        while (((line = br.readLine())) != null) {
            Trainer newTrainer = trainerDirector.makeTrainer(line);
            trainers.put(newTrainer.getName(), newTrainer);
        }
        return trainers;
    }

    public Trainer chooseTrainer() {
        System.out.print("Choose trainer:");
        for (Map.Entry<String, Trainer> trainerEntry : Trainer.getAllTrainers().entrySet()) {
            System.out.print(" " + trainerEntry.getKey() + ",");
        }
        System.out.println();

        sc = new Scanner(System.in);
        String trainerString = sc.nextLine();
        Trainer trainer = Trainer.getAllTrainers().get(trainerString);
        return trainer;
    }

    public Pokemon choosePokemon(Trainer trainer) {
        System.out.print("Choose pokemon:");
        for(Map.Entry<String, Pokemon> pokemonEntry : trainer.getPokemons().entrySet())   {
            System.out.print(" " + pokemonEntry.getKey() + ",");
        }
        System.out.println();

        sc = new Scanner(System.in);
        String pokemonString = sc.nextLine();
        Pokemon pokemon = Pokemon.getAllPokemons().get(pokemonString);
        return pokemon;
    }

    public void chooseItems(Trainer trainer, Pokemon pokemon) {
        System.out.println("Choose items:");
        for(Map.Entry<String, Item> itemEntry : trainer.getItems().entrySet()) {
            itemEntry.getValue().displayItem();
        }
        System.out.println();

        sc = new Scanner(System.in);
        String itemsString = sc.nextLine();
        String[] words = itemsString.split(" ");
        String tempString = "";
        int equippedItems = 0;
        for (int i = 0; i < words.length & equippedItems <= 3; i++) {
            // adding the next word to tempString
            tempString = (tempString.equals("")) ? words[i] : tempString + " " + words[i];

            // if found in trainer's item list, equip the pokemon with it
            Item item = trainer.getItems().get(tempString);
            if (item != null) {
                if (equippedItems == 3) {
                    System.out.println("Can't equip more than 3 items. Only the first 3 were chosen.");
                    break;
                }
                System.out.println(tempString);
                pokemon.equipItem(trainer, item);
                tempString = "";
                equippedItems++;
            }
        }
    }
}
