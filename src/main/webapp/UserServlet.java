import java.sql.SQLException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserServlet") 
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // Database connection parameters
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=UserDatabase"; 
    private static final String USER = "sa"; 
    private static final String PASS = "123456"; 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // Insert user data into the database
            String insertSQL = "INSERT INTO Users (Name, Email) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.executeUpdate();
            }
            // Display confirmation message
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h2>User " + name + " with email " + email + " has been successfully saved.</h2>");
            displayUsers(out, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }   
        }
    private void displayUsers(PrintWriter out, Connection conn) throws SQLException {
        String selectSQL = "SELECT * FROM Users";
        try (PreparedStatement pstmt = conn.prepareStatement(selectSQL);
             ResultSet rs = pstmt.executeQuery()) {
            out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Email</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("ID") + "</td><td>" + rs.getString("Name") + "</td><td>" + rs.getString("Email") + "</td></tr>");
            }
            out.println("</table>");
        }
    }
}
