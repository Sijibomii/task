import { Http } from "./raw";


export interface Response{
  code?: Number,
  message?: String
}

export interface LoginResponse extends Response {
    data?: {
        accessToken: String,
        refreshToken: String
    }
}

type tags = {
  id: string
  label: string
}

type categories = {
  id: string
  label: string
}

type assignees = {
  id: string
  avartar: string
  name: string
}

type tasks = {
  category_id: string
  id: string
  description: string
  heading: string
  comment: Number
  no_of_assignee: Number
  tags: tags[]
  assignees: assignees[]
}

export interface ProjectBoardResponse extends Response {
  data?: {
    board_id: string,
    tasks: tasks[],
    categories: categories[]
  }
}

type fBoard = {
  label: string
  id: string
  board_id: string
}

export interface BoardsResponse extends Response {
  data?: fBoard[]
}


export const wrap = (http: Http) => {
  return {
    captcha: () => http.request("GET", "/captcha") as Promise<Blob>,

    login: (email: String, password: String, captcha: String) => http.request("POST", "/login", { 
        email, password, captcha
    }) as Promise<LoginResponse>,

    // set return types
    // register
    register: (email: String, password: String, captcha: String) => http.request("POST", "/register", { 
        email, password, captcha
    }) as Promise<Response>,

    projectBoard: (projBoardId: string, accessToken: string) => http.request("GET", `/projects/board/${projBoardId}`, null, {}, {
      Authorization: `Bearer ${accessToken}` 
    }) as Promise<ProjectBoardResponse>, 

    favouriteBoards: (accessToken: string) => http.request("GET", "/projects/favourites", null, {}, {
      Authorization: `Bearer ${accessToken}` 
    }) as Promise<BoardsResponse>, 
 
    allProjects: (accessToken: string) => http.request("GET", "/projects/all", null, {}, {
      Authorization: `Bearer ${accessToken}` 
    }) as Promise<BoardsResponse>, 

    addFavouriteBoard: (board_type: string, board_id: string) => http.request("POST", "/projects/favourites", {
      board_id, board_type
    }) as Promise<Response>,

    testUser: (username: string) =>
      http.request("GET", `/dev/test-info?username=${username}`) as Promise<{
        accessToken: string;
        refreshToken: string;
      }>,
  };
};