package com.ead.authuser.entity;

import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserEntity.class)
public abstract class UserEntity_ {

    public static volatile SingularAttribute<UserEntity, String> email;
    public static volatile SingularAttribute<UserEntity, UserStatus> userStatus;
    public static volatile SingularAttribute<UserEntity, UserType> userType;

//    public static volatile SingularAttribute<UserEntity, UUID> userId;
//    public static volatile SingularAttribute<UserEntity, String> userName;
//    public static volatile SingularAttribute<UserEntity, String> password;
//    public static volatile SingularAttribute<UserEntity, String> fullName;
//    public static volatile SingularAttribute<UserEntity, String> phoneNumber;
//    public static volatile SingularAttribute<UserEntity, String> cpf;
//    public static volatile SingularAttribute<UserEntity, String> imageUrl;
//    public static volatile SingularAttribute<UserEntity, LocalDateTime> creationDate;
//    public static volatile SingularAttribute<UserEntity, LocalDateTime> lastUpdateDate;
}