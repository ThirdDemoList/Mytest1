package com.demo.mark.mytest1;

/**
 * 作者：mark on 2017/7/8 11:32
 * <p>
 * 邮箱：2285581945@qq.com
 */
public class Userbean {
    private String username;
    private String imageurl;

    public Userbean(String username, String imageurl) {
        this.username = username;
        this.imageurl = imageurl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
