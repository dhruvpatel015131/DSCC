import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;

public class DBServiceClient {

	public DBServiceClient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sql = "", ch = "";
		try {
			StudDBInf stub = (StudDBInf) Naming.lookup("rmi://localhost:1900/ROCforStudDB");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				System.out.println("Select an Option");
				System.out.println("1. Retrive Student Information ");
				System.out.println("2. Insert Student Information ");
				System.out.println("3. Exit");
				System.out.println("Enter Your Choice");

				ch = br.readLine();
				if (ch.equals("1")) {
					sql = "SELECT * FROM student";
					sql = stub.getData(sql);
				} else if (ch.equals("2")) {
					sql = "INSERT INTO student(rollno, studnm) VALUES(10,'LMN')";
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
