package com.mmall.concurrency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author mcg
 * @Date 2019/4/14 11:35
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListForm {

    private List<User> userList;

}
