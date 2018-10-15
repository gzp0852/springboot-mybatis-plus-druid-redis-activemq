//const url= 'http://10.5.33.65:8081';
//const url= 'http://10.5.107.36:8081';
//const url= 'http://10.5.112.117:8081';
   const url= 'http://10.39.7.254:8081';
//const url= 'http://39.108.213.45:8081';



function isLogin() {
				var token = localStorage.getItem("token");
				console.log(token);
				if(!token) {
					location.href = 'login.html';
				}
			}

function getNowFormatDate() {
			    var date = new Date();
			    /*var seperator1 = "-";
			    var seperator2 = ":";*/
			    var month = date.getMonth() + 1;
			    var strDate = date.getDate();
			    var hours = date.getHours();
			    var minutes = date.getMinutes();
			    var seconds = date.getSeconds();
			    
			    if (month >= 1 && month <= 9) {
			        month = "0" + month;
			    }
			    if (strDate >= 0 && strDate <= 9) {
			        strDate = "0" + strDate;
			    }
			    if (hours >= 0 && hours <= 9) {
			    	hours = "0" + hours;
			    }
			    if (minutes >= 0 && minutes <= 9) {
			    	minutes = "0" + minutes;
			    }
			    if (seconds >= 0 && seconds <= 9) {
			    	seconds = "0" + seconds;
			    }
			    
			    
			    /*var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
			            + " " + date.getHours() + seperator2 + date.getMinutes()
			            + seperator2 + date.getSeconds();*/
			    var currentdate = date.getFullYear() + month + strDate  + hours 
			    		+ minutes + seconds;
			    return currentdate;
			}

function getNowFormatDate1() {
			    var date = new Date();
			    var seperator1 = "-";
			    var seperator2 = ":";
			    var month = date.getMonth() + 1;
			    var strDate = date.getDate();
			    var hours = date.getHours();
			    var minutes = date.getMinutes();
			    var seconds = date.getSeconds();
			    
			    if (month >= 1 && month <= 9) {
			        month = "0" + month;
			    }
			    if (strDate >= 0 && strDate <= 9) {
			        strDate = "0" + strDate;
			    }
  			    
			    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
			    return currentdate;
			}