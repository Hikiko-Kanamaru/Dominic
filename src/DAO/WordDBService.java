package DAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.WordBookDomain;
import domain.WordJoinDomain;
import domain.WordProfileDomain;
import domain.WordSet;

public class WordDBService {


	private WordBookDao wBook;
	private WordProfileDao  wProf;
	final private int userId;
	boolean setFlag = false;



	{
		wBook = ServiceDaoFactory.createWordBookDao();
		wProf = ServiceDaoFactory.createWordProfileDao();
	}


//	単語と数値とテキストを受け取って保存するもの
//	が取り敢えずあればいいや
//
//	単語を削除するラッパーも必要
//	ユーザーリストの方から削除すればいいだけ
//
//	他に必要なのは単語リスト返すものが必要かな
//	つまりDAOを作って
//	API化する必要がある
//	その際にユーザーIDを持っていると危険なので注意する
//	単語IDは外に出しても大丈夫

//コンストラクタ

	/**
	 * @param userId
	 */
	public WordDBService(int userId) {
		super();
		this.userId = userId;
		if(userId > 1 ) {
			setFlag = true;
		}

	}


	/**
	 * UserIdが１の場合は、セッターを無効化する処理をする。
	 * 毎回処理を書くのがめんどくさいのでフラグ化する
	 */
	public WordDBService() {
		super();
		userId = 1;
	}


//メソッド


//	単語と数値とテキストを受け取って保存するもの
//	が取り敢えずあればいいや
//	保存したものを返したほうがいいから、apiでWordSetだけあればいいそうなのでWsを返す

	/**
	 * 値のチェックはここではやらない
	 * 正しい値が入っているものとして処理する
	 */
	public WordSet dbSet(WordSet ws) throws Exception {

		WordSet inWS = new WordSet();
		boolean newType = false;


//		登録していいかフラグを確認する
		if(!(setFlag)) {
			return inWS;
		}

		WordProfileDomain profile =new WordProfileDomain(-1, ws.getWord(), ws.getNumber(), ws.getAnswertext(), "未設定");
		WordProfileDomain checkProfile =new WordProfileDomain(false);


//		profileに登録されているかを確認する
		checkProfile = wProf.findByNotid(profile);
		if(checkProfile.getId()==-1) {
//		profileが存在しなかったら新規でプロファイルを作る
//		新規フラグを立てる
			setFlag = true;
//プロファイルを読み込む
			checkProfile = wProf.insertorUpdate(profile);
		}

		WordBookDomain book = new WordBookDomain(false);

		book =  wBook.findByIdAndUserId(checkProfile.getId(), userId);

//		新規フラグが折れていたらbookを確認する
//		bookに登録されていなかったり、フラグが立っていたら、新規登録する。
		if( newType || book.getID() == -1) {
			WordBookDomain inbook = new WordBookDomain(checkProfile.getId(), userId, -1,ws.getAnswertext());
			book = wBook.insertorUpdate(inbook);

		}else {
//		bookの中に登録されていたらbookを上書きする

			book.setText(ws.getAnswertext());
			book.setMemoryLevel(book.getMemoryLevel()+1);
			book = wBook.insertorUpdate(book);
		}


		inWS = new WordSet(checkProfile.getValue(), checkProfile.getWord(), book.getText(),checkProfile.getId());

		return inWS;

	}

	//ワードセットから、テキストデ➖タを得るメソッド
	public WordSet dbGet(WordSet wordset) {
		WordSet ws = new WordSet();

		WordProfileDomain domain = new WordProfileDomain(-1, wordset.getWord(),wordset.getNumber(), "", "");
		try {
			var inProf = wProf.findByNotid(domain);
			ws = dbGet(inProf.getId());
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


		return ws;
	}

	//IDから、テキストデ➖タを得るメソッドどちらも必要
public WordSet dbGet(int profId) {
	WordSet ws = new WordSet();
	try {
		var inProf = wProf.findByid(profId);
		var inBook = wBook.findByIdAndUserId(inProf.getId(), userId);
		ws = new WordSet(inProf.getValue(), inProf.getWord(), inBook.getText(),profId);
	} catch (Exception e) {
		System.out.println("wordDBService_dbGetでサーバーエラー");
		e.printStackTrace();
	}

	return ws;
	}

/**
 * profIdとユーザーIDから、bookdomainを返す
 * 自分のリストにそのIDが存在するかの確認用
 * @param profId
 * @return
 */
public WordBookDomain dbWordBookDomainGet(int profId) {
	WordBookDomain wbd = new WordBookDomain(false);
	try {
		wbd = wBook.findByIdAndUserId(profId, userId);

	} catch (Exception e) {
		System.out.println("dbWordBookDomainGetでサーバーエラー");
		e.printStackTrace();
	}

	return wbd;
	}






//ワードセットを削除するメソッド
	public boolean dbDelete(WordSet wordset) throws Exception {
		boolean answer =false ;

		WordProfileDomain profDomain = new WordProfileDomain(-1, wordset.getWord(), wordset.getNumber(), wordset.getAnswertext(), "");

		profDomain = wProf.findByNotid(profDomain);

		WordBookDomain bookDomain = wBook.findByIdAndUserId(profDomain.getId(), userId);

		if(bookDomain.getID() != -1) {
			wBook.delete(bookDomain);
			System.out.println("WordServlec削除実行できました");
			answer = true;
		}else {
			System.out.println("WordServlec削除に失敗しました");
		}
		return answer;
	}



//	検索結果を返すためのメソッド
//	serchAPIで利用するためのもの
//	検索時にわかるのは、数値と単語とuseridです。
	/**
	 * 検索結果がない場合,wordSetのIDが-1になります。
	 * @param userID
	 * @param number
	 * @param word
	 * @return　WordSet
	 */
	public WordSet SerchText(int userID,int number,String word) {
		WordSet ws = new WordSet();
		WordProfileDomain domain  = new WordProfileDomain(-1, word, number, "", ""); ;
		WordBookDomain bookDomain = new WordBookDomain(false);
		//		まずは、単語と数値でプロファイルを探す
		try {
			domain = wProf.findByNotid(domain);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			System.out.println("SerchText検索Profに失敗しました");
			e.printStackTrace();
		}
		if(domain.getId() != -1) {
//		プロファイルとidをもとにbookを探す
			try {
				bookDomain = wBook.findByIdAndUserId(domain.getId(), userID);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				System.out.println("SerchText検索wBookに失敗しました");
				e.printStackTrace();
			}

			if(bookDomain.getID() != -1) {
				System.out.println("SerchText検索データが発見されました");
				ws = new WordSet(domain.getValue(),domain.getWord() , bookDomain.getText());

			}

		}


		return ws;
	}

//	検索結果を返すためのメソッド
//	serchAPIで利用するためのもの
//	検索時にわかるのは、数値と単語とuseridです。
	/**
	 * 検索結果がない場合,wordSetのIDが-1になります。
	 * @param userID
	 * @param number
	 * @param word
	 * @return　WordSet
	 */
	public WordSet SerchText(int userID,int number,String word,Connection con) {
		WordSet ws = new WordSet();
		WordProfileDomain domain  = new WordProfileDomain(-1, word, number, "", ""); ;
		WordBookDomain bookDomain = new WordBookDomain(false);
		//		まずは、単語と数値でプロファイルを探す
		try {
			domain = wProf.findByNotid(domain,con);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			System.out.println("SerchText検索Profに失敗しました");
			e.printStackTrace();
		}
		if(domain.getId() != -1) {
//		プロファイルとidをもとにbookを探す
			try {
				bookDomain = wBook.findByIdAndUserId(domain.getId(), userID,con);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				System.out.println("SerchText検索wBookに失敗しました");
				e.printStackTrace();
			}

			if(bookDomain.getID() != -1) {
				System.out.println("SerchText検索データが発見されました");
				ws = new WordSet(domain.getValue(),domain.getWord() , bookDomain.getText());

			}

		}


		return ws;
	}

//		profIDとuserIDでbookを削除する機能。登録はprofのみで良い
	/**
	 * wordProfを削除するメソッド
	 * @param profID
	 * @return
	 */
	public boolean dbBookDelete(int profID) {
		boolean answer = false;
		if(setFlag) {
//		ユーザーIDが存在して削除して良い状態
//			profが存在するか確認する
			try {
				WordBookDomain wbd = wBook.findByIdAndUserId(profID, userId);
//			削除する
				if(wbd.getID()!= -1) {
					answer = wBook.delete(wbd);
				}

			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				System.out.println("WordDBService_Deleteでサーバーエラー");
				e.printStackTrace();
			}

		}

		return answer;
	}


//	ユーザーIDからBookの登録データを得るメソッド
	/**
	 * 	 * ユーザーIDで検索し
	 * wordprofidがkeyのmapが得られる
	 * returns:
	 * 	key:id
	 * 	value:WordBookDomain
	 */
	public Map<Integer, WordBookDomain> bookFindByid(){

	Map<Integer, WordBookDomain> answer  = new HashMap<Integer, WordBookDomain>();
	try {
		answer = wBook.findByUserID(userId);
	} catch (Exception e) {
		System.out.println("bookFindByidでサーバーエラー");
		e.printStackTrace();
	}
	return answer;

	}






	public List<WordJoinDomain> getWordJoinList(){

		List<WordJoinDomain> answer = new ArrayList<>();

		try {
			answer = wBook.findWordJoinByUserID(userId);
		} catch (Exception e) {
			System.out.println("getWordJoinListでサーバーエラー");
			e.printStackTrace();
		}

		return  answer;
	}


	public WordJoinDomain getWordJoinDomain(int profId){

		WordJoinDomain answer = new WordJoinDomain(false) ;

		try {
			answer = wBook.findWordJoinByUserIDandProfID(userId, profId);
		} catch (Exception e) {
			System.out.println("getWordJoinListでサーバーエラー");
			e.printStackTrace();
		}

		return  answer;
	}




//ゲッターセッター
	/**
	 * @return wBook
	 */
	public WordBookDao getwBook() {
		return wBook;
	}


	/**
	 * @return wProf
	 */
	public WordProfileDao getwProf() {
		return wProf;
	}


	/**
	 * @return userId
	 */
	public int getUserId() {
		return userId;
	}


	/**
	 * @param wBook セットする wBook
	 */
	public void setwBook(WordBookDao wBook) {
		this.wBook = wBook;
	}


	/**
	 * @param wProf セットする wProf
	 */
	public void setwProf(WordProfileDao wProf) {
		this.wProf = wProf;
	}


	/**
	 * @param setFlag セットする setFlag
	 */
	public void setSetFlag(boolean setFlag) {
		this.setFlag = setFlag;
	}








}
