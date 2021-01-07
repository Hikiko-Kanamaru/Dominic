package DAO;

import java.beans.PropertyVetoException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

class ServiceDaoFactory {

	//	ファクトリーメソッド

	/**
	 * ファクトリーでは接続を作っておいて、Daoに引き渡す
	 * @return
	 */
	static WordBookDao createWordBookDao() {
		//		データソースを作る
		DataSource ds = getDataSource();
		//		引き渡すインスタンスを作る　データソースを受け取って
		//		dsをもとに接続を作っていく
		WordBookDao dao = new WordBookDaoImpl(ds) ;
		return dao;
	}


	/**
	 * ファクトリーでは接続を作っておいて、Daoに引き渡す
	 * @return
	 */
	static WordProfileDao createWordProfileDao() {
		//		データソースを作る
		DataSource ds = getDataSource();
		//		引き渡すインスタンスを作る　データソースを受け取って
		//		dsをもとに接続を作っていく
		WordProfileDao dao = new WordProfileDaoImpl(ds) ;
		return dao;
	}



	//データソースメソッド
	/**
	 * データソース=接続を作るメソッド
	 * このクラスのみで使うので、外部に出さない。
	 * ただし.createUserDao内で使うので、staticにする
	 */
	private static DataSource getDataSource() {


		var bundle = ResourceBundle.getBundle("environment");
		//c3p0の読み込み
		ComboPooledDataSource ds ;
		ds = new ComboPooledDataSource();

//		InitialContext ctx = null;
//		DataSource ds = null;



		try {

			ds.setDriverClass(bundle.getString("jdbc.driver"));

//			ctx = new InitialContext();
//			//			jdbc/shopping_dbは、サーバーのcontext.xmlに参照して利用する
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/dominic_db");
		}
//		catch (NamingException e) {
//			System.out.println("db接続できませんでした。");
//			if (ctx != null) {
//				try {
//					ctx.close();
//				} catch (NamingException e1) {
//					System.out.println("ここに来てない３");
//					throw new RuntimeException(e1);
//				}
//			}
//			throw new RuntimeException(e);
//		}
		catch (PropertyVetoException e) {
			System.out.println("データベース接続でエラー");
			throw new RuntimeException(e);
		}

		ds.setJdbcUrl(bundle.getString("jdbc.url"));
		ds.setUser(bundle.getString("jdbc.user"));
		ds.setPassword(bundle.getString("jdbc.password"));

		return ds;
	}

}
