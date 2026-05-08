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

    public Resource getResource(String urlStr) {
        if (urlStr.startsWith("/")) {
            urlStr = urlStr.substring(1);
        }

        ResourceList rl = scanResult.getResourcesWithPath(urlStr);
        return rl.isEmpty() ? null : rl.get(0);
    }

    public URL getURL(String urlStr) throws MalformedURLException {
        Resource res = getResource(urlStr);
        return (res != null) ? res.getURL() : null;
    }

    public InputStream getResourceAsInputStream(String urlStr) throws IOException {

        if (urlStr.startsWith("classpath://")) {
            Resource res = getResource(urlStr.substring(13));
            return (res != null) ? res.open() : null;
        } else
            return new FileInputStream(urlStr);
    }

    public static void initResourceManager(String basePath) {
        if (INSTANCE == null) {
            INSTANCE = new ResourceManager(basePath);
        }
    }

    public static ResourceManager get() {
        return INSTANCE;
    }

    // Remember to close it when the app shuts down
    public void shutdown() {
        scanResult.close();
    }
}
