package com.iscolt.micm.provider.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/9
 * @see: com.iscolt.micm.provider.entity
 * @version: v1.0.0
 */
@Data
@Entity
@Table(name = "tenant_user_info")
public class TenantUserInfo implements Serializable {
    private static final long serialVersionUID = -7597163662994061489L;
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "tenant_id")
    private int tenantId;
    @Basic
    @Column(name = "organization_id")
    private long organizationId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "sex")
    private String sex;
    @Basic
    @Column(name = "age")
    private Integer age;
    @Basic
    @Column(name = "birthday")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp birthday;
    @Basic
    @Column(name = "highest_of_education")
    private String highestOfEducation;
    @Basic
    @Column(name = "level")
    private String level;
    @Basic
    @Column(name = "country")
    private String country;
    @Basic
    @Column(name = "province")
    private String province;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "county")
    private String county;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "id_number")
    private String idNumber;
    @Basic
    @Column(name = "id_card_positive")
    private String idCardPositive;
    @Basic
    @Column(name = "id_cart_back")
    private String idCartBack;
    @Basic
    @Column(name = "nation")
    private String nation;
    @Basic
    @Column(name = "native_place")
    private String nativePlace;
    @Basic
    @Column(name = "ename")
    private String ename;
    @Basic
    @Column(name = "marital_status")
    private String maritalStatus;
    @Basic
    @Column(name = "photo")
    private String photo;
    @Basic
    @Column(name = "zodiac")
    private String zodiac;
    @Basic
    @Column(name = "constellation")
    private String constellation;
    @Basic
    @Column(name = "blood_type")
    private String bloodType;
    @Basic
    @Column(name = "demicile")
    private String demicile;
    @Basic
    @Column(name = "political_outlook")
    private String politicalOutlook;
    @Basic
    @Column(name = "time_to_join_the_party")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp timeToJoinTheParty;
    @Basic
    @Column(name = "qq")
    private String qq;
    @Basic
    @Column(name = "wechat")
    private String wechat;
    @Basic
    @Column(name = "now_city")
    private String nowCity;
    @Basic
    @Column(name = "emergency_contact")
    private String emergencyContact;
    @Basic
    @Column(name = "emergency_contact_number")
    private Integer emergencyContactNumber;
    @Basic
    @Column(name = "bank_card_number")
    private Integer bankCardNumber;
    @Basic
    @Column(name = "opening_bank")
    private String openingBank;
    @Basic
    @Column(name = "educational_type")
    private String educationalType;
    @Basic
    @Column(name = "graduate_school")
    private String graduateSchool;
    @Basic
    @Column(name = "enrolment_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp enrolmentTime;
    @Basic
    @Column(name = "graduation_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp graduationTime;
    @Basic
    @Column(name = "major")
    private String major;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "credit")
    private Integer credit;
    @Basic
    @Column(name = "integral")
    private Integer integral;
}
