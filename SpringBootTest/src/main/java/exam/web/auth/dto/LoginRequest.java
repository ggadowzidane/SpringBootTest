package exam.web.auth.dto;

import lombok.Getter;
import lombok.Setter;

public class LoginRequest {

	private String loginId;
    private String loginPwd;
    
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

    
}
