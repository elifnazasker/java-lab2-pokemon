# Verification — variant 83447690

Date: 31 October 2026. Platform: Windows, Adoptium JDK 21.0.12.
Compilation target: Java 17.

## Input

The six Pokémon and all assigned moves were transcribed from the eight supplied screenshots; see ASSIGNMENT.md.
Pokemon.jar is unchanged, SHA-256:
`EDAAB1C1CF96E5F5175BA86BBA368B7EBA7E2B423AA6ED947BB6DBE588D1CFB8`.

## Checks

- 21 production Java source files compile without errors or warnings.
- 90 integration checks pass against the actual uploaded JAR.
- Every Pokémon's ordered move list matches the screenshots, including counts 4/3/4/2/3/4.
- Checks cover real Wooper → Quagsire inheritance, independent Flareon ancestry, final leaf classes, move priority, fractional accuracy, Overheat's stored stage drop, Rest healing/status replacement/two future sleeping turns/full-HP failure, confusion, Punishment base/increased/capped/reset power, Dream Eater awake failure/sleep damage/healing/overkill, Fake Out first-use/later failure/current-turn flinch/next-turn recovery, Swagger effects, Withdraw, Smack Down's marker and Vice Grip damage.
- Overheat's effect test forces accuracy to 1 on the test instance to avoid a random test failure. Production accuracy remains 0.9 and is checked separately.
- Random secondary probabilities and critical-hit distributions were reviewed in source, not statistically tested.
- The runnable JAR has Main-Class and relative Class-Path. Full battles are launched through `java -jar dist/Lab2Pokemon.jar`.
- Two complete battles ended with winner messages and process exit code 0. Logs: evidence/battle-1.txt and evidence/battle-2.txt. Rest and confusion can make some random battles take several minutes.
- VS Code configuration files are provided; the GUI was not exercised.

## Known mechanics and portability limits

The educational JAR has a negative-stage formula defect (`2 + stage` instead of `2 - stage`), immune damaging hits still remove 1 HP, random damage and critical formulas differ from modern games, and hidden accuracy stats require restore() before isolated tests.
These observations were established during the initial API/bytecode inspection and integration tests. The JAR remains unchanged.

LibraryAccess reads private stat stages for Punishment and replaces the prepared move for Fake Out. These fields were inspected and tested in this supplied JAR. Another library version may require adapting those field names; access failure raises an explicit error.

Fake Out's skipped-first-turn edge cases and Smack Down's airborne rules remain limited as described in MOVES.md. Grounding is recorded but cannot automatically change the engine's future type checks. These are explicit limitations, not verified full game mechanics.

All Pokémon in a new battle must be new instances. Rest and Withdraw use public library effects; the reflection adapter does not alter the original JAR on disk.
No GitHub repository was created and no assignment was submitted online.

## Evidence

Current test results and final battle logs are stored in evidence.
Older provisional implementation logs are excluded from the final archive.

