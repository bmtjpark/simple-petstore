package system.com.pyxis.petstore.support;

import system.com.pyxis.petstore.page.HomePage;
import system.com.pyxis.petstore.page.SearchPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.getProperty;

public final class Routes {

    private static final String HTTP = "http";

    private static Map<Class<?>, String> urlMappings = new HashMap<Class<?>, String>();

    static {
        urlMappings.put(HomePage.class, "/");
        urlMappings.put(SearchPage.class, "/item/search");
    }

    private Routes() {
    }

    public static URL urlFor(Class<?> pageClass) throws MalformedURLException {
        return new URL(HTTP, serverHost(), serverPort(), path(pageClass));
    }

    private static String path(Class<?> pageClass) {
        return "/" + context() + urlMappings.get(pageClass);
    }

    private static String serverHost() {
        return getProperty("server.host", "localhost");
    }

    private static int serverPort() {
        return getPropertyAsInt("server.port", "8080");
    }

    private static String context() {
        return getProperty("app.context", "petstore");
    }

    private static int getPropertyAsInt(final String name, final String defaultValue) {
        return Integer.parseInt(getProperty(name, defaultValue));
    }
}