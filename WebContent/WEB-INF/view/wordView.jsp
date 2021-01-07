<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- <head> -->


<c:forEach items="${wordJoinList}" var="wordJoin">

<div class="cFlame">
<div class="card text-left <c:out value="cardNo${wordJoin.id}"/>" data-id=<c:out value="${wordJoin.id}"/>>
  <div class="card-body">
    <h4 class="card-title CtitleText <c:out value="text${wordJoin.id}"/>" id="<c:out value="text${wordJoin.id}"/>"> <c:out value="${wordJoin.text}"/>
    </h4>


    <div class="subtext" >

      <span class="card-text <c:out value="value${wordJoin.id}"/>"> <c:out value="${wordJoin.value}"/></span>
      <span>=</span>
      <span class="card-text <c:out value="word${wordJoin.id}"/>"><c:out value="${wordJoin.word}"/></span>
  <p class="editButton">
      <button type="button" class="btn btn-primary edit <c:out value="editButton${wordJoin.id}"/>" value="<c:out value="${wordJoin.id}"/>">
        編集
      </button>
  </p>
    </div>
</div>

<div class="editCord" id="<c:out value="content-${wordJoin.id}"/>">
  <div class="textSet">
    <h4 class="card-subtitle">

      <!--text入力 -->
      <input
        type="text"
        name="inputtext"
        id="<c:out value="regText${wordJoin.id}"/>"
        value="<c:out value="${wordJoin.text}"/>"
        placeholder="記憶文章"
        class="<c:out value="regText${wordJoin.id}"/> <c:out value="text${wordJoin.id}"/> regText"
      />
<!--                inputにこの属性が与えられていた。  name="1name" -->
<!-- inputname1に変更する -->

    </h4>

    <!-- 数値入力 -->
<div class="input-group">
<div class="input-group-prepend">
<span class="input-group-text" id="<c:out value="valueword${wordJoin.id}"/>">紐づける</span>
</div>

      <input
        type="number"
        name="<c:out value="regvalue${wordJoin.id}"/>"
        id="<c:out value="regvalue${wordJoin.id}"/>"
        placeholder="数値"
         value="<c:out value="${wordJoin.value}"/>"
        class="<c:out value="regvalue${wordJoin.id}"/> <c:out value="value${wordJoin.id}"/> regvalue form-control"
        max = 999999 min = 0 step = 1
      />



        <input
          type="text"
          name="<c:out value="regword${wordJoin.id}"/>"
          id="<c:out value="regword${wordJoin.id}"/>"
          placeholder="単語"
          value="<c:out value="${wordJoin.word}"/>"
          class="<c:out value="regword${wordJoin.id}"/> <c:out value="word${wordJoin.id}"/> regword form-control"
        />



</div>


      <!-- 単語入力 -->

<p class="text-left regButtonP" style="padding-top: 10px">

      <button type="button" class="btn btn-primary reg text-right <c:out value="regButton${wordJoin.id}" />" value="<c:out value="${wordJoin.id}"/>" style="margin-right: 0.5rem">
        登録
      </button>
      <!-- class firstsetは,デフォルト表示用のもののため、再利用の際には消しておくこと-->

      <button type="button" class="btn btn-danger deleteButton text-right <c:out value="delButton${wordJoin.id}" />" value="<c:out value="${wordJoin.id}"/>">
        削除
      </button>
<span class="regBoxmgs" id="<c:out value="regBoxmgs${wordJoin.id}"/>" style="padding-left: 2rem;"></span>


</p>
  </div>
</div>
</div>

<!-- /cFlame -->
</div>
</c:forEach>
