package org.ussa.integration;

import org.springframework.test.AbstractTransactionalSpringContextTests;

/**
 * User: jminer
 * Date: Apr 8, 2008
 * Time: 5:05:22 PM
 */
public class AbstractUssaIntegrationTests extends AbstractTransactionalSpringContextTests {
    private static final String[] CONFIG_LOCATIONS = new String[]{
        "classpath:/applicationContext-resources-test.xml",
        "classpath:/applicationContext-resources-common-test.xml",
        "classpath:/applicationContext-service-membership.xml",
        "file:./src/main/webapp/WEB-INF/applicationContext.xml"
    };

    protected String[] getConfigLocations() {
        return CONFIG_LOCATIONS;
    }
}