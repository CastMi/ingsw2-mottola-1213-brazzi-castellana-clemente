package it.swimv2.util;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ContextUtil {

    public static InitialContext getInitialContext() throws NamingException {
    	Properties props = new Properties();
        props.setProperty("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");
        props.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
        props.setProperty("java.naming.provider.url", "127.0.0.1:1099");  
        return new InitialContext(props);
    }
    
}
