package Utility;

import org.aeonbits.owner.Config;

@org.aeonbits.owner.Config.Sources({"classpath:environments/${property.file}.properties"})
public interface Configist extends org.aeonbits.owner.Config {

    @Key("base.uri")
    String baseUri();

    @Key("system.user.id")
    String sysuserid();

    @Key("oauth2.token")
    String authToken();

}

