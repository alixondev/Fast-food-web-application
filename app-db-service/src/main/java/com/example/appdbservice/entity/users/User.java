package com.example.appdbservice.entity.users;

import com.example.appdbservice.entity.enums.LanguageEnum;
import com.example.appdbservice.entity.order.CourierOrder;
import com.example.appdbservice.entity.product.Attachment;
import com.example.appdbservice.entity.template.AbsLongEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "users")
@SQLDelete(sql = "update users set deleted = true where id = ?")
@Where(clause = "deleted=false")
public class User extends AbsLongEntity implements UserDetails {

    @Column(nullable = false)
    private String firstname;

    private String lastname;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @JoinColumn(name = "avatar_id", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private Attachment avatar;

    @Column(name = "avatar_id")
    private Long avatarId;

    @Enumerated(EnumType.STRING)
    private LanguageEnum language;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", insertable = false, updatable = false)
    private List<Address> defaultAddresses;

    @Column(name = "address_id")
    private Long addressId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    @OneToMany(mappedBy = "courier")
    private List<CourierOrder> orderList;

    private String password;

    private boolean enabled;
    private boolean credentialsNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;


    public User(String firstName, String lastname, String phoneNumber, LanguageEnum language, Role role) {
        this.firstname = firstName;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.language = language;
        this.role = role;
    }


    public User(String firstname, String lastname, String phoneNumber,Long addressId, Long avatarId, String password,LanguageEnum language,  boolean enabled, Role role ) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.addressId = addressId;
        this.avatarId = avatarId;
        this.password = password;
        this.language = language;
        this.enabled = enabled;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> grantedAuthorities = role.getPermissions()
                .stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.name()))
                .collect(Collectors.toList());
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
