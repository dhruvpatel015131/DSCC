import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class StudDBOperations extends UnicastRemoteObject implements StudDBInf {
	
	private static final long serialVersionUID = 1L;
	Connection con;
	Statement stmt;
	ResultSet rs;
	ResultSetMetaData rsmd;
	String colStr, resultStr;
	
	public StudDBOperations() throws RemoteException{
		super();
		con = null;
		stmt = null;
		rs = null;
		rsmd = null;
		colStr = "";
		resultStr = "";
	}
	
	public void setDBCon() {
		try {
			String URL = "jdbc:mysql://localhost:3307/studinfo";
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL,"root","");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public String getData(String strQry) throws RemoteException{
		try {
			setDBCon();
			System.out.println("Server Registered.");
			stmt = con.createStatement();
			rs = stmt.executeQuery(strQry);
			rsmd = (ResultSetMetaData) rs.getMetaData();
			for(int i=1; i<=rsmd.getColumnCount();i++) {
				colStr = colStr + rsmd.getColumnName(i) + "\t";
			}
			while(rs.next()){
				for(int i=1; i<=rsmd.getColumnCount();i++) {
					resultStr = resultStr + rs.getString(i) + "\t";
				}
				resultStr = resultStr + "\n";
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return colStr + "\n\n" + resultStr;
	}

	

	@Override
	public String insertData(String strQry) throws RemoteException {
		try {
			setDBCon();
			System.out.println("Server Registered.");
			stmt=con.createStatement();
			int recordInserted=stmt.executeUpdate(strQry);
			if(recordInserted != 0)
			{
				resultStr="Record inserted successfully.";
			}
			else
			{
				resultStr="Record not Inserted successfully.";
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return resultStr;
	}

}