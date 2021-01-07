
$(document).ready(() => {
  let scroll = $(window).scrollTop();
  // console.log(scroll);

  $.ajax({
    url: 'LoginCheckApi',
    type: 'GET',
    data: {
    },
    dataType: 'JSON'
  }).done(function (res) {
    console.log("ログインチェック中");
//
const test = res.loginTest;
if (test == "true") {
userName = res.userName;
$("#login").text(userName)
  $("#login").attr("data-target","#modelIdLogout");
}

  });






    // モーダルから抜けたときにinputを削除する処理
    $("#modelIdLogin,#createUserModal").on("hidden.bs.modal", ()=> {
      $("#modelIdLogin input,#createUserModal input").val("");
      $("#passMessage").empty()
      });


    // ログインボタンを押されたときの処理
$(document).on("click", "#loginCheck", function () {


    // 入力欄から変数を受け取る
    const loginID = $("#loginID").val();
    const loginPassword = $("#loginPassword").val();
  // デバック用
    // console.log(loginID);
    // console.log(loginPassword);
  // /デバック用後で消すこと
  let msg = "";

    // 入力欄が不完全ならメッセージを出して終了
  if(loginID=="" || loginPassword==""){
    msg = "正しく入力されていません";
    console.log("どちらかがnullです")
    $("#passMessage").text(msg)
  } else {
    console.log("AJAXの判定開始")
    $.ajax({
      url: 'loginApi',
      type: 'POST',
      data: {
        lang_id: loginID,
        password: loginPassword
      },
      dataType: 'JSON'
    })
      .done(function (res) {
        console.log("この下が判定結果");
         $('#closebutton').click();
        const test = res.loginTest;
        if (test == "true") {
        console.log("真偽値の読み込みに成功")
        userName = res.userName;
        $("#login").text(userName)
  // ログインに成功したらモーダルを閉じる
          // $("#modelIdLogin").modal("toggle");
          $('#closebutton').click();
         $('.modal-backdrop').fadeOut();
          $("#login").attr("data-target","#modelIdLogout");
        } else {
          console.log("ログインに失敗")
        $("#passMessage").text(res.msg);

			}
		})
      .fail(function () {
        console.log("jsonの読み込みに失敗してます")
			$("#passMessage").text('失敗しました');
		});
  }
  console.log("AJAXの判定終了")



  // 変数をAJAXでサーバーと照会する
  // 返ってきた配列からmgsを取り出して
  // メッセージがあったら失敗にする

  // ログイン成功。
  // 今はログイン成功のフローだけ作っておく modelIdLogin


    // if(loginID!="" && loginPassword!=""){
    // console.log("ログイン成功しました")
    // $("#passMessage").text("ログイン成功")
    // $("#login").attr("data-target","#modelIdLogout");
  // userNameに返ってきた名前を入れてアラートを閉じる
  // ログイン失敗
  // msgを表示する。
  // passwordを空にする
  // 通信失敗
  // 通信に失敗したアラートを出す。


  });






  ///ログイン処理終了


  // ログアウト処理
  $(document).on("click", "#CheckOut",  () =>{
  // ajaxでログアウト処理を呼ぶ
  // 施工失敗にかかわらず
  // データをリセットさせたいので、indexページに飛ばしてしまうのが良い
$.ajax({
      url: 'logOutApi',
      type: 'GET',
      data: { },
      dataType: 'JSON'
    })
  .done(function (res) {
    console.log("ログアウトしました");


  setTimeout(()=>{
      window.location.href = '';
    }, 1000);
		})
      .fail(function () {

		});


  });


  // /ログアウト処理


  // 新規登録処理
  // 試しにモーダルを重ねて表示してみる
  // モーダルが重なるとろくなことにならんかったから、別のを出す

//   パスワード処理
  let IDchakflag = false;
  let passwordflag = false;
  const IDPattern = /^[a-zA-Z0-9]{6,20}$/;
  const paswordPattern = /^[A-Za-z0-9_.-]{6,20}$/;

// ID入力処理
  $(document).on("keyup", "#createloginID", () => {
    $("#userCreateCheck").attr("disabled", true);
    const userIDtmp = $("#createloginID").val();

    if (userIDtmp.length >= 6) {

      if (IDPattern.test(userIDtmp) == true) {
        IDchakflag = true;
        $("#createUserIDModalMessage").text("")
        if (passwordflag == true) {
          $("#userCreateCheck").attr("disabled", false);
        }
      } else if (userIDtmp.length > 20) {
        $("#createUserIDModalMessage").text("文字列が長すぎます")
      } else{
        $("#createUserIDModalMessage").text("使用できない文字が含まれています。")
      }



    }


});

  // パスワード入力処理
  $(document).on("keyup", ".form-control-createUser", () => {
    $("#userCreateCheck").attr("disabled", true);
    passwordflag = false;
  const pass1 = $("#setloginPassword1").val();
  const pass2 = $("#setloginPassword2").val();
  if (pass1.length >=6 && pass2.length >= 6 ) {
  if( pass1 != pass2  ){
    $("#createUserModalMessage").text("パスワードが一致しません。")
  }else if(pass1.length >20 ){
    $("#createUserModalMessage").text("パスワードが長すぎます。");
  } else if (pass1 == pass2) {
    if (paswordPattern.test(pass1) == false) {
      $("#createUserModalMessage").text("使用できない文字列です");
    } else {
      // こちらでバリデーションが済んだところ
    $("#createUserModalMessage").text("パスワード一致しました。");
    }
    passwordflag = true;
    if (IDchakflag == true) {
      $("#userCreateCheck").attr("disabled", false);
    }
    }

  }

  if (pass1.length < 6 || pass2.length < 6){
    $("#createUserModalMessage").empty();
  }

    });

  $(document).on("click", "#createUser",  () =>{
    $("#modelIdLogin").modal("hide");

    $("#createUserModal").modal("show");


    $("#createUserModalMessage").empty();
    $("#createUserIDModalMessage").empty()
    passwordflag = false;
    IDchakflag = false;
    $("#userCreateCheck").attr("disabled", true);
    //falseで解除できる
    // $("#userCreateCheck").attr("disabled", false);


    // パスワード処理終了


  // バリデーションは、二回やる
  // パスワードが同一かどうかはこちらでやり
  // ユーザーIDが被っていないかどうかはサーバー側でやる
  // 問題があった際は、メッセージはcreateUserModalMessageに表示する
  // パスワードのバリデーションは、onclikに書かなくてよいな。

  // ajaxで登録処理を呼ぶ
  // 成功したらログインモーダルを閉じて、
  // ログインモーダルを開く

  });

  // ユーザー制作ボタンをクリック
  $(document).on("click", "#userCreateCheck", () => {
    console.log("制作中押されてる");
    // ajaxで制作する場所

    const userIDtmp = $("#createloginID").val();
    const pass1 = $("#setloginPassword1").val();
    $("#userCreateCheck").attr("disabled", true);

$.ajax({
      url: 'LoginUserCreateApi',
      type: 'POST',
  data: {
    lang_id: userIDtmp,
    password: pass1
      },
      dataType: 'JSON'
    })
  .done(function (res) {
    console.log("通信に成功しました");
    const Test = res.loginTest

if(Test == "true" ){
  $("#userCreateCheck").attr("disabled", true);

    let msg1 = res.msg + "  画面を閉じます。";
    $("#createUserModalMessage").text(msg1);

  setTimeout(()=>{
    $("#createUserModal").modal("toggle");
    }, 3000);


    } else {
      $("#createUserModalMessage").text(res.msg);

    }



  })
  .fail(function (res) {
    $("#createUserModalMessage").text(res.msg)

		});


    // データベースに登録する
  });



  // モーダルが開かれたときにスクロール量を保存するメソッド
$('#modelIdLogin').on('show.bs.modal',()=>{
 scroll = $(window).scrollTop();
  // console.log(scroll);
});


  // モーダルが閉じられたときに移動するメソッド

$('.modal').on('hidden.bs.modal',()=>{
setTimeout(() => {

const h1 = scroll;
  $('html, body').animate({scrollTop:h1});

  console.log("画面の位置を調整します");

},10);

});


});

