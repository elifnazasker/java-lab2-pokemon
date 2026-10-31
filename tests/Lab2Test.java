import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import moves.*;
import pokemons.*;
import support.*;
import ru.ifmo.se.pokemon.*;

/** Integration checks against the original uploaded JAR. */
public final class Lab2Test {
    private static int checks;
    private static void check(boolean ok, String message) {
        if (!ok) throw new AssertionError(message);
        checks++;
    }
    private static Field field(Class<?> owner, String name) throws Exception {
        Field f = owner.getDeclaredField(name);
        f.setAccessible(true);
        return f;
    }
    private static void use(Move move, Pokemon user, Pokemon target) throws Exception {
        Method attack = Move.class.getDeclaredMethod("attack", Pokemon.class, Pokemon.class);
        attack.setAccessible(true);
        attack.invoke(move, user, target);
    }
    private static int stage(Pokemon pokemon, Stat stat) throws Exception {
        return ((Effect) field(Pokemon.class, "stage").get(pokemon)).stat(stat);
    }
    private static Pokemon fresh() {
        Pokemon p = new Claydol("test",50);
        p.restore();
        return p;
    }
    private static double baseDamage(Punishment move, Pokemon user, Pokemon target) throws Exception {
        Method m = Punishment.class.getDeclaredMethod("calcBaseDamage", Pokemon.class, Pokemon.class);
        m.setAccessible(true);
        return (Double) m.invoke(move, user, target);
    }
    public static void main(String[] args) throws Exception {
        Pokemon[] team = {new Claydol("c",50), new Steelix("s",50), new Raikou("r",50),
            new Wooper("w",50), new Flareon("f",50), new Quagsire("q",50)};
        String[][] expected = {
            {"Overheat","Rest","KarateChop","ConfuseRay"},
            {"Punishment","DreamEater","Flamethrower"},
            {"Punishment","DreamEater","Flamethrower","SmackDown"},
            {"FakeOut","Swagger"},
            {"FakeOut","Swagger","Withdraw"},
            {"FakeOut","Swagger","Withdraw","ViceGrip"}};
        for (int i=0; i<team.length; i++) {
            Pokemon p = team[i];
            check(p.isAlive() && p.getLevel() == 50, "Initialization");
            List<?> list = (List<?>) field(Pokemon.class, "moves").get(p);
            check(list.size() == expected[i].length, "Assigned move count");
            for (int j=0; j<expected[i].length; j++) {
                check(list.get(j).getClass().getSimpleName().equals(expected[i][j]), "Exact screenshot mapping");
                check(Modifier.isFinal(list.get(j).getClass().getModifiers()), "Move leaf is final");
            }
            check(Modifier.isFinal(p.getClass().getModifiers()) == (p.getClass() != Wooper.class), "Pokemon leaf final");
        }
        check(team[5] instanceof Wooper, "Real evolution inheritance");
        check(team[4].getClass().getSuperclass() == Pokemon.class, "Flareon is not a Wooper evolution");
        check(team[0].hasType(Type.GROUND) && team[0].hasType(Type.PSYCHIC), "Claydol types");

        Pokemon user = fresh(), target = fresh();
        Overheat overheat = new Overheat();
        field(Move.class,"accuracy").setDouble(overheat, 1); // deterministic effect test
        use(overheat, user, target);
        check(stage(user, Stat.SPECIAL_ATTACK) == -2, "Overheat self drop");
        user = fresh();
        user.setMod(Stat.HP, 30);
        Effect.poison(user);
        use(new Rest(), user, target);
        check(user.getHP() == user.getStat(Stat.HP), "Rest heals all HP");
        check(user.getCondition() == Status.SLEEP, "Rest replaces poison with sleep");
        user.turn(); // end of Rest turn
        check(user.getCondition() == Status.SLEEP, "Rest asleep after its own turn");
        user.prepareMove();
        check(field(Pokemon.class,"preparedMove").get(user) == Move.getNoMove(), "First sleeping turn cannot act");
        user.turn();
        check(user.getCondition() == Status.SLEEP, "Rest asleep after first future turn");
        user.turn();
        check(user.getCondition() == Status.NORMAL, "Rest wakes after second future turn");
        use(new Rest(), user, target);
        check(user.getCondition() == Status.NORMAL, "Rest fails at full HP");

        user = fresh(); target = fresh();
        use(new ConfuseRay(), user, target);
        check(field(Pokemon.class,"confusion").getInt(target) > 0, "Confuse Ray confuses");

        user = fresh(); target = fresh();
        Punishment punishment = new Punishment();
        baseDamage(punishment, user, target);
        check(field(Move.class,"power").getDouble(punishment) == 60, "Punishment base 60");
        target.setMod(Stat.ATTACK, 2);
        target.setMod(Stat.DEFENSE, -1);
        baseDamage(punishment, user, target);
        check(field(Move.class,"power").getDouble(punishment) == 100, "Punishment ignores negative stages");
        target.setMod(Stat.SPEED, 6);
        target.setMod(Stat.ACCURACY, 6);
        baseDamage(punishment, user, target);
        check(field(Move.class,"power").getDouble(punishment) == 200, "Punishment cap");
        target.restore();
        baseDamage(punishment, user, target);
        check(field(Move.class,"power").getDouble(punishment) == 60, "Punishment recalculates every hit");

        user = fresh(); target = fresh();
        user.setMod(Stat.HP, 60);
        double hp = target.getHP(), userHP = user.getHP();
        use(new DreamEater(), user, target);
        check(target.getHP() == hp && user.getHP() == userHP, "Dream Eater fails on awake target");
        target.setCondition(new Effect().condition(Status.SLEEP).attack(0).turns(3));
        use(new DreamEater(), user, target);
        check(target.getHP() < hp && user.getHP() > userHP, "Dream Eater drains sleeping target");
        check(user.getHP() <= user.getStat(Stat.HP), "Healing capped to max HP");
        user = fresh(); target = fresh();
        user.setMod(Stat.HP, 50);
        target.setMod(Stat.HP, (int) target.getHP() - 1);
        target.setCondition(new Effect().condition(Status.SLEEP).attack(0).turns(3));
        userHP = user.getHP();
        use(new DreamEater(), user, target);
        check(!target.isAlive() && user.getHP() == userHP + 1, "Drain uses actual HP loss, not overkill");

        user = new Quagsire("fake",50); user.restore(); target = fresh();
        field(Pokemon.class,"preparedMove").set(target, Move.getStruggleMove());
        FakeOut fake = new FakeOut();
        check(fake.getPriority() == 3, "Fake Out priority");
        hp = target.getHP();
        use(fake, user, target);
        check(target.getHP() < hp, "First Fake Out damages");
        check(field(Pokemon.class,"preparedMove").get(target) == Move.getNoMove(), "Flinch cancels this turn");
        hp = target.getHP();
        use(fake, user, target);
        check(target.getHP() == hp, "Repeated Fake Out fails");
        target.prepareMove();
        check(field(Pokemon.class,"preparedMove").get(target) != Move.getNoMove(), "Flinch does not persist next turn");

        user = new Quagsire("other",50); user.restore(); target = fresh();
        use(new Withdraw(), user, target);
        check(stage(user,Stat.DEFENSE) == 1, "Withdraw boosts defense");
        hp = target.getHP();
        use(new FakeOut(), user, target);
        check(target.getHP() == hp, "Fake Out fails after another action");
        target = fresh();
        Method swagger = Swagger.class.getDeclaredMethod("applyOppEffects", Pokemon.class);
        swagger.setAccessible(true);
        swagger.invoke(new Swagger(), target);
        check(stage(target,Stat.ATTACK) == 2 && field(Pokemon.class,"confusion").getInt(target) > 0, "Swagger boost and confusion");
        user = fresh(); target = fresh();
        use(new SmackDown(), user, target);
        check(MoveState.isGrounded(target), "Smack Down grounding marker");
        hp = target.getHP();
        use(new ViceGrip(), user, target);
        check(target.getHP() < hp, "Vice Grip damage");
        check(field(Move.class,"accuracy").getDouble(new Overheat()) == 0.9, "Fractional accuracy");
        check(field(Move.class,"accuracy").getDouble(new Swagger()) == 0.85, "Swagger accuracy");
        System.out.println("PASS: " + checks + " integration checks for variant 83447690.");
    }
}

