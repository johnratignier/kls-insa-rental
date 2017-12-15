<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!DOCTYPE html>
    <html>

        <head>

            <meta charset="UTF-8">

            <title>KLS - Connexion</title>

            <style>
            	            	
                @import url(http://fonts.googleapis.com/css?family=Exo:100,200,400);
                @import url(http://fonts.googleapis.com/css?family=Source+Sans+Pro:700,400,300);

                body{
                    margin: 0;
                    padding: 0;
                    background: #fff;

                    color: #fff;
                    font-family: Arial;
                    font-size: 12px;
                }

                .body{
                    position: absolute;
                    top: -20px;
                    left: -20px;
                    right: -40px;
                    bottom: -40px;
                    width: auto;
                    height: auto;
                    background-image: url(/kls/img/img_home_background_tommorowland_night.jpg);
                    background-size: cover;
                    -webkit-filter: blur(5px);
                    z-index: 0;
                }

                .grad{
                    position: absolute;
                    top: -20px;
                    left: -20px;
                    right: -40px;
                    bottom: -40px;
                    width: auto;
                    height: auto;
                    background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(0,0,0,0)), color-stop(100%,rgba(0,0,0,0.65))); /* Chrome,Safari4+ */
                    z-index: 1;
                    opacity: 0.7;
                }

                .header{
                    position: absolute;
                    top: calc(50% - 35px);
                    left: calc(50% - 255px);
                    z-index: 2;
                }

                .header div{
                    float: left;
                    color: #fff;
                    font-family: 'Exo', sans-serif;
                    font-size: 35px;
                    font-weight: 200;
                }

                .header div span{
                    color: #5379fa !important;
                }

                .login{
                    position: absolute;
                    top: calc(50% - 75px);
                    left: calc(50% - 50px);
                    height: 150px;
                    width: 350px;
                    padding: 10px;
                    z-index: 2;
                }

            </style>

            <script src="/kls/bootstrap/js/prefixfree.min.js"></script>
            

        </head>

        <body style="overflow:hidden;">
            <%@ include file="/WEB-INF/header.jsp" %>

                <div class="body"></div>
                <div class="grad"></div>
                <div class="header">
                    <div>K-<span>Le-Son</span></div>
                </div>
                <br>
                <div class="login">
                    <form action="/kls/login" method="post">
                        <input class="form-control" type="text" placeholder="adresse mail" name="mail"><br>
                        <input class="form-control" type="password" placeholder="mot de passe" name="password"><br>
                        <input style="cursor:pointer;" class="btn btn-primary col-md-7" type="submit" value="Connexion">
                        <a href="/kls/signin"><div style="cursor:pointer;" class="btn btn-secondary mr-auto">Pas de compte</div></a>
                    </form>
                </div>

                <script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>

                </body>

            </html>