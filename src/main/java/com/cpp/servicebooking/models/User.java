package com.cpp.servicebooking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Username needs to be an email")
    @NotBlank(message = "username is required")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Password field is required")
    private String password;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "userInfo_id")
    private UserInfo userInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "serviceprovide_id")
    private ServiceProvide serviceProvide;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<RequestOrder> requestOrders = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user", cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<OrderRequest> orderRequests = new HashSet<>();

    private Date create_At;
    private Date update_At;

    public User(){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Date getCreate_At() {
        return create_At;
    }

    public void setCreate_At(Date create_At) {
        this.create_At = create_At;
    }

    public Date getUpdate_At() {
        return update_At;
    }

    public void setUpdate_At(Date update_At) {
        this.update_At = update_At;
    }

    public String getFullName() {
        return userInfo.getFirstname() + " " + userInfo.getLastname();
    }

    public ServiceProvide getServiceProvide() {
        return serviceProvide;
    }

    public void setServiceProvide(ServiceProvide serviceProvide) {
        this.serviceProvide = serviceProvide;
    }

    public List<RequestOrder> getRequestOrders() {
        return requestOrders;
    }

    public void setRequestOrders(List<RequestOrder> requestOrders) {
        this.requestOrders = requestOrders;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @PrePersist
    protected void onCreate(){
        this.create_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.update_At = new Date();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        //list.add(new SimpleGrantedAuthority(roleStr));

        return list;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
