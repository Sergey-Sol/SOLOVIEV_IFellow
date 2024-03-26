package readConf;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("test.conf");
    }

    String baseUrlReqresp = readConfig().getString("base.url.reqresp");
    String postUrlReqresp = readConfig().getString("post.url.reqresp");
    String baseUrlRAM = readConfig().getString("base.url.RAM");
    String postUrlRAM = readConfig().getString(" post.url.RAM");
}
