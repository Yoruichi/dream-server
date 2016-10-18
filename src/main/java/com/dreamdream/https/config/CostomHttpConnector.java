package com.dreamdream.https.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CostomHttpConnector {
//    @Bean
//    public EmbeddedServletContainerFactory servletContainerFactory() {
//        TomcatEmbeddedServletContainerFactory factory =
//                new TomcatEmbeddedServletContainerFactory() {
//                    @Override
//                    protected void postProcessContext(Context context) {
//                        // SecurityConstraint必须存在，可以通过其为不同的URL设置不同的重定向策略。
//                        SecurityConstraint securityConstraint = new SecurityConstraint();
//                        securityConstraint.setUserConstraint("CONFIDENTIAL");
//                        SecurityCollection collection = new SecurityCollection();
//                        collection.addPattern("/*");
//                        securityConstraint.addCollection(collection);
//                        context.addConstraint(securityConstraint);
//                    }
//                };
//        factory.addAdditionalTomcatConnectors(createHttpConnector());
//        return factory;
//    }
//
//    private Connector createHttpConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        connector.setSecure(false);
//        connector.setPort(8079);
//        connector.setRedirectPort(8443);
//        return connector;
//    }

}
