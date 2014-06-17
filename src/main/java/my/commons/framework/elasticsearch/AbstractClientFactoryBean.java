package my.commons.framework.elasticsearch;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.FactoryBean;

import java.util.Properties;

/**
 * Spring FactoryBean for elasticsearch Client
 *
 * @author xiegang
 * @since 2014/6/17
 */
public abstract class AbstractClientFactoryBean implements FactoryBean<Client> {
    /** es conf properties */
    protected Properties properties;

    /** config properties location */
    protected String configLocation;

    @Override
    public Class<?> getObjectType() {
        return Client.class;
    }

    /** singleton */
    @Override
    public boolean isSingleton() {
        return true;
    }

    /** es config properties */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /** config properties location (classpath) */
    public void setConfigLocation(String configLocation) {
        this.configLocation = configLocation;
    }
}
