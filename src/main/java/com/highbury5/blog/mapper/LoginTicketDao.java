package com.highbury5.blog.mapper;

import com.highbury5.blog.model.LoginTicket;
import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 *  @author highbury5
 *  @date  2017-11-22
 */

//@Mapper
public interface LoginTicketDao {
    /*String TABLE_NAME = "login_ticket";
    String INSERT_FIELD = "user_id,ticket,expired,status";
    String SELECT_FIELD = "id," + INSERT_FIELD;*/

    //@Insert({"insert into ", TABLE_NAME , " (", INSERT_FIELD, " ) values ( #{userId},#{ticket},#{expired},#{status})"})
    public void insertLoginTicket(LoginTicket loginTicket);

    //@Select({"select ", SELECT_FIELD, " from ", TABLE_NAME, "where ticket = #{ticket} ;"})
    public LoginTicket selectLoginTicketByTicket(String ticket);


}
