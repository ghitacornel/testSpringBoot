package beans.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

public class WireMockExtension implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {

    private static boolean started = false;
    private static final int port = 1111;

    protected static WireMockServer wireMockServer;

    public WireMockExtension() {
        wireMockServer = new WireMockServer(port);
    }

    @Override
    public void beforeAll(ExtensionContext context) {
        if (!started) {
            started = true;
            // Your "before all tests" startup logic goes here
            // The following line registers a callback hook when the root test context is shut down
            context.getRoot().getStore(GLOBAL).put("wireMockFlag", this);
            wireMockServer.start();
        }
    }

    @Override
    public void close() {
        wireMockServer.shutdown();
    }
}
