package games.negative.alumina.builder;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;


/**
 * The PotionBuilder class is used to construct PotionEffect objects with custom options.
 */
public class PotionBuilder {

    private final PotionEffectType type;
    private int duration = -1;
    private int amplifier = 0;
    private boolean ambient = true;
    private boolean particles = true;
    private boolean icon = true;

    /**
     * The PotionBuilder class is used to construct PotionEffect objects with custom options.
     */
    public PotionBuilder(@NotNull PotionEffectType type) {
        this.type = type;
    }

    /**
     * Sets the duration of the PotionEffect in ticks.
     *
     * @param duration the duration of the PotionEffect in ticks
     * @return the PotionBuilder instance
     */
    public PotionBuilder duration(int duration) {
        this.duration = duration;
        return this;
    }

    /**
     * Sets the duration of the PotionEffect based on the given {@link Duration}.
     * The duration is calculated by converting the given duration to milliseconds and then dividing by 50.
     *
     * @param duration the duration of the PotionEffect
     * @return the PotionBuilder instance with the updated duration
     */
    public PotionBuilder duration(@NotNull Duration duration) {
        this.duration = (int) duration.toMillis() / 50;
        return this;
    }

    /**
     * Sets the amplifier of the PotionEffect being constructed.
     *
     * @param amplifier the amplifier of the PotionEffect
     * @return the PotionBuilder object with the specified amplifier
     */
    public PotionBuilder amplifier(int amplifier) {
        this.amplifier = amplifier;
        return this;
    }

    /**
     * Sets the ambient flag for the PotionEffect builder.
     *
     * @param ambient a boolean value indicating whether the PotionEffect should have an ambient effect or not
     * @return the PotionBuilder with the updated ambient flag
     */
    public PotionBuilder ambient(boolean ambient) {
        this.ambient = ambient;
        return this;
    }

    /**
     * Sets whether particles should be shown for the potion effect.
     *
     * @param particles true if particles should be shown, false otherwise
     * @return the updated PotionBuilder instance
     */
    public PotionBuilder particles(boolean particles) {
        this.particles = particles;
        return this;
    }

    /**
     * Sets the value of the "icon" option for the PotionBuilder object.
     *
     * @param icon The value indicating whether the potion effect should display an icon.
     * @return The PotionBuilder object with the updated "icon" option.
     */
    public PotionBuilder icon(boolean icon) {
        this.icon = icon;
        return this;
    }

    /**
     * Builds a PotionEffect with the specified options.
     *
     * @return a PotionEffect object with the specified options.
     */
    @NotNull
    public PotionEffect build() {
        return new PotionEffect(type, duration, amplifier, ambient, particles, icon);
    }

}
