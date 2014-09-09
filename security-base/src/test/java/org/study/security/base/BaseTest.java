package org.study.security.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;

/**
 * Created by haoyuewen on 9/7/14.
 */
public class BaseTest extends UnitilsJUnit4 {

    /* The logger instance for this class */
    private static Log logger = LogFactory.getLog(BaseTest.class);

    @SpringApplicationContext(value = {"classpath:conf/spring.xml"})
    protected ApplicationContext applicationContext;

    @Test
    public void testApplicationContext() {
        logger.info("Init AppicationContext");
        Assert.assertNotNull(applicationContext);
    }

}
