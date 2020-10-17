package com.lening.shop.service;

import com.lening.pojo.TbSeller;
import com.lening.sellergoods.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @date： 2020/8/26
 * @author：Dream
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    private SellerService sellerService;

    public SellerService getSellerService() {
        return sellerService;
    }

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**
         * 用户权限的集合
         */
        List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
        /**
         * 权限是写死的，用户的是  roll_seller，准确的说是该用户的角色，这个可以按照用户名去查询
         */
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_SELLER"));

        /**
         * 单家的username就是商家的id  sellerId
         */
        TbSeller seller = sellerService.findOne(username);
        if (seller != null) {
            if (seller.getStatus().equals("1")) {
                return new User(username, seller.getPassword(), grantedAuths);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
