$(function(){

    //点击缩略图，打开新的页面用于显示图片
    $('.ms_thumbnail, .choose_pic').click(function() {
        var src = $(this).find("img").attr("src");
        window.open(src)
    })

    
    //点击上传图片的按钮
    var upload_btn;
    $('.ms_upload_file_btn').click(function(){
        $("#replace_input").click();
        upload_btn = this;
    });
    $("#replace_input").change(function(){
        if($(this).val() !== '') {
            $("#submit_form").submit();
            $("#exec_target").one("load", function(){
            var data = $(window.frames.exec_target.document.body).find("#imgurl").data("url");
            if(data !== null){
                $(upload_btn).parents("tr").find(".ms_feedback").attr("src",data);
                $(upload_btn).parents("tr").find("input[type='hidden']").val(data);
            }
        });
        }
    })

})