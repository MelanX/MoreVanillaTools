package de.melanx.MoreVanillaTools.items;

import de.melanx.MoreVanillaTools.compat.LibCompat;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class AxeBase extends AxeItem implements BaseTool {

    private final ToolMaterials tier;

    public AxeBase(ToolMaterials tier, float attackDamageModifier, float attackSpeedModifier, Properties properties) {
        super(tier, attackDamageModifier, attackSpeedModifier, properties);
        this.tier = tier;
    }

    @Nonnull
    @Override
    public ToolMaterials getTier() {
        return this.tier;
    }

    @Nonnull
    @Override
    public InteractionResult useOn(@Nonnull UseOnContext context) {
        InteractionResult result = super.useOn(context);

        if (LibCompat.isMoreVanillaLibLoaded()) {
            LibCompat.onUseOn(this, context);
        }

        return result;
    }

    @Override
    public boolean mineBlock(@Nonnull ItemStack stack, @Nonnull Level level, @Nonnull BlockState state, @Nonnull BlockPos pos, @Nonnull LivingEntity entityLiving) {
        boolean result = super.mineBlock(stack, level, state, pos, entityLiving);

        if (LibCompat.isMoreVanillaLibLoaded()) {
            LibCompat.onMineBlock(this, stack, level, state, pos, entityLiving);
        }

        return result;
    }

    @Override
    public boolean hurtEnemy(@Nonnull ItemStack stack, @Nonnull LivingEntity target, @Nonnull LivingEntity attacker) {
        boolean result = super.hurtEnemy(stack, target, attacker);

        if (LibCompat.isMoreVanillaLibLoaded()) {
            LibCompat.onHurtEnemy(this, stack, target, attacker);
        }

        return result;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (enchantment == Enchantments.KNOCKBACK && this.tier == ToolMaterials.SLIME) {
            return false;
        }

        return super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable Level level, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag isAdvanced) {
        if (LibCompat.isMoreVanillaLibLoaded()) {
            LibCompat.editHoverText(this, stack, level, tooltip, isAdvanced);
        }

        super.appendHoverText(stack, level, tooltip, isAdvanced);
    }
}
