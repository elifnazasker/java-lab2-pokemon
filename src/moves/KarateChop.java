package moves;

import ru.ifmo.se.pokemon.*;

public final class KarateChop extends PhysicalMove {
    public KarateChop() { super(Type.FIGHTING, 50, 1); }
    @Override protected double calcCriticalHit(Pokemon self, Pokemon target) {
        if (Math.random() < 1.0 / 8) {
            System.out.println("Critical hit!");
            return 2; // Preserve the educational engine's critical multiplier.
        }
        return 1;
    }
    @Override protected String describe() { return "uses Karate Chop"; }
}
