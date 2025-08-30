package ch.timeo.arrowCrafting.registry;

import ch.timeo.arrowCrafting.components.Component;
import ch.timeo.arrowCrafting.components.effect.DefaultEffect;
import ch.timeo.arrowCrafting.components.effect.Effect;
import ch.timeo.arrowCrafting.components.fletching.DefaultFletching;
import ch.timeo.arrowCrafting.components.fletching.Fletching;
import ch.timeo.arrowCrafting.components.point.DefaultPoint;
import ch.timeo.arrowCrafting.components.point.EnderPoint;
import ch.timeo.arrowCrafting.components.point.Point;
import ch.timeo.arrowCrafting.components.point.SlimePoint;
import ch.timeo.arrowCrafting.components.shaft.DefaultShaft;
import ch.timeo.arrowCrafting.components.shaft.FireShaft;
import ch.timeo.arrowCrafting.components.shaft.Shaft;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class ArrowRegistry {




    // region Fletching
    private static final Map<String, Fletching> ID_TO_FLETCHING = new HashMap<>();
    private static final Map<Material, String> MAT_TO__FLETCHING_ID = new HashMap<>();

    private static void registerFletching(){
        registerComponent(new DefaultFletching(), Material.FEATHER, ID_TO_FLETCHING, MAT_TO__FLETCHING_ID);
    }

    public static boolean isValidFletching(Material material){
        return MAT_TO__FLETCHING_ID.containsKey(material);
    }

    public static Fletching getFletching(String id){
        return ID_TO_FLETCHING.get(id);
    }
    public static Fletching getFletching(Material material){
        String id = MAT_TO__FLETCHING_ID.get(material);
        if(id == null) return null;
        return getFletching(id);
    }
    public static String getFletchingId(Material material){
        return MAT_TO__FLETCHING_ID.get(material);
    }

    // endregion

    // region Shaft
    private static final Map<String, Shaft> ID_TO_SHAFT = new HashMap<>();
    private static final Map<Material, String> MAT_TO_SHAFT_ID = new HashMap<>();

    private static void registerShaft(){
        registerComponent(new DefaultShaft(), Material.STICK,ID_TO_SHAFT,MAT_TO_SHAFT_ID);
        registerComponent(new FireShaft(), Material.BLAZE_ROD, ID_TO_SHAFT, MAT_TO_SHAFT_ID);
    }

    public static boolean isValidShaft(Material material){
        return MAT_TO_SHAFT_ID.containsKey(material);
    }

    public static Shaft getShaft(String id){
        return ID_TO_SHAFT.get(id);
    }
    public static Shaft getShaft(Material material){
        String id = MAT_TO_SHAFT_ID.get(material);
        if(id == null) return null;
        return getShaft(id);
    }
    public static String getShaftId(Material material){
        return MAT_TO_SHAFT_ID.get(material);
    }
    // endregion

    // region Point
    private static final Map<String, Point> ID_TO_POINT = new HashMap<>();
    private static final Map<Material, String> MAT_TO_POINT_ID = new HashMap<>();

    private static void registerPoint(){
        registerComponent(new DefaultPoint(), Material.FLINT, ID_TO_POINT, MAT_TO_POINT_ID);
        registerComponent(new EnderPoint(), Material.ENDER_PEARL, ID_TO_POINT, MAT_TO_POINT_ID);
        registerComponent(new SlimePoint(), Material.SLIME_BALL, ID_TO_POINT, MAT_TO_POINT_ID);
    }

    public static boolean isValidPoint(Material material){
        return MAT_TO_POINT_ID.containsKey(material);
    }

    public static Point getPoint(String id){
        return ID_TO_POINT.get(id);
    }
    public static Point getPoint(Material material){
        String id = MAT_TO_POINT_ID.get(material);
        if(id == null) return null;
        return getPoint(id);
    }
    public static String getPointId(Material material){
        return MAT_TO_POINT_ID.get(material);
    }
    // endregion

    // region Effect
    private static final Map<String, Effect> ID_TO_EFFECT = new HashMap<>();
    private static final Map<Material, String> MAT_TO_EFFECT_ID = new HashMap<>();

    private static void registerEffect(){
        registerComponent(new DefaultEffect(), Material.AIR, ID_TO_EFFECT, MAT_TO_EFFECT_ID);
    }

    public static boolean isValidEffect(Material material){
        return MAT_TO_EFFECT_ID.containsKey(material);
    }

    public static Effect getEffect(String id){
        return ID_TO_EFFECT.get(id);
    }
    public static Effect getEffect(Material material){
        String id = MAT_TO_EFFECT_ID.get(material);
        if(id == null) return null;
        return getEffect(id);
    }
    public static String getEffectId(Material material) {
        return MAT_TO_EFFECT_ID.get(material);
    }
    // endregion

    public static void registerAll() {
        registerShaft();
        registerFletching();
        registerPoint();
        registerEffect();
    }

    private static <T extends Component> void registerComponent(T component, Material material, Map<String,T> idToComp, Map<Material,String> matToString) {
        idToComp.put(component.getId(), component);
        matToString.put(material, component.getId());
    }
}