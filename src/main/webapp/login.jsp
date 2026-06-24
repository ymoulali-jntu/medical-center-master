<%-- 
    Document   : login
    Created on : Apr 15, 2011, 11:02:17 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@include file="include/header.jsp" %>

<%@include file="include/alwaysinclude.jsp" %>

    <div class="login-container">
        <div class="login-box">
            <h2>Login</h2>
            <form method="post" action="http://localhost:8090/MedicalCentre/LoginVerify" onsubmit="return isEmpty();" id="loginForm">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" name="username" id="username" placeholder="Enter your username" required autocomplete="username" />
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password" placeholder="Enter your password" required autocomplete="current-password" />
                </div>
                <div class="form-actions">
                    <input type="submit" name="command" id="command" value="Log In" class="btn-login" />
                </div>
                <div id="login-error" class="error-message"></div>
            </form>
        </div>
    </div>
<%@include file="include/footer.jsp" %>

<script type="text/javascript">
    function isEmpty(){
        var user = $('#username').val().trim();
        var pass = $('#password').val().trim();
        var errorDiv = $('#login-error');

        if(user === "" || pass === ""){
            errorDiv.text('Please enter both username and password').show();
            return false;
        } else {
            errorDiv.hide();
            return true;      
        }
    }

    $(document).ready(function(){
        $('#login-error').hide();
    });
</script>