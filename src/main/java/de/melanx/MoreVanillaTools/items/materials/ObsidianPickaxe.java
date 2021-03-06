package de.melanx.MoreVanillaTools.items.materials;

import de.melanx.MoreVanillaTools.items.base.PickaxeBase;
import de.melanx.morevanillalib.api.ToolMaterials;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ObsidianPickaxe extends PickaxeBase {

    private static final int DAMAGE = 0;
    private static final int SPEED = -3;

    public ObsidianPickaxe() {
        super(ToolMaterials.OBSIDIAN, DAMAGE, SPEED);
    }

    @Override
    public float getDestroySpeed(@Nonnull ItemStack stack, BlockState state) {
        if (state.getBlock() == Blocks.OBSIDIAN) {
            int efficiencyLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, stack);
            return 15.0F * (efficiencyLevel / 3.5F + 1);
        } else {
            return super.getDestroySpeed(stack, state);
        }
    }

}
