/**numberカードに情報をセットする関数
*importが使用するべきでないとのことなので未使用になっています
 * 構造として、ターゲットの番号になっているだろうという決め打ちなので、変更の際には命名に注意すること。
 *taret:変更するカードの番号
 *data:NumberAndHuman形式のデータ
 */
function numberSet(target,data) {

    console.log("実行されました")
    const taggName = target + 'name';
    const taggNameURL = target + 'nameURL';
    const taggAction = target + 'action';
    const taggActionURL = target + 'actionURL';
    const taggNumber = target + 'number';


    $(".taggName").val("data.name");
    $(".taggNameURL").val("data.nameURL");
    $(".taggAction").val("data.action");
    $(".taggActionURL").val("data.actionURL");
    $(".taggNumber").val("data.number");

}

