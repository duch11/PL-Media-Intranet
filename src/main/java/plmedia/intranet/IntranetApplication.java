package plmedia.intranet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import plmedia.intranet.dao.DBUtil.PermissionsUtil;
import plmedia.intranet.dao.repository.PermissionRepo;
import plmedia.intranet.model.Permission;

@SpringBootApplication
public class IntranetApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntranetApplication.class, args);

	}
}
