package com.revature.ams.Member;

import com.revature.ams.util.ConnectionFactory;
import com.revature.ams.util.exceptions.DataNotFoundException;
import com.revature.ams.util.interfaces.Crudable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MemberRepository implements Crudable<Member> {
    @Override
    public boolean update(Member updatedObject) {
        return false;
    }

    @Override
    public boolean delete(Member removedObject) {
        return false;
    }

    @Override
    public List<Member> findAll() {
        return List.of();
    }

    @Override
    public Member create(Member newObject) {
        return null;
    }

    @Override
    public Member findById(int number) {
        return null;
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
