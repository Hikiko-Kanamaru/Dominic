

$(document).ready(function () {
    let loadflag = false;

    console.log('search.jsが読み込まれました')


    // トップ画面で結果を表示するための読み込み
    // めっゆー画面では読み込むファイルを切り替えること
    $("#search").click(() => {

        console.log("検索ボタンが押されました")
    const number = $('#number').val();
    const name = $('#name').val();
    $("#card01").fadeOut('slow');
    $("#card02").fadeOut('slow');
    $("#card03").fadeOut('slow');
    $("#content-02").slideUp();
    $("#content-04").slideUp();
    $("#content-06").slideUp();

    setTimeout(() => {
        $(".imgNameURL1").attr("src", "images/JohnDoe.png");
        $(".imgNameURL2").attr("src", "images/JohnDoe.png");
        $(".imgNameURL3").attr("src", "images/JohnDoe.png");
    },500);


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
            if (loadflag != true) {
                $("#out").load("answer.html");
                loadflag = true;
            }
            // 回答画面を読み込む
            setTimeout(() => {
                $("#ansewr").val(res.answerword);



            //                const kore = $("#Boxmsg1").text();
            // console.log(kore)
            // console.log("いまここ456")


                $("#numbermsg").text(res.seachNumber);
                $("#wordmsg").text(res.seachWord);


            }, 500);


            // 各種カードの読み込み
            setTimeout(() => {


                numberSet(1, res.first);
                numberSet(2, res.second);
                numberSet(3, res.third);
                               const boxsize = res.boxsize;
                if (boxsize > 2) {
                $("#card01").fadeIn('slow');
                }

                if (boxsize > 1) {
                    $("#card02").fadeIn('slow');
                    }
                 if (boxsize > 0) {
                     $("#card03").fadeIn('slow');
                     console.log("読み込まれています")
                 }


            }, 800);
            setTimeout(() => {



            },200);




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


