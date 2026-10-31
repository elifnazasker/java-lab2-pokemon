import java.util.Locale;
import pokemons.*;
import ru.ifmo.se.pokemon.Battle;

/** Two teams of three. All Pokemon use level 50 for a short demonstration. */
public final class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        Battle battle = new Battle();
        battle.addAlly(new Claydol("Claydol", 50));
        battle.addAlly(new Raikou("Raikou", 50));
        battle.addAlly(new Wooper("Wooper", 50));
        battle.addFoe(new Steelix("Steelix", 50));
        battle.addFoe(new Flareon("Flareon", 50));
        battle.addFoe(new Quagsire("Quagsire", 50));
        battle.go();
    }
}

