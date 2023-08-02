import { Http } from "./raw";
export interface Response {
    code?: Number;
    message?: String;
}
export interface LoginResponse extends Response {
    data?: {
        access: String;
        refresh: String;
    };
}
export declare const wrap: (http: Http) => {
    captcha: () => Promise<Blob>;
    login: (email: String, password: String, captcha: String) => Promise<Response>;
    loginVerify: (code: String) => Promise<LoginResponse>;
    register: (email: String, password: String, captcha: String) => Promise<Response>;
    projectBoard: (projectId: string, accessToken: string) => Promise<Response>;
    favouriteBoards: (accessToken: string) => Promise<Response>;
    allProjects: (accessToken: string) => Promise<Response>;
    addFavouriteBoard: (board_type: string, board_id: string) => Promise<unknown>;
    testUser: (username: string) => Promise<{
        accessToken: string;
        refreshToken: string;
    }>;
};
