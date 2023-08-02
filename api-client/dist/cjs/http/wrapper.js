"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.wrap = void 0;
var wrap = function (http) {
    return {
        // captcha: I'm not sure the typescript type of response
        //  to use: 
        //  const captchaImage = await captcha;
        //   const imageUrl = URL.createObjectURL(captchaImage);
        //   const captchaElement = document.createElement('img');
        //   captchaElement.src = imageUrl;
        captcha: function () { return http.request("GET", "/captcha"); },
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
        projectBoard: function (projectId, accessToken) { return http.request("GET", "/projects/board/".concat(projectId), {}, {}, {
            Authorization: "Bearer ".concat(accessToken)
        }); },
        favouriteBoards: function (accessToken) { return http.request("GET", "/projects/favourites", {}, {}, {
            Authorization: "Bearer ".concat(accessToken)
        }); },
        allProjects: function (accessToken) { return http.request("GET", "/projects/all", {}, {}, {
            Authorization: "Bearer ".concat(accessToken)
        }); },
        addFavouriteBoard: function (board_type, board_id) { return http.request("POST", "/projects/favourites", {
            board_id: board_id,
            board_type: board_type
        }); },
        testUser: function (username) {
            return http.request("GET", "/dev/test-info?username=".concat(username));
        },
    };
};
exports.wrap = wrap;
