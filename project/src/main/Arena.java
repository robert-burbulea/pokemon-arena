package main;

import main.builder.Trainer;
import main.strategy.CanCastSpell;
import main.strategy.Pokemon;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Arena {
    private static Arena arenaInstance;
    Lock lock;
    Condition player1Turn;
    Condition player2Turn;
    boolean player1Ready;
    boolean player2Ready;
    boolean matchFinished;

    private Arena() {
        lock = new ReentrantLock();
        player1Turn = lock.newCondition();
        player2Turn = lock.newCondition();
        player1Ready = true;
        player2Ready = false;
        matchFinished = false;
    }

    //Singleton design pattern
    public static Arena instance(){
        if (arenaInstance == null) {
            arenaInstance = new Arena();
        }
        return arenaInstance;
    }
    public void startMatch() {
        try {
            double chance = Math.ceil(Math.random() * 3);
            if (chance == 1.0 || chance == 2.0) {
                System.out.println("Match against Neutral");
                Trainer trainer = new Reader().chooseTrainer();
                Pokemon pokemon = new Reader().choosePokemon(trainer);
                new Reader().chooseItems(trainer, pokemon);
                System.out.println(pokemon);
                System.out.println("Fight!");

                if (chance == 1.0) {
                    this.matchVSNeutral(pokemon, Pokemon.getAllPokemons().get("Neutrel1"));
                } else {
                    this.matchVSNeutral(pokemon, Pokemon.getAllPokemons().get("Neutrel2"));
                }
            } else {
                System.out.println("PvP");
                System.out.println("Player 1 pick");
                Trainer trainer1 = new Reader().chooseTrainer();
                Pokemon pokemon1 = new Reader().choosePokemon(trainer1);
                new Reader().chooseItems(trainer1, pokemon1);

                System.out.println("Player 2 pick");
                Trainer trainer2 = new Reader().chooseTrainer();
                Pokemon pokemon2 = new Reader().choosePokemon(trainer2);
                new Reader().chooseItems(trainer2, pokemon2);

                System.out.println("Player 1");
                System.out.println(pokemon1.inGameToString());
                System.out.println("VS");
                System.out.println("Player 2");
                System.out.println(pokemon2.inGameToString());
                System.out.println("Fight!");

                Player1 player1 = new Player1(pokemon1, pokemon2);
                Player2 player2 = new Player2(pokemon2, pokemon1);
                ExecutorService executor = Executors.newFixedThreadPool(2);

                executor.execute(player1);
                executor.execute(player2);

                executor.shutdown();
            }
        } catch (IllegalMonitorStateException e) {
            e.printStackTrace();
        }
    }

    public boolean takeTurn(Pokemon pokemon, Pokemon enemy) {
        Scanner scanner = new Scanner(System.in);
        String actionString = "";

        System.out.println("Choose action: Ability 1 (1), Ability 2 (2), Attack (3)");
        actionString = scanner.next();
        if (actionString.equals("1")) {
            if (pokemon.getAbility1().getCd() > 0) {
                System.out.println("Ability 1 is on cooldown for " + pokemon.getAbility1().getCd() + " rounds");
                return false;  // turn didn't finish properly
            }

            // this is part of the strategy design pattern
            pokemon.setCastSpell(new CanCastSpell());
            pokemon.getCastSpellStatus().castSpell(pokemon, enemy, pokemon.getAbility1());


        } else if (actionString.equals("2")) {
            if (pokemon.getAbility2().getCd() > 0) {
                System.out.println("Ability 2 is on cooldown for " + pokemon.getAbility2().getCd() + " rounds");
                return false;
            }

            // this is part of the strategy design pattern
            pokemon.setCastSpell(new CanCastSpell());
            pokemon.getCastSpellStatus().castSpell(pokemon, enemy, pokemon.getAbility1());

        } else if (actionString.equals("3")) {
            //this is part of the strategy design pattern
            //if for some reason the pokemon can't attack, apart from being stunned,
            // the attack will not be successful
            pokemon.getAttackStatus().attackEnemy(pokemon, enemy, true);

        } else {
            System.out.println("You can only press 1, 2 or 3");
            return false;
        }

        return true;

    }

    public void matchVSNeutral(Pokemon pokemon, Pokemon neutral) {
        System.out.println(pokemon.inGameToString());
        System.out.println(neutral.inGameToString());
        boolean turnFinished;

        while (true) {
            turnFinished = takeTurn(pokemon, neutral);
            if (!turnFinished) continue;

            if (neutral.getHP() <= 0) {
                System.out.println("You won!");
                break;
            }

            if (neutral.isStunned()) {
                System.out.println("Neutral is stunned");
                neutral.setStunned(false);
            } else {
                neutral.getAttackStatus().attackEnemy(neutral, pokemon, false);
            }
            if (pokemon.getHP() <= 0) {
                System.out.println("You lost!");
                break;
            }
            pokemon.reduceCooldownsBy1();
//            neutral.reduceCooldownsBy1();

            System.out.println(pokemon.inGameToString());
            System.out.println(neutral.inGameToString());
        }
    }

    public void player1VSPlayer2(Pokemon pokemon1, Pokemon pokemon2) {
        try {
            while (true) {
                lock.lock();
                if (!player1Ready) {
                    player1Turn.await();
                }
                System.out.println(pokemon1.sideBySideToString(pokemon2));
                System.out.println("Player 1's turn");
                // try turns until the turn finishes properly: due to cooldown/wrong input, turns can be invalid
                while (true) {
                    if (pokemon1.isStunned()) {
                        System.out.println("Player 1 is stunned");
                        pokemon1.setStunned(false);
                        break;
                    }
                    boolean turnFinished = this.takeTurn(pokemon1, pokemon2);
                    if (turnFinished) break;
                }
                if (pokemon2.getHP() <= 0) {
                    System.out.println("Player 1 won!");
                    matchFinished = true;
                    break;
                }
                pokemon1.reduceCooldownsBy1();
                player2Turn.signal();
                player2Ready = true;
                player1Ready = false;
            }
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void player2VSPlayer1(Pokemon pokemon2, Pokemon pokemon1) {
        try {
            while (true) {
                lock.lock();
                if (!player2Ready) {
                    player2Turn.await();
                }
                System.out.println(pokemon2.sideBySideToString(pokemon1));
                System.out.println("Player 2's turn");
                // try turns until the turn finishes properly: due to cooldown/wrong input, turns can be invalid
                while (true) {
                    if (pokemon2.isStunned()) {
                        System.out.println("Player 2 is stunned");
                        pokemon2.setStunned(false);
                        break;
                    }
                    boolean turnFinished = this.takeTurn(pokemon2, pokemon1);
                    if (turnFinished) break;
                }
                if (pokemon1.getHP() <= 0) {
                    System.out.println("Player 2 won!");
                    matchFinished = true;
                    break;
                }
                pokemon2.reduceCooldownsBy1();
                player1Turn.signal();
                player1Ready = true;
                player2Ready = false;
            }


        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
