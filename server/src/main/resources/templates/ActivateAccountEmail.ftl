<html>
<body>
<div>
    <h3>Hello ${name}！</h3><br/>
    <br/>Your account has been successfully created, please click here to activate the account：<br/>
    <br/><a href="${host}/register/activate?key=${token}">Click here to activate</a><br/>
    <br/>Or alternatively copy the following link and enter into your browser<br/>
    ${host}/register/active?key=${token}
    <br>Link will be active for 1 hour </br>
</div>
</body>
</html>