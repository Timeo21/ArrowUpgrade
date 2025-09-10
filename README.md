# ArrowUpgrade - Arrow Customization System for Paper
ArrowUpgrade is a plugin that allow players to use fletching table to create customs arrows. Arrows are made of three or four parts which are made of different materials to provide many effects on fly, hit or landing.
## Fletching Table
Obtaining the fletching table is unchanged. Right-clicking the table will open an inventory with four slots interactable slots, fours information slots and a result slot. Add materials in valid slot to create customs arrows.

![fletching ui](/Images/fletching_ui.png)
## Arrow Craft
Arrows are made of three to four parts:
 - Fletching
 - Shaft
 - Point
 - Effect (optional)
Each parts can be made of various materials to provide many effects.

### Fletching
- Feather:
   - Default materials, does nothing
- Ender Pearl:
   - Teleports hit entity to shooter
### Shaft
- Stick:
   - Default material, does nothing
- Blaze Rod:
   - Ignites the arrow and makes the hit entity burn until extinguished
- Breeze Rod:
   - Makes the arrow fly 50% faster
### Point
- Flint:
   - Default material, does nothing
- Ender Pearl:
   - Teleports the player to the hit block or entity
- Slime Ball:
   - Arrow bounces on surfaces 3 times before stopping
- Chorus Fruit:
   - Randomly teleports the hit entity within a 10 block radius
### Effect
- None:
   - No effect
- Glowstone Dust:
   - Turns the arrow into a spectral arrow that outline the hit entity
- Potion (any type):
   - Turns the arrow into a tipped arrow that apply the potion effect on hit entity
## To-Do List
- [x] Add Breeze Rod (shaft)
- [ ] Remove debug messages
- [ ] Phantom Membrane (fletching)
- [ ] Amethyst Shard (Point)
