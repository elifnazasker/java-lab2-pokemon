package moves;

import ru.ifmo.se.pokemon.*;
import support.LibraryAccess;

public final class Punishment extends PhysicalMove {
    public Punishment() { super(Type.DARK, 60, 1); }
    @Override protected double calcBaseDamage(Pokemon self, Pokemon target) {
        power = Math.min(200, 60 + 20 * LibraryAccess.positiveStages(target));
        return super.calcBaseDamage(self, target);
    }
    @Override protected String describe() { return "uses Punishment"; }
}
