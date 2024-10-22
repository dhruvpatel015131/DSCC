package electricity;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class DBOperationService extends UnicastRemoteObject implements ElectricityDBInf {
	Connection con;
	Statement stmt;
	ResultSet rs;
	ResultSetMetaData rsmd;
	String colStr, resultStr;

	public DBOperationService() throws RemoteException {
		super();
		con = null;
		stmt = null;
		rs = null;
		rsmd = null;
		colStr = "";
		resultStr = "";
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setDBCon() {
		try {
			String URL = "jdbc:mysql://localhost:3307/bill";
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, "root", "");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public String getData(String strQry) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			setDBCon();
			System.out.println("Server Registered.");
			stmt = con.createStatement();
			rs = stmt.executeQuery(strQry);
			rsmd = (ResultSetMetaData) rs.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				colStr = colStr + rsmd.getColumnName(i) + "\t";
			}
			while (rs.next()) {
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					resultStr = resultStr + rs.getString(i) + "\t";
				}
				resultStr = resultStr + "\n";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return colStr + "\n\n" + resultStr;
	}

	@Override
	public String insertData(String strQry) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			setDBCon();
			System.out.println("Server Registered.");
			stmt = con.createStatement();
			int recordInserted = stmt.executeUpdate(strQry);
			if (recordInserted != 0) {
				resultStr = "Record inserted successfully.";
			} else {
				resultStr = "Record not Inserted successfully.";
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return resultStr;
	}

}
