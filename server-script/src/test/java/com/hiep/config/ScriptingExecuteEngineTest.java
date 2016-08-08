package com.hiep.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.hiep.scripts.ScriptingExecuteEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by hiep on 8/8/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class ScriptingExecuteEngineTest {

    @Autowired
    private ScriptingExecuteEngine scriptEngine;

    @Test
    public void testExecuteScript(){
        String result = (String) scriptEngine.invoke("helloWord", "Hiep");
        assertThat(result, is("Hello, Hiep"));
    }

    @Test
    public void testGetComplexOjeect(){
        Object object = scriptEngine.invoke("complexObject");
        Gson gson = new GsonBuilder().create();
        JsonElement element = gson.toJsonTree(object);
        User user = gson.fromJson(element, User.class);
        assertThat(element.getAsJsonObject().get("name").getAsString(), is("Hiep"));
        assertThat(user.getName(), is("Hiep"));
    }

    private class User {
        private String name;
        private Address address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }
    }

    private class Address {
        private String city;
        private String dist;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDist() {
            return dist;
        }

        public void setDist(String dist) {
            this.dist = dist;
        }
    }
}