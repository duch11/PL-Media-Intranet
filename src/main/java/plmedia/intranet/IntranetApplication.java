package plmedia.intranet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import plmedia.intranet.dao.DBUtil;

import java.util.ArrayList;
import plmedia.intranet.dao.DBUtilInterface;
import plmedia.intranet.model.Parent;

@SpringBootApplication
public class IntranetApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntranetApplication.class, args);

		DBUtilInterface db = new DBUtil();

		ArrayList<Parent> parents = db.getAllParents();

		for (Parent p :
				parents) {
			System.out.println(p);
			for (int s :
					p.getChildren()) {
				System.out.println(db.getChildObject(s));
			}
		}

	}
}
