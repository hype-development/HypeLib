package games.negative.alumina.model.message;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a piece of content that can be sent to a receiver.
 * @param <T> The type of the receiver.
 * @see games.negative.alumina.message.Message
 */
public interface Deliverable<T> {

    /**
     * Sends the content to the receiver.
     * @param receiver The receiver of the content.
     */
    void send(@NotNull T receiver);

}
