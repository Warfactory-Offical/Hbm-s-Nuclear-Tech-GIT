package api.hbm.resource;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ResourceHelper {

    @SideOnly(Side.CLIENT)
    public static boolean doResourcePacksHaveResource(@NotNull String modId, @NotNull String resource) {
        return doResourcePacksHaveResource(new ResourceLocation(modId, resource));
    }

    @SideOnly(Side.CLIENT)
    public static boolean doResourcePacksHaveResource(@NotNull ResourceLocation resource) {
        IResourceManager manager = Minecraft.getMinecraft().getResourceManager();
        try {
            IResource ignored = manager.getResource(resource);
            IOUtils.closeQuietly(ignored);
            return true;
        } catch (IOException ignored) {
            return false;
        }
    }
}
