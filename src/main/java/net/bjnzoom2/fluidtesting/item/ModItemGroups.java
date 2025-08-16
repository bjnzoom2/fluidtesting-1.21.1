package net.bjnzoom2.fluidtesting.item;

import net.bjnzoom2.fluidtesting.FluidTesting;
import net.bjnzoom2.fluidtesting.fluid.ModFluids;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup FLUORITE_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(FluidTesting.MOD_ID, "custom_buckets"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.custom_buckets"))
                    .icon(() -> new ItemStack(ModFluids.RED_WATER_BUCKET)).entries((displayContext, entries) -> {
                        entries.add(ModFluids.RED_WATER_BUCKET);
                    }).build());
}
