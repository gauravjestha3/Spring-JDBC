package com.assisgnment.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.assisgnment.model.Address;
import com.assisgnment.model.Modelview;
import com.assisgnment.model.ViewModel;

@Repository
public class Repo {
	private static Connection connection = null;
	private static Statement statement = null;

	private static final Logger log = LoggerFactory.getLogger(Repo.class);
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3307/employee?verifyServerCertificate=false&useSSL=true&";
	private static final String USER = "root";
	private static final String PASS = "jesu";

	public ArrayList<Modelview> getAllEmployee() {

		Modelview modelview = null;
		ArrayList<Modelview> employeeList = new ArrayList<>();

		try {
			Class.forName(JDBC_DRIVER);

			log.info("Connecting to database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);

			log.info("Creating statement...");
			statement = connection.createStatement();

			log.info("Getting Data...");
			String sql = "SELECT * FROM user,address";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				modelview = new Modelview();
				Address address=new Address();
				
				modelview.setId(resultSet.getString("id"));
				modelview.setFirstname(resultSet.getString("firstname"));
				modelview.setLastname(resultSet.getString("lastname"));
				modelview.setPassword(resultSet.getString("password"));
				// modelview.setAddress(resultSet.());
			address.setHouseno(resultSet.getString("houseno"));
				address.setStreet(resultSet.getString("street"));
				address.setCity(resultSet.getString("city"));
				address.setAddressid(resultSet.getString("addressid"));
				
				modelview.setDate(resultSet.getDate("date"));
				modelview.setAddress(address);
				employeeList.add(modelview);

			}
			resultSet.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			System.out.println(se.getMessage());

			se.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {

			log.info("Closing the connection");
			try {
				statement.close();
				connection.close();
			} catch (SQLException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		}
		log.info("Goodbye!");
		return employeeList;
	}

	public Modelview saveEmp(ViewModel viewmodel) {
		Address address=viewmodel.getAddress();
		String response = null;
		try {
			Class.forName(JDBC_DRIVER);

			log.info("Connecting to database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);

			log.info("Creating statement...");
			statement = connection.createStatement();
		
			log.info("Saving Data...");
			
				String query = "INSERT INTO user VALUES ('"
						+viewmodel.getId()+"','"
						+viewmodel.getFirstname()+"','"
						+viewmodel.getLastname()+"','"
						+viewmodel.getPassword()+"','"
						+viewmodel.getDate()+"')";
				
				String addressquery = "INSERT INTO address VALUES ('"
						+address.getHouseno()+"','"
						+address.getStreet()+"','"
						+address.getCity()+"','"
						+address.getAddressid()+"')";
				
				Class.forName("com.mysql.jdbc.Driver");
				
				connection = DriverManager.getConnection(DB_URL, USER, PASS);
				statement = connection.createStatement();
				 int value = statement.executeUpdate(query);
				
				statement = connection.createStatement();
				value = statement.executeUpdate(addressquery);
		}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				
					try {
						statement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					
			}
		
			Modelview modelview = new Modelview();
			modelview.setId(viewmodel.getId());
			modelview.setFirstname(viewmodel.getFirstname());
			modelview.setLastname(viewmodel.getLastname());
			modelview.setPassword(viewmodel.getPassword());
			modelview.setDate(viewmodel.getDate());
			address.setHouseno(viewmodel.getAddress().getHouseno());
			address.setStreet(viewmodel.getAddress().getStreet());
			address.setCity(viewmodel.getAddress().getCity());
			address.setAddressid(viewmodel.getAddress().getAddressid());
			
			modelview.setAddress(address);
		return modelview;	
			
		
		}
		
	public ArrayList<Modelview> searchEmployee(String firstName) {

		Modelview modelview = null;
		ArrayList<Modelview> employeList = new ArrayList<>();

		try {
			Class.forName(JDBC_DRIVER);

			log.info("Connecting to database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);

			log.info("Creating statement...");
			statement = connection.createStatement();

			log.info("Getting Data...");
//			String sql = "SELECT * FROM user where firstname='"+firstName+"';";
			String sql="select * from  (select  user.Id,user.firstName,user.lastName,user.password,user.date,"
					+" address.houseno,"
					+ "address.street,address.city,address.addressid from user inner join address on user.id=address.addressid) as userdata where firstname='"+firstName+"';";
			
				
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				modelview = new Modelview();
				Address address=new Address();
				
				modelview.setId(resultSet.getString("id"));
				modelview.setFirstname(resultSet.getString("firstname"));
				modelview.setLastname(resultSet.getString("lastname"));
				modelview.setPassword(resultSet.getString("password"));
				// modelview.setAddress(resultSet.());
			address.setHouseno(resultSet.getString("houseno"));
				address.setStreet(resultSet.getString("street"));
				address.setCity(resultSet.getString("city"));
				address.setAddressid(resultSet.getString("addressid"));
				
				modelview.setDate(resultSet.getDate("date"));
				modelview.setAddress(address);
				employeList.add(modelview);


			}
			resultSet.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			System.out.println(se.getMessage());

			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {

			log.info("Closing the connection");
			try {
				statement.close();
				connection.close();
			} catch (SQLException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		}
		log.info("Goodbye!");
		return employeList;
	}

	public ArrayList<Modelview> searchID(String id) {

		Modelview modelview = null;
		ArrayList<Modelview> employList = new ArrayList<>();

		try {
			Class.forName(JDBC_DRIVER);

			log.info("Connecting to database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);

			log.info("Creating statement...");
			statement = connection.createStatement();

			log.info("Getting Data...");
			String sql="select * from  (select  user.Id,user.firstName,user.lastName,user.password,user.date,"
					+" address.houseno,"
					+ "address.street,address.city,address.addressid from user inner join address on user.id=address.addressid) as userid where id='"+id+"';";
			
//			String sql = "SELECT * FROM user where id='"+id+"';";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				modelview = new Modelview();
				Address address=new Address();
				
				modelview.setId(resultSet.getString("id"));
				modelview.setFirstname(resultSet.getString("firstname"));
				modelview.setLastname(resultSet.getString("lastname"));
				modelview.setPassword(resultSet.getString("password"));
				// modelview.setAddress(resultSet.());
			address.setHouseno(resultSet.getString("houseno"));
				address.setStreet(resultSet.getString("street"));
				address.setCity(resultSet.getString("city"));
				address.setAddressid(resultSet.getString("addressid"));
				
				modelview.setDate(resultSet.getDate("date"));
				modelview.setAddress(address);
				employList.add(modelview);

			
			}
			resultSet.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			System.out.println(se.getMessage());

			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {

			log.info("Closing the connection");
			try {
				statement.close();
				connection.close();
			} catch (SQLException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		}
		log.info("Goodbye!");
		return employList;
	}

}
