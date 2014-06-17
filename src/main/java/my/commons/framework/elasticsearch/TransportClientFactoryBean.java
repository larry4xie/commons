package my.commons.framework.elasticsearch;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Spring FactoryBean for elasticsearch TransportClient
 * @author xiegang
 * @since 2014/6/17
 */
public class TransportClientFactoryBean extends AbstractClientFactoryBean {
    private static final Logger LOG = LoggerFactory.getLogger(TransportClientFactoryBean.class);

    private String[] nodes =  { "localhost:9300" };

    /**
     * Define ES nodes to communicate with.
     * <br/>use : hostname:port form
     * <p>Example :<br/>
     * <pre>
     * {@code
     *
     * <property name="nodes">
     *  <list>
     *   <value>localhost:9300</value>
     *   <value>localhost:9301</value>
     *  </list>
     * </property>
     * }
     * </pre>
     * If not set, default to [ "localhost:9300" ].
     * <br>If port is not set, default to 9300.
     * @param nodes An array of nodes hostname:port
     */
    public void setNodes(String[] nodes) {
        this.nodes = nodes;
    }

    /** localhost:9300;localhost:9301;localhost:9302 */
    public void setNodesString(String nodes) {
        this.nodes = nodes.split(";");
    }

    /**
     * Define ES nodes to communicate with.
     * @return An array of nodes hostname:port
     */
    public String[] getNodes() {
        return nodes;
    }

    @Override
    public Client getObject() throws Exception {
        ImmutableSettings.Builder builder = ImmutableSettings.settingsBuilder();

        if (null != this.configLocation && null == this.properties) {
            LOG.info("load elasticsearch config from file {}", this.configLocation);
            builder.loadFromClasspath(this.configLocation);
        }

        if (null != this.properties) {
            LOG.info("load elasticsearch config from properties");
            builder.put(this.properties);
        }

        TransportClient client = new TransportClient(builder.build());

        for (int i = 0; i < nodes.length; i++) {
            client.addTransportAddress(toAddress(nodes[i]));
        }

        return client;
    }

    /**
     * Helper to define an hostname and port with a String like hostname:port
     * @param address Node address hostname:port (or hostname)
     */
    private InetSocketTransportAddress toAddress(String address) {
        if (address == null) return null;

        String[] slitted = address.split(":");
        int port = 9300;
        if (slitted.length > 1) {
            port = Integer.parseInt(slitted[1]);
        }

        return new InetSocketTransportAddress(slitted[0], port);
    }
}
