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

  const number = $('#number' + value).val();
	const name = $('#nameid' + value).val();
	const nameURL = $('#nameURL' + value).val();
	const action = $('#action' + value).val();
	const actionURL = $('#actionURL' + value).val();

  console.log(number + name + nameURL + action + actionURL);


  $.ajax({
    type: "GET",
//    url: "http://localhost:8080/DominicAPI/numberRegAPI",
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
  console.log("実行されました")
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



  //画像の書き換え
  let imgJS = new Image();
  imgJS.src = data.nameURL;
  let imageURL = data.nameURL;

  const taggImgNameURL = 'imgNameURL' + target;
  imgJS.onload = function () {
      console.log("画像が読み込めたので画像を書き替えます。");
      $('.' + taggImgNameURL).attr("src", data.nameURL);
      // $('.' + taggImgNameURL).attr("src", "images/gunpei.jpg");

  };
  imgJS.onerror = function(){
      console.log("画像が読み込めませんでした。")
  }

  imgJS.src = data.nameURL;

  // imgJS.src = "images/gunpei.jpg"

}