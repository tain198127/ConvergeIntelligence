/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * need after jquery
 */
var www_cicore_request = '';
var cicore_request = '';
$(function() {
    www_cicore_request = {
        url: 'Entrance',
        async: true,
        beforeSend: function(XHR) {

        },
        //dataType :'json',
        cache: true,
        complete: function(XHR, TS) {

        },
        contents: {},
        contentType: 'application/x-javascript;charset=UTF-8',
        crossDomain: true,
        /*
         data:{
         Action:'ShowName',
         Class:'www.ConvergeIntelligence.com.Analyze',
         data:{1111:1111,'2222':2222}
         },*/
        dataFilter: function(d, f) {

        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert('error:[' + textStatus + ']');
        },
        global: true,
        headers: {},
        ifModified: true,
        password: '11111',
        statusCode: {
            //404:function(){alert('page not found');},
            //408:function(){alert('408超时');}
        },
        success: function(data, textStatus, jqXHR) {
            //alert('ok');
            //alert(jqXHR);
        },
        timeout: 2500,
        type: 'POST'
    };
    cicore_request = function(cfg){
        var str = $.toJSON(cfg.data);
        $.extend(true,cfg,{data:str});
        var req = $.extend(true,{},www_cicore_request,cfg);
        $.ajax(req);
    };
    (function() {
        $('[ajaxsub="true"]')
                .each(function(item) {
                    var triggerEvent = this.getAttribute('triggerEvent') || 'click';
                    $(this).bind(triggerEvent, function(e) {
                        $(this).trigger('fireRequest', this);
                    });
                })//定义了triggerEvent
                .bind('fireRequest', function(event, obj) {
                    var requestParam = $(this).prop('triggerParam');
                    var str = $.toJSON(requestParam.data);
                    var realData = $.extend(true, {}, requestParam, {data: str});
                    var finalReq = $.extend(true, {}, www_cicore_request, realData);
                    $.ajax(finalReq);

                });//实际的AJAX请求方法
    })();
    

    (function() {
        var finder = $('#photoshow > li');
        finder.hide();
        $(finder.get(0)).show();
        var psLength = finder.length;
        var h= finder.parent().height();
        var w = finder.parent().width();
        var timeSpan = 5000;
        var timeoutHandler = '';
        /*
        finder.parent().css({
            display:'block',
            width:w+'px',
            height:h+'px'
        });*/
        var sild = function(idx) {
            var nextIdx = (idx === psLength - 1)?0:idx + 1;
            var pos = $(finder.get(idx)).position();
            $(finder.get(nextIdx)).css({
                position:'absolute',
                left:pos.left+'px',
                top:pos.top+'px'
            });
            $(finder.get(idx)).fadeOut(timeSpan);
            $(finder.get(nextIdx)).fadeIn(timeSpan, function() {
               timeoutHandler = setTimeout(sild(nextIdx), timeSpan);
            })
        };
        sild(0);
    })();
});

