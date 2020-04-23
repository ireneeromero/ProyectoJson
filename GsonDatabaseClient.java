package salud.isaConCadenadeMando;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GsonDatabaseClient {

	public static void main(String[] args) {
		try{
			UserManualPhysioSteps user= new UserManualPhysioSteps(null);
			RescueMedicinePresentations resmed = new RescueMedicinePresentations(user);
			Physiotherapies phy = new Physiotherapies(resmed);
			Medicine med = new Medicine(phy);
			Database ccm = new Database(med);
			
			try {
				System.out.println(ccm.parse("./datos.json"));
			} finally {
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}

	}

}
