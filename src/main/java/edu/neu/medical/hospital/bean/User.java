package edu.neu.medical.hospital.bean;

public class User {
    private Integer id;

    private String loginName;

    private String password;

    private String trustName;

    private Integer departmentId;

    private String userCategory;

    private String professionalTitle;

    private String info;

    public User(Integer id, String loginName, String password, String trustName, Integer departmentId, String userCategory, String professionalTitle, String info) {
        this.id = id;
        this.loginName = loginName;
        this.password = password;
        this.trustName = trustName;
        this.departmentId = departmentId;
        this.userCategory = userCategory;
        this.professionalTitle = professionalTitle;
        this.info = info;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getTrustName() {
        return trustName;
    }

    public void setTrustName(String trustName) {
        this.trustName = trustName == null ? null : trustName.trim();
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(String userCategory) {
        this.userCategory = userCategory == null ? null : userCategory.trim();
    }

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle == null ? null : professionalTitle.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }
}