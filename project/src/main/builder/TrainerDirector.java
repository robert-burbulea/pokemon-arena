package main.builder;

public class TrainerDirector {
    private TrainerBuilder trainerBuilder;

    public TrainerDirector(TrainerBuilder trainerBuilder) {
        this.trainerBuilder = trainerBuilder;
    }

    public TrainerDirector() {

    }

    public Trainer getTrainer(){
        return this.trainerBuilder.getTrainer();
    }

    public Trainer makeTrainer(String line) {

        return trainerBuilder.readTrainer(line);
    }
}
