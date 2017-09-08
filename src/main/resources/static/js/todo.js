/**
 * Created by lenovo on 2017  八月  18  0018.
 */
//底部
function addFooter(number, url) {
    //url用来判断是哪个数据
    //设置下边
    //获取item的数量
    //根据ul的孩子个数
    //拼接底部元素
    $("#footer").empty();
    var span = "<span id='todo-count'><strong>" + number + "</strong> item left</span>";
    var all = "<li><a href='/todos/all'>All</a></li>";
    var active = "<li><a href='/todos/active'>Active</a></li>";
    var completed = "<li><a href='/todos/completed'>Completed</a></li>";
    var ul = "<ul id='filters'>" + all + active + completed + "</ul>";
    $("#footer").append(span + ul);
    $("[href='" + url + "']").addClass('selected');
}

//依次添加li div(input(checkbox) label button(销毁按钮)) input(保存数据)
function add(value, id, compeleted) {

    //显示主布局
    if ($("#main").css('display') != 'block') {
        $("#main").css('display', 'block');
    }
    //增加li
    var label = "<label>" + value + "</label>";
    var button = "<button class='destroy'></button>";
    var inputedit = "<input class='edit' value='" + value + "'></input>";
    var li;
    var input;
    var div;
    /*
     * 0表示真
     * */
    if (compeleted == 0) {
        input = "<input class='toggle' type='checkbox' checked='true'></input>";
        div = "<div>" + input + label + button + "</div>";
        li = "<li data-id='" + id + "' class='completed'>" + div + inputedit + "</li>";
    } else {
        input = "<input class='toggle' type='checkbox'></input>";
        div = "<div>" + input + label + button + "</div>";
        li = "<li data-id='" + id + "'>" + div + inputedit + "</li>";
    }

    //获取ul对象
    $("#todo-list").append(li);

    //设置按钮style
    if ($("#footer").css('display') == 'none') {
        $("#footer").css('display', 'block');
    }
    //更新数量
    editCount($("#todo-list li").length);
}

//修改显示的数量
function editCount(count) {
    $("#todo-count strong").text(count);
}

//重置
function reset() {
    var all = $("#todo-list li").length;
    var selected = $("#todo-list li.completed").length;
    var isChecked = $("#toggle-all").prop('checked');
    if (all == 0 || all != selected) {
        $("#toggle-all").prop('checked', isChecked ? !isChecked : isChecked);
    } else if (all == selected) {
        $("#toggle-all").prop('checked', isChecked ? isChecked : !isChecked);
    }
    //重新设置数量
    editCount(all - selected);
}

//初始化数据
function init(url) {
    //清空ul中li数据
    $.ajax({
        url: url,
        type: "GET",
        dataType: 'json',
        success: function (obj) {
            //选择不同类型的数据需要将之前的数据清空
            $("#todo-list").empty();
            addFooter(obj.length, url);//初始化底部布局
            //初始化数据
            if (obj.length != 0) {
                $.each(obj, function (index, content) {
                    add(content.title, content.id, content.completed);
                });
                //设置全选按钮的状态
                reset();//重置全选按钮的状态
                /*if (obj.length == $(".toggle:checkbox:checked").length) {
                 $("#toggle-all").prop('checked', 'true');
                 }*/
                if ($("#todo-list li.completed").length != 0) {
                    var button = "<button id='clear-completed' >清空</button>";
                    $("#footer").append(button);
                }

            }
        },
        error: function () {
            alert("网络异常,请重试!");
        }
    });
}

$(document).ready(function () {
    //初始化数据
    init("/todos/all");

    //增加
    $('#new-todo').bind('keydown', function (event) {
        if (event.keyCode == "13") {
            //添加元素
            //增加
            var value = $(this).val();//获取输入框中的数据
            if (value == "") {
                alert("内容不能为空!");
            } else {
                var addaction = $(this);
                //ajax请求添加
                $.ajax({
                    url: "/todos",
                    type: "post",
                    dataType: 'json',
                    contentType: 'application/json; charset=UTF-8',
                    data: JSON.stringify({title: value}),
                    success: function (obj) {
                        add(addaction.val(), obj, 1);
                        addaction.val("");
                    },
                    error: function () {
                        alert("添加失败,请重试!");
                        addaction.val("");
                    }
                });
            }
        }
    });

    //删除
    //监听删除按钮点击事件
    $("body").on('click', '.destroy', function () {
        //ajax请求删除数据
        //获取id
        //获取对应的li下标
        var index = $(this).parent().parent().index();
        var itemId = $("#todo-list li:eq(" + index + ")").attr("data-id");
        $.ajax({
            url: "/todos",
            type: "delete",
            contentType: 'application/json; charset=UTF-8',
            dataType: 'json',
            data: JSON.stringify({
                id: parseInt(itemId)
            }),
            success: function (obj) {
                //var index = $(this).parent().parent().index();//对应li的id
                //alert(index);
                $("#todo-list li:eq(" + index + ")").remove()//表示删除第一个li。
                var count = $("#todo-list li").length;
                $("#todo-count strong").text(count);
                if (count == 0) {
                    //隐藏两个布局
                    $("#main").css('display', 'none');
                    $("#footer").css('display', 'none');
                }
            },
            error: function () {
                alert("网络异常,请重试!");
            }
        });
    });

    //监听checkbox的点击事件
    //全选按钮
    //修改
    $("body").on('click', "[type='checkbox']", function () {
        if ($(this).attr('id') == 'toggle-all') {
            var isChecked = $("#toggle-all").prop('checked');
            //ajax修改全部
            $.ajax({
                url: "/todos/all",
                type: "put",
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify({
                    completed: isChecked ? 0 : 1
                }),
                success: function (obj) {
                    //全选或取消全选
                    $(".toggle").prop('checked', isChecked);
                    if (isChecked) {
                        //修改左下角内容
                        //更新数量
                        editCount(isChecked ? 0 : $("#todo-list li").length);
                        //显示右下角清除按钮
                        $("#clear-completed").remove();
                        var button = "<button id='clear-completed' >清空</button>";
                        $("#footer").append(button);
                        //修改li的class
                        $("#todo-list li").addClass("completed");
                    } else {
                        //更新数量
                        editCount($("#todo-list li").length);
                        //移除按钮
                        $("#clear-completed").remove();
                        //修改li的class
                        $("#todo-list li").removeClass("completed");
                    }
                },
                error: function () {
                    alert("修改失败,请重试!");
                }
            });

        } else {
            var checkbox = $(this);
            var isChecked = checkbox.prop('checked');
            var index = $(this).parent().parent().index();
            var itemId = $("#todo-list li:eq(" + index + ")").attr("data-id");//id
            //ajax修改
            $.ajax({
                url: "/todos",
                type: "put",
                dataType: "json",
                contentType: 'application/json; charset=UTF-8',
                data: JSON.stringify({
                    id: parseInt(itemId),
                    completed: isChecked ? 0 : 1
                }),
                success: function (obj) {
                    //设置选中的li的class
                    //获取该点击的checkbox的状态
                    var isChecked = checkbox.prop('checked');
                    var li = checkbox.parent().parent();
                    if (isChecked) {
                        //选中
                        //获取父结点li
                        li.addClass("completed");
                    } else {
                        //取消
                        li.removeClass("completed");
                    }
                    //选中的个数
                    var len = $(".toggle:checkbox:checked").length;
                    //ul中li的总个数
                    var all = $("#todo-list li").length;

                    //如果选中的个数和总个数一致，则让全选按钮处于点击状态
                    //获取全选按钮的点击状态
                    var status = $("#toggle-all").prop('checked');
                    if (len == all) {
                        $("#toggle-all").prop('checked', status ? status : !status);
                    } else {
                        $("#toggle-all").prop('checked', status ? !status : status);
                    }

                    //修改左下角显示的数量
                    editCount(all - len);
                    if (len == 0) {
                        //移除右下角按钮
                        $("#clear-completed").remove();
                    } else {
                        //显示右下角清除按钮
                        $("#clear-completed").remove();
                        var button = "<button id='clear-completed' >清空</button>";
                        $("#footer").append(button);
                    }

                    //如果请求来自于activie或者completed，则必须进行移出操作
                    //即修改之后不应该在该单元显示
                    var url = $("a.selected").attr('href');//获取当前a标签的url地址
                    if (url == '/todos/active' || url == '/todos/completed') {
                        $("#todo-list li:eq(" + index + ")").remove();
                        reset();//重置全选按钮和左下角
                    }
                },
                error: function () {
                    alert("修改失败,请重试!");
                }
            });

        }
    });

    //监听清空按钮
    //删除
    $("body").on('click', "#clear-completed", function () {
        //获取选中的按钮的id
        var json = [];
        $("li.completed").each(function (index) {
            json.push({
                id: parseInt($(this).attr("data-id"))
            });
        });
        //ajax请求
        $.ajax({
            url: "/todos/all",
            type: "delete",
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: JSON.stringify(json),
            success: function (obj) {
                $("li.completed").each(function (index) {
                    $(this).remove();
                });
                //隐藏两个布局
                if ($("#todo-list li").length == 0) {
                    $("#main").css('display', 'none');
                    $("#footer").css('display', 'none');
                }
                $("#clear-completed").remove();//删除右下角清空按钮
                reset();//重置全选按钮的状态
            },
            error: function () {
                alert("清空失败,请重试!");
            }
        })
        ;
    });

    //监听a标签的点击
    //筛选
    $("body").on('click', 'a', function () {
        //设置选中状态
        $("a").removeClass('selected');
        $(this).addClass('selected');
        //ajax请求
        //获取a的href属性
        var url = $(this).attr('href');
        init(url);
        return false;
    });
});