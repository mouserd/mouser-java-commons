<%--
  ~ This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All
  ~ rights reserved.
  ~
  ~ Should you wish to use or enquire about any of the content contained please
  ~ contact David Mouser (david.mouser@gmail.com).
  --%>

<!--
~ This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd. All
~ rights reserved.
~
~ Should you wish to use or enquire about any of the content contained please
~ contact David Mouser (david.mouser@gmail.com).
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Users</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="../assets/css/bootstrap.css" rel="stylesheet">
    <style>
        body {
            padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
        }
    </style>
    <link href="../assets/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="114x114"
          href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72"
          href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed"
          href="../assets/ico/apple-touch-icon-57-precomposed.png">

    <title>Users</title>
    <script type="text/javascript"
            src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
    <script type="text/javascript">
        $.getJSON('/api/user/2.json', function (data) {
            var items = [];

            $.each(data, function (key, val) {
                items.push('<li id="' + key + '">' + key + " = " + val + '</li>');
            });

            $('<ul/>', {
                'class':'my-new-list',
                html:items.join('')
            }).appendTo('#response');
        });
    </script>
</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand" href="#">
                Pixelus Consulting
            </a>

            <ul class="nav">
                <li class="active">
                    <a href="#">Home</a>
                </li>
                <li><a href="#">Link</a></li>
                <li class="divider-vertical"></li>
                <li><a href="#"><span class="badge badge-info">1</span> Update</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <span class="span8 offset2">
            <div class="alert alert-info">
                <a class="close" data-dismiss="alert">×</a>
                <h4 class="alert-heading">You've been here before!</h4>
                Interested in receiving our newsletter? Sign up now!
            </div>
        </span>
    </div>
</div>
<div class="container-fluid">
    <div class="row-fluid">
        <span class="span2">
            <p></p>
        </span>
        <span class="span8">

            <div class="hero-unit">
                <h2>Custom Web Development</h2>

                <p>Tagline</p>

                <p>
                    <a class="btn btn-primary btn-large">
                        Read more...
                    </a>
                </p>
            </div>
        </span>
        <span class="span2">
            <p></p>
        </span>
    </div>

    <div class="row-fluid">
        <div id="response" class="span4"><span class="label">02/04/2012</span></div>
        <div class="span8" style="background-color: grey;"></div>
    </div>
</div>
</body>
</html>