/*
 * This file is part of TechReborn, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2018 TechReborn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package techreborn.events;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import reborncore.RebornRegistry;
import reborncore.common.blocks.BlockMachineBase;
import reborncore.common.util.BucketHandler;
import techreborn.TechReborn;
import techreborn.blocks.*;
import techreborn.config.ConfigTechReborn;
import techreborn.entities.EntityNukePrimed;
import techreborn.init.ModSounds;
import techreborn.init.TRArmorMaterial;
import techreborn.init.TRContent;
import techreborn.init.TRContent.*;
import techreborn.init.TRTileEntities;
import techreborn.init.TRToolTier;
import techreborn.itemblocks.ItemBlockRubberSapling;
import techreborn.items.DynamicCell;
import techreborn.items.ItemFrequencyTransmitter;
import techreborn.items.ItemManual;
import techreborn.items.ItemScrapBox;
import techreborn.items.armor.ItemCloakingDevice;
import techreborn.items.armor.ItemLapotronicOrbpack;
import techreborn.items.armor.ItemLithiumIonBatpack;
import techreborn.items.armor.ItemTRArmour;
import techreborn.items.battery.*;
import techreborn.items.tool.ItemDebugTool;
import techreborn.items.tool.ItemTreeTap;
import techreborn.items.tool.ItemWrench;
import techreborn.items.tool.advanced.ItemAdvancedChainsaw;
import techreborn.items.tool.advanced.ItemAdvancedDrill;
import techreborn.items.tool.advanced.ItemAdvancedJackhammer;
import techreborn.items.tool.advanced.ItemRockCutter;
import techreborn.items.tool.basic.ItemBasicChainsaw;
import techreborn.items.tool.basic.ItemBasicDrill;
import techreborn.items.tool.basic.ItemBasicJackhammer;
import techreborn.items.tool.basic.ItemElectricTreetap;
import techreborn.items.tool.industrial.*;
import techreborn.items.tool.vanilla.ItemTRAxe;
import techreborn.items.tool.vanilla.ItemTRHoe;
import techreborn.items.tool.vanilla.ItemTRPickaxe;
import techreborn.items.tool.vanilla.ItemTRSpade;
import techreborn.items.tool.vanilla.ItemTRSword;
import techreborn.utils.InitUtils;

import java.util.Arrays;

/**
 * @author drcrazy
 *
 */

@Mod.EventBusSubscriber(modid = TechReborn.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEventHandler {

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		Arrays.stream(Ores.values()).forEach(value -> RebornRegistry.registerBlock(value.block, new Item.Properties().group(TechReborn.ITEMGROUP)));
		Arrays.stream(StorageBlocks.values()).forEach(value -> RebornRegistry.registerBlock(value.block, new Item.Properties().group(TechReborn.ITEMGROUP)));
		Arrays.stream(MachineBlocks.values()).forEach(value -> {
			RebornRegistry.registerBlock(value.frame, new Item.Properties().group(TechReborn.ITEMGROUP));
			RebornRegistry.registerBlock(value.casing, new Item.Properties().group(TechReborn.ITEMGROUP));
		});
		Arrays.stream(SolarPanels.values()).forEach(value -> RebornRegistry.registerBlock(value.block, new Item.Properties().group(TechReborn.ITEMGROUP)));
		Arrays.stream(Cables.values()).forEach(value -> RebornRegistry.registerBlock(value.block, new Item.Properties().group(TechReborn.ITEMGROUP)));
		Arrays.stream(Machine.values()).forEach(value -> RebornRegistry.registerBlock(value.block, new Item.Properties().group(TechReborn.ITEMGROUP)));

		// Misc. blocks
		RebornRegistry.registerBlock(TRContent.COMPUTER_CUBE = InitUtils.setup(new BlockComputerCube(), "computer_cube"), new Item.Properties().group(TechReborn.ITEMGROUP));
		RebornRegistry.registerBlock(TRContent.NUKE = InitUtils.setup(new BlockNuke(), "nuke"), new Item.Properties().group(TechReborn.ITEMGROUP));
		RebornRegistry.registerBlock(TRContent.REFINED_IRON_FENCE = InitUtils.setup(new BlockRefinedIronFence(), "refined_iron_fence"), new Item.Properties().group(TechReborn.ITEMGROUP));
		RebornRegistry.registerBlock(TRContent.REINFORCED_GLASS = InitUtils.setup(new BlockReinforcedGlass(), "reinforced_glass"), new Item.Properties().group(TechReborn.ITEMGROUP));
		RebornRegistry.registerBlock(TRContent.RUBBER_LEAVES = InitUtils.setup(new BlockRubberLeaves(), "rubber_leaves"), new Item.Properties().group(TechReborn.ITEMGROUP));
		RebornRegistry.registerBlock(TRContent.RUBBER_LOG = InitUtils.setup(new BlockRubberLog(), "rubber_log"), new Item.Properties().group(TechReborn.ITEMGROUP));
		RebornRegistry.registerBlock(TRContent.RUBBER_PLANKS = InitUtils.setup(new BlockRubberPlank(), "rubber_planks"), new Item.Properties().group(TechReborn.ITEMGROUP));
		RebornRegistry.registerBlock(TRContent.RUBBER_SAPLING = InitUtils.setup(new BlockRubberSapling(), "rubber_sapling"),
				ItemBlockRubberSapling.class, 
				"rubber_sapling");
		RebornRegistry.registerBlock(TRContent.RUBBER_PLANK_SLAB = InitUtils.setup(new BlockRubberPlankSlab(), "rubber_plank_slab"), new Item.Properties().group(TechReborn.ITEMGROUP));
		RebornRegistry.registerBlock(TRContent.RUBBER_PLANK_STAIR = InitUtils.setup(new BlockRubberPlankStair(), "rubber_plank_stair"), new Item.Properties().group(TechReborn.ITEMGROUP));

		TechReborn.LOGGER.debug("TechReborns Blocks Loaded");
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		Arrays.stream(Ingots.values()).forEach(value -> RebornRegistry.registerItem(value.item));
		Arrays.stream(Nuggets.values()).forEach(value -> RebornRegistry.registerItem(value.item));
		Arrays.stream(Gems.values()).forEach(value -> RebornRegistry.registerItem(value.item));
		Arrays.stream(Dusts.values()).forEach(value -> RebornRegistry.registerItem(value.item));
		Arrays.stream(SmallDusts.values()).forEach(value -> RebornRegistry.registerItem(value.item));
		Arrays.stream(Plates.values()).forEach(value -> RebornRegistry.registerItem(value.item));
		Arrays.stream(Parts.values()).forEach(value -> RebornRegistry.registerItem(value.item));
		Arrays.stream(Upgrades.values()).forEach(value -> RebornRegistry.registerItem(value.item));
		
		// Gem armor & tools
		if (ConfigTechReborn.enableGemArmorAndTools) {
			//Todo: repair with tags
			RebornRegistry.registerItem(TRContent.BRONZE_SWORD = InitUtils.setup(new ItemTRSword(TRToolTier.BRONZE, "ingotBronze"), "bronze_sword"));
			RebornRegistry.registerItem(TRContent.BRONZE_PICKAXE = InitUtils.setup(new ItemTRPickaxe(TRToolTier.BRONZE, "ingotBronze"), "bronze_pickaxe"));
			RebornRegistry.registerItem(TRContent.BRONZE_SPADE = InitUtils.setup(new ItemTRSpade(TRToolTier.BRONZE, "ingotBronze"), "bronze_spade"));
			RebornRegistry.registerItem(TRContent.BRONZE_AXE = InitUtils.setup(new ItemTRAxe(TRToolTier.BRONZE, "ingotBronze"), "bronze_axe"));
			RebornRegistry.registerItem(TRContent.BRONZE_HOE = InitUtils.setup(new ItemTRHoe(TRToolTier.BRONZE, "ingotBronze"), "bronze_hoe"));

			RebornRegistry.registerItem(TRContent.BRONZE_HELMET = InitUtils.setup(new ItemTRArmour(TRArmorMaterial.BRONZE, EntityEquipmentSlot.HEAD, "ingotBronze"), "bronze_helmet"));
			RebornRegistry.registerItem(TRContent.BRONZE_CHESTPLATE = InitUtils.setup(new ItemTRArmour(TRArmorMaterial.BRONZE, EntityEquipmentSlot.CHEST, "ingotBronze"), "bronze_chestplate"));
			RebornRegistry.registerItem(TRContent.BRONZE_LEGGINGS = InitUtils.setup(new ItemTRArmour(TRArmorMaterial.BRONZE, EntityEquipmentSlot.LEGS, "ingotBronze"), "bronze_leggings"));
			RebornRegistry.registerItem(TRContent.BRONZE_BOOTS = InitUtils.setup(new ItemTRArmour(TRArmorMaterial.BRONZE, EntityEquipmentSlot.FEET, "ingotBronze"), "bronze_boots"));

			RebornRegistry.registerItem(TRContent.RUBY_SWORD = InitUtils.setup(new ItemTRSword(TRToolTier.RUBY, "gemRuby"), "ruby_sword"));
			RebornRegistry.registerItem(TRContent.RUBY_PICKAXE = InitUtils.setup(new ItemTRPickaxe(TRToolTier.RUBY, "gemRuby"), "ruby_pickaxe"));
			RebornRegistry.registerItem(TRContent.RUBY_SPADE = InitUtils.setup(new ItemTRSpade(TRToolTier.RUBY, "gemRuby"), "ruby_spade"));
			RebornRegistry.registerItem(TRContent.RUBY_AXE = InitUtils.setup(new ItemTRAxe(TRToolTier.RUBY, "gemRuby"), "ruby_axe"));
			RebornRegistry.registerItem(TRContent.RUBY_HOE = InitUtils.setup(new ItemTRHoe(TRToolTier.RUBY, "gemRuby"), "ruby_hoe"));

			RebornRegistry.registerItem(TRContent.RUBY_HELMET = InitUtils.setup(new ItemTRArmour(TRArmorMaterial.RUBY, EntityEquipmentSlot.HEAD, "gemRuby"), "ruby_helmet"));
			RebornRegistry.registerItem(TRContent.RUBY_CHESTPLATE = InitUtils.setup(new ItemTRArmour(TRArmorMaterial.RUBY, EntityEquipmentSlot.CHEST, "gemRuby"), "ruby_chestplate"));
			RebornRegistry.registerItem(TRContent.RUBY_LEGGINGS = InitUtils.setup(new ItemTRArmour(TRArmorMaterial.RUBY, EntityEquipmentSlot.LEGS, "gemRuby"), "ruby_leggings"));
			RebornRegistry.registerItem(TRContent.RUBY_BOOTS = InitUtils.setup(new ItemTRArmour(TRArmorMaterial.RUBY, EntityEquipmentSlot.FEET, "gemRuby"), "ruby_boots"));

			RebornRegistry.registerItem(TRContent.SAPPHIRE_SWORD = InitUtils.setup(new ItemTRSword(TRToolTier.SAPPHIRE, "gemSapphire"), "sapphire_sword"));
			RebornRegistry.registerItem(TRContent.SAPPHIRE_PICKAXE = InitUtils.setup(new ItemTRPickaxe(TRToolTier.SAPPHIRE, "gemSapphire"), "sapphire_pickaxe"));
			RebornRegistry.registerItem(TRContent.SAPPHIRE_SPADE = InitUtils.setup(new ItemTRSpade(TRToolTier.SAPPHIRE, "gemSapphire"), "sapphire_spade"));
			RebornRegistry.registerItem(TRContent.SAPPHIRE_AXE = InitUtils.setup(new ItemTRAxe(TRToolTier.SAPPHIRE, "gemSapphire"), "sapphire_axe"));
			RebornRegistry.registerItem(TRContent.SAPPHIRE_HOE = InitUtils.setup(new ItemTRHoe(TRToolTier.SAPPHIRE, "gemSapphire"), "sapphire_hoe"));

			RebornRegistry.registerItem(TRContent.SAPPHIRE_HELMET = InitUtils.setup(new ItemTRArmour(TRArmorMaterial.SAPPHIRE, EntityEquipmentSlot.HEAD, "gemSapphire"), "sapphire_helmet"));
			RebornRegistry.registerItem(TRContent.SAPPHIRE_CHESTPLATE = InitUtils.setup(new ItemTRArmour(TRArmorMaterial.SAPPHIRE, EntityEquipmentSlot.CHEST, "gemSapphire"), "sapphire_chestplate"));
			RebornRegistry.registerItem(TRContent.SAPPHIRE_LEGGINGS = InitUtils.setup(new ItemTRArmour(TRArmorMaterial.SAPPHIRE, EntityEquipmentSlot.LEGS, "gemSapphire"), "sapphire_leggings"));
			RebornRegistry.registerItem(TRContent.SAPPHIRE_BOOTS = InitUtils.setup(new ItemTRArmour(TRArmorMaterial.SAPPHIRE, EntityEquipmentSlot.FEET, "gemSapphire"), "sapphire_boots"));

			RebornRegistry.registerItem(TRContent.PERIDOT_SWORD = InitUtils.setup(new ItemTRSword(TRToolTier.PERIDOT, "gemPeridot"), "peridot_sword"));
			RebornRegistry.registerItem(TRContent.PERIDOT_PICKAXE = InitUtils.setup(new ItemTRPickaxe(TRToolTier.PERIDOT, "gemPeridot"), "peridot_pickaxe"));
			RebornRegistry.registerItem(TRContent.PERIDOT_SPADE = InitUtils.setup(new ItemTRSpade(TRToolTier.PERIDOT, "gemPeridot"), "peridot_spade"));
			RebornRegistry.registerItem(TRContent.PERIDOT_AXE = InitUtils.setup(new ItemTRAxe(TRToolTier.PERIDOT, "gemPeridot"), "peridot_axe"));
			RebornRegistry.registerItem(TRContent.PERIDOT_HOE = InitUtils.setup(new ItemTRHoe(TRToolTier.PERIDOT, "gemPeridot"), "peridot_hoe"));

			RebornRegistry.registerItem(TRContent.PERIDOT_HELMET = InitUtils.setup(new ItemTRArmour(TRArmorMaterial.PERIDOT, EntityEquipmentSlot.HEAD, "gemPeridot"), "peridot_helmet"));
			RebornRegistry.registerItem(TRContent.PERIDOT_CHESTPLATE = InitUtils.setup(new ItemTRArmour(TRArmorMaterial.PERIDOT, EntityEquipmentSlot.CHEST, "gemPeridot"), "peridot_chestplate"));
			RebornRegistry.registerItem(TRContent.PERIDOT_LEGGINGS = InitUtils.setup(new ItemTRArmour(TRArmorMaterial.PERIDOT, EntityEquipmentSlot.LEGS, "gemPeridot"), "peridot_leggings"));
			RebornRegistry.registerItem(TRContent.PERIDOT_BOOTS = InitUtils.setup(new ItemTRArmour(TRArmorMaterial.PERIDOT, EntityEquipmentSlot.FEET, "gemPeridot"), "peridot_boots"));
		}

		// Battery
		RebornRegistry.registerItem(TRContent.RED_CELL_BATTERY = InitUtils.setup(new ItemRedCellBattery(), "red_cell_battery"));
		RebornRegistry.registerItem(TRContent.LITHIUM_ION_BATTERY = InitUtils.setup(new ItemLithiumIonBattery(), "lithium_ion_battery"));
		RebornRegistry.registerItem(TRContent.LITHIUM_ION_BATPACK = InitUtils.setup(new ItemLithiumIonBatpack(), "lithium_ion_batpack"));
		RebornRegistry.registerItem(TRContent.ENERGY_CRYSTAL = InitUtils.setup(new ItemEnergyCrystal(), "energy_crystal"));
		RebornRegistry.registerItem(TRContent.LAPOTRON_CRYSTAL = InitUtils.setup(new ItemLapotronCrystal(), "lapotron_crystal"));
		RebornRegistry.registerItem(TRContent.LAPOTRONIC_ORB = InitUtils.setup(new ItemLapotronicOrb(), "lapotronic_orb"));
		RebornRegistry.registerItem(TRContent.LAPOTRONIC_ORBPACK = InitUtils.setup(new ItemLapotronicOrbpack(), "lapotronic_orbpack"));

		// Tools
		RebornRegistry.registerItem(TRContent.TREE_TAP = InitUtils.setup(new ItemTreeTap(), "treetap"));
		RebornRegistry.registerItem(TRContent.WRENCH = InitUtils.setup(new ItemWrench(), "wrench"));

		RebornRegistry.registerItem(TRContent.BASIC_DRILL = InitUtils.setup(new ItemBasicDrill(), "basic_drill"));
		RebornRegistry.registerItem(TRContent.BASIC_CHAINSAW = InitUtils.setup(new ItemBasicChainsaw(), "basic_chainsaw"));
		RebornRegistry.registerItem(TRContent.BASIC_JACKHAMMER = InitUtils.setup(new ItemBasicJackhammer(), "basic_jackhammer"));
		RebornRegistry.registerItem(TRContent.ELECTRIC_TREE_TAP = InitUtils.setup(new ItemElectricTreetap(), "electric_treetap"));

		RebornRegistry.registerItem(TRContent.ADVANCED_DRILL = InitUtils.setup(new ItemAdvancedDrill(), "advanced_drill"));
		RebornRegistry.registerItem(TRContent.ADVANCED_CHAINSAW = InitUtils.setup(new ItemAdvancedChainsaw(), "advanced_chainsaw"));
		RebornRegistry.registerItem(TRContent.ADVANCED_JACKHAMMER = InitUtils.setup(new ItemAdvancedJackhammer(), "advanced_jackhammer"));
		RebornRegistry.registerItem(TRContent.ROCK_CUTTER = InitUtils.setup(new ItemRockCutter(), "rock_cutter"));

		RebornRegistry.registerItem(TRContent.INDUSTRIAL_DRILL = InitUtils.setup(new ItemIndustrialDrill(), "industrial_drill"));
		RebornRegistry.registerItem(TRContent.INDUSTRIAL_CHAINSAW = InitUtils.setup(new ItemIndustrialChainsaw(), "industrial_chainsaw"));
		RebornRegistry.registerItem(TRContent.INDUSTRIAL_JACKHAMMER = InitUtils.setup(new ItemIndustrialJackhammer(), "industrial_jackhammer"));
		RebornRegistry.registerItem(TRContent.NANOSABER = InitUtils.setup(new ItemNanosaber(), "nanosaber"));
		RebornRegistry.registerItem(TRContent.OMNI_TOOL = InitUtils.setup(new ItemOmniTool(), "omni_tool"));

		// Armor
		RebornRegistry.registerItem(TRContent.CLOAKING_DEVICE = InitUtils.setup(new ItemCloakingDevice(), "cloaking_device"));

		// Other
		RebornRegistry.registerItem(TRContent.FREQUENCY_TRANSMITTER = InitUtils.setup(new ItemFrequencyTransmitter(), "frequency_transmitter"));
		RebornRegistry.registerItem(TRContent.SCRAP_BOX = InitUtils.setup(new ItemScrapBox(), "scrap_box"));
		RebornRegistry.registerItem(TRContent.MANUAL = InitUtils.setup(new ItemManual(), "manual"));
		RebornRegistry.registerItem(TRContent.DEBUG_TOOL = InitUtils.setup(new ItemDebugTool(), "debug_tool"));
		RebornRegistry.registerItem(TRContent.CELL = InitUtils.setup(new DynamicCell(), "cell"));
		MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);

		// TODO: do we need this at all?
		BlockMachineBase.advancedFrameStack = new ItemStack(TRContent.MachineBlocks.ADVANCED.getFrame());
		BlockMachineBase.basicFrameStack = new ItemStack(TRContent.MachineBlocks.BASIC.getFrame());
		
		TechReborn.LOGGER.debug("TechReborns Items Loaded");
	}

	@SubscribeEvent
	public static void registerTiles(RegistryEvent.Register<TileEntityType<?>> event) {
		TRTileEntities.TYPES.forEach(tileEntityType -> event.getRegistry().register(tileEntityType));
	}
	

	@SubscribeEvent
	public static void registerEntityTypes(RegistryEvent.Register<EntityType<?>> event) {
		TRContent.ENTITY_NUKE = EntityType.Builder.create(EntityNukePrimed.class, EntityNukePrimed::new).build("nuke");
		TRContent.ENTITY_NUKE.setRegistryName(new ResourceLocation(TechReborn.MOD_ID, "nuke"));
		event.getRegistry().register(TRContent.ENTITY_NUKE);
		
	}
	
	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		ModSounds.CABLE_SHOCK = ModSounds.getSound("cable_shock");
		ModSounds.BLOCK_DISMANTLE = ModSounds.getSound("block_dismantle");
		ModSounds.SAP_EXTRACT = ModSounds.getSound("sap_extract");
		ModSounds.AUTO_CRAFTING = ModSounds.getSound("auto_crafting");
		ModSounds.MACHINE_RUN = ModSounds.getSound("machine_run");
		ModSounds.MACHINE_START = ModSounds.getSound("machine_start");
		ModSounds.ALARM = ModSounds.getSound("alarm");
		ModSounds.ALARM_2 = ModSounds.getSound("alarm_2");
		ModSounds.ALARM_3 = ModSounds.getSound("alarm_3");
		
	}
	
//	@SubscribeEvent(priority = EventPriority.LOW)//LOW is used as we want it to load as late as possible, but before crafttweaker
//	public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
//		//Register ModRecipes
//		ModRecipes.init();
//	}

}