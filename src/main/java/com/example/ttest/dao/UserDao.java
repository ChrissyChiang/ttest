//package com.example.ttest.dao;
//
//import com.example.ttest.model.UserEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.List;
//
//@Component
//public class UserDao {
//
//    @Autowired
//    private NamedParameterJdbcTemplate np;
//
//    public UserEntity getUserById(Integer userId) {
//        String sql = "SELECT name FROM users WHERE id = :userId";
//
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("userId", userId);
//
//        List<UserEntity> userList = np.query(sql, map, new UserRowMapper());
//
//        if (userList.size() > 0) {
//            return userList.get(0);
//        } else {
//            return null;
//        }
//    }//getUserById
//
//    public UserEntity getUserByUserName(String userName) {
////未完
//        return null;
//    }//getUserByUserName
//
//}
//
