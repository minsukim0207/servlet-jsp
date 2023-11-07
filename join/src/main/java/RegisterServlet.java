

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// JDBC �ҷ�����
		
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String jdbcUsername = "KHCAFE";
		String jdbcPassword = "KHCAFE";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		
			//�ְ����ϴ� DB �� ����
			/*
			 * CREATE TABLE MemberInfo (
				    MNO INT PRIMARY KEY,
				    MName VARCHAR(50),
				    MEmail VARCHAR(100),
				    MBirth  DATE
			);*/
			   int mno = Integer.parseInt(request.getParameter("mno"));
	            String mname = request.getParameter("mname");
	            String memail = request.getParameter("memail");
	            String mbirth = request.getParameter("mbirth");
			
			//ȸ������ insert 
	            String sql = "INSERT INTO MemberInfo (MNO, MName, MEmail, MBirth) VALUES (?, ?, ?, ?)";
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setInt(1, mno);
	            preparedStatement.setString(2, mname);
	            preparedStatement.setString(3, memail);
	            preparedStatement.setDate(4, java.sql.Date.valueOf(mbirth));

	            preparedStatement.executeUpdate();
			
			//���� ������ ��� ȸ�� ������ ���ǿ� ����
			//���� ��ü�� ���� Ŭ���̾�Ʈ�� �������� ������ �����͸� �����ϰ�
			//�����ϴ� �۾��� ����
			//session "mno"��� �̸����� �����͸� �����ϴ� ����
			//request : ���� Ŭ���̾�Ʈ�� ��û�� ���� ������ �����ϴ� ������ ��
			//session �̶� ? ���̳� ���ø����̼� ���¸� �����ϰ� �����͸� �����ϱ� ���� ���
			//getSession() : request���� ���� ������ ������ ��
			//setAttribute("mno", mno) : ���ǿ� �����͸� �����ϴ� �޼���
			//"mno" �����͸� �����ϰ�, "mno" ���� ���� �ش� �����ͷ� �����ǰ� ��
			//�̷��� ����� �����ʹ� �ٸ� �������̳� jsp ���������� ���� ������
	            request.getSession().setAttribute("mno", mno);
	            request.getSession().setAttribute("mname", mname);
	            request.getSession().setAttribute("memail", memail);
	            request.getSession().setAttribute("mbirth", mbirth);
		
			//������ ��� �̵��� ������ �������ְ� �ٽ� ����
			response.sendRedirect("register_success.jsp");
			
		} catch (SQLException e) {
			// ������ ��� �̵��� ������ ����
			response.sendRedirect("register_error.jsp");
			e.printStackTrace();
		}
	}

}