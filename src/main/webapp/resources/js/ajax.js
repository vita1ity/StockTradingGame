$(document).on('click', '.pager_item', function (e) {
	e.preventDefault();
	var url = $(this).data("page");
	console.log(url);
	
	$.ajax({
        url:url,
        type: "GET"
	}).done (function(data) {
		console.log(data);
		var len = data.length;
		var starList = "";
		for (var i = 0; i < len; i++) {
			var code = data[i].stock.code;
			starList += "<li class=\"star-item\" id=\"" + code + "\">\n";
			starList += createStockHtml(data[i]);
			starList += "</li>\n";
			
		}
		
		console.log(starList);
		
		$("#starList").html(starList);
		
		//pager
		
		$('#currentPage').val(data[0].page);
		$('#pageUrl').val("marketPage?page=" + data[0].page);
		
		console.log("Page url: " + $('#pageUrl').val());
		
		var pager = "";
		
		var page = data[0].page;
		console.log(page);
		if (page != 1) {
			var pageUrl = "/stocktradinggame/marketPage?page=" + (page - 1) + "";
			pager += "<li class=\"prev pager_nav pager_item\" data-page=\"" + pageUrl + "\"><a>Prev</a></li>\n";
		}
		var numberOfPages = $("#numOfPages").val();
		console.log("pages: " + numberOfPages);
		for (var i = 1; i <= numberOfPages; i++) {
			if (i == page) {
				pager += "<li class=\"pager_item\"><a>" + i + "</a></li>";
			}
			else {
				var pageUrl = "/stocktradinggame/marketPage?page=" + i + "";
				pager += "<li class=\"pager_item\" data-page=\"" + pageUrl + "\"><a>" + i + "</a></li>\n";
			}
		}
		if (page != numberOfPages) {
			var pageUrl = "/stocktradinggame/marketPage?page=" + (page + 1) + "";
			pager += "<li class=\"next pager_nav pager_item\" data-page=\"" + pageUrl + "\"><a>Next</a></li>\n";
		}
	
		$("#pager").html(pager);
		
	
	}).fail (function(err) {
	    console.error(err);
	});
	
});
$(document).on('click', '#addStock', function (e) {
	e.preventDefault();
	
	var current = $(this).parent().parent().parent().parent();
	var id =  current.attr("id");
	console.log(id);
	
	var added = false;
	var last = false;
	if ($('#firstStock').val() == "") {
		$('#firstStock').val(id);
		added = true;
	}
	else if ($('#secondStock').val() == "") {
		$('#secondStock').val(id);
		added = true;
	}
	else if ($('#thirdStock').val() == "") {
		$('#thirdStock').val(id);
		added = true;
	}
	else if ($('#forthStock').val() == "") {
		$('#forthStock').val(id);
		added = true;
	}
	else if ($('#fifthStock').val() == "") {
		$('#fifthStock').val(id);
		added = true;
		last = true;
	}
		
	
	if (added) {
		$('#allStocks').find('li').each(function(i){
			var stock = $(this);
			if (stock.attr("id") == id) {
				var addedHtml = "";
				addedHtml += "<div class=\"option\">Stock Added</div>\n";
				addedHtml += "<div class=\"add\" style=\"background: url(/stocktradinggame/resources/images/icon/check.png) no-repeat center center\"></div>\n";
				
				stock.find('.desc').html(addedHtml);
				
				if (last) {
					$('#registerButton').removeClass("disabledRegister");
					$('#registerButton').attr("disabled", false);
					$('#disabledMessage').text("");
					
					$(this).find('.msg').html("<p>Great! You can register now.</p>\n");
					//alert("Great! You can register now.");
				}
			}
		});
		
		console.log("first: " + $('#firstStock').val());
		console.log("second: " + $('#secondStock').val());
		console.log("third: " + $('#thirdStock').val());
		console.log("forth: " + $('#forthStock').val());
		console.log("fifth: " + $('#fifthStock').val());
	}
	else {
		$('#allStocks').find('li').each(function(i){
			var stock = $(this);
			if (stock.attr("id") == id) {
				$(this).find('.msg').html("<p>You have already added 5 free stocks. Please register now.</p>\n");
			}
		});
		//alert("You have already chosen 5 free stocks. Please register now.");
		return;
	}
	
});
$(document).on('click', '#buyStock', function (e) {
	e.preventDefault();
	var url = $(this).data("url");
	
	var price = parseInt($(this).parent().find(".price_inp").val());
	var userMoney = parseInt($('#accountBalance').text());
	console.log("user money: " + userMoney);
	console.log("price: " + price);
	console.log(userMoney < price);
	if (!$.isNumeric(price)) {
		
		$(this).parent().parent().find('.msg').html("<p>Price should be numeric value</p>\n");
		
		return;
	}
	else if (parseInt(price) < 0) {
		$(this).parent().parent().find(".msg").html("<p>Price should be greater than 0</p>\n");
		return;
	}
	//not enough money
	else if (userMoney < price){
		$(this).parent().parent().find(".msg").html("<p>You don't have enough money to buy the stock!</p>\n");
		return;
	}
	console.log("price: " + price);
	console.log(url);
	
	$.ajax({
        url:url,
        type: "POST",
        data: {price: price}
	}).done (function(data) {
		/*var item = $(this).find(".star-item");
		console.log(item);*/
		var freeStocks = data.user.freeItemsLeft;
		if (freeStocks > 0) {
			alert("You have " + freeStocks + " free stocks left");
		}
		//console.log($('#accountBalance').text());
		//console.log(data.user.accountBalance);
		$('#accountBalance').text(data.user.accountBalance);
		
		$('#starList').find('li').each(function(i){
	    	var current = $(this);
	    	//console.log(current);
	    	//console.log(current.attr("id"));
	    	if (current.attr("id") == data.stock.code) {
	    		//console.log(true);
	    		var stockHtml = createStockHtml(data);
	    		$(current).html(stockHtml);
	    	}
	    	i++;
	    });
		
		
	}).fail (function(err) {
	    console.error(err);
	});
});

$(document).on('click', '#cancelOrder', function (e) {
	e.preventDefault();
	var url = $(this).data("url");
	
	console.log(url);
	
	$.ajax({
        url:url,
        type: "GET"
        
	}).done (function(data) {
		
		$('#starList').find('li').each(function(i){
	    	var current = $(this);
	    	//console.log(current);
	    	//console.log(current.attr("id"));
	    	console.log(data.page);
	    	if (current.attr("id") == data.stock.code) {
	    		//console.log(true);
	    		var stockHtml = createStockHtml(data);
	    		$(current).html(stockHtml);
	    	}
	    	i++;
	    });
		
		
	}).fail (function(err) {
	    console.error(err);
	});
});

$(document).on('click', '#sellStock', function (e) {
	e.preventDefault();
	
	var url = $(this).data("url");
	
	var price = $(this).parent().find(".price_inp").val();
	if (!$.isNumeric(price)) {
		$(this).parent().parent().find('.msg').html("<p>Price should be numeric value</p>\n");
		return;
		
	}
	else if (parseInt(price) < 0) {
		$(this).parent().parent().find('.msg').html("<p>Price should be greater than 0</p>\n");
		return;
		
	}
	console.log("price: " + price);
	console.log(url);
	
	$.ajax({
        url:url,
        type: "POST",
        data: {price: price}
	}).done (function(data) {
		/*var item = $(this).find(".star-item");
		console.log(item);*/
		$('#accountBalance').text(data.user.accountBalance);
		$('#starList').find('li').each(function(i){
	    	var current = $(this);
	    	//console.log(current);
	    	//console.log(current.attr("id"));
	    	if (current.attr("id") == data.stock.code) {
	    		//console.log(true);
	    		var stockHtml = createStockHtml(data);
	    		$(current).html(stockHtml);
	    	}
	    	i++;
	    });
		
		
	}).fail (function(err) {
	    console.error(err);
	});
});

$(document).on('click', '#buyStockPage', function (e) {
	e.preventDefault();
	var url = $(this).data("url");
	var userMoney = parseInt($('#accountBalance').text());
	
	var price = $(this).parent().find(".price_inp").val();
	console.log("price: " + price);
	if (!$.isNumeric(price)) {
		$(this).parent().parent().find('.stock-error').html("<span class=\"error-register-message\">Price should be numeric value</span>\n");
		//alert("Price should be numeric value");
		return;
	}
	else if (parseInt(price) < 0) {
		$(this).parent().parent().find('.stock-error').html("<span class=\"error-register-message\">Price should be greater than 0</span>\n");
		//alert("Price should be greater than 0");
		return;
	}
	else if (userMoney < price){
		$(this).parent().parent().find('.stock-error').html("<span class=\"error-register-message\">You don't have enough money to buy the stock!</span>\n");
		//alert("Price should be greater than 0");
		return;
	}
	console.log(url);
	
	$.ajax({
        url:url,
        type: "POST",
        data: {price: price}
	}).done (function(data) {
		$('#accountBalance').text(data.user.accountBalance);
		var item = createStockHtmlStockPage(data);
		$('#starDescription').html(item);
		
		
	}).fail (function(err) {
	    console.error(err);
	});
});


$(document).on('click', '#sellStockPage', function (e) {
	e.preventDefault();
	var url = $(this).data("url");
	
	var price = $(this).parent().find(".price_inp").val();
	console.log("price: " + price);
	if (!$.isNumeric(price)) {
		$(this).parent().parent().find('.stock-error').html("<span class=\"error-register-message\">Price should be numeric value</span>\n");
		//alert("Price should be numeric value");
		return;
	}
	else if (parseInt(price) < 0) {
		$(this).parent().parent().find('.stock-error').html("<span class=\"error-register-message\">Price should be greater than 0</span>\n");
		//alert("Price should be greater than 0");
		return;
	}
	console.log(url);
	
	$.ajax({
        url:url,
        type: "POST",
        data: {price: price}
	}).done (function(data) {
		$('#accountBalance').text(data.user.accountBalance);
		var item = createStockHtmlStockPage(data);
		$('#starDescription').html(item);
		
		
	}).fail (function(err) {
	    console.error(err);
	});
});


$(document).on('click', '#cancelOrderPage', function (e) {
	e.preventDefault();
	var url = $(this).data("url");
	
	console.log(url);
	
	$.ajax({
        url:url,
        type: "GET"
	}).done (function(data) {
		
		var item = createStockHtmlStockPage(data);
		$('#starDescription').html(item);
		
		
	}).fail (function(err) {
	    console.error(err);
	});
});

$(document).on('click', '#sellStockAccount', function (e) {
	e.preventDefault();
	var url = $(this).data("url");
	
	var price = $(this).parent().find(".price_inp").val();
	console.log("price: " + price);
	
	if (!$.isNumeric(price)) {
		$(this).parent().parent().find('.msg').html("<p>Price should be numeric value</p>\n");
		return;
	}
	else if (parseInt(price) < 0) {
		$(this).parent().parent().find(".msg").html("<p>Price should be greater than 0</p>\n");
	
		return;
	}
	
	console.log(url);
	
	$.ajax({
        url:url,
        type: "POST",
        data: {price: price}
	}).done (function(data) {
		$('#accountBalance').text(data.user.accountBalance);
		$('.balance').text("Balance: $ " + data.user.accountBalance);
		if (data.relation == "AVAILABLE") {
			$('#userStocks').find('li').each(function(i){
		    	var current = $(this);
		    	//console.log(current);
		    	//console.log(current.attr("id"));
		    	console.log(data.page);
		    	if (current.attr("id") == data.stock.code) {
		    		//console.log(true);
		    		//var stockHtml = createStockHtml(data);
		    		$(current).remove();
		    		return;
		    		//$(current).html("");
		    	}
		    	i++;
		    });
		}
		
		
	}).fail (function(err) {
	    console.error(err);
	});
});

$(document).on('click', '#cancelOrderAccount', function (e) {
	e.preventDefault();
	var url = $(this).data("url");
	
	console.log(url);
	
	$.ajax({
        url:url,
        type: "GET"
	}).done (function(data) {
		
		$('#pendingOrders').find('li').each(function(i){
	    	var current = $(this);
	    	//console.log(current);
	    	//console.log(current.attr("id"));
	    	console.log(data.page);
	    	if (current.attr("id") == data.stock.code) {
	    		//console.log(true);
	    		//var stockHtml = createStockHtml(data);
	    		$(current).remove();
	    		return;
	    		//$(current).html("");
	    	}
	    	i++;
	    });
		
		
	}).fail (function(err) {
	    console.error(err);
	});
});

function createStockHtml(data) {
	var starList = "";
	var image = data.stock.imageName;
	var imagePath = "/stocktradinggame/resources/images/content/" + image;
	//starList += "<li class=\"star-item\">";
	starList += "<div class=\"star-item_no_a\" style=\"background: url(" + imagePath + ") no-repeat center center\" href=\"\">\n";
	var relation = data.relation;
	if (relation != null) {
		if (relation == "AVAILABLE") {
			starList += "<span class=\"star-hover\">\n";
				
			
				starList += "<div class=\"msg\">\n";
					starList += "<p></p>";
				starList += "</div>";
				
				starList += "<div class=\"desc\">\n";
					var dataUrl = "/stocktradinggame/buy-stock/" + data.stock.code;
					starList +=	"<div class=\"buybtn\" id=\"buyStock\" data-url=\"" + dataUrl + "\">\n";
					
						starList += "<span>BUY</span>\n";
					starList += "</div>\n";
					starList += "<span class=\"desc_container\">\n";
						var firstName = data.stock.firstName;
						var lastName = data.stock.lastName;
						var stockCode = data.stock.code;
						starList += "<h2 class=\"name\"><a href=\"/stocktradinggame/stock/" + stockCode + "\">" + firstName + " <br /> " + lastName + "</a></h2>\n";
						starList += "<span class=\"star-price-icon rightcolumn\">$</span>\n";
						var price = data.stock.sellPrice;
						starList += "<input type=\"text\" value=\"" + price + "\" class=\"price_inp rightcolumn\" id=\"price_inp\">\n";
					starList += "</span>\n";
				starList += "</div>\n";
			starList += "</span>\n";
		}
		else if (relation == "BOUGHT") {
			starList += "<span class=\"star-hover bought\">\n";
				starList += "<div class=\"msg\">\n";
					starList += "<p></p>";
				starList += "</div>";
			
				starList += "<div class=\"desc\">\n";
					starList += "<div class=\"option\">Your Stock</div>\n";

					var dataUrl = "/stocktradinggame/sell-stock/" + data.stock.code;
					starList +=	"<div class=\"buybtn\" id=\"sellStock\" data-url=\"" + dataUrl + "\">\n";
						starList += "<span>SELL</span>\n";
					starList += "</div>\n";
					starList += "<span class=\"desc_container\">\n";
						var firstName = data.stock.firstName;
						var lastName = data.stock.lastName;
						var stockCode = data.stock.code;
						starList += "<h2 class=\"name\"><a href=\"/stocktradinggame/stock/" + stockCode + "\">"  + firstName + " <br /> " + lastName + "</a></h2>\n";
						starList += "<span class=\"star-price-icon rightcolumn\">$</span>\n";
						var price = data.stock.sellPrice;
						starList += "<input type=\"text\" value=\"" + price + "\" class=\"price_inp rightcolumn\" id=\"price_inp\">\n";
					starList += "</span>\n";
				starList += "</div>\n"
			starList += "</span>\n";
		}
		else if (relation == "PLACED_ORDER"){
			starList += "<span class=\"star-hover ordered\">\n";
			
				starList += "<div class=\"msg\">\n";
					starList += "<p></p>";
				starList += "</div>";
			
				starList += "<div class=\"desc\">\n";
					starList += "<div class=\"option\">Your Order</div>\n";

					var dataUrl = "/stocktradinggame/cancel-order/" + data.stock.code;
					starList +=	"<div class=\"buybtn\" id=\"cancelOrder\" data-url=\"" + dataUrl + "\">\n";
						starList += "<span>CANCEL</span>\n";
					starList += "</div>\n";
					starList += "<span class=\"desc_container\">\n";
						var firstName = data.stock.firstName;
						var lastName = data.stock.lastName;
						var stockCode = data.stock.code;
						starList += "<h2 class=\"name\"><a href=\"/stocktradinggame/stock/" + stockCode + "\">" + firstName + " <br /> " + lastName + "</a></h2>\n";
						var price = data.order.price;
						starList += "<div class=\"star-price rightcolumn\">$ " + price + "</div>\n";
					starList += "</span>\n";
				starList += "</div>\n"
			starList += "</span>\n";
			
		}
		
		starList += "<div class=\"star-name\">\n";
			starList += "<h2>\n";
				var firstName = data.stock.firstName;
				var lastName = data.stock.lastName;
				starList += "<span>" + firstName + "<br />" + lastName + "</span>\n</h2>\n";
				var price = "";
				if (relation == "BOUGHT") {
					price = data.stock.buyPrice;
				}
				else {
					price = data.stock.sellPrice;
				}
				starList += "<span class=\"star-price\">$ " + price + "</span>\n";
			starList += "</div>\n";
		starList += "</div>\n";
	}
	else {
		starList += "<div class=\"star-name_new\">\n";
			starList += "<h2>\n";
				var firstName = data.stock.firstName;
				var lastName = data.stock.lastName;
				var stockCode = data.stock.code;
				starList += "<span><a href=\"/stocktradinggame/stock/" + stockCode + "\">" + firstName + "<br />" + lastName + "</a></span>\n";
				starList += "</h2>\n";
				var price = data.stock.sellPrice;
				starList += "<span class=\"star-price\">$ " + price + "</span>\n";
			starList += "</div>\n";
		starList += "</div>\n";
	
	}
	
	//starList += "</li>\n";
	
	//console.log(starList);
	
	return starList;
}

function createStockHtmlStockPage(data) {
	var item = "";
	console.log(data);
	var firstName = data.stock.firstName;
	var lastName = data.stock.lastName;
	var description = data.stock.description;
	item += "<h3>" + firstName + " " + lastName + "</h3>\n";
	item += "<p>" + description + "</p>\n";
	
	item += "<div class=\"rates\">\n";
		var lastTraded = data.stock.lastTraded;
		var buyPrice = data.stock.buyPrice;
		var sellPrice = data.stock.sellPrice;
		item += "<span class=\"lt\"><p>Last Traded<br><span class=\"prices\">$ " + lastTraded + "</span></p></span>\n";
		item += "<span class=\"lt\"><p>Best Buy Price<br><span class=\"prices\">$ " + buyPrice + "</span></p></span>\n";
		item += "<span class=\"lt\"><p>Best Sell Price<br><span class=\"prices\">$ " + sellPrice + "</span></p></span>\n";
	item += "</div>\n";	
		
	if (data.relation != null) {
		if (data.relation == "AVAILABLE") {
			
			item += "<div class=\"set_price\">\n";
				item += "<p>Stock Order</p>\n";
				item += "<span class=\"prices_in\">$</span>\n";
				item += "<input id=\"stockPrice\" type=\"text\" value=\"" + sellPrice  + "\" class=\"price_inp pos\" id=\"price_inp\">\n";
				var buyUrl = "/stocktradinggame/buy-stock/" + data.stock.code;
				item += "<div class=\"s_buybtn buybtn\" id=\"buyStockPage\" data-url=\"" + buyUrl + "\">\n";
					item += "<span>ORDER</span>\n";
				item += "</div>\n";
			item += "</div>\n";
			
		}
		else if (data.relation == "BOUGHT") {
			item += "<div class=\"set_price\">\n";
				item += "<p>Your Stock</p>\n";
				item += "<span class=\"prices_in\">$</span>\n";
				item += "<input id=\"stockPrice\" type=\"text\" value=\"" + sellPrice  + "\" class=\"price_inp pos\" id=\"price_inp\">\n";
				var sellUrl = "/stocktradinggame/sell-stock/" + data.stock.code;
				item += "<div class=\"s_buybtn buybtn\" id=\"sellStockPage\" data-url=\"" + sellUrl + "\">\n";
					item += "<span>SELL</span>\n";
				item += "</div>\n";
			item += "</div>\n";
		}
		else if (data.relation == "PLACED_ORDER") {
			item += "<div class=\"set_price\">\n";
				item += "<p>Your Order</p>\n";
				
				var orderPrice = data.order.price;
				item += "<span class=\"prices\">$ " + orderPrice + "</span>\n";
				var cancelUrl = "/stocktradinggame/cancel-order/" + data.stock.code;
				item += "<div class=\"s_buybtn buybtn\" id=\"cancelOrderPage\" data-url=\"" + cancelUrl + "\">\n";
					item += "<span>CANCELL</span>\n";
				item += "</div>\n";
			item += "</div>\n";
		}
		//<div class="stock-error">
		//	<span class="error-register-message"></span>
		//	</div>
		item += "<div class=\"stock-error\">\n";
			item += "<span class=\"error-register-message\"></span>\n";
		item += "</div>\n";
	}
	return item;

}

function refreshPage() {
	
	var currenPage = $('#currentPage').val();
	console.log("Page refresh: " + currentPage);
	
	var url = $('#pageUrl').val();
	console.log(url);
	
	$.ajax({
        url:url,
        type: "GET"
	}).done (function(data) {
		console.log(data);
		var len = data.length;
		
		for (var i = 0; i < len; i++) {
			$('#starList').find('li').each(function(j){
		    	var current = $(this);
		    	var $cur = $(this);
		    	if ($cur.is(":hover")) {
		    		console.log("HOVERED ITEM");
		    		console.log(current.attr("id"));
		    		
		    	}
		    	//console.log(current);
		    	//console.log(current.attr("id"));
		    	else if (current.attr("id") == data[i].stock.code) {
		    		//console.log(true);
		    		var stockHtml = createStockHtml(data[i]);
		    		$(current).html(stockHtml);
		    	}
		    	j++;
		    });
		}
		/*var len = data.length;
		var starList = "";
		for (var i = 0; i < len; i++) {
			var code = data[i].stock.code;
			starList += "<li class=\"star-item\" id=\"" + code + "\">\n";
			starList += createStockHtml(data[i]);
			starList += "</li>\n";
			
		}
		
		$("#starList").html(starList);*/
		
		
	
	}).fail (function(err) {
	    console.error(err);
	});
	
}
function refreshRunningLine() {
	//get all stocks for running line
	console.log("Refresh running line");
	var refreshUrl = $('#refreshUrl').val();
	
	$.ajax({
        url: refreshUrl,
        type: "GET"
	}).done (function(data) {
		//console.log(data);
		var len = data.length;
		var stocks = "";
		for (var i = 0; i < len; i++) {
			
			var lastTraded = data[i].lastTraded;
			var firstName = data[i].firstName;
			var lastName = data[i].lastName;
			stocks += "<span class=\"stock_name\">" + firstName + " " + lastName + "</span>\n";
			stocks += "<span class=\"tr_price\">\n";
				if (data[i].up) {
					var url = "resources/images/up.png";
					stocks += "<div class=\"arr\" style=\"background: url(" + url + ") no-repeat center center\"></div>\n";
				}
				else {
					var url = "resources/images/down.png";
					stocks += "<div class=\"arr\" style=\"background: url(" + url + ") no-repeat center center\"></div>\n";
				}
				
				stocks += "Traded Price: <span class=\"money\">$" + lastTraded + "</span>\n";
			stocks += "</span>\n";
		
		
		}
		
		$("#allStockScroll").html(stocks);
		
		
	
	}).fail (function(err) {
	    console.error(err);
	});
}
//refresh the page every 30 seconds
window.setInterval(function(){
	if ($('section.market-page').length > 0) {
		refreshRunningLine();
	 	refreshPage();
	}
}, 5000);

