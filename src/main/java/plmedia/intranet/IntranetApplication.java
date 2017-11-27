package plmedia.intranet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import plmedia.intranet.dao.DBUtil;

import java.util.ArrayList;
import plmedia.intranet.model.Parent;

@SpringBootApplication
public class IntranetApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntranetApplication.class, args);

		DBUtil db = new DBUtil();

		db.createParent("123456", "Andreas@rednex.dk", "Andreas", "Nissmand", "ROLE_PAR");


		ArrayList<Parent> parents = db.getAllParents();

		for (Parent p :
				parents) {
			System.out.println(p);
/*			for (int s :
					db.GetChildrenIDByParentID(p.getUserId())) {
				System.out.println(db.getChildObject(s));
			}*/
		}

	}
}
