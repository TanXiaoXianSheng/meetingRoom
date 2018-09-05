function findByUsername(){
	$.ajax({
		type:"POST",
		url:"./findByUsernameAction.action",
		data:{
			findByUsername:$("#findByUsername").val(),
		},
		dataType:"text",
		beforeSend:function(XMLHttpRequest){
			//$("#show").text("loading...");
		},
		success:function(data,textStatus){
			
			$("#findResult").text(data);
			//$("#findResult").append(data);
			$("#findResult").css("color","red");
			var obj = document.getElementById("yincang");
			
		},
	});
}

function findResult(a){
	$.ajax({
		type:"POST",
		url:"./personalProfileAction.action",
		data:{
			//findResult:$("#findResult").val(),
			findResult:document.getElementById("findResult").innerText,
		},
		dataType:"Json",
		beforeSend:function(XMLHttpRequest){
		},
		success:function(data,textStatus){
			var obj = document.getElementById(a);
			if(obj.style.display==""){
				obj.style.display="none";
			}else{
				obj.style.display="";
			}
			$("#username").text(data.username);
			$("#phone_number").text(data.phone_number);
			$("#sex").text(data.sex);
			$("#addMessage").text("");
		},
	});
}

function addFriend(){
	$.ajax({
		type:"POST",
		url:"./addFriendAction.action",
		data:{
			friendUsername:document.getElementById("username").innerText,
		},
		dataType:"text",
		beforeSend:function(XMLHttpRequest){
			
		},
		success:function(data,textStatus){
			$("#addMessage").text(data);
			
		},
	});
}

function readFriendsList(){
	$.ajax({
		type:"POST",
		url:"./readFriendsList.action",
		data:{
			
		},
		dataType:"text",
		beforeSend:function(XMLHttpRequest){
			
		},
		success:function(data,textStatus){
			
		},
	});
}