// License: GPL. For details, see LICENSE file.
package org.openstreetmap.josm.io.remotecontrol;

import org.openstreetmap.josm.Main;
import org.openstreetmap.josm.data.preferences.BooleanProperty;
import org.openstreetmap.josm.io.remotecontrol.handler.RequestHandler;

/**
 * Manager class for remote control operations.
 *
 * IMPORTANT! increment the minor version on compatible API extensions
 * and increment the major version and set minor to 0 on incompatible changes.
 */
public class RemoteControl {

    /**
     * If the remote control feature is enabled or disabled. If disabled,
     * it should not start the server.
     */
    public static final BooleanProperty PROP_REMOTECONTROL_ENABLED = new BooleanProperty("remotecontrol.enabled", false);

    /**
     * If the remote control feature is enabled or disabled for HTTPS. If disabled,
     * only HTTP access will be available.
     * @since 7335
     */
    public static final BooleanProperty PROP_REMOTECONTROL_HTTPS_ENABLED = new BooleanProperty("remotecontrol.https.enabled", false);

    /**
     * RemoteControl HTTP protocol version. Change minor number for compatible
     * interface extensions. Change major number in case of incompatible
     * changes.
     */
    static final int protocolMajorVersion = 1;
    static final int protocolMinorVersion = 5;

    /**
     * Starts the remote control server
     */
    public static void start() {
        RemoteControlHttpServer.restartRemoteControlHttpServer();
        RemoteControlHttpsServer.restartRemoteControlHttpsServer();
    }

    /**
     * Stops the remote control server
     * @since 5861
     */
    public static void stop() {
        RemoteControlHttpServer.stopRemoteControlHttpServer();
        RemoteControlHttpsServer.stopRemoteControlHttpsServer();
    }

    /**
     * Adds external request handler.
     * Can be used by plugins that want to use remote control.
     *
     * @param command The command name.
     * @param handlerClass The additional request handler.
     */
    public void addRequestHandler(String command, Class<? extends RequestHandler> handlerClass) {
        RequestProcessor.addRequestHandlerClass(command, handlerClass);
    }

    /**
     * Returns the remote control directory.
     * @return The remote control directory
     * @since 7335
     */
    public static String getRemoteControlDir() {
        return Main.pref.getPreferencesDir() + "remotecontrol/";
    }
}
