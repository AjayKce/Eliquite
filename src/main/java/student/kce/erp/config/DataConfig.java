package student.kce.erp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {
	
	@Value("${db.driver}")
    private String DRIVER;

    @Value("${db.password}")
    private String PASSWORD;

    @Value("${db.url}")
    private String URL;

    @Value("${db.username}")
    private String USERNAME;

	@Bean
	public DataConnect dataConnect() {
		DataConnect dataConnect = new DataConnect();
		dataConnect.setDriver(DRIVER);
		dataConnect.setPassword(PASSWORD);
		dataConnect.setUrl(URL);
		dataConnect.setUsername(USERNAME);
		return dataConnect;
	}
	
}
