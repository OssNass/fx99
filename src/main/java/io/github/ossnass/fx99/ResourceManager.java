package io.github.ossnass.fx99;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.Resource;
import io.github.classgraph.ResourceList;
import io.github.classgraph.ScanResult;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class allows the loading of resources stored inside the application using ClasssGraph library
 */
public class ResourceManager {
    private static ResourceManager INSTANCE;

    // Keep the scan result alive so resources stay valid
    private final ScanResult scanResult;

    private ResourceManager(String basePath) {
        // Initialize the scan once.
        // Tip: acceptPackages/acceptPaths makes this much faster!
        this.scanResult = new ClassGraph()
                .acceptPaths(basePath)
                .scan();
    }

    /**
     * Returns  a resource as a {@link Resource} object
     *
     * @param urlStr the URL path of the resource, if the path starts with "/" it will be removed
     * @return the loaded resource
     */
    public Resource getResource(String urlStr) {
        if (urlStr.startsWith("/")) {
            urlStr = urlStr.substring(1);
        }

        ResourceList rl = scanResult.getResourcesWithPath(urlStr);
        return rl.isEmpty() ? null : rl.get(0);
    }

    /**
     * Converts a url from string to a URL object
     *
     * @param urlStr the URL in string form
     * @return the URL as a URL object
     * @throws MalformedURLException in case of incorrect format
     */
    public URL getURL(String urlStr) throws MalformedURLException {
        Resource res = getResource(urlStr);
        return (res != null) ? res.getURL() : null;
    }

    /**
     * Opens a resource as a stream object
     *
     * @param urlStr the path to resource, could be a URL or a path to file in classpath, if it starts with "classpath://"
     *               then it will be treated as a classpath URL and the prefix removed and the rest of the string
     *               otherwise it will be consided a standard URL and treated as such.
     * @return the resource in stream form
     * @throws IOException in case of bad URL or inability to open the resource
     */
    public InputStream getResourceAsInputStream(String urlStr) throws IOException {

        if (urlStr.startsWith("classpath://")) {
            Resource res = getResource(urlStr.substring(12));
            return (res != null) ? res.open() : null;
        } else
            return new FileInputStream(urlStr);
    }

    /**
     * Initializes the resource manager
     *
     * @param basePath the base path in the classpath for resources to pre-index values
     */
    public static void initResourceManager(String basePath) {
        if (INSTANCE == null) {
            INSTANCE = new ResourceManager(basePath);
        }
    }

    /**
     * Returns the single instance of resource manager
     *
     * @return the only instance of {@link ResourceManager}
     */
    public static ResourceManager get() {
        return INSTANCE;
    }

    /**
     * This will close the resource manager scanner, must be called when existing the application
     */
    public void shutdown() {
        scanResult.close();
    }
}
