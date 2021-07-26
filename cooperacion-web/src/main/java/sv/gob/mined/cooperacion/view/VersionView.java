/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.cooperacion.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class VersionView implements Serializable {

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("bundle");
    
    private String version;
    private String versionLong; 

    @PostConstruct
    public void init() {

        try {
            Properties properties = new Properties();
            properties
                    .load(this
                            .getClass()
                            .getClassLoader()
                            .getResourceAsStream("bundle.properties"));

            version = properties.getProperty("version");
            versionLong = properties.getProperty("versionLong");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersionLong() {
        return versionLong;
    }

    public void setVersionLong(String versionLong) {
        this.versionLong = versionLong;
    }
    
    public String getAmbiente(){
        return RESOURCE_BUNDLE.getString("ambiente");
    }
}
