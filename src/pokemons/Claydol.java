package pokemons;

import ru.ifmo.se.pokemon.*;
import moves.*;

/** Variant 83447690: types, base stats and assigned moves. */
public final class Claydol extends Pokemon {
    public Claydol(String name, int level) {
        super(name, level);
        setType(Type.GROUND, Type.PSYCHIC);
        setStats(60, 70, 105, 70, 120, 75);
        setMove(new Overheat(), new Rest(), new KarateChop(), new ConfuseRay());
    }
}

