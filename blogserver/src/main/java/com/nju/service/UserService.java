package com.nju.service;

import com.nju.bean.Result;
import com.nju.bean.Role;
import com.nju.bean.User;
import com.nju.mapper.RolesMapper;
import com.nju.mapper.UserMapper;
import com.nju.utils.FileUtil;
import com.nju.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RolesMapper rolesMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(s);
        if (user == null) {
            //避免返回null，这里返回一个不含有任何值的User对象，在后期的密码比对过程中一样会验证失败
            return new User();
        }
        //查询用户的角色信息，并返回存入user中
        List<Role> roles = rolesMapper.getRolesByUid(user.getId());
        user.setRoles(roles);
        return user;
    }

    /**
     * @param user
     * @return 0表示成功
     * 1表示用户名重复
     * 2表示失败
     */
    public int reg(User user) {
        User loadUserByUsername = userMapper.loadUserByUsername(user.getUsername());
        if (loadUserByUsername != null) {
            return 1;
        }
        //插入用户,插入之前先对密码进行加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);//用户可用
        long result = userMapper.reg(user);
        //配置用户的角色，默认都是普通用户
        String[] roles = new String[]{"2"};
        int i = rolesMapper.addRoles(roles, user.getId());
        boolean b = i == roles.length && result == 1;
        if (b) {
            return 0;
        } else {
            return 2;
        }
    }

    public int updateUserEmail(String email) {
        return userMapper.updateUserEmail(email, Util.getCurrentUser().getId());
    }

    public List<User> getUserByNickname(String nickname) {
        List<User> list = userMapper.getUserByNickname(nickname);
        return list;
    }

    public List<Role> getAllRole() {
        return userMapper.getAllRole();
    }

    public int updateUserEnabled(Boolean enabled, Long uid) {
        return userMapper.updateUserEnabled(enabled, uid);
    }

    public int deleteUserById(Long uid) {
        return userMapper.deleteUserById(uid);
    }

    public int updateUserRoles(Long[] rids, Long id) {
        int i = userMapper.deleteUserRolesByUid(id);
        return userMapper.setUserRoles(rids, id);
    }

    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }


    //一些端口信息
    @Value("${server.port}")
    private String port = "8081";

    private static final String ip = "http://localhost";

    private static final String static_root = "D:/devplatform_files";
//    private static final String static_root = "/Users/mintao/Pictures";

    //@Override
    public Result uploadAvatar(String username, MultipartFile file) throws IOException {
        //获取原文件的名称
        String originalFilename = file.getOriginalFilename();
//      String rootFilePath = System.getProperty("user.dir")+"/src/main/resources/files/"+originalFilename;
        //获取到文件路径
        String rootFilePath = static_root +"/avatar/"+ originalFilename;
        //保存在文件中
        FileUtil.writeBytes(file.getBytes(),rootFilePath);
        //图片访问用到的url
        String avatar = ip+":"+port+"/avatar/"+originalFilename;
        try{
            //头像信息存入数据库
            userMapper.updateAvatar(avatar,username);
            //自己封装的Result结果返回类
            return Result.success(200,"上传成功",avatar);
        }catch (Exception e){
            System.out.println(e);
            return Result.fail("上传失败");
        }
    }

}
