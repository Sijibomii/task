export var wrap = function (http) {
    return {
        // captcha: I'm not sure the typescript type of response
        //  to use: 
        //  const captchaImage = await captcha;
        //   const imageUrl = URL.createObjectURL(captchaImage);
        //   const captchaElement = document.createElement('img');
        //   captchaElement.src = imageUrl;
        captcha: function () { return http.request("GET", "/captcha", {}); },
        login: function (email, password, captcha) { return http.request("POST", "/login", {
            email: email,
            password: password,
            captcha: captcha
        }); },
        loginVerify: function (code) { return http.request("POST", "/login/verify", {
            code: code
        }); },
        // register
        register: function (email, password, captcha) { return http.request("POST", "/register", {
            email: email,
            password: password,
            captcha: captcha
        }); },
        testUser: function (username) {
            return http.request("GET", "/dev/test-info?username=".concat(username));
        },
    };
};
