package com.hiep.examples;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by hiep on 8/7/2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({HardToMock.class})
public class HardToMockPowerMockTest {
    @Mock
    private HardToMock hardToMock;

    @Test
    public void testHardToMock(){
        String expected = "Mock result from final method";
        when(hardToMock.finalMethod()).thenReturn(expected);

        assertEquals(expected, hardToMock.finalMethod());
    }
}