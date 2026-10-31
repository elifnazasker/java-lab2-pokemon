package support;

import java.lang.reflect.Field;
import ru.ifmo.se.pokemon.*;

/**
 * Small adapter for data absent from the public API of the supplied JAR.
 * Tied to this exact Pokemon.jar; incompatible versions fail with a clear error.
 */
public final class LibraryAccess {
    private static final Field STAGE = field("stage");
    private static final Field PREPARED = field("preparedMove");
    private LibraryAccess() { }
    private static Field field(String name) {
        try {
            Field f = Pokemon.class.getDeclaredField(name);
            f.setAccessible(true);
            return f;
        } catch (ReflectiveOperationException | RuntimeException e) {
            throw new IllegalStateException("Incompatible Pokemon.jar: missing access to " + name, e);
        }
    }
    public static int positiveStages(Pokemon target) {
        try {
            Effect stages = (Effect) STAGE.get(target);
            int total = 0;
            for (Stat stat : Stat.values()) {
                if (stat != Stat.HP) total += Math.max(0, stages.stat(stat));
            }
            return total;
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Cannot read stat stages", e);
        }
    }
    public static void cancelPreparedMove(Pokemon target) {
        try {
            PREPARED.set(target, Move.getNoMove());
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Cannot apply flinch", e);
        }
    }
}
