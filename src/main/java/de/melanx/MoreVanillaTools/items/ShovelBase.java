package de.melanx.MoreVanillaTools.items;

import de.melanx.MoreVanillaTools.compat.LibCompat;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ShovelBase extends ShovelItem implements BaseTool {

    private final ToolMaterials tier;

    public ShovelBase(ToolMaterials tier, float attackDamageModifier, float attackSpeedModifier, Properties properties) {
        super(tier, attackDamageModifier, attackSpeedModifier, properties);
        this.tier = tier;
    }

    @Nonnull
    @Override
    public ToolMaterials getTier() {
        return this.tier;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (enchantment == Enchantments.KNOCKBACK && this.tier == ToolMaterials.SLIME) {
            return false;
        }

        return super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public int getEnchantmentLevel(ItemStack stack, Enchantment enchantment) {
        if (enchantment == Enchantments.KNOCKBACK && stack.getItem() instanceof ShovelBase item && item.tier == ToolMaterials.SLIME) {
            return 3;
        }

        return super.getEnchantmentLevel(stack, enchantment);
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable Level level, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag isAdvanced) {
        if (LibCompat.isMoreVanillaLibLoaded()) {
            LibCompat.editHoverText(this, stack, level, tooltip, isAdvanced);
        }

        super.appendHoverText(stack, level, tooltip, isAdvanced);
    }
}
