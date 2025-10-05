# == Frontiers a2025.1.0: Just the Facts ==
A small update for the Halloween season, with some special Halloween content...obtainable year-round, of course!

_Special thanks to @jesterrcore for helping with this update - this was his idea! :D_

--------------------------
## Additions
### Blighted Birch
- A new wood set only obtainable from saplings found in Witch Huts
- Sports a unique, pale purple hue!
- The logs have unique properties; logs that haven't been cut yet will change color based on the time of day!
- Similar to Oak Trees, sometimes the leaves will drop a unique fruit...
### Pomegranates
- Drop from Blighted Birch Leaves
- Restores 1 hunger and 1.5 saturation
- Has a unique gimmick; when eaten, it will have a 75% chance of remaining in your inventory!
### Witch Hat
- Dropped by Witches with a 2.5% chance
- Has 380 durability
- Doesn't provide any armor points, but has a few special abilities when worn:
  - Potion drinking time is significantly reduced (25% the usual time)
  - The wearer will have the same damage resistances that a Witch normally does
  - Either of these effects will reduce the hat's durability by 3 each time
- Just like a Witch, the wearer will randomly produce little spell particles over their head!
### Jungle Spider
- Spawns in the [read the name]
- Exclusive to Hardmode
- Smaller than both Spiders & Cave Spiders, has more health & damage, and is faster!
  - To make it worse...it doesn't have glowing red eyes. Have fun. :)
- Getting bit on Normal difficulty and above inflicts the target with Weakness I
- Targets Chickens & untamed Parrots, because it's a Jungle Spider. It's gotta eat somehow.
- Will try and spin a Cobweb in an open space every ~3 minutes. You could make a farm out of this...
### Enchanting Magnet
- Crafted from 6 Glass, 1 Emerald Block, 1 Nether Star, & 1 Bottle o' Enchanting
- Acts as a unique method of storing Experience Points:
  - Rather than being able to deposit your currently held Experience, Enchanting Magnets will instead attract nearby Experience Orbs
    - Experience Orbs will actually prioritize Enchanting Magnets over players, making them great for mob farms
  - Once the magnet is holding Experience, a little spinning orb will appear in the center
    - The larger the orb, the more Experience is inside. The orb stops growing at 1200 points, and the magnet stops picking up Experience at 32767 points
    - A comparator can be used to determine the stored amount: the comparator will output 15 once the magnet contains 1200 or more exp points
  - There are two ways to extract stored Experience from the magnet:
    - Use a glass bottle on it to get a single Bottle o' Enchanting - so long as there's at least 12 points inside. It'll cost you 12 points to withdraw a single bottle.
    - If you're greedy and want it quick, just break the magnet without Silk Touch. It'll always drop itself without needing a tool, but if you break it without Silk Touch it'll drop exp...**but at the price of only giving you 50% of the stored value inside!**
  - The magnet can be broken with any tool enchanted with Silk Touch to store the Experience inside, allowing you to bring it with you.
### Item Vacuum
- Crafted from 4 Iron Bars, 1 Hopper, & 1 Spawner Chunk
### Mana Orb
- Added basic implementation. Currently obtainable via the Creative-exclusive **Bottle o' Magicks**.
- Touches the player, makes a unique twinkly noise, then disappears. That's all for now. :P
- This is the basis for the soon-to-come magic system that Frontiers will add...
### Misc
- Spawn Eggs
  - Added spawn eggs for the Crawler and Jungle Spider 
- Splash Text
  - Playing on Halloween will now select from a list of unique splashes, not just "OOoooOOOoooo! Spooky!"
  - Added some new splashes to the base splash list
  - Added birthday splash texts for some important Frontiers team members

--------------------------
## Changes
### Quicksand
- Both variants can now be smelted into Glass in a Furnace
- Immune entities are now tag-driven instead of hardcoded
  - This allows for other mods to add their own immune entities
- With the above change, several new entities are no longer affected by Quicksand (mostly mechanical entities):
  - Jungle Spiders
  - Item Frames
  - Glow Item Frames
  - Paintings
  - Fireballs
  - Small Fireballs
  - Dragon Fireballs
  - Item Displays
  - Interactions
  - Markers
  - End Crystals
  - Area Affect Clouds
  - Falling Blocks
  - Leash Knots
  - Lightning Bolts
  - Crags Stalkers/Monsters

--------------------------
## Vanilla Changes
### Witch Hut
- Now have a varying chance of having the red mushroom in their flower pots be replaced with a Blighted Birch sapling, being the only way to obtain it.
  - On Halloween, this chance is guaranteed
  - Otherwise, it has a 1/5 chance of replacing it
### Witch
- Now has a 2.5% chance to drop its hat
### Misc
- Loading Screen
  - Will now be colored orange instead of red on Halloween (unless monochrome logo is enabled)