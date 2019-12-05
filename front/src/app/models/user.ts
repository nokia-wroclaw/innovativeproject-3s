export interface User {
    id: number;
    token: string;
    email: string;
    username: string;
    password: string;
    created: Date;
    type: string;
}
