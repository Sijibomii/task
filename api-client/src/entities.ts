
export type UUID = string;

// organization
export type Orgaanization ={
    id: string;
    members_count: number;
};

// projects
export type Project ={
    id: string;
    members_count: number;
};

// teams
export type Team ={
    id: string;
    members_count: number;
};

export type User = {
    username: string;
    online: boolean;
    orgs: Orgaanization[];
    teams: Team[];
    projects: Project[];
  };
