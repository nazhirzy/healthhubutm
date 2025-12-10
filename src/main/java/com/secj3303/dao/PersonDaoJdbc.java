package com.secj3303.dao;

import com.secj3303.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonDaoJdbc implements PersonDao{
   
    private final DataSource dataSource;

    @Autowired
    public PersonDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Person> findAll() {			//findAll
        List<Person> list = new ArrayList<>();
        String sql = "SELECT * FROM person ORDER BY id";
        //  code to complete …
        //
        try (Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) { 
                while(rs.next()){
                    Person person = new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("yob"), rs.getDouble("weight"), rs.getDouble("height"));
                    list.add(person);
            }      } catch (SQLException e) { e.printStackTrace();	}
        return list;
    }

    @Override
    public Person findById(int id) {			//findById
        String sql = "SELECT * FROM person WHERE id = ?";
	//code to complete
        Person p = null;
        try(
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()){
                    if (rs.next()) {
                        p = new Person();

                        p.setId(rs.getInt("id"));
                        p.setName(rs.getString("name"));
                    }
                }
            } catch (SQLException e) {e.printStackTrace();}

            return p;
            }

    @Override
    public int insert(Person person) {
        String sql =
            "INSERT INTO person (name, yob, weight, height) " +
            "VALUES (?, ?, ?, ?)";
         try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, person.getName());
            ps.setInt(2, person.getYob()); // or setObject(.., Types.INTEGER) if nullable
            ps.setDouble(3, person.getWeight());
            ps.setDouble(4, person.getHeight());

            int rows = ps.executeUpdate();
            System.out.println("add(): inserted rows = " + rows);
            return rows;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error in add()", e);
        }

    }

    @Override
    public void update(Person p) {
        // to complete
        String sql =
        "UPDATE person SET name=?, yob=?, age=?, weight=?, height=? " +
        "WHERE id=?";

    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)
    ) {
        ps.setString(1, p.getName());
        ps.setInt(2, p.getYob());
        ps.setInt(3, p.getAge());
        ps.setDouble(4, p.getWeight());
        ps.setDouble(5, p.getHeight());
        ps.setInt(6, p.getId());

        ps.executeUpdate();
    } catch (SQLException e) {e.printStackTrace();}
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM person WHERE id=?";
        // to complete
        try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {e.printStackTrace();}
    }

}