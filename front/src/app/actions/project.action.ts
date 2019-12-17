import { Project } from '../models/project';

export class AddProject {
    static readonly type = '[Project] Add';

    constructor(public payload: Project) {

    }
}

export class GetProject {
    static readonly type = '[Project] Get';
}

export class DeleteProject {
    static readonly type = '[Project] Delete';

    constructor(public id: number) {

    }
}

export class UpdateProject {
    static readonly type = '[Project] Update';

    constructor(public payload: Project, public id: number) {

    }
}
