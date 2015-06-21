package com.gvq.commandframework.impl;

import com.gvq.commandframework.model.impl.HashCommandParams;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by gerard on 21/6/15.
 */
public class HashCommandParamsTest {

    @Test(expected = NullPointerException.class)
    public void test_when_params_is_null_then_exception_is_thrown() {
        HashCommandParams sut = new HashCommandParams(null);

    }

    @Test(expected = NullPointerException.class)
    public void test_when_param_name_is_null_then_exception_is_thrown() {
        Map<String, Object> params = new HashMap<String, Object>();
        HashCommandParams sut = new HashCommandParams(params);
        sut.getParam(null);
    }

    @Test
    public void test_when_hash_params_is_created_then_get_method_returns_same_hash_map_object() {
        Map<String, Object> params = new HashMap<String, Object>();
        HashCommandParams sut = new HashCommandParams(params);
        assertTrue(params.equals(sut.getParams()));
    }

    @Test
    public void test_when_hash_params_is_created_then_size_method_returns_stored_parameters() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("param1", "hello");
        params.put("params2", "hello");
        HashCommandParams sut = new HashCommandParams(params);
        assertTrue(sut.size() == params.size());
    }

    @Test
    public void test_when_param_name_is_not_available_then_isParamAvailable_method_returns_false() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("param1", "hello");
        params.put("params2", "hello");
        HashCommandParams sut = new HashCommandParams(params);
        assertFalse(sut.isParamAvailable("param3"));
    }

    @Test
    public void test_when_param_name_is_available_then_correct_object_is_returned() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("param1", "hello 1");
        params.put("param2", "hello 2");
        HashCommandParams sut = new HashCommandParams(params);
        assertEquals("hello 1", sut.getParam("param1"));
        assertEquals("hello 2", sut.getParam("param2"));
        assertEquals("hello 1", sut.getParam("param1", String.class));
        assertEquals("hello 2", sut.getParam("param2", String.class));
    }
}
