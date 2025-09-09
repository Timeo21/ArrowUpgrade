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
   - Default materials, do nothing
- Ender Pearl:
   - Teleport hit entity to shooter
### Shaft
- Stick:
   - Default material, do nothing
- Blaze Rod:
   - Ignite the arrow and make the hit entity burn until extinguished
- Breeze Rod:
   - Make the arrow fly 50% faster
### Point
- Flint:
   - Default material, do nothing
- Ender Pearl:
   - Teleport the player to the hit block or entity
- Slime Ball:
   - Arrow bounce on surfaces 3 times before stopping
- Chorus Fruit:
   - Randomly teleport the hit entity within a 10 block radius
### Effect
- None:
   - No effect
- Glowstone Dust:
   - Turn the arrow into a spectral arrow that outline the hit entity
- Potion (any type):
   - Turn the arrow into a tipped arrow that apply the potion effect on hit entity
## To-Do List
- [x] Add Breeze Rod (shaft)
- [ ] Phantom Membrane (fletching)
- [ ] Amethyst Shard (Point)
