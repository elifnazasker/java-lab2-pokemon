package pokemons;

import ru.ifmo.se.pokemon.*;
import moves.*;

/** Variant 83447690: types, base stats and assigned moves. */
public final class Raikou extends Pokemon {
    public Raikou(String name, int level) {
        super(name, level);
        setType(Type.ELECTRIC);
        setStats(90, 85, 75, 115, 100, 115);
        setMove(new Punishment(), new DreamEater(), new Flamethrower(), new SmackDown());
    }
}

