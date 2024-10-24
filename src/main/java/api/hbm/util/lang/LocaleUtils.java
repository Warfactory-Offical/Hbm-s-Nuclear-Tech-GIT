package api.hbm.util.lang;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

@SuppressWarnings("deprecation")
public class LocaleUtils {
    private static final Pattern NEW_LINE_PATTERN = Pattern.compile("/n");

    public static String format(String key, Object... format) {
        if (FMLCommonHandler.instance().getSide() == Side.SERVER) {
            return net.minecraft.util.text.translation.I18n.translateToLocalFormatted(key, format);
        } else {
            return net.minecraft.client.resources.I18n.format(key, format);
        }
    }

    /**
     * This function calls {@link net.minecraft.client.resources.I18n} when called on client
     * or {@link net.minecraft.util.text.translation.I18n} when called on server.
     * <ul>
     * <li>
     * It is recommended to use {@link net.minecraft.client.resources.I18n} on the client.
     * </li>
     * <li>
     * For translations on the server, use {@link net.minecraft.util.text.TextComponentTranslation}
     * </li>
     * <li>
     * {@code LocaleUtils} is only for cases where translation is needed, but there is no player/client in context.
     * </li>
     * </ul>
     * @param key
     * @return
     */
    public static boolean hasKey(String key) {
        if (FMLCommonHandler.instance().getSide() == Side.SERVER) {
            return net.minecraft.util.text.translation.I18n.canTranslate(key);
        } else {
            return net.minecraft.client.resources.I18n.hasKey(key);
        }
    }
    /**
     * Returns translated text corresponding to given key {@code key} on current locale,
     * split with text {@code '\n'}.
     *
     * @param key localization key
     * @param args substitutions
     * @return translated text split with text {@code '\n'}
     */
    @NotNull
    public static String[] formatLines(String key, Object... args) {
        return NEW_LINE_PATTERN.split(format(key, args));
    }
}
