# TEESTFX

Welcome to TeestFX, the JavaFX port of the Modern Remaster of that game you* played on your** dad's old calculator! 
	<sub>*I     **my</sub>

## The Game

Our Hero can be male, female, or described in the second-person. That said, the Hero's past, personality, and procedures are better known by you than by me. For example: 

-"_He_" could be a light-skinned human, a dark-skinned elf, or a blue-skinned lizard. 

-"_She_" could be wearing an old cloak, shiny plate mail, or a floofy ballgown. 

-"_You_" could be a on a quest for money, revenge, or the future of your people.

All that the Hero ultimately needs to have are two working arms and the will to fight on. 

But like everything else, the Hero is defined just as much by actions as appearance. Some circumstance within the Hero's similarly nameless (and only slightly less featureless) fantasy realm will create a single-combat dual between the Hero and some foe or another. Each varies in fantasticality and familiarity, but all match or exceed the ferocity of their opponent. Initially, these enemies will be resisted by nothing sans a club. However, the Hero will gain access to new gear, martial manuevers, and magical spells (all ambiguous in their specific appearance and method of use) as enemies fall. And these boons will certainly be necessary to turn the tide, as successive foes grow stronger faster than the Hero does.


## How to Run

Most Java compilers with JavaFX should be able to run Teest without any other assests, aside from the included files for object classes. A list of all 26 classes needed for the game is presented in Appendix II, for troubleshooting or in case of accidental deletion. If one has Java 8, and all necessary files are present, the game can also be run from the system command line once compiled there. 
<sub>~~Best practice is apparently including a Java install with the game but I haven't learned that yet so...~~<sub>

The gameplay isn't too obtuse, but as a summary: the Hero and the enemy at hand exchange attacks until one is defeated. If the Hero wins, one of three upgrades can be selected. Weapon upgrades unlock special attacks and increase the power of both these and basic attacks. Shield upgrades unlock some more defensive manuevers and increase the Hero's HP as well. Magic upgrades unlock and empower spells while increasing Mana. An appendix of all learnable abilities is provided at the bottom. 

Certain enemies may have skills or spells as well. And as the Hero advances, enemies of all types will grow in power: their stats scale faster than the Hero's, including the power of their abilities. The Hero will run out of upgrades by a certain level, but can continue fighting endlessly until some foe or another obtains victory.


## Contributors

While there is no one else who directly contributed to this project (as all original objects and methods are mine), this version, as its name implies, was built for JavaFX. All credit for inherited code goes to them. This game would also not have been possible without the support and guidance of my instructors.


## Some Code I'm Proud Of

Compared to previous versions, code has been moved into objects where once it was in the console. For example, I don't need to have nested ifs on the upgrade stat buttons that check for level and stat type. Instead, Stat now has 

```
public String upLevel()	{
		
		this.setLevel(this.getLevel() + 1);
		
		return getUser().formatMessage(upgrade());
	}

abstract protected String upgrade();

```
Which in, for example, the Gear subclass is handled by:

```
protected String upgrade() {
		
		String emptyLevel = ("[u] hone[s] [pf] further with [pp] " + getImplement() + ".\n");
		//TODO: make a more dynamic formula for Action-learning levels
		if ((getLevel() % 2) == 0) {
		
			try {
				//learns a new Action
				return (((Hero)getUser()).learnFromStat(this).learnMessage());
			} catch (IllegalArgumentException ex) {
				
				return emptyLevel;
			}
		} else {
			
			if (upItem()) {
				//upgrades implement
				return ("[u] seek[s] out a new " + getName() + 
						",\n and procure[s] a " + getImplement() + ".\n");
			} else {
				
				return emptyLevel;
			}
		}
	}
```
<sub>non-TODO comments added for readability</sub>

And yes, making objects handle things like this themselves seems obvious, but making the code to do it isn't always so.


In addition, I've made Action handling more dynamic, allowing for a new variety of Action types that I unfortunately ran out of time to add. But no amount of Action varieties would matter past a certain point with the old enemy system; there was simply only so much to do against static HP pools with a name. Thus, the more important change in my opinion was the establishment of new enemy types. Currently, only Skill- and Spell-using enemies have been added, but that was again due to time constraints. I hope that even just two new types makes the game more interesting to play.
## Appendix I: Skill and Spell effects

### Weapon level 2: ***Lunge***

Deals damage scaled from the _Weapon_ stat. Has a wider damage range than a normal attack. If it kills, the enemy will be denied any chance of a mutual-kill, making it useful when both parties are close to death.


### Weapon level 4: ***Mana Burst***

Sacrifices 50% of maximum Mana to deal a strong, _Weapon_-scaled attack. Cannot be used if Mana is below that threshold.

Special: ***Ascended Mana Burst***

If the Hero lacks the Mana necessary to perform a _Mana Burst_ but also knows the _Ascend_ Spell, health can be sacrificed to perform the Skill. No confirmation is asked before _Ascend_ing a qualifying _Mana Burst_, so be careful if the Hero knows both Actions and is below 50% Mana.


### Shield level 2: ***Counter***

Deals light damage scaling only from the Hero's level. Reduces incoming damage by the same amount, possibly stunning.


### Shield level 4: ***Shield Bash***

Deals damage scaling from the _Shield_ stat. Has a coin-flip chance to outright stun the target; if this succeeds, cooldown is increased by two.


### Magic level 1: ***Drain***

Restores a small amount of health to the user scaled from the _Magic_ stat. This amount is also reduced from the enemy's potential damage, possibly stunning them.


### Magic level 2: ***Bolt***

Deals damage scaling from the _Magic_ stat. Its cheap cost makes it a good spammable attack option if the Hero has a weaker weapon.


### Magic level 3: ***Ascend***

Sacrifices a portion of current Health* to restore 50% of maximum Mana. Has no Mana cost itself.
<sub>*it's actually a level-scaled attack by, and against, the Hero</sub>

### Magic level 4: ***Revert***

Restores a large amount of health to the user scaled from the _Magic_ stat. Expensive, but efficable.


### Magic level 5: ***Astra***

Summons a random number of "stars," up to the amount that the Hero has the Mana to cast. Each "star" does _Magic_-scaling damage and costs mana individually. 




## Appendix II: Required Classes

Action
ActionButton
ActionDoer
ActionList
ActionUser
AllActions
Attacking
BasicAttack
BattlePane
Caster
ConsolePane
DamageScaler
Entity
Fighter
FormattedButton
Formatter
Gauge
Gear
HealthScale
Hero
Magic
ManaScale
Monster
MonsterFlavor
MonsterMaker
MonsterStat
NewStatButton
PaintableButton
PhantomPane
PronounSet
Scaler
Shield
Skill
SkilledMonster
SkillUser
Spell
SpellMonster
Stat
TeestFX
UpgradeButton
Weapon