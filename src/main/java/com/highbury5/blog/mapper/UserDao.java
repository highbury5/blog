package com.highbury5.blog.mapper;

import com.highbury5.blog.model.User;

public interface UserDao {
    public User findUser(int id);

    public User findUserByName(String name);

    public void insertUser(User user);
}
