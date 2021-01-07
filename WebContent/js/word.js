$("#word").on("click", function () {
    console.log("単語登録ボタンが押されました。")
    const loginname = $('#login').text();
    if (loginname == "ログイン") {
    $('#login').click();
      return;
  }

    const number = $('#numbermsg').text();
    const word = $('#wordmsg').text();
    const answertext = $('#ansewr').val();

    $.ajax({
      type: "GET",
      url: "wordRegAPI",
      data: {
        number: number,
        word: word,
        answertext: answertext
      },
      dataType: "JSON",
    }).done((res) => {
      console.log("answertextを表示しています")
      console.log(res.answertext);

      if (res.answertext == answertext) {
        $('#answerBoxmgs').text("登録に成功しました。");
        $('#answerBoxmgs').show();
        setTimeout(() => {
          $('#answerBoxmgs').text("")

        }, 10000);

      }else{
        $('#answerBoxmgs').text("登録を行いませんでした。");
        $('#answerBoxmgs').show();
        setTimeout(() => {
          $('#answerBoxmgs').text("")

        }, 10000);

      }


     })

      .fail(function (res) {
        $('#answerBoxmgs').text("登録に失敗しました。しばらく時間をおいてください。");
        $('#answerBoxmgs').show();
        setTimeout(() => {
          $('#answerBoxmgs').text("")

        }, 10000);

      });

  });