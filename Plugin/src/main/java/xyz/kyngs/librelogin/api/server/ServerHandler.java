package xyz.kyngs.librelogin.api.server;

import com.google.common.collect.Multimap;
import xyz.kyngs.librelogin.api.database.User;

import java.util.Collection;

/**
 * An interface which manages lobby and limbo servers.
 *
 * @param <S>
 * @param <P>
 */
public interface ServerHandler<P, S> {

    /**
     * Gets the latest ping of the server, returns null if the server is not online.
     * Can block if the server has not yet been pinged.
     *
     * @param server The server to ping
     * @return The data of the server, or null if the server is not online
     */
    ServerPing getLatestPing(S server);

    /**
     * Chooses an optimal lobby server to connect the player to. Usually the one with the lowest player count.
     *
     * @param user     The user of the player
     * @param player   The player we're choosing the server for
     * @param remember Whether to respect the remember last server option
     * @return An optimal lobby server, or null if there are no lobby servers
     */
    S chooseLobbyServer(User user, P player, boolean remember);

    /**
     * Chooses an optimal limbo server to connect the player to. Usually the one with the lowest player count.
     *
     * @param user   The user of the player
     * @param player The player we're choosing the server for
     * @return An optimal limbo server, or null if there are no limbo servers
     */
    S chooseLimboServer(User user, P player);

    /**
     * Gets the lobby servers.
     *
     * @return The lobby servers
     */
    Multimap<String, S> getLobbyServers();

    /**
     * Gets the limbo servers.
     *
     * @return The limbo servers
     */
    Collection<S> getLimboServers();

    /**
     * Registers a new lobby server.
     *
     * @param server     The server to register
     * @param forcedHost The forced host
     */
    void registerLobbyServer(S server, String forcedHost);

    /**
     * Registers a new lobby server.
     *
     * @param server The server to register
     */
    default void registerLobbyServer(S server) {
        registerLobbyServer(server, "root");
    }

    /**
     * Registers a new limbo server.
     *
     * @param server The server to register
     */
    void registerLimboServer(S server);

}