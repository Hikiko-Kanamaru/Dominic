

$(document).ready(function () {




// //カードが移動したら読み込む
// $(window).scroll(function(){
// console.log("スクロールを検知しました")
//   var t = $('.noloadtitle').offset().top; // ターゲットの位置取得
//   var p = t - $(window).height();  // 画面下部からのターゲットの位置
//   if($(window).scrollTop() > p){
//     console.log("画像を読み込みます");
//     // console.log(this.text());
//   }else{
//     console.log("画面上にいません")
//     // console.log(this.text());
//   }
// })






    let loadflag = false;

    console.log('mainsearch.jsが読み込まれました')


    // トップ画面で結果を表示するための読み込み
    // めっゆー画面では読み込むファイルを切り替えること
    $("#search").click(() => {

        console.log("検索ボタンが押されました")
    const number = $('#number').val();
    const name = $('#name').val();



    if(number >999999 || number < 0){
      $("#maininputmsg").attr("hidden", true);
        $("#maininputmsg2").attr("hidden", false);
        return;
    }

    $("#maininputmsg").attr("hidden", true);
    $("#maininputmsg2").attr("hidden", true);

// バリデーションは後でやる。今はここまま認める

        $.ajax({
            type: "GET",
            url: "SerchApi",
            data: {
                serchNumber: number,
                serchWord: name
              },
            dataType: "JSON",
        }).done((res) => {
            console.log("検索に成功しました");
            // 読み込みに成功したら

            // 回答画面を読み込む
           $("#ansewr").val(res.answerword);
                $("#numbermsg").text(res.seachNumber);
                $("#wordmsg").text(res.seachWord);

            // 各種カードの読み込み
            setTimeout(() => {

                //                const boxsize = res.boxsize;
                // if (boxsize > 2) {
                // $("#card01").fadeIn('slow');
                // }

                // if (boxsize > 1) {
                //     $("#card02").fadeIn('slow');
                //     }
                //  if (boxsize > 0) {
                //      $("#card03").fadeIn('slow');
                //      console.log("読み込まれています")
                //  }


            }, 800);




        }).fail((res) => {
            //読み込みに失敗したら
            console.log("jsonの読み込みに失敗しました")
            $("#maininputmsg").attr("hidden", false)
        });



        // search ボタンクリック終了


    });

});

function numberSet(target, data) {
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


