import java.sql.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

class Library {

    int finePerDay = 5;

    // ===== USER =====

    void addUser(User u) {
        try {
            Connection con = DBConnection.getConnection();
            String q = "INSERT INTO users VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(q);

            ps.setInt(1, u.userId);
            ps.setString(2, u.name);
            ps.executeUpdate();

            System.out.println("User added!");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void showUsers() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM users");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("user_id") + " | " +
                    rs.getString("name")
                );
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===== BOOK =====

    void addBook(Book b) {
        try {
            Connection con = DBConnection.getConnection();
            String q = "INSERT INTO books (id,title,author) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(q);

            ps.setInt(1, b.id);
            ps.setString(2, b.title);
            ps.setString(3, b.author);

            ps.executeUpdate();
            System.out.println("Book added!");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void showBooks() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM books");

            while (rs.next()) {
                String status = rs.getBoolean("is_issued") ?
                    "Issued to User " + rs.getInt("issued_to_user_id") :
                    "Available";

                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("title") + " | " +
                    rs.getString("author") + " | " + status
                );
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===== ISSUE =====

    void issueBook(int bookId, int userId) {
        try {
            Connection con = DBConnection.getConnection();

            LocalDate issueDate = LocalDate.now();
            LocalDate dueDate = issueDate.plusDays(7);

            String q = "UPDATE books SET is_issued=true, issued_to_user_id=?, issue_date=?, due_date=? WHERE id=? AND is_issued=false";

            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, userId);
            ps.setDate(2, java.sql.Date.valueOf(issueDate));
            ps.setDate(3, java.sql.Date.valueOf(dueDate));
            ps.setInt(4, bookId);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Issued! Due: " + dueDate);
            else
                System.out.println("Book not available");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===== RETURN =====

    void returnBook(int bookId, Scanner sc) {
        try {
            Connection con = DBConnection.getConnection();

            String q = "SELECT due_date FROM books WHERE id=?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, bookId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                LocalDate dueDate = rs.getDate("due_date").toLocalDate();

                System.out.print("Enter return date (YYYY-MM-DD): ");
                LocalDate today = LocalDate.parse(sc.next());

                if (today.isAfter(dueDate)) {
                    long daysLate = ChronoUnit.DAYS.between(dueDate, today);
                    System.out.println("Fine: Rs " + (daysLate * finePerDay));
                }

                PreparedStatement ps2 = con.prepareStatement(
                    "UPDATE books SET is_issued=false, issued_to_user_id=NULL WHERE id=?"
                );
                ps2.setInt(1, bookId);
                ps2.executeUpdate();

                System.out.println("Returned!");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===== SEARCH =====

    void searchByTitle(String title) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM books WHERE title=?"
            );
            ps.setString(1, title);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
                System.out.println("Found: " + rs.getString("title"));
            else
                System.out.println("Not found");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    void searchByAuthor(String author) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM books WHERE author=?"
            );
            ps.setString(1, author);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
                System.out.println("Found: " + rs.getString("title"));
            else
                System.out.println("Not found");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}