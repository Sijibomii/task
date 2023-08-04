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
type tags = {
    id: string;
    label: string;
};
type categories = {
    id: string;
    label: string;
};
type assignees = {
    id: string;
    avartar: string;
    name: string;
};
type tasks = {
    category_id: string;
    id: string;
    description: string;
    heading: string;
    comment: Number;
    no_of_assignee: Number;
    tags: tags[];
    assignees: assignees[];
};
export interface ProjectBoardResponse extends Response {
    data?: {
        board_id: string;
        tasks: tasks[];
        categories: categories[];
    };
}
type fBoard = {
    label: string;
    id: string;
    board_id: string;
};
export interface BoardsResponse extends Response {
    data?: fBoard[];
}
export declare const wrap: (http: Http) => {
    captcha: () => Promise<Blob>;
    login: (email: String, password: String, captcha: String) => Promise<LoginResponse>;
    register: (email: String, password: String, captcha: String) => Promise<Response>;
    projectBoard: (projBoardId: string, accessToken: string) => Promise<ProjectBoardResponse>;
    favouriteBoards: (accessToken: string) => Promise<BoardsResponse>;
    allProjects: (accessToken: string) => Promise<BoardsResponse>;
    addFavouriteBoard: (board_type: string, board_id: string) => Promise<Response>;
    testUser: (username: string) => Promise<{
        accessToken: string;
        refreshToken: string;
    }>;
};
export {};
