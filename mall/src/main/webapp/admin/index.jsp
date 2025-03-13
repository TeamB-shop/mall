<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>������ ������</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/mainlogin.css?v=3">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
</head>
<body class="bodycss">
    <header class="admin_title">
        <p><img src="./img/logo.png" class="logo_sm"> ADMINISTRATOR</p>
    </header>
    <form id="frm" method="post" action="../adlist/masterloginok.do" onsubmit="return loginck()">
    <section class="admin_bgcolor">
        <div class="admin_login">
            <span>
                <div class="left_div">
                <ul>
                <li><input type="text" class="input_text1" name="mid" placeholder="������ ���̵� �Է��ϼ���"></li>
                <li><input type="password" class="input_text1" name="mpass" placeholder="������ �н����带 �Է��ϼ���"></li>
                </ul>
                </div>
                <div class="right_div">
                    <button type="submit" class="btn_submit" title="MASTER LOGIN">MASTER LOGIN</button>
                </div>
                <em class="alert_msg">�� �� ����Ʈ�� ������ �ܿ��� ������ ���մϴ�. ������ ���ٿ� ���� ���� ������ ��� ��� �˴ϴ�.</em>
            </span>
            <span>
                <ol class="admin_info">
                    <li title="�ű� ������ ��Ͽ�û">�ű� ������ ��Ͽ�û</li>
                    <li title="���̵�/�н����� ã��">���̵�/�н����� ã��</li>
                </ol>                
            </span>
        </div>
    </section>
    </form>
    <footer class="admin_copy_login">
        <div>
            Copyright �� 2024 shopbag All rights reserved.
        </div>
    </footer>
</body>
<script>
function loginck(){
	if(frm.mid.value == ""){
		alert("���̵� �Է��ϼ���");
		return false;
	}
	else if(frm.mpass.value == ""){
		alert("�н����带 �Է��ϼ���");
		return false
	}
	else{
		return true;
	}
}

</script>
</html>