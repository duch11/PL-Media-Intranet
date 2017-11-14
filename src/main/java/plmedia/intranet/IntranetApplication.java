package plmedia.intranet;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IntranetApplication {

	@Value("${spring.datasource.driverClassName}")
	private String databaseDriverClassName;

	@Value("${spring.datasource.url}")
	private String datasourceUrl;

	@Value("${spring.datasource.username}")
	private String databaseUsername;

	@Value("${spring.datasource.password}")
	private String databasePassword;


	public static void main(String[] args) {
		SpringApplication.run(IntranetApplication.class, args);



	}

	@Bean
	public DataSource datasource() {
		org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
		ds.setDriverClassName(databaseDriverClassName);
		ds.setUrl(datasourceUrl);
		ds.setUsername(databaseUsername);
		ds.setPassword(databasePassword);

		return ds;
	}
}
