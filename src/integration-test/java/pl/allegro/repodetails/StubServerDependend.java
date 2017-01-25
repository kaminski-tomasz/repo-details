package pl.allegro.repodetails;

import com.xebialabs.restito.server.StubServer;
import com.xebialabs.restito.support.junit.ServerDependencyRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

public class StubServerDependend {
    protected StubServer server;

    @Rule
    public ServerDependencyRule serverDependency = new ServerDependencyRule();

    @Before
    public void startServer() {
        if (serverDependency.isServerDependent()) {
            server = new StubServer().run();
        }
    }

    @After
    public void stopServer() {
        if (server != null) {
            server.stop();
        }
    }
}
