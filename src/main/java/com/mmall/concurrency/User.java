package com.mmall.concurrency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author mcg
 * @Date 2019/4/14 11:35
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    String name;
    String id;
}
