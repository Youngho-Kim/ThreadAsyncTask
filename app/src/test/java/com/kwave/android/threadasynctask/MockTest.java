package com.kwave.android.threadasynctask;

import android.content.Context;

import com.kwave.android.threadasynctask.TDD.StringConverter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by kwave on 2017-06-14.
 */

@RunWith(MockitoJUnitRunner.class)
public class MockTest {
    @Mock
    Context context;
//    Context mockTest = mock(new Activity());
//    @Spy
//    Context spyContext;

    List<Integer> ints = mock(List.class);

    @Before
    public void stubbing(){
        ints.add(100);  // 실행X , 실행되어도 빈 깡통
        when(context.getString(R.string.sample_string)).thenReturn("sample string");
        when(ints.get(anyInt())).thenReturn(10);
        when((context.getSystemService(Context.NETWORK_STATS_SERVICE))).thenThrow(IOException.class);
    }

    @Test
    public void testHttpCoonnect(){
        System.out.printf("context is null : %b",context == null);
        StringConverter converter = new StringConverter(context);
        assertThat(converter.getString(R.string.sample_string),is("sample string"));
        assertThat(ints.get(100),is(10));   // 목을 가지고 대입연산 확인
//        verify(context).getSystemService(Context.NETWORK_STATS_SERVICE);    // 로직확인
        verify(context, times(2)).getSystemService(Context.NETWORK_STATS_SERVICE);    // 로직확인
    }
    @Test
    public void testGetString(){
        System.out.printf("context is null : %b",context == null);
        StringConverter converter = new StringConverter(context);
        assertThat(converter.getString(R.string.sample_string),is("sample string"));
        assertThat(ints.get(100),is(10));   // 목을 가지고 대입연산 확인
//        verify(context).getSystemService(Context.NETWORK_STATS_SERVICE);    // 로직확인
        verify(context, times(2)).getSystemService(Context.NETWORK_STATS_SERVICE);    // 로직확인
    }

    @Test
    public void testSerialize(){
        StringConverter converter = new StringConverter(context);
        String result = converter.serialize(false);
//        assertEquals("false",result);
        assertEquals("true",result);
//        String wrrongResult = converter.serialize("falase");
    }

}
