package Assignment;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class JdbcAssessment {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int no=sc.nextInt();
	
		int Wno=sc.nextInt();
		sc.nextLine();
		String shift=sc.nextLine();
		
		Connection Con=null;
		CallableStatement cstmt=null;
		//Step1 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//step2 
		try {
			Con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","System","Admin#123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Step3
		try {
			cstmt=Con.prepareCall("{call InsertShift(?,?,?,?)}");
			cstmt.setInt(1, no);
			cstmt.setInt(2, Wno);
			cstmt.setString(3, shift);
			
			cstmt.registerOutParameter(4,Types.VARCHAR);
			cstmt.executeUpdate();
			System.out.println(cstmt.getString(4));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			try {
				Con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
