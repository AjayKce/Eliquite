package student.kce.erp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="student")
@Data
public class Student {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="id")
private int id;

@Column(name="admin_id")
private int adminId;

@Column(name="college_id")
private int collegeId;

@Column(name="staff_id")
private int staffId;

@Column(name="batch")
private String batch;

@Column(name="semester")
private String semester;

@Column(name="quota")
private String quota;

@Column(name="department")
private String department;

@Column(name="section")
private String section;

@Column(name="username")
private String username;

@Column(name="password")
private String password;

@Column(name="firstname")
private String firstname;

@Column(name="lastname")
private String lastname;

@Column(name="gender")
private String gender;

@Column(name="email")
private String email;

@Column(name="religion")
private String religion;

@Column(name="bloodgroup")
private String bloodgroup;

@Column(name="nationality")
private String nationality;

@Column(name="date_of_birth")
private String dateOfBirth;

@Column(name="phone")
private String phone;

@Column(name="rollno")
private String rollno;

@Column(name="accomodation")
private String accomodation;

@Column(name="father_name")
private String fatherName;

@Column(name="father_phone")
private String fatherPhone;

@Column(name="mother_name")
private String motherName;

@Column(name="mother_phone")
private String motherPhone;

@Column(name="guardian_name")
private String custodianName;

@Column(name="guardian_phone")
private String custodianPhone;

@Column(name="account_number")
private String accountNumber;

@Column(name="ifsc_code")
private String ifscCode;

@Column(name="account_holder")
private String accountHolder;

@Column(name="registered_phone")
private String registeredPhone;

@Column(name="pancard_number")
private String pancardNumber;

@Column(name="aadhar_number")
private String aadharNumber;

@Column(name="whatsapp_account")
private String whatsappAccount;

@Column(name="facebook_account")
private String facebookAccount;

@Column(name="linkedin_account")
private String linkedinAccount;

@Column(name="github_account")
private String githubAccount;

@Column(name="hackerrank_account")
private String hackerrankAccount;

@Column(name="hackerearth_account")
private String hackerearthAccount;

@Column(name="codechef_account")
private String codechefAccount;

@Column(name="instagram_account")
private String instagramAccount;

@Column(name="twitter_account")
private String twitterAccount;

@Column(name="state")
private String state;

@Column(name="district")
private String district;

@Column(name="present_address1")
private String presentAddress1;

@Column(name="present_address2")
private String presentAddress2;

@Column(name="present_address3")
private String presentAddress3;

@Column(name="permanent_address1")
private String permanentAddress1;

@Column(name="permanent_address2")
private String permanentAddress2;

@Column(name="permanent_address3")
private String permanentAddress3;
}
