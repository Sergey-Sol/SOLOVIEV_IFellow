package readConf;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {

    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("test.conf");
    }

    String URL = readConfig().getString("url");
    String USERNAME = readConfig().getString("username");
    String PASSWORD = readConfig().getString("password");
    String SEARCHVALUE = readConfig().getString("searchValue");

}
