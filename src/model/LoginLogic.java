package model;

import java.util.Objects;

import DAO.DaoFactory;
import domain.LoginData;

public class LoginLogic {

	public LoginData execute(String name, String pass) {
		LoginData answer = new LoginData();

		if (Objects.nonNull(name) && Objects.nonNull(pass) && name != "" && pass != "") {

			var UserDao = DaoFactory.createUserDao();

			try {
				answer = UserDao.findByLoginIdAndLoginPass(name, pass);
			} catch (Exception e) {
				System.out.println("サーバー系のエラーです");
				e.printStackTrace();
				return answer;
			}


		}else {
			System.out.println("入力の何かがダメでした");

		}

		return answer;
	}

}
