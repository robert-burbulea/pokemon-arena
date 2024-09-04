package main;

import main.builder.Trainer;
import main.strategy.Pokemon;
import main.items.Item;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static Arena arena;
    public static void main(String[] args) {
        try {
            String filename;
            int i;
            HashMap<String, Pokemon> pokemons = new HashMap<String, Pokemon>();
            HashMap<String, Item> items = new HashMap<String, Item>();

            filename = "data\\pokemonFiles\\allPokemons.txt";
            Pokemon.setAllPokemons( new Reader(filename).readPokemons());
//            for (Map.Entry<String, Pokemon> pokemonEntry : Pokemon.getAllPokemons().entrySet()) {
//                System.out.println(pokemonEntry.getValue());
//            }

            filename = "data\\itemFiles\\items1.txt";
            Item.setAllItems( new Reader(filename).readItems() );
//            for (Map.Entry<String, Item> itemEntry : Item.getAllItems().entrySet()) {
//                System.out.println(itemEntry.getValue());
//            }

            filename = "data\\trainerFiles\\allTrainers.txt";
            Trainer.setAllTrainers( new Reader(filename).readTrainers() );
//            for (Map.Entry<String, Trainer> trainerEntry : Trainer.getAllTrainers().entrySet()) {
//                System.out.println(trainerEntry.getValue());
//            }

            arena = Arena.instance();
            arena.startMatch();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /*
    TO DO
    1. beautify console to print landscape, your pokemon's and your enemies' hp, avatar
    2. choose pokemon and objects before duel
    3. every turn choose your next move: show damage dealt above hp bar, new hp etc
    4. add one point after each victory

     */
}
