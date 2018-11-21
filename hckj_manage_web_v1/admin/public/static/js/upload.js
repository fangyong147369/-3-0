/**
 * Created by pwx on 2016/8/1.
 */
(function ($) {
    $.fn.extend({
        createUpload: function (opt) {
            opt = opt || {};
            var list = $(this).find('.fileList');
            var btn = $(this).find('.filePicker');
            var img = $(this).find('input');
            var type = opt.type || 'uploadimage';
            btn.click(function () {
                if (list.find('img').length > 0)
                    return false;
            });
            var uploader = WebUploader.create({
                auto: true,
                swf: '/lib/webuploader/0.1.5/Uploader.swf',
                // 文件接收服务端。
                server: '/util/uploadImage',
                // 选择文件的按钮。可选。
                // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                pick: btn,
                // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
                resize: false,
                // 只允许选择图片文件。
                duplicate: false,
                accept: {
                    title: 'Images',
                    extensions: 'gif,jpg,jpeg,bmp,png,zip,apk',
                    mimeTypes: opt.mime || 'image/*'
                }
            });

            if (img.val()) {
                if (type == 'uploadimage') {
                    var $div = $('<div style="width: 140px;height:70px;float:left;"></div>');
                    var $img = $('<img style="width: 140px;height:50px;" src="' + img.val() + '"/>');
                    var $a = $('<a style="margin-left:35px;" href="javascript:void(0);" onclick="$(this).parent().remove();">删除</a>');
                    $div.append($img).append($a);
                    list.append($div);
                } else {
                    var $div = $('<div style="width: 500px;height:20px;float:left;"></div>');
                    var $span = '<span><a target="_blank" href="' + btn.val() + '">' + btn.val() + '</a></span>';
                    var $a = $('<a style="margin-left:35px;" href="javascript:void(0);" onclick="$(this).parent().remove();">删除</a>');
                    $div.append($span).append($a);
                    list.append($div);
                }
            }

            // 文件上传成功，给item添加成功class, 用样式标记上传成功。
            uploader.on('uploadSuccess', function (file, json) {
                var $div = null;
                if (json.type == 'image') {
                    $div = $('<div style="width: 140px;height:70px;float:left;" id="' + file.id + '"></div>');
                    var $img = $('<img style="width: 140px;height:50px;" src="' + json.url + '"/>');
                    $div.append($img);
                    var $a = $('<a style="margin-left:35px;" href="javascript:void(0);" onclick="$(this).parent().remove();">删除</a>');
                    $div.append($a);
                    list.append($div);
                } else {
                    $div = $('<div style="width: 500px;height:20px;float:left;" id="' + file.id + '"></div>');
                    var $span = '<span><a target="_blank" href="' + json.url + '">' + json.url + '</a></span>';
                    $div.append($span);
                    var $a = $('<a style="margin-left:35px;" href="javascript:void(0);" onclick="$(this).parent().remove();">删除</a>');
                    $div.append($a);
                    list.empty().append($div);
                }
                img.val(json.url);
            });

            // 文件上传失败，显示上传出错。
            uploader.on('uploadError', function (file) {
                console.log(file);
                Huimodal_alert('上传出错', 2000);
            });
        }
    });
})(jQuery);
