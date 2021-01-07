package DAO;

import java.beans.PropertyVetoException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DaoFactory {

	//	ファクトリーメソッド

	/**
	 * ファクトリーでは接続を作っておいて、Daoに引き渡す
	 * @return
	 */
	public static UserDao createUserDao() {
		//		データソースを作る
		DataSource ds = getDataSource();
		//		引き渡すインスタンスを作る　データソースを受け取って
		//		dsをもとに接続を作っていく
		UserDao dao = new UserDaoImpl(ds);
		return dao;
	}

	/**
	 *
	 * @return NameListDao
	 */
	public static NameListDao createNameList() {
		DataSource ds = getDataSource();
		NameListDao dao = new NameListDaoImpl(ds);
		return dao;
	}

	/**
	 *
	 * @return ActionListDao
	 */
	public static ActionListDao createActionList() {
		DataSource ds = getDataSource();
		ActionListDao dao = new ActionListDaoImpl(ds);
		return dao;
	}


	/**
	 *
	 * @return NumbersDao
	 */
	public static NumbersDao createNumbersDao() {
		DataSource ds = getDataSource();
		NumbersDao dao = new NumbersDaoImpl(ds);
		return dao;
	}



	/**
	 *
	 * @return WordProfileDao
	 */
	public static WordProfileDao createWordProfileDao() {
		DataSource ds = getDataSource();
		WordProfileDao dao = new WordProfileDaoImpl(ds);
		return dao;
	}



	//データソースメソッド
	/**
	 * データソース=接続を作るメソッド
	 * このクラスのみで使うので、外部に出さない。
	 * ただし.createUserDao内で使うので、staticにする
	 */
	public static DataSource getDataSource() {


		var bundle = ResourceBundle.getBundle("environment");
		//c3p0の読み込み
		ComboPooledDataSource ds ;
		ds = new ComboPooledDataSource();

//		InitialContext ctx = null;
//		DataSource ds = null;



		try {

			ds.setDriverClass(bundle.getString("jdbc.driver"));
		}
		catch (PropertyVetoException e) {
			System.out.println("データベース接続でエラー");
			throw new RuntimeException(e);
		}

		ds.setJdbcUrl(bundle.getString("jdbc.url"));
		ds.setUser(bundle.getString("jdbc.user"));
		ds.setPassword(bundle.getString("jdbc.password"));

		//c3p0の設定を修正していく

		ds.setMaxIdleTime(3);
		ds.setInitialPoolSize(1);
		ds.setMaxPoolSize(5);
		ds.setCheckoutTimeout(5000);
		ds.setMaxIdleTimeExcessConnections(5);
		ds.setUnreturnedConnectionTimeout(5);



		return ds;
	}

}
