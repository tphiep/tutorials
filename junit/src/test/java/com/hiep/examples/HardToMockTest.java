package com.hiep.examples;

import com.hiep.mock.PreMock;
import com.hiep.mock.PreMockJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@PreMock({HardToMock.class, EasyMock.class})
@RunWith(PreMockJUnit4ClassRunner.class)
public class HardToMockTest {

    @Mock
    HardToMock hardToMock;

    @Mock
    EasyMock easyMock;


    @Test
    public void testIt() throws Exception {
        when(hardToMock.finalMethod()).thenReturn("Mock result from final method.");
        when(hardToMock.nativeMethod()).thenReturn("Mock result from native method.");
        when(easyMock.method()).thenReturn("PreMock does not get in the way");

        assertEquals("Mock result from final method.", hardToMock.finalMethod());
        assertEquals("Mock result from native method.", hardToMock.nativeMethod());
        assertEquals("PreMock does not get in the way", easyMock.method());
    }

}