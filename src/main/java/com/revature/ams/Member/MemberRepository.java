package com.revature.ams.Member;

import com.revature.ams.util.ConnectionFactory;
import com.revature.ams.util.exceptions.DataNotFoundException;
import com.revature.ams.util.exceptions.InvalidInputException;
import com.revature.ams.util.interfaces.Crudable;

import java.sql.*;
import java.util.List;

public class MemberRepository implements Crudable<Member> {
    @Override
    public boolean update(Member updatedMember) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "update members set first_name = ?, last_name = ?, email = ?, password = ? where member_id = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, updatedMember.getFirstName());
            preparedStatement.setString(2, updatedMember.getLastName());
            preparedStatement.setString(3, updatedMember.getEmail());
            preparedStatement.setString(4, updatedMember.getPassword());
            preparedStatement.setInt(5, updatedMember.getMemberId());

            int checkInsert = preparedStatement.executeUpdate();

            if (checkInsert == 0) {
                throw new DataNotFoundException("Member ID " + updatedMember.getMemberId() + " does not exist. Please double check.");
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Member removedMember) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "delete from members where member_id = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, removedMember.getMemberId());

            int checkInsert = preparedStatement.executeUpdate();

            if (checkInsert == 0) {
                throw new DataNotFoundException("Member with id " + removedMember.getMemberId() + " does not exist. Please double check");
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Member> findAll() {
        return List.of();
    }

    @Override
    public Member create(Member newMember) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "insert into members(first_name, last_name, email, member_type, password) values (?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, newMember.getFirstName());
            preparedStatement.setString(2, newMember.getLastName());
            preparedStatement.setString(3, newMember.getEmail());
            preparedStatement.setObject(4, newMember.getType(), Types.OTHER);
            preparedStatement.setString(5, newMember.getPassword());

            int checkInsert = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (checkInsert == 0 || !resultSet.next()) {
                throw new InvalidInputException("Something was wrong when entering " + newMember + " into the database");
            }

            newMember.setMemberId(resultSet.getInt(1));
            return newMember;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Member findById(int memberId) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "select * from members where member_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, memberId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new DataNotFoundException("No member with that info found");
            }

            Member member = new Member();

            member.setMemberId(resultSet.getInt("member_id"));
            member.setFirstName(resultSet.getString("first_name"));
            member.setLastName(resultSet.getString("last_name"));
            member.setEmail(resultSet.getString("email"));
            member.setType(resultSet.getString("member_type"));

            return member;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Member findByEmailAndPassword(String email, String password) {
        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){
            String sql = "select * from members where email = ? and password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()){
                throw new DataNotFoundException("No member with that info found");
            }

            Member member = new Member();

            member.setMemberId(resultSet.getInt("member_id"));
            member.setFirstName(resultSet.getString("first_name"));
            member.setLastName(resultSet.getString("last_name"));
            member.setEmail(resultSet.getString("email"));
            member.setType(resultSet.getString("member_type"));

            return member;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
