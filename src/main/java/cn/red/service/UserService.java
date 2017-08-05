package cn.red.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.red.mapper.UserMapper;
import cn.red.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public User getProfile(int sessionUid, int uid) {
		User user = userMapper.selectUserByUid(uid);
		
		return user;
	}
	
	public User getEditInfo(int uid) {
		return userMapper.selectEditInfo(uid);
	}

	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

	public String updatePassword(String password, String newpassword, String repassword, int sessionUid) {
		String oldPassword = userMapper.selectPasswordByUid(sessionUid);
        if(!oldPassword.equals(password)){
            return "原密码输入错误~";
        }

        if(newpassword.length() < 6 || newpassword.length() > 20){
            return "新密码长度要在6~20之间~";
        }

        if(!newpassword.equals(repassword)){
            return "新密码两次输入不一致~";
        }

        userMapper.updatePassword(newpassword,sessionUid);
        return "ok";
	}
	
	public void updateHeadUrl(int uid, String headUrl) {
        userMapper.updateHeadUrl(uid,headUrl);
    }
	
}