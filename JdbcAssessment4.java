package Assignment;
import java.util.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

class Shift
{
	private int staffNo;
	private int WardNo;
	private String shift;
	
	public Shift(int staffNo, int wardNo, String shift) {
		super();
		this.staffNo = staffNo;
		WardNo = wardNo;
		this.shift = shift;
	}
	
	public int getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(int staffNo) {
		this.staffNo = staffNo;
	}
	public int getWardNo() {
		return WardNo;
	}
	public void setWardNo(int wardNo) {
		WardNo = wardNo;
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	
	public static void retriveByShift(List<Shift> l,String s1)
	{
		l.stream().filter(t->t.getShift().equals(s1)).forEach(t->System.out.println(t.getStaffNo()+" "+t.getWardNo()+" "+t.getShift()));
	}
	
	
}
public class JdbcAssessment4 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		List<Shift> list=new ArrayList<Shift>();
		System.out.println("Choose one of the below\n1.Morning \n2.AfterNoon");
		
		
		String s1=sc.nextLine();
		
		Connection Con=null;
		CallableStatement cstmt=null;
		ResultSet R=null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver error");
		}
		try {
			Con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Admin#123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection Error");
		}

		try
		{
			cstmt=Con.prepareCall("{call shiftExtract(?)}");
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.execute();
			R=(ResultSet)cstmt.getObject(1);
			while(R.next())
			{
				
				Shift s=new Shift(R.getInt(1),R.getInt(2),R.getString(3));
				list.add(s);
				
			}
		}
		catch(SQLException e)
		{
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
		
	
	Shift.retriveByShift(list, s1);
	
		
}

}
