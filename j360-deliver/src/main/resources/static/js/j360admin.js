/**
 * Created by xumin on 2015/8/21.
 */


$(document).ready(function () {
    // Add body-small class if window less than 768px
    if ($(this).width() < 769) {
        $('body').addClass('body-small')
    } else {
        $('body').removeClass('body-small')
    }

    // MetsiMenu
    $('#side-menu').metisMenu();

    // Collapse ibox function
    $('.collapse-link').click(function () {
        var ibox = $(this).closest('div.ibox');
        var button = $(this).find('i');
        var content = ibox.find('div.ibox-content');
        content.slideToggle(200);
        button.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
        ibox.toggleClass('').toggleClass('border-bottom');
        setTimeout(function () {
            ibox.resize();
            ibox.find('[id^=map-]').resize();
        }, 50);
    });

    // Close ibox function
    $('.close-link').click(function () {
        var content = $(this).closest('div.ibox');
        content.remove();
    });

    // Fullscreen ibox function
    $('.fullscreen-link').click(function() {
        var ibox = $(this).closest('div.ibox');
        var button = $(this).find('i');
        $('body').toggleClass('fullscreen-ibox-mode');
        button.toggleClass('fa-expand').toggleClass('fa-compress');
        ibox.toggleClass('fullscreen');
        setTimeout(function() {
            $(window).trigger('resize');
        }, 100);
    });

    // Close menu in canvas mode
    $('.close-canvas-menu').click(function () {
        $("body").toggleClass("mini-navbar");
        SmoothlyMenu();
    });

    // Open close right sidebar
    $('.right-sidebar-toggle').click(function () {
        $('#right-sidebar').toggleClass('sidebar-open');
    });

    // Initialize slimscroll for right sidebar
    $('.sidebar-container').slimScroll({
        height: '100%',
        railOpacity: 0.4,
        wheelStep: 10
    });

    // Open close small chat
    $('.open-small-chat').click(function () {
        $(this).children().toggleClass('fa-comments').toggleClass('fa-remove');
        $('.small-chat-box').toggleClass('active');
    });

    // Initialize slimscroll for small chat
    $('.small-chat-box .content').slimScroll({
        height: '234px',
        railOpacity: 0.4
    });

    // Small todo handler
    $('.check-link').click(function () {
        var button = $(this).find('i');
        var label = $(this).next('span');
        button.toggleClass('fa-check-square').toggleClass('fa-square-o');
        label.toggleClass('todo-completed');
        return false;
    });

    // Append config box / Only for demo purpose
    // Uncomment on server mode to enable XHR calls
    /*$.get("skin-config.html", function (data) {
        if (!$('body').hasClass('no-skin-config'))
            $('body').append(data);
    });*/

    // Minimalize menu
    $('.navbar-minimalize').click(function () {
        $("body").toggleClass("mini-navbar");
        SmoothlyMenu();

    });

    // Tooltips demo
    $('.tooltip-demo').tooltip({
        selector: "[data-toggle=tooltip]",
        container: "body"
    });

    // Move modal to body
    // Fix Bootstrap backdrop issu with animation.css
    $('.modal').appendTo("body");

    // Full height of sidebar
    function fix_height() {
        var heightWithoutNavbar = $("body > #wrapper").height() - 51;
        $(".sidebard-panel").css("min-height", heightWithoutNavbar + "px");

        var navbarHeigh = $('nav.navbar-default').height();
        var wrapperHeigh = $('#page-wrapper').height();

        if (navbarHeigh > wrapperHeigh) {
            $('#page-wrapper').css("min-height", navbarHeigh + "px");
        }

        if (navbarHeigh < wrapperHeigh) {
            $('#page-wrapper').css("min-height", $(window).height() + "px");
        }

        if ($('body').hasClass('fixed-nav')) {
            $('#page-wrapper').css("min-height", $(window).height() - 50 + "px");
        }

        j360.wrapperContentHeight = parseInt(j360.pageWrapper.css("min-height")) - 50;

    }

    fix_height();

    // Fixed Sidebar
    $(window).bind("load", function () {
        if ($("body").hasClass('fixed-sidebar')) {
            $('.sidebar-collapse').slimScroll({
                height: '100%',
                railOpacity: 0.9
            });
        }
    });

    // Move right sidebar top after scroll
    $(window).scroll(function () {
        if ($(window).scrollTop() > 0 && !$('body').hasClass('fixed-nav')) {
            $('#right-sidebar').addClass('sidebar-top');
        } else {
            $('#right-sidebar').removeClass('sidebar-top');
        }
    });

    $(window).bind("load resize scroll", function () {
        if (!$("body").hasClass('body-small')) {
            fix_height();
        }
    });

    $("[data-toggle=popover]")
        .popover();

    // Add slimscroll to element
    $('.full-height-scroll').slimscroll({
        height: '100%'
    })
});


// Minimalize menu when screen is less than 768px
$(window).bind("resize", function () {
    if ($(this).width() < 769) {
        $('body').addClass('body-small')
    } else {
        $('body').removeClass('body-small')
    }
});

// Local Storage functions
// Set proper body class and plugins based on user configuration
$(document).ready(function () {
    if (localStorageSupport) {

        var collapse = localStorage.getItem("collapse_menu");
        var fixedsidebar = localStorage.getItem("fixedsidebar");
        var fixednavbar = localStorage.getItem("fixednavbar");
        var boxedlayout = localStorage.getItem("boxedlayout");
        var fixedfooter = localStorage.getItem("fixedfooter");

        var body = $('body');

        if (fixedsidebar == 'on') {
            body.addClass('fixed-sidebar');
            $('.sidebar-collapse').slimScroll({
                height: '100%',
                railOpacity: 0.9
            });
        }

        if (collapse == 'on') {
            if (body.hasClass('fixed-sidebar')) {
                if (!body.hasClass('body-small')) {
                    body.addClass('mini-navbar');
                }
            } else {
                if (!body.hasClass('body-small')) {
                    body.addClass('mini-navbar');
                }

            }
        }

        if (fixednavbar == 'on') {
            $(".navbar-static-top").removeClass('navbar-static-top').addClass('navbar-fixed-top');
            body.addClass('fixed-nav');
        }

        if (boxedlayout == 'on') {
            body.addClass('boxed-layout');
        }

        if (fixedfooter == 'on') {
            $(".footer").addClass('fixed');
        }
    }
});

// check if browser support HTML5 local storage
function localStorageSupport() {
    return (('localStorage' in window) && window['localStorage'] !== null)
}

// For demo purpose - animation css script
function animationHover(element, animation) {
    element = $(element);
    element.hover(
        function () {
            element.addClass('animated ' + animation);
        },
        function () {
            //wait for animation to finish before removing classes
            window.setTimeout(function () {
                element.removeClass('animated ' + animation);
            }, 2000);
        });
}

function SmoothlyMenu() {
    if (!$('body').hasClass('mini-navbar') || $('body').hasClass('body-small')) {
        // Hide menu in order to smoothly turn on when maximize menu
        $('#side-menu').hide();
        // For smoothly turn on menu
        setTimeout(
            function () {
                $('#side-menu').fadeIn(500);
            }, 100);
    } else if ($('body').hasClass('fixed-sidebar')) {
        $('#side-menu').hide();
        setTimeout(
            function () {
                $('#side-menu').fadeIn(500);
            }, 300);
    } else {
        // Remove all inline style from jquery fadeIn function to reset menu state
        $('#side-menu').removeAttr('style');
    }
}

// Dragable panels
function WinMove() {
    var element = "[class*=col]";
    var handle = ".ibox-title";
    var connect = "[class*=col]";
    $(element).sortable(
        {
            handle: handle,
            connectWith: connect,
            tolerance: 'pointer',
            forcePlaceholderSize: true,
            opacity: 0.8
        })
        .disableSelection();
}

//end

window.j360 = {
    siteMenu: $("#side-menu"),
    siteMenuLinks: $('#side-menu > li:not(.nav-header) > a'),
    wrapper: $("#wrapper"),
    navHeader: $(".nav-header"),
    pageWrapper: $("#page-wrapper"),
    footer: $(".footer"),
    fixedTop: $("nav.navbar-fixed-top"),
    wrapperContentHeight:0,
    window: $(window),
    body: $("body"),
    bodyHtml: $("body, html"),
    document: $(document),
    screenSize: "",
    sideBar:$('.sidebar-collapse'),
    megaDropdown: $(".mega-dropdown-toggle"),
    dialogOpenLink: $("a.dialog-open-link"),
    isMobile: function () {
        return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)
    }(),
    randomInt: function (n, t) {
        return Math.floor(Math.random() * (t - n + 1) + n)
    }
}

var menuSet = j360.siteMenuLinks;
/*
 var screenSize = "";
 var c = function () {
 var t, wrapperWidth = $("#wrapper").width();
 t = 740 >= wrapperWidth ? "xs" : wrapperWidth > 740 && 992 > wrapperWidth ? "sm" : wrapperWidth >= 992 && 1200 >= wrapperWidth ? "md" : "lg", wrapperWidth != t && (s = t, screenSize = t, "sm" == screenSize)
 };*/
//Nav的mini模式下的操作
var ___time;
menuSet.each(function () {
    var thisSet = $(this);
    var thisLabel = thisSet.children(".nav-label");
    var thisSecondNav = thisSet.siblings(".collapse");

    //去除关闭动作，避开那些点击之外的sidebar的内容
    $(document).click(function (e) {
        if (!$(e.target).closest(".sidebar-collapse").length && !$(e.target).closest("div.menu-popover").length) {
            thisSet.removeClass("hover").popover("hide")
        }
    })
    $(".sidebar-collapse").on("update", function () {
        thisSet.removeClass("hover").popover("hide")
    })

    //是否有二级菜单
    var hasChildren = thisSecondNav.length ? (thisSet.on("click.active", function (e) {
        e.preventDefault()
    }).parent("li").removeClass("active"), true) : false;

    thisSet.popover({
        animation: false,
        trigger: "manual",
        container: "body",
        viewport: thisSet,
        html: true,
        title: function () {
            return thisLabel.html();
        },
        content: function () {
            //判断是否有二级菜单，否则显示本身
            return hasChildren ? '<div class="popovernav"><ul class="nav nav-second-level "> ' + thisSecondNav.html() + '</ul></div>' : '';
        },
        template: '<div class="popover menu-popover"><h4 class="popover-title"></h4><div class="popover-content"></div></div>'
    }).on("click.popover", function (e) {
        if (j360.body.hasClass("fixed-sidebar") && j360.body.hasClass("mini-navbar") && j360.body.hasClass("fixed-nav")) {
            menuSet.popover("hide");
            thisSet.addClass("hover").popover("show");
        }
        //e.preventDefault();   //取消a的默认事件
    }).on("shown.bs.popover", function () {
        //计算popover的高度和位置，还需要考虑到滚动条情况？？？
        if (hasChildren) {
            var e = parseInt($(".navbar-default.navbar-static-side").css("margin-top"));   //菜单的y坐标
            var a = thisSet.outerHeight() - 10;                   //本菜单的高度=40
            //总高度-y坐标-本菜单的高度=剩余的可用的高度
            var menupopover_top = parseInt($(".menu-popover").css("top"));

            var o = (j360.body.hasClass("fixed-sidebar")) ? $(window).outerHeight() - e - a : $(document).height() - e - a;
            //导航的总高度
            var l = j360.body.find(".sidebar-collapse").outerHeight();
            o = o - menupopover_top;

            //如果下面的高度不够，设置pop-content的bottom = top f==popover-content-popover-top
            var menupopover_content = $(".menu-popover div.popover-content");

            var content_height = menupopover_content.find(".popovernav").outerHeight();
            //如果top-a-本身的高度 < 0
            //如果剩余的高度不够，此处暂时不做二级菜单的滚动条处理
            if (o - content_height < 0) {
                menupopover_content.css({
                    top: "",
                    bottom: a + "px"
                })
            }
        }
    }).on("hidden.bs.popover", function () {
        thisSet.removeClass("hover"), hasChildren ? thisSecondNav.removeAttr("style").appendTo(thisSet.parent()) : false;
    }).hover(function () {
            //in
            if (j360.body.hasClass("fixed-sidebar") && j360.body.hasClass("mini-navbar") && j360.body.hasClass("fixed-nav")) {
                menuSet.popover("hide");
                thisSet.addClass("hover").popover("show");
            }
        }, function () {
            //out
            //如果是本身、加上弹出的popover则不隐藏
            //thisSet.removeClass("hover").popover("hide");
            //r = popover
            var ___menuPopover = $("div.menu-popover");
            clearInterval(___time);
            ___time = setInterval(function () {
                if (___menuPopover.length) {
                    ___menuPopover.one("mouseleave", function () {
                        thisSet.removeClass("hover").popover("hide");
                    })
                    if (!___menuPopover.is(":hover")) {
                        thisSet.removeClass("hover").popover("hide");
                    }
                    clearInterval(___time);
                }
            }, 500)
        }
    )
})


//导航的dropdown
j360.megaDropdown.on("click", function (e) {
    j360.megaDropdown.parent().toggleClass("open");
    e.preventDefault();
})
j360.bodyHtml.on("click", function (e) {
    //关闭操作，除弹出对话框之外的操作
    if (j360.megaDropdown.length) {
        if (!$(e.target).closest(".mega-dropdown").length) {
            j360.megaDropdown.parent().removeClass("open")
        }
        ;
    }
})

/**
 * Created by xumin on 2015/8/27.
 */
//ie下乱码
$.ajaxSetup({
    contentType: "application/x-www-form-urlencoded; charset=utf-8",
    cache:false,
    statusCode:{
        /*500:function(){
         $.smallBox({
         title : "操作失败",
         content : "<i class='fa fa-clock-o'></i> <i>"
         + "系统返回异常，我们将会记录该异常并立即解决，或者请与您的服务人员联系。" + "</i>",
         color : "#C46A69",
         iconSmall : "fa fa-times fa-2x fadeInRight animated",
         timeout : 6000
         });
         }*/
    },
    complete:function(XMLHttpRequest,textStatus){
        if(textStatus=="parsererror"){
            /*$.smallBox({
             title : "操作失败",
             content : "<i class='fa fa-clock-o'></i> <i>"
             + "返回信息失败，刷新页面检查是否登录超时" + "</i>",
             color : "#C46A69",
             iconSmall : "fa fa-times fa-2x fadeInRight animated",
             timeout : 6000
             });*/
        } else if(textStatus=="error"){
            /*$.smallBox({
             title : "操作失败",
             content : "<i class='fa fa-clock-o'></i> <i>"
             + "系统返回异常，系统已记录该异常或请与您的服务人员联系。" + "</i>",
             color : "#C46A69",
             iconSmall : "fa fa-times fa-2x fadeInRight animated",
             timeout : 6000
             });*/
        }
    }
});
var _title = "泛销";
$.navAsAjax = true; // Your left nav in your app will no longer fire ajax calls

var ie = ( function() {
    var undef, v = 3, div = document.createElement('div'), all = div.getElementsByTagName('i');
    while (div.innerHTML = '<!--[if gt IE ' + (++v) + ']><i></i><![endif]-->', all[0]);
    return v > 4 ? v : undef;
}()); // do we need this?


/*
 * ELEMENT EXIST OR NOT
 * Description: returns true or false
 * Usage: $('#myDiv').doesExist();
 */

jQuery.fn.doesExist = function() {
    return jQuery(this).length > 0;
};

/*
 * LOAD SCRIPTS
 * Usage:
 * Define function = myPrettyCode ()...
 * loadScript("js/my_lovely_script.js", myPrettyCode);
 */

var jsArray = {};

function loadScript(scriptName, callback) {
    if (!jsArray[scriptName]) {
        jsArray[scriptName] = true;

        // adding the script tag to the head as suggested before
        var body = document.getElementsByTagName('body')[0];
        var script = document.createElement('script');
        script.type = 'text/javascript';
        script.src = scriptName;

        // then bind the event to the callback function
        // there are several events for cross browser compatibility
        //script.onreadystatechange = callback;
        script.onload = callback;

        // fire the loading
        body.appendChild(script);

    } else if (callback) {// changed else to else if(callback)
        //console.log("JS file already added!");
        //execute function
        callback();
    }

}

/* ~ END: LOAD SCRIPTS */

/*
 * APP AJAX REQUEST SETUP
 * Description: Executes and fetches all ajax requests also
 * updates naivgation elements to active
 */
if($.navAsAjax)
{
    // fire this on page load if nav exists
    if ($('#side-menu').length) {
        checkURL();
    };

    $(document).on('click', 'a.dialog-open-link', function(e) {
        e.preventDefault();
        $this = $(e.currentTarget);
        //表单内部的打开窗口链接操作，取消普通的超链接操作
        var title = $this.attr("title");
        var link = $this.attr("href");
        __open(title,link);
    });

    $(document).on('click', '#side-menu a[href!="#"]', function(e) {
        e.preventDefault();
        $this = $(e.currentTarget);

        // if parent is not active then get hash, or else page is assumed to be loaded
        if (!$this.parent().hasClass("active") && !$this.attr('target')) {
            window.location.hash = $this.attr('href');
        }
    });

    // fire links with targets on different window
    $(document).on('click', '#side-menu a[target="_blank"]', function(e) {
        e.preventDefault();
        $this = $(e.currentTarget);
        window.open($this.attr('href'));
    });

    // fire links with targets on same window
    $(document).on('click', '#side-menu a[target="_top"]', function(e) {
        e.preventDefault();
        $this = $(e.currentTarget);
        window.location = ($this.attr('href'));
    });

    // all links with hash tags are ignored
    $(document).on('click', '#side-menu a[href="#"]', function(e) {
        e.preventDefault();
    });

    // DO on hash change
    $(window).on('hashchange', function() {
        checkURL();
    });
}

//use when url not changed,but param must change then use this tag
var nonReloadUrlTag = "non-reload=true";

// CHECK TO SEE IF URL EXISTS
function checkURL() {
    if(!location.hash){
        url = "";
    }else{
        //get the url by removing the hash
        url = location.hash.replace(/^#/, '');
    }
    var ajax_container = $("#container");
    // Do this if url exists (for page refresh, etc...)
    if (url) {
        //if has non-reload then url changed,but page non-reload
        if(url.indexOf(nonReloadUrlTag) != -1){
            if(preHash != ""){
                var cHash = url;
                if(url.indexOf("?")!=-1){
                    var vHash = preHash.split("?");
                    cHash = vHash[0];
                }
                if(preHash == cHash){
                    return ;
                }
            }
        }else{
            //empty the preHash.
            preHash = "";
        }
        // remove all active class
        $('#side-menu li.active').removeClass("active");
        // match the url and add the active class
        $('#side-menu li:has(a[href="' + url + '"])').addClass("active");
        title = ($('#side-menu a[href="' + url + '"]').attr('title'))

        // change page title from global var
        document.title = (title || document.title);
        //console.log("page title: " + document.title);

        // parse url to jquery
        loadURL(url, ajax_container);
    } else {
        // grab the first URL from nav
        var defaultLink = $('#side-menu > li:first-child > a[href!="#"]');
        if(!defaultLink.length){
            defaultLink = $('#side-menu ul > li:first-child > a[href!="#"]');
        }
        //update hash
        window.location.hash = defaultLink.attr('href');

    }

}

//load the root content by url
var preHash = "";
function loadRootUrl(url){
    getPreHashUrl();
    window.location.hash = url;
}
function getPreHashUrl(){
    if(!location.hash){
        preHash = "";
    }else{
        //get the url by removing the hash
        preHash = location.hash.replace(/^#/, '');
    }
    if(preHash.indexOf("?")!=-1){
        var vHash = preHash.split("?");
        preHash = vHash[0];
    }
}
// LOAD AJAX PAGES
function drawBreadCrumb() {
    $("ol.breadcrumb li:last-child").remove();
    $('#side-menu li.active > a').each(function() {
        $("ol.breadcrumb").append($("<li></li>").html($.trim($(this).clone().children(".nav").remove().end().text())));
    });
}

function loadURL(url, ajax_container) {
    //console.log(ajax_container)
    $.ajax({
        type : "GET",
        url : url,
        dataType : 'html',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        cache : false, // (warning: this will cause a timestamp and will call the request twice)
        beforeSend : function() {
            // cog placed
            ajax_container.html('<h1><i class="fa fa-cog fa-spin"></i> 加载中...</h1>');
            // only draw breadcrumb if it is content material
            // TODO: check if document title injection refreshes in IE...
            // TODO: see the framerate for the animation in touch devices
            if (ajax_container[0] == $("#container")[0]) {
                drawBreadCrumb();
                // update title with breadcrumb...
                document.title = _title + " - " + $(".breadcrumb li:last-child").text();
                // scroll up
                $("html, body").animate({
                    scrollTop : 0
                }, "fast");
            } else {
                ajax_container.animate({
                    scrollTop : 0
                }, "fast");
            }
        },
        /*complete: function(){
         // Handle the complete event
         // alert("complete")
         },*/
        success : function(data) {
            // cog replaced here...
            // alert("success")

            ajax_container.css({
                opacity : '0.0'
            }).html(data).delay(50).animate({
                opacity : '1.0'
            }, 300);

        },
        error : function(xhr, ajaxOptions, thrownError) {
            ajax_container.html('<h4 style="margin-top:10px; display:block; text-align:left"><i class="fa fa-warning txt-color-orangeDark"></i> 错误 404! 页面没找到！</h4>');
        },
        async : true
    });

    //console.log("ajax request sent");
}

function loadMoreURL(url, ajax_container) {
    //console.log(ajax_container)

    $.ajax({
        type : "GET",
        url : url,
        dataType : 'html',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        cache : false, // (warning: this will cause a timestamp and will call the request twice)
        beforeSend : function() {
            // cog placed
        },
        /*complete: function(){
         // Handle the complete event
         // alert("complete")
         },*/
        success : function(data) {
            // cog replaced here...
            // alert("success")

            ajax_container.css({
                opacity : '0.0'
            }).append(data).delay(50).animate({
                opacity : '1.0'
            }, 300);

        },
        error : function(xhr, ajaxOptions, thrownError) {
            ajax_container.append('<h4 style="margin-top:10px; display:block; text-align:left"><i class="fa fa-warning txt-color-orangeDark"></i> 错误 404! 页面没找到！</h4>');
        },
        async : true
    });

    //console.log("ajax request sent");
}



/**	定义参数
 var _ajax = {url:'',data:{},success:function(data){

		}
	};*/
/**
 通用执行方法*/
function __ajax(_ajax){
    $.ajax({
        url : _ajax.url,
        cache : false,
        data : _ajax.data,
        dataType : "json",
        async : true,
        success : function(data) {
            _ajax.success(data)
        }
    })
}

function __open(title,url,buttons){
    BootstrapDialog.show({
        title: title,
        size: BootstrapDialog.SIZE_WIDE,
        message: function(dialog) {
            var $message = $('<div class="form-read"></div>');
            var pageToLoad = dialog.getData('pageToLoad');
            $message.load(pageToLoad);
            return $message;
        },
        data: {
            'pageToLoad': url
        },
        buttons:buttons
    });
}
//模板
function fn_(fn_name,data){
    $("#"+fn_name).html(
        $("#"+fn_name+"_template").render(data)
    );
}


function __confirm(title,callback){
    swal({
            title: title,
            text: "",
            type: "warning",
            showCancelButton: true,
            showLoaderOnConfirm: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定！",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: true },
        function () {
            callback.confirm();
        });
}

function __form_save(form_id,actionUrl,opt,callback_func){
    var defaults={
        dialogId:null,
        msg:true
    };
    var plugin = this;
    plugin.settings = $.extend({}, defaults, opt);
    $("form input:disabled").removeAttr("disabled");

    var data = $("#"+form_id).serialize();
    __ajax_action(actionUrl,data,plugin.settings,function(pdata){
        if(plugin.settings.msg){
            __show(pdata);
        }
        if(typeof callback_func === 'function'){
            callback_func(pdata);
        }

    });

}
//发送ajax请求
function __ajax_action(vActionUrl,data,opt,callback_func){
    var defaults={
    };
    var plugin = this;
    plugin.settings = $.extend({}, defaults, opt);
    $.ajax({
        url : vActionUrl,
        method:"post",
        cache : false,
        dataType : "json",
        async : true,
        data : data,
        success : function(data) {
            if(typeof callback_func === "function"){
                callback_func(data);
            }
        }
    });
}
//显示消息，data是返回的数据
function __show(data){
    if(data.result.errorCode == 1 || data.result.errorCode == "1"){
        toastr.success(data.title,data.result.errorMessage);
    }else{
        toastr.error(data.title,data.result.errorMessage);
    }
}