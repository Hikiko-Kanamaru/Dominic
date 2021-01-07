<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- <head> -->
<link rel="stylesheet" href="css/cardViewStyle.css">

<link rel="stylesheet" href="css/cardViewStyle576.css" media="screen and (max-width: 576px)">
<link rel="stylesheet" href="css/cardViewStyle768.css " media="screen and (min-width: 576px) and (max-width: 768px)">
<link rel="stylesheet" href="css/cardViewStyle992.css " media="screen and (min-width: 768px) and (max-width: 992px)">
<link rel="stylesheet" href="css/cardViewStyle1200.css " media="screen and (min-width: 992px) and (max-width: 1200px)">
<link rel="stylesheet" href="css/cardViewStyleOver.css " media="screen and (min-width: 1200px) ">
<!-- </head> -->

<% request.setCharacterEncoding("UTF-8"); %>
<% String[] numberWord = (String[])request.getAttribute("numberWord"); %>
<% String[] numberMessage = (String[])request.getAttribute("numberMessage"); %>

<div class="cardWorld clearfix">
<!-- カードループ開始 -->
<% int count = 0; %>
<c:forEach  begin= "0" end = "99" step="1" varStatus = "status">
<c:set var = "i" value="${status.index}"/>


<c:if test="${i%10==0}">
<div class="card text-left base-card" style="background-color:#7d7d7d;">
  <div class="card-body numberSetCard">


    <h4 class="card-title base-card-titel" style="font-size: 10rem;
    text-align: center;"><%= numberWord[count] %></h4>
    <p class="card-text base-card-text" style="font-size: 2.3rem;
    text-align: center;"

    ><%= numberMessage[count] %>
    </p>
    <% count += 1; %>
  </div>
</div>

</c:if>


<!-- カルーセルの表示 -->

<div class="card text-left bg-transparent" id="${'card'+= i}" >
<div id="${'carouselControls' += i}"  class="carousel slide" data-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active">
    <img class="card-img-top ${'imgNameURL'+= i} w-100" src="" alt="" />
  </div>
  <div class="carousel-item">

<img class="card-img-top ${'imgActionURL'+= i} w-100" src="" alt="">
  </div>
  </div>

  <!-- カーソルの表示 -->
  <a class ="carousel-control-prev" href ="${'#carouselControls' += i}" role ="button" data-slide ="prev">
  <span class ="carousel-control-prev-icon" aria-hidden ="true">
  </span >
  <span class ="sr-only"> 前 に 戻る </span >
  </a >
   <!-- 次に 送る アイコン -->
  <a class ="carousel-control-next" href ="${'#carouselControls' += i}" role ="button" data-slide ="next">
  <span  class ="carousel-control-next-icon" aria-hidden ="true">
  </span>
  <span class ="sr-only"> 次 に 送る </span>
  </a>
<!-- カーソルの表示 -->







</div>
    <!-- <img class="card-img-top ${'imgNameURL'+= i}" src="images/JohnDoe.png" alt="" /> -->
    <div class="card-body">
      <h4 class="card-title noloadtitle ${'number'+= i}" id="${'number'+= i}" >${i}</h4>
      <h4 class="card-subtitle ${'name'+= i}" id="${'name'+= i}">人名読込中</h4>
      <p class="card-text ${'action'+= i}">アクション読込中</p>
<!--       <button type="button" class="btn btn-primary edit" value="02"> -->
      <button type="button" class="btn btn-primary edit" value="${i}">
        編集
      </button>
    </div>

<!--     <div class="editCord" id="content-02"> -->
    <div class="editCord" id="${'content-'+= i}">
      <div class="numberSet">
        <h4 class="card-subtitle">
          <input
            type="text"
            name="${'inputname'+= i}"
            id="${'nameid'+= i}"
            value="人名"
            placeholder="名前"
            class="${'name'+= i}"
          />
<!--                inputにこの属性が与えられていた。  name="1name" -->
<!-- inputname1に変更する -->

        </h4>
        <input type="url" name="${'nameURL'+= i}" id="${'nameURL'+= i}" value="www"
        placeholder="URL" class="${'nameURL'+= i}">
        <p class="cart-text">
          人物に関連した画像のURLを登録して下さい。<br />画像形式は、jpg・pngに対応しています。
        </p>
        <h4 class="card-subtitle">
          <input
            type="text"
            name="${'action'+= i}"
            id="${'action'+= i}"
            value="行動"
            placeholder="アクション"
            class="${'action'+= i}"
          />
        </h4>
        <input type="url" name="${'actionURL'+= i}" id="${'actionURL'+= i}" value="www"
        placeholder="URL" class="${'actionURL'+= i}" >
        <p class="cart-text">アクションに関連した画像のURLを登録して下さい。</p>
        <button type="button" class="btn btn-primary reg" value="${i}">
          登録
        </button>
      </div>
    </div>
  </div>

</c:forEach>


</div>
<!-- cardWorld終了div -->


<div hidden class="space">

</div>

<!-- カードループ終了 -->


    <!-- bootstrapの読み込み -->
<!-- <script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script> -->
<!-- inviewの読み込み -->
<script type="text/javascript" src="js/jquery.inview.js"></script>

<script src="js/maincard.js"></script>
