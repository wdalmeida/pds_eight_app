<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>WebSite</title>

    <meta charset="utf-8">
    <meta name="description" content="Sublime Stunning free HTML5/CSS3 website template"/>
    <link rel="stylesheet" type="text/css" href="sublime/css/reset.css">
    <link rel="stylesheet" type="text/css" href="sublime/css/fancybox-thumbs.css">
    <link rel="stylesheet" type="text/css" href="sublime/css/fancybox-buttons.css">
    <link rel="stylesheet" type="text/css" href="sublime/css/fancybox.css">
    <link rel="stylesheet" type="text/css"href="sublime/css/animate.css">
    <link rel="stylesheet" type="text/css" href="sublime/css/main.css">

    <script type="text/javascript" src="sublime/js/jquery.js"></script>
    <script type="text/javascript" src="sublime/js/fancybox.js"></script>
    <script type="text/javascript" src="sublime/js/fancybox-buttons.js"></script>
    <script type="text/javascript" src="sublime/js/fancybox-media.js"></script>
    <script type="text/javascript" src="sublime/js/fancybox-thumbs.js"></script>
    <script type="text/javascript" src="sublime/js/wow.js"></script>
    <script type="text/javascript" src="sublime/js/main.js"></script>
</head>
<body>

<section class="billboard light">
    <header class="wrapper light">

        <nav>
            <ul>
                <li><a href="">Connection</a></li>
                <li><a href="">Produits</a></li>
                <li><a href="">Journal</a></li>
                <li><a href="">Contact</a></li>
            </ul>
        </nav>
    </header>

    <div class="caption light animated wow fadeInDown clearfix">
        <h1>EIGHT</h1>
        <p>Banque mutualiste</p>
        <hr>
    </div>
    <div class="shadow"></div>
</section><!--  End billboard  -->

<form method="post" action="inscription" style="margin-left: 300px">

    <fieldset style="padding: 10px; border: 1px #0568CD solid; width: 400px;">

        <legend style=" font-weight: bold;color: #0568CD;">Connexion</legend>

<br/>
        <label for="email">Identifiant<span style=" color: #c00;">*</span></label>
        <input style="margin: 3px 3px 0px 0px; border: 1px #999 solid;" type="text" id="email" name="email" value="" size="20" maxlength="60" />
        <br />
        <br/>
        <label style="float: left; margin: 3px 0px 0px 0px;" for="motdepasse">Mot de passe <span style=" color: #c00;">*</span></label>
        <input style="margin: 3px 3px 0px 0px;border: 1px #999 solid;" type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
        <br />
        <br/>
        <input style="margin-left:200px" type="submit" value="Connexion" />
        <br />
    </fieldset>
</form>
<footer>
    <img style="width:100px; height:90px" src="sublime/img/Capture3.PNG" alt="" class="footer_logo" />
    <div class="wrapper">
        <div class="rights">
        </div>
        <nav>
            <ul>
                <li><a href="#">Connection</a></li>
            </ul>
        </nav>
    </div>
</footer><!--  End footer  -->
<script src='../ga.js'></script>
</body>
</html>