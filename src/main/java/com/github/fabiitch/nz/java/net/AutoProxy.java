package com.github.fabiitch.nz.java.net;

import com.badlogic.gdx.Gdx;
import lombok.experimental.UtilityClass;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.util.Iterator;
import java.util.List;

@UtilityClass
public class AutoProxy {

    public static void init() throws Exception {
        System.setProperty("java.net.useSystemProxies", "true");
        List<Proxy> l = ProxySelector.getDefault().select(new URI("https://www.yahoo.com/")); //TODO change uri

        for (Iterator<Proxy> iter = l.iterator(); iter.hasNext(); ) {

            Proxy proxy = iter.next();
            Gdx.app.log("Proxy", "type : " + proxy.type());

            InetSocketAddress addr = (InetSocketAddress) proxy.address();
            if (addr == null) {
                Gdx.app.log("Proxy", "no proxy");
            } else {
                Gdx.app.log("Proxy", "hostname : " + addr.getHostName());
                Gdx.app.log("Proxy", "port : " + addr.getPort());
            }
        }
    }
}
