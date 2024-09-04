# Pokemon Arena
![Project Logo](/pokemon.png)

This is a pokemon game that uses console input to battle another bot player in an arena, using multithreading.

To start, follow the text from the console and input your options for your pokemon trainer, pokemon and miscellaneous items.

Design patterns I used:

Singleton: There needs to be a single arena in which the trainers play, otherwise it wouldn't make sense from a gaming perspective.

Factory: Used it for creating items

Builder: Used for creating Trainers

Strategy: used for enabling and disabling pokemon's abilities, such as attacking, dodging, spells etc.
