# Automated method test plan

This suite implements md/testplan.md: run without user input, report Test / Expected / Actual / Result, and allow test data to be edited. It is based on the updated main branch; previous documentation branches are not merged into this branch. Production Java files are unchanged.

## Run

From the repository root, with a JDK installed:

```sh
mkdir -p /tmp/vjava-test-classes
javac -encoding UTF-8 -d /tmp/vjava-test-classes ./*.java
java -cp /tmp/vjava-test-classes AllTests
```

Compile only the root Java files. md/Tester.java and md/myMath.java are assignment examples, not game tests. Java 21 was used for validation. No third-party libraries, input, or `-ea` option are required. AllTests.main calls public static runTests(); each class-specific tester also exposes public static runTests(). A failed check is printed, remaining checks continue, and the final runner throws AssertionError (nonzero exit) if any checks failed. Exceptions from an individual checked operation are reported as failures unless explicitly expected.

## Change test data

Edit TestData.java for metadata, colors, starting positions, timer boundary cases, effect amounts and random sample count. The timer table includes an independent expected isDone value (1=true). Edit the named cases in the class-specific testers for coordinates, keys, expected return values and scenario setup. Recompile after any edit. To check failure reporting, change the first TIMERS row from `{25,0}` to `{25,1}`: the timer test should report FAIL and the process should exit nonzero. Restore it afterward.

Every row in the console includes a descriptive case name and expected and actual values. Arrays are compared by content, and null is compared directly. No test simply prints an assumed passing result. Random tests check legal membership rather than requiring an unlikely sequence or distribution. A forced single-exit fixture tests deterministic random dispatch. Fixtures are independent of gameplay and do not read stdin or depend on time or game completion.

## Cases and method coverage

Construct fresh fixtures as shown in each tester. Constructor and void-method calls establish preconditions; assertions inspect methods that return values. Source tables and individual TestSupport.check calls provide the exact input and expected values for each named case.

| Tester | Methods returning values | Preconditions / steps / data | Expected results |
| --- | --- | --- | --- |
| GameItemTest | getName, getSymbol, getType | Construct each ITEMS row; query all getters | Ordinary, empty and null metadata returned unchanged |
| KeyTest | getColor; inherited metadata getters | Construct each COLORS entry with symbol K | Exact color, color + Key, K, collectible |
| DoorTest | getColor, matchesKey; inherited metadata getters | Construct each door; supply matching, different, lowercase or null key | Exact metadata; true only for matching case-sensitive color; null currently throws |
| PotionTest | getEffectAmount; inherited metadata getters | Construct with effect 25, 0, -5 and Test/P/health | Effect retained unchanged, metadata exact |
| HealthPotionTest | inherited getEffectAmount/getName/getSymbol/getType | Construct default health potion | 25, Health potion, heart symbol, health |
| SpeedPotionTest | inherited getEffectAmount/getName/getSymbol/getType | Construct default speed potion | 5, speed potion, lightning symbol, speed |
| PlayerTest | getRow, getCol | Construct POSITIONS rows, including zero and out-of-board values; move to (3,4) | Constructor retains inputs; moved row/column 3/4 |
| PlayerTest | hasKey, hasRedKey, hasBlueKey | Fresh player, then collect general/red/blue keys in stages; separate colored-only fixture | Initially false; corresponding flags become true; unrelated flags remain false |
| PlayerTest | hasPassedRedDoor, hasPassedBlueDoor | Fresh player, then pass each door | False initially; true only after matching setter |
| PlayerTest | getHealth | Start 100; damage 40; heal 10; default heal; heal 30; damage 150 | 100, 60, 70, 95, 125, -25; no current cap/clamp |
| TimerTest | getMovesRemaining, isDone | TIMERS positive/zero/negative cases; decrease from 1 twice; add 6, 0, -5 | Exact count; false above zero, true at/below zero; addition can revive timer |
| DungeonTest | getTile | Fresh board at player/wall/exit; set (1,2) to X; query neighbor; invalid row | Player/wall/exit symbols; X; neighbor unchanged; bad index currently throws |
| DungeonTest | isValidMove | Bounds on all four sides; walls, floor, trap, monster, treasure, exit; no keys/red only/blue only/both keys | Bounds/walls false; traversable tiles true; each door requires its own key |
| DungeonTest | findTile | Fresh board; red/blue/exit/first floor; absent and null target; remove red key | Exact coordinate arrays; row-major first match; null when missing |
| ComputerPlayerTest | inherited getters | Computer constructed at (1,1) | Row/column 1, health 100 |
| ComputerPlayerTest | chooseRandomMove | Repeat at start; fixture with only D open; fixture with no exit | Every sampled move S or D; forced D; no exit currently throws |
| ComputerPlayerTest | chooseMove | Difficulty 1 on forced fixture; difficulty 2 and 3 at start | Easy returns D; hard and other integer use strategy returning S |
| ComputerPlayerTest | chooseStrategicMove | Initial tie, then staged key and door flags/positions | S on initial tie; D toward each red-key/red-door/blue-key/blue-door/exit objective |
| ComputerPlayerTest | chooseStrategicMove | Enclosed player; removed red-key objective | Current behavior throws IllegalArgumentException / NullPointerException |
| ComputerPlayerTest | findPath | Adjacent right/down, forced first step, same cell, wall, locked/unlocked door, outside board, isolated target; query position afterward | D/S/forced D; null for same/unreachable targets; D through unlocked door; no position change |

## Scope and interpretation

All declared value-returning production methods are covered. Boolean methods include true and false results; searches include found and missing results; getters include varied values and state transitions. Inherited getters are also checked for item subtypes. Exceptions labeled “current” characterize existing invalid-input behavior, not a claim that crashing is desirable or robust input handling exists. Passing tests establish these documented cases only, not that the game has no defects or that hard mode always wins.

MyProgram.main, displayBoard, displayVisibleBoard and other void methods do not return values and are outside the assigned automated return-value scope. Mutators are used to arrange fixtures, not tested as return-value methods. The existing displayVisibleBoard loop issue is not executed by this suite. Full console interactions, win/loss output, and interactive menus remain interface tests.
