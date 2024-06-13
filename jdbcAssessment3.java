package Assignment;
import java.sql.*;
import java.util.Scanner;
public class jdbcAssessment3 {
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement pst=null;
		Scanner s=new Scanner(System.in);
		System.out.println("Enter the staffNo");
		int sno=s.nextInt();
		s.nextLine();
		System.out.println("Enter the shift to be updtaed");
		String shift=s.nextLine();
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("driver error");
		}
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","Admin#123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("connection error");
		}
		try {
			pst=con.prepareStatement("update empShifts set shift=? where staffno=?");
			pst.setString(1, shift);
			pst.setInt(2, sno);
			int res=pst.executeUpdate();
			System.out.println(res+" row updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
