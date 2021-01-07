$(document).ready(function () {
var loadCount = 0;
console.log("loadCount読み込み時"+loadCount);

// インビューをここに書いてみる

$(".noloadtitle").on('inview',function(isInvew){
  // console.log('inviewの有効');
  // console.log($(this).text());
const number = $(this).text();
const load = $(this).hasClass('noloadtitle');
const countFlag = (loadCount <= 2);
if (load && isInvew ) {
  // console.log('ここまで来てます。2');
  $('.imgNameURL'+ number).attr("src","images/JohnDoe.png");
  $('.imgActionURL'+ number).attr("src","images/unkownAction.png");


  // アクセスが多用されているので読み込みを行わない
  if(countFlag){

    console.log("loadCountに加算します"+loadCount);
    loadCount += 1;



    $.ajax({
      type: "GET",
      url: "SerchCardApi",
      data: {
        Number: number
      },
      dataType: "JSON",
    }).done((res) => {
      console.log("読み込みに成功しました" + res.name);
      console.log("loadCountAjax成功変更前"+loadCount);
      if(res.number != -1){
        numberSet2(number,res);
        // console.log("読み込み完了しました。");
        $(this).removeClass('noloadtitle');
      }else{
        console.log("検索に失敗しているので、読込中を入れる");





      }
      console.log("減算します。loadCountAjax成功"+loadCount);
      loadCount -= 1 ;

    }).fail((res) => {
      //読み込みに失敗したら
      // console.log("jsonの読み込みに失敗しました")
      console.log("減算します。loadCountAjax失敗"+loadCount);
      loadCount -= 1;
    });
    // アクセスが多用されているので読み込みを行わないif終了
  } else {
    console.log("アクセスが多いので読み込みませんでした"+loadCount);
  }

  }






});



$(".edit").on("click", function() {
    const value = $(this).attr("value");
    const uke  = "content-" + value;
    console.log(uke);
    $('#'+uke).slideToggle();
});


$(".reg").on("click", function () {
  const loginname = $('#login').text();
  console.log(loginname);
  if (loginname == "ログイン") {
  $('#login').click();
    return;
}

	console.log("登録ボタンが押されています");
    const value = $(this).attr("value");
    const uke  = "content-" + value;
  console.log(uke);
  const number0 = $('#number' + value).val();
  const number = $('#number' + value).text();
  console.log("0は、"+number0+ "  textは" + number)
	const name = $('#nameid' + value).val();
	const nameURL = $('#nameURL' + value).val();
	const action = $('#action' + value).val();
	const actionURL = $('#actionURL' + value).val();

  console.log(number + name + nameURL + action + actionURL);


  $.ajax({
    type: "GET",
    url: "numberRegAPI",
    data: {
      number: number,
      name: name,
      nameURL: nameURL,
      action: action,
      actionURL: actionURL
    },
    dataType: "JSON",
  }).done((res) => {
    console.log("res.nameを表示しています")
    console.log(res.name);
    numberSet2(value, res);
   })

    .fail(function (res) {
      alert("登録に失敗しました。")



    });

});



// 同じ関数名が被ってしまうのでこちらでは、2を付けてくべつする
// importを使わない方がいいみたいなので、コピペでいく
function numberSet2(target, data) {
  // console.log("実行されました")
  const taggdiv= "#number" + target;
  const taggName = 'name' + target;
  // console.log(taggName);

  const taggNameURL = 'nameURL' + target;
  const taggAction = 'action' + target;
  const taggActionURL = 'actionURL' + target;
  const taggNumber = 'number' + target;

  const dataName = data.name;
  console.log(dataName);
  //テキストの書き換え
  $('.' + taggName).text(data.name);
  $('.' + taggName).val(data.name);
  $('.' + taggNameURL).text(data.nameURL);
  $('.' + taggNameURL).val(data.nameURL);
  $('.' + taggAction).text(data.action);
  $('.' + taggAction).val(data.action);
  $('.' + taggActionURL).text(data.actionURL);
  $('.' + taggActionURL).val(data.actionURL);
  $('.' + taggNumber).text(data.number);
  $('.' + taggNumber).val(data.number);



  //人物画像の書き換え
  let imgJS = new Image();
  imgJS.src = data.nameURL;
  let imageURL = data.nameURL;

  const taggImgNameURL = 'imgNameURL' + target;
  imgJS.onload = function () {
      // console.log("画像が読み込めたので画像を書き替えます。");
      $('.' + taggImgNameURL).attr("src", data.nameURL);
      // $('.' + taggImgNameURL).attr("src", "images/gunpei.jpg");
  };
  imgJS.onerror = function(){
      // console.log("画像が読み込めませんでした。")


      if (!data.actionimageURLion &&  !data.nameURL) {
        // console.log("URLが存在しない処理")
      }else{
        // console.log("URLが存在する処理")

        // console.log(data.nameURL+"nameURL")

        $(taggdiv).addClass('noloadtitle');
        // console.log("人読み込みに失敗したので"+taggdiv+"にクラスを追加");

      }


  }

  imgJS.src = data.nameURL;

  // imgJS.src = "images/gunpei.jpg"

//アクション画像の書き換え
  //人物画像の書き換え
  let actionimgJS = new Image();
  let actionimageURL = data.actionURL;
  actionimgJS.src = actionimageURL;

  const taggImgActionURL = 'imgActionURL' + target;
  actionimgJS.onload = function () {
    // console.log("アクション画像が読み込めたので画像を書き替えます。");
    $('.' + taggImgActionURL).attr("src", actionimageURL);
    // $('.' + taggImgNameURL).attr("src", "images/gunpei.jpg");
  };
  actionimgJS.onerror = function(){
      // console.log("アクション画像が読み込めませんでした。")
      if (!data.actionimageURLion &&  !data.nameURL) {
        // console.log("URLが存在しない処理")
      }else{
        // console.log("URLが存在する処理")
      $(taggdiv).addClass('noloadtitle');
      // console.log(actionimageURL+"アクションURL")
      // console.log("動読み込みに失敗したので"+taggdiv+"にクラスを追加");

      }


  }

  actionimgJS.src = actionimageURL;













}




});
