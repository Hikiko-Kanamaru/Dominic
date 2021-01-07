$(document).ready(function() {

	//単語リストを表示する画面のJS
	//起動処理
	// まずはajaxでhtmlを読み込む

	$.ajax({
		type: "GET",
		url: "mainWordViewAPI",
		dataType: "html",
	}).done((res) => {
		// console.log("resを表示しています")
		// console.log(res)
		const resHTML = $.parseHTML(res);

		$("#cardWorld").append(resHTML);

	})



		.fail(function(res) {
			alert("単語リストの読み込みに失敗しました。")



		});





	// 編集画面を表示する
	$(".cardViewjs").on("click",".edit", function() {
		console.log('編集ボタンが押されました');
		const value = $(this).attr("value");
		const uke = "content-" + value;
		console.log(uke);
		$('#' + uke).slideToggle();
	});


	//登録ボタンの処理
	$(".cardViewjs").on("click",".reg" ,function() {

		const loginname = $('#login').text();
		// console.log(loginname);
		if (loginname == "ログイン") {
			$('#login').click();
			return;
		}

		const value = $(this).attr("value");
		const uke  = "content-" + value;
	 	console.log(uke);

		console.log("登録ボタンが押されています");
		const getText = $('#regText' + value).val();
		const getWord = $('#regword' + value).val();
		const getValue = $('#regvalue' + value).val();

		console.log(getText + getWord + getValue );



		$.ajax({
			type: "GET",
			url: "wordRegAPI",
			data: {
				cellValue:value,
			  number:getValue ,
			  word: getWord,
			  answertext: getText
			},
			dataType: "JSON",
		  }).done((res) => {
			// console.log("answertextを表示しています")
			// console.log(res.answertext);
			// console.log("answertextを表示しています");
			// console.log(getText);
			// console.log(res.answertext == getText);

			if (res.answertext == getText) {
				console.log("値が変更されています"+res.value);


				//   $(this).parents(".cFlame").attr("backgroun","red");
				//   $(this).parents(".cFlame").html("");
				changeWordCell(value,res);
				setTimeout(() => {
					$('#regBoxmgs'+ res.value).text("登録に成功しました。");
					$('#regBoxmgs'+ res.value).show();

				}, 200);






			  setTimeout(() => {
				$('#regBoxmgs'+ res.value).text("")

			  }, 10000);

			}else{
				$('#regBoxmgs'+ value).text("登録を行いませんでした。");
				$('#regBoxmgs'+ value).show();
				setTimeout(() => {
				  $('#regBoxmgs'+ value).text("")

				}, 10000);
			}


		   })

			.fail(function (res) {
			  $('#regBoxmgs'+ value).text("登録に失敗しました。しばらく時間をおいてください。");
			  $('#regBoxmgs'+ value).show();
			  setTimeout(() => {
				$('#regBoxmgs'+ value).text("")

			  }, 10000);

			});

	});

	//削除ボタンの処理
	// データベースから削除して
	// 表示を消す
	// フェードアウトさせればいいや
	//デフォルト表示の場合フェードアウトさせない

	$(".cardViewjs").on("click",".deleteButton" ,function() {

		const loginname = $('#login').text();
		console.log(loginname);
		if (loginname == "ログイン") {
			$('#login').click();
			return;
		}

		const value = $(this).attr("value");
		const uke  = "content-" + value;
	 	console.log(uke);

		console.log("削除ボタンが押されています");
		const getText = $('#regText' + value).val();
		const getWord = $('#regword' + value).val();
		const getValue = $('#regvalue' + value).val();

		console.log(getText + getWord + getValue );



		$.ajax({
			type: "GET",
			url: "wordDeleteAPI",
			data: {
			  number:getValue ,
			  word: getWord,
			  answertext: getText
			},
			dataType: "JSON",
		  }).done((res) => {
			console.log("answerflagを表示しています")
			console.log(res.flag);

			if (res.flag == true) {
			  $('#regBoxmgs'+ value).text("削除に成功しました。");
			  $('#regBoxmgs'+ value).attr('visibility','visible');
			  if (!$(this).hasClass('firstset')){
				  			  setTimeout(() => {
				$('.cardNo'+ value).fadeOut(2000);
			  }, 1000);
			  }


			}else{
				$('#regBoxmgs'+ value).text("削除されませんでした。");
				// $('#regBoxmgs'+ value).show();
				$('#regBoxmgs'+ value).attr('visibility','visible');
				setTimeout(() => {
					$('#regBoxmgs'+ value).text("");
				  $('#regBoxmgs'+ value).attr('visibility','hidden');
				}, 10000);

			}


		   })

			.fail(function (res) {
			  $('#regBoxmgs'+ value).text("通信に失敗しました。しばらく時間をおいてください。");
			  $('#regBoxmgs'+ value).attr('visibility','visible');
			  setTimeout(() => {
				$('#regBoxmgs'+ value).text("")
				$('#regBoxmgs'+ value).attr('visibility','hidden');
			  }, 10000);

			});

	});



	// 指定された、データを更新する関数
	// idをdata-idに保存しておく
	// 内容を変更する
	//cellを指定するためのvalueを受け取る変数名はoldValueにする
	//変更前の値を指定する
	//wordsetを引数に取る
		//対応する値
		// private int number;
		//	対応する文字
			// private String word;
		//	結果のテキスト文章
			// private String answertext;
			//bookプロファイルID
			// private int value = -1;

	function changeWordCell(oldValue,res){
		console.log("changeWordCell実行されました")

		$.ajax({
			type: "GET",
			url: "mainWordViewMonoAPI",
			data: {
			  value:res.value
			},
			dataType: "html",
		  }).done((result) => {
			$(".regButton"+oldValue).parents(".cFlame").html(result);
			console.log("上書きに成功しました");
			$('.editButton'+res.value).click();

			// setTimeout(() => {

			// 	console.log("実行されました");
			// 	console.log('#content-'+res.value)
			// 	$('.editButton'+res.value).click();
			// 	// $('#content-'+res.value).attr("display","block");
			// }, 1000);

		   })

			.fail(function (result) {
				console.log("上書きに失敗しました。")

			});

		// const classcardNoID = "cardNo" ;
		// const classtextID = 'text';
		// const classvalueID = "value" ;
		// const classwordID = "word" ;
		// const classcontentID = 'content-';
		// const classregTextID = "regText" ;
		// const classvaluewordID = "valueword" ;
		// const classregvalueID = 'regvalue';
		// const classregwordID = "regword" ;
		// const classregBoxmgsID = "regBoxmgs" ;
		// const classregButtonID = 'regButton';
		// const classdelButtonID = "delButton" ;

		// //テキストの書き換え
		// $('.' + classtextID+oldValue).text(res.answertext);
		// $('.' + classtextID+oldValue).addClass(classtextID + res.value);
		// $('.' + classtextID+oldValue).removeClass(classtextID + oldValue);

		// $('#' + classtextID+oldValue).attr("" + "res.value");





		};








	// 同じ関数名が被ってしまうのでこちらでは、2を付けてくべつする
	// importを使わない方がいいみたいなので、コピペでいく
	function numberSet2(target, data) {
		// console.log("実行されました")
		const taggdiv = "#number" + target;
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



	}




});
