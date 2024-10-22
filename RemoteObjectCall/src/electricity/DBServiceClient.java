package electricity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;

public class DBServiceClient {

	public DBServiceClient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		String sql = "", ch = "";
		// TODO Auto-generated method stub
		try {
		ElectricityDBInf stub = (ElectricityDBInf)Naming.lookup("rmi://localhost:1900/ROCforElectricityDB");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("Select an Option");
			System.out.println("1. Retrive Student Information ");
			System.out.println("2. Insert Student Information ");
			System.out.println("3. Exit");
			System.out.println("Enter Your Choice");

			ch = br.readLine();
			if (ch.equals("1")) {
				sql = "SELECT * FROM bill";
				sql = stub.getData(sql);
			} else if (ch.equals("2")) {
				sql = "INSERT INTO bill VALUES('aditya','06/10/2024',1000)";
				sql = stub.insertData(sql);
			} else if (ch.equals("3")) {
				System.exit(0);
			} else {
				sql = "Please select Valid Option";
			}
			System.out.println(sql);
			
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}

	}

}
