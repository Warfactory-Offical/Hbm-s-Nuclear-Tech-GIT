package api.hbm.item.base;

import api.hbm.item.component.IItemBehavior;
import api.hbm.item.component.IItemComponent;
import api.hbm.item.component.behavior.IItemNameProvider;
import api.hbm.item.component.behavior.IItemSubHandler;
import api.hbm.item.component.behavior.IMultipleModelBehavior;
import api.hbm.util.lang.LocaleUtils;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import com.hbm.main.MainRegistry;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import it.unimi.dsi.fastutil.shorts.Short2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.shorts.Short2ObjectMap;
import it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public abstract class HBMItemBase<T extends HBMItemBase<?>.HBMItem> extends Item {

    private static final List<HBMItemBase<?>> ITEMS = new ArrayList<>();

    public static List<HBMItemBase<?>> getItems() {
        return Collections.unmodifiableList(ITEMS);
    }

    private final Map<String, T> names = new Object2ObjectOpenHashMap<>();
    protected final Short2ObjectMap<T> items = new Short2ObjectLinkedOpenHashMap<>();
    protected final Short2ObjectMap<ModelResourceLocation> itemModels = new Short2ObjectOpenHashMap<>();
    protected final Short2ObjectMap<ModelResourceLocation[]> specialItemsModels = new Short2ObjectOpenHashMap<>();
    protected static final ModelResourceLocation MISSING_LOCATION = new ModelResourceLocation("builtin/missing",
            "inventory");

    private final CreativeTabs[] defaultCreativeTabs = new CreativeTabs[] {MainRegistry.resourceTab};
    private final Set<CreativeTabs> additionalCreativeTabs = new ObjectArraySet<>();

    private final String translationKey = "hbmitem";
    protected final short idOffset;

    public HBMItemBase(short offset) {
        setHasSubtypes(true);
        this.idOffset = offset;
    }
    // ========================================================
    // RENDERING
    // =======================================================

    @SideOnly(Side.CLIENT)
    public void registerColor() {
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(this::getColorForItemStack, this);
    }

    @SideOnly(Side.CLIENT)
    protected int getColorForItemStack(ItemStack stack, int tintIndex) {
        return 0xFFFFFF;
    }

    @SideOnly(Side.CLIENT)
    public void registerModels() {
        for (Short itemKey : items.keySet()) {
            T item = items.get(itemKey);
            int numberOfModels = item.getModelAmounts();
            if (numberOfModels > 1) {
                ModelResourceLocation[] resourceLocations = new ModelResourceLocation[numberOfModels];
                for (int i = 0; i < resourceLocations.length; i++) {
                    ResourceLocation location = createItemModelLocation(item, "/" + (i + 1));
                    ModelBakery.registerItemVariants(this, location);
                    resourceLocations[i] = new ModelResourceLocation(location, "inventory");
                }
                specialItemsModels.put((short) (idOffset + itemKey), resourceLocations);
                continue;
            }
            ResourceLocation location = createItemModelLocation(item, "");
            if (numberOfModels > 0) {
                ModelBakery.registerItemVariants(this, location);
            }
            itemModels.put((short) (idOffset + itemKey), new ModelResourceLocation(location, "inventory"));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerTextureMesh() {
        ModelLoader.setCustomMeshDefinition(this, itemStack -> {
            short itemDamage = (short) itemStack.getItemDamage();
            if (specialItemsModels.containsValue(itemDamage)) {
                int modelIndex = getModelIndex(itemStack);
                return specialItemsModels.get(itemDamage)[modelIndex];
            }
            if (itemModels.containsKey(itemDamage)) {
                return itemModels.get(itemDamage);
            }
            return MISSING_LOCATION;
        });
    }


    // ========================================================
    // GETTERS
    // ========================================================

    public final T getItem(short metaValue) {
        return items.get(metaValue);
    }

    public final T getItem(String name) {
        return names.get(name);
    }

    public final T getItem(ItemStack stack) {
        return getItem((short) (stack.getItemDamage() - idOffset));
    }

    protected ResourceLocation createItemModelLocation(T item, String post) {
        return new ResourceLocation("hbm", modelPath(item) + post);
    }
    protected String modelPath(T item) {
        return "itembase/" + item.unlocalizedName;
    }

    public List<IItemBehavior> getBehaviors(ItemStack itemStack) {
        T item = getItem(itemStack);
        if (item == null) return ImmutableList.of();
        return item.getBehaviors();
    }


    protected int getModelIndex(ItemStack itemStack) {
        T item = getItem(itemStack);

        // Model behavior
        IMultipleModelBehavior modelBehavior = item.getModelBehavior();
        if (modelBehavior != null) {
            return modelBehavior.getModelIndex(itemStack);
        }

        return 0;
    }

    @Override
    public String getTranslationKey() {
        return getTranslationKey((T) null);
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return getTranslationKey(getItem(stack));
    }

    public String getTranslationKey(@Nullable T item) {
        return item == null ? this.translationKey : this.translationKey + "." + item.unlocalizedName;
    }

    // ========================================================
    // ITEM BEHAVIOR
    // ========================================================

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        T item = getItem(itemStack);
        if (item == null) return super.getItemBurnTime(itemStack);
        return item.getBurnValue();
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        boolean returnValue = false;
        for (IItemBehavior behavior : getBehaviors(stack)) {
            if (behavior.onLeftClickEntity(stack, player, entity)) {
                returnValue = true;
            }
        }
        return returnValue;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        boolean returnValue = false;
        for (IItemBehavior behavior : getBehaviors(stack)) {
            if (behavior.itemInteractionForEntity(stack, playerIn, target, hand)) {
                returnValue = true;
            }
        }
        return returnValue;
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        for (IItemBehavior behavior : getBehaviors(stack)) {
            EnumActionResult result = behavior.onItemUseFirst(player, world, pos, hand, side, hitX, hitY, hitZ);
            if (result != EnumActionResult.PASS) {
                return result;
            } else if (items.isEmpty()) {
                return EnumActionResult.PASS;
            }
        }
        return EnumActionResult.PASS;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
        ItemStack originalStack = stack.copy();
        for (IItemBehavior behavior : getBehaviors(stack)) {
            ActionResult<ItemStack> result = behavior.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
            stack = result.getResult();
            if (result.getType() != EnumActionResult.PASS) {
                if (!ItemStack.areItemStacksEqual(originalStack, stack))
                    player.setHeldItem(hand, stack);
                return result.getType();
            } else if (stack.isEmpty()) {
                player.setHeldItem(hand, ItemStack.EMPTY);
                return EnumActionResult.PASS;
            }
        }
        return EnumActionResult.PASS;
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        HashMultimap<String, AttributeModifier> modifiers = HashMultimap.create();
        T item = getItem(stack);
        if (item != null) {
            for (IItemBehavior behavior : getBehaviors(stack)) {
                modifiers.putAll(behavior.getAttributeModifiers(slot, stack));
            }
        }
        return modifiers;
    }

    @Override
    public @NotNull ActionResult<ItemStack> onItemRightClick(@NotNull World world, @NotNull EntityPlayer player, EnumHand hand) {
        ItemStack itemStack = player.getHeldItem(hand);
        for (IItemBehavior behavior : getBehaviors(itemStack)) {
            ActionResult<ItemStack> result = behavior.onItemRightClick(world, player, hand);
            itemStack = result.getResult();
            if (result.getType() != EnumActionResult.PASS) {
                return ActionResult.newResult(result.getType(), itemStack);
            } else if (itemStack.isEmpty()) {
                return ActionResult.newResult(EnumActionResult.PASS, ItemStack.EMPTY);
            }
        }

        return ActionResult.newResult(EnumActionResult.PASS, itemStack);
    }

    @NotNull
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        if (stack.getItemDamage() >= idOffset) {
            T item = getItem(stack);
            if (item == null) return "invalid item";
            String unlocalizedName = getTranslationKey(item) + ".name";
            if (item.getNameProvider() != null) {
                return item.getNameProvider().getItemStackDisplayName(stack, unlocalizedName);
            }
            return LocaleUtils.format(unlocalizedName);
        }
        return super.getItemStackDisplayName(stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<String> lines, ITooltipFlag flag) {
        T item = getItem(stack);
        if (item == null) return;
        String unlocalizedTooltip = getTranslationKey() + ".tooltip";
        if (I18n.hasKey(unlocalizedTooltip)) {
            Collections.addAll(lines, LocaleUtils.formatLines(unlocalizedTooltip));
        }

        for (IItemBehavior behavior : getBehaviors(stack)) {
            behavior.addInformation(stack, lines);
        }
    }

    @Override
    public CreativeTabs[] getCreativeTabs() {
        if (additionalCreativeTabs.isEmpty()) return defaultCreativeTabs;
        Set<CreativeTabs> tabs = new ObjectArraySet<>(additionalCreativeTabs);
        tabs.addAll(Arrays.asList(defaultCreativeTabs));
        return tabs.toArray(new CreativeTabs[0]);
    }

    public void addAdditionalCreativeTabs(@NotNull CreativeTabs @NotNull... tabs) {
        for (CreativeTabs tab : tabs) {
            if (!ArrayUtils.contains(defaultCreativeTabs, tab) && tab != CreativeTabs.SEARCH) {
                additionalCreativeTabs.add(tab);
            }
        }
    }

    @Override
    protected boolean isInCreativeTab(CreativeTabs tab) {
        return tab == CreativeTabs.SEARCH ||
                ArrayUtils.contains(defaultCreativeTabs, tab) ||
                additionalCreativeTabs.contains(tab);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> stacks) {
        if (!isInCreativeTab(tab)) return;
        for (T item : items.values()) {
            if (!item.isInCreativeTab(tab)) continue;
            item.getSubHandler().getSubItems(item.getStackForm()    , tab, stacks);
        }
    }

    // ========================================================
    // ITEM HANDLING
    // ========================================================

    protected abstract T createItem(short id, String unlocalizedName);

    public final T addItem(int id, String unlocalizedName) {
        Validate.inclusiveBetween(0, Short.MAX_VALUE - 1, id + idOffset,
                "Item ID should be in range from 0 to Short.MAX_VALUE-1");
        T item = createItem((short) id, unlocalizedName);
        if (items.containsKey((short) id)) {
            T registeredItem = items.get((short) id);
            throw new IllegalArgumentException(String.format("Id %d is already occupied by item %s (requested by item %s)", id, registeredItem.unlocalizedName, unlocalizedName));
        }
        items.put((short) id, item);
        names.put(unlocalizedName, item);
        return item;
    }

    public void registerSubItems() {}
    public class HBMItem {
        public HBMItemBase<T> getItemBase() {
            return HBMItemBase.this;
        }

        public final int id;

        public final String unlocalizedName;

        private final List<IItemComponent> COMPONENTS = new ArrayList<>();
        private final List<IItemBehavior> BEHAVIORS = new ArrayList<>();

        private IMultipleModelBehavior modelBehavior;
        private IItemNameProvider nameProvider;
        private IItemSubHandler subHandler;

        private int burnValue = 0;
        private int maxStackSize = 64;
        private int models = 0;

        @Nullable
        private CreativeTabs[] tabOverrides;
        protected HBMItem(int id, String unlocalizedName) {
            this.id = id;
            this.unlocalizedName = unlocalizedName;
        }

        public HBMItem setBurnValue(int burnValue) {
            this.burnValue = burnValue;
            return this;
        }

        public HBMItem setMaxStackSize(int maxStackSize) {
            this.maxStackSize = maxStackSize;
            return this;
        }

        public HBMItem setModelAmounts(int models) {
            this.models = models;
            return this;
        }

        public int getBurnValue() {
            return burnValue;
        }

        public int getMaxStackSize() {
            return maxStackSize;
        }

        public HBMItem setModelBehavior(IMultipleModelBehavior modelBehavior) {
            this.modelBehavior = modelBehavior;
            return this;
        }

        public IMultipleModelBehavior getModelBehavior() {
            return modelBehavior;
        }


        public HBMItem setNameProvider(IItemNameProvider nameProvider) {
            this.nameProvider = nameProvider;
            return this;
        }

        public IItemNameProvider getNameProvider() {
            return nameProvider;
        }

        public HBMItem setSubHandler(IItemSubHandler subHandler) {
            this.subHandler = subHandler;
            return this;
        }

        public IItemSubHandler getSubHandler() {
            return subHandler;
        }

        public HBMItem setTabOverrides(CreativeTabs... tabOverrides) {
            this.tabOverrides = tabOverrides;
            HBMItemBase.this.addAdditionalCreativeTabs(tabOverrides);
            return this;
        }

        @Nullable
        public CreativeTabs[] getTabOverrides() {
            return tabOverrides;
        }

        public ItemStack getStackForm(int amount) {
            return new ItemStack(HBMItemBase.this, amount, idOffset + this.id);
        }

        public boolean isItemEqual(ItemStack other) {
            return other.getItem() == HBMItemBase.this && other.getItemDamage() == (idOffset + this.id);
        }

        public int getModelAmounts() {
            return models;
        }

        public ItemStack getStackForm() {
            return getStackForm(1);
        }

        public List<IItemComponent> getComponents() {
            return COMPONENTS;
        }

        public List<IItemBehavior> getBehaviors() {
            return BEHAVIORS;
        }

        public boolean isInCreativeTab(CreativeTabs tab) {
            CreativeTabs[] tabs = this.tabOverrides != null ? this.tabOverrides : HBMItemBase.this.defaultCreativeTabs;
            return tabs.length > 0 && (tab == CreativeTabs.SEARCH || ArrayUtils.contains(tabs, tab));
        }
    }
}
