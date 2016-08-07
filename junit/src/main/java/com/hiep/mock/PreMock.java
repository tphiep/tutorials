package com.hiep.mock;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by hiep on 8/7/2016.
 *  This annotation will be used within the JUnit test to determine which classes can be pre-mocked.
 *  This will insure we do not alter a class unexpectedly; only the unit test and classes listed here are loaded by the custom class loader.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface PreMock {
    Class<?>[] value();
}
