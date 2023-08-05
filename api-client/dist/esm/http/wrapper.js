export var wrap = function (http) {
    return {
        captcha: function () { return http.request("GET", "/captcha"); },
        login: function (email, password, captcha) { return http.request("POST", "/login", {
            email: email,
            password: password,
            captcha: captcha
        }); },
        // set return types
        // register
        register: function (email, password, captcha) { return http.request("POST", "/register", {
            email: email,
            password: password,
            captcha: captcha
        }); },
        projectBoard: function (projBoardId, accessToken) { return http.request("GET", "/projects/board/".concat(projBoardId), null, {}, {
            Authorization: "Bearer ".concat(accessToken)
        }); },
        favouriteBoards: function (accessToken) { return http.request("GET", "/projects/favourites", null, {}, {
            Authorization: "Bearer ".concat(accessToken)
        }); },
        allProjects: function (accessToken) { return http.request("GET", "/projects/all", null, {}, {
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
